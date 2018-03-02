package org.neuralNetwork.algorithm;

import java.util.*;
import org.neuralNetwork.component.*;

/**
 * 神经网络相关算法
 * 
 * @author Weibing Long<br>
 * @since 2018.02.02
 * 
 */
public class neuralNetworks {
	
	/**
	 * 前馈算法<hr>
	 * 默认激活函数tanh，若自定义激活函数则自行修改本函数
	 * @param layers 所有神经层
	 * @param weights 所有权重值
	 * @param bis 所有偏置值
	 * @return
	 */
	public static Map<Integer, List<List<Neural>>> forward(Layer[] layers, Map<Integer, double[][]> weights, List<Double> bis) {
		
		Map<Integer, List<List<Neural>>> result = new HashMap<Integer, List<List<Neural>>>();
		List<List<Neural>> inputs = new ArrayList<List<Neural>>();
		List<List<Neural>> outputs = new ArrayList<List<Neural>>();
		result.put(0, inputs);
		result.put(1, outputs);
		for (int i = 0; i < layers.length-1; i++) {
			List<Neural> inputNeurals = new ArrayList<Neural>();
			List<Neural> outputNeurals = new ArrayList<Neural>();
			inputs.add(inputNeurals);
			outputs.add(outputNeurals);
			for (int j = 0; j < layers[i+1].size(); j++) {
				inputNeurals.add(new Neural(0));
				outputNeurals.add(new Neural(0));
			}
		}
		
		for (int i = 0; i < layers.length; i++) {
			if (i == 0) continue;
			for (int j = 0; j < layers[i].size(); j++) {

				double sum = 0;
			    for (int z = 0; z < layers[i-1].size(); z++) {
			    	sum += layers[i-1].getNeurals().get(z).getValue() * weights.get(i-1)[z][j];
			    }
			    sum += bis.get(i-1);
			    inputs.get(i-1).get(j).setValue(sum);
			    

			    sum = ActivationFunction.tanh(sum);
			    layers[i].getNeurals().get(j).setValue(sum);
			    outputs.get(i-1).get(j).setValue(sum);
			}
		}
		
		return result;
	}
	
	/**
	 * 反向传播算法 <br>参考论文 <a href="http://download.csdn.net/download/github_37412255/10263517">《Multilabel Neural Networks with Applications to Functional Genomics and Text Categorization》</a><hr>
	 * @param layers 所有神经层
	 * @param weights 所有权重值
	 * @param bis 所有偏置值
	 * @return
	 */
	public static Map<Integer, Object> backward(Layer[] layers, List<Double> target, Map<Integer, double[][]> weights, List<Double> bis, String flag, String alphe) {
		
		// 多标签分类的反向传播算法
		if (flag.equals("multi-label")) {
			List<Integer> posExample = new ArrayList<Integer>();  // 正例标签索引集合
			List<Integer> couExample = new ArrayList<Integer>();  // 反例标签索引集合
			
			// 将正反例的标签索引添加到对应的集合中，正例添加到posExample，反例添加到couExample
			for (int i = 0; i < target.size(); i++) {
				// 该标签为正例
				if (target.get(i) == 1) {
					posExample.add(i);
				} else {  // 该标签为反例
					couExample.add(i);
				}
			}
			
			// 中间步骤
			// 计算dj
			List<Double> d = new ArrayList<Double>();
			for (int i = 0; i < target.size(); i++) {
				double x = layers[layers.length-1].getNeurals().get(i).getValue();
				double y = 0;
				// 该标签为正例
				if (target.get(i) == 1) {			
					for (int j = 0; j < couExample.size(); j++) {
						double z = layers[layers.length-1].getNeurals().get(couExample.get(j)).getValue();
						y += Math.exp(-(x - z));
					}
					y *= (1 / posExample.size() * couExample.size()) * ((1 + x) * (1 - x));
				} else {  // 该标签为反例
					for (int j = 0; j < posExample.size(); j++) {
						double z = layers[layers.length-1].getNeurals().get(posExample.get(j)).getValue();
						y += Math.exp(-(x - z));
					}
					y *= -(1 / posExample.size() * couExample.size()) * ((1 + x) * (1 - x));
				}
				d.add(y);
			}
			
			Map<Integer, List> map = new HashMap<Integer, List>();
			int m = 0;
			map.put(m++, d);
			// 计算es等
			for (int i = layers.length-2; i >= 0; i--) {
				double x = layers[layers.length-1].getNeurals().get(i).getValue();
				for (int j = 0; j < layers[i+1].size(); j++) {
					// d.get(j) * weights
				}
			}
			
			// 更新权值
			for (int i = weights.size()-1; i >= 0; i--) {
				double[][] x = weights.get(i);
				for (int j = 0; j < x.length; j++) {
					for (int k = 0; k < x[j].length; k++) {
						// x[j][k] == x[s][j]
						
					}
				}
			}
			
			// 更新偏置值
		}
		return null;
	}
	
}
