package org.util;

import java.util.*;
import org.neuralNetwork.component.*;
import org.neuralNetwork.data.ArffData;
import org.neuralNetwork.data.Instance;

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
	public static void forward(Layer[] layers, Map<Integer, double[][]> weights, List<List<Double>> bis) {
		
		for (int i = 0; i < layers.length; i++) {
			if (i == 0) continue;
			for (int j = 0; j < layers[i].size(); j++) {

				double sum = 0;
			    for (int z = 0; z < layers[i-1].size(); z++) {
			    	sum += layers[i-1].getNeurals().get(z).getValue() * weights.get(i-1)[z][j];
			    }
			    if (i < layers.length-1)
			    	sum += bis.get(i+1).get(j);
			    sum = ActivationFunction.tanh(sum);
			    layers[i].getNeurals().get(j).setValue(sum);
			}
		}
		
	}
	
	/**
	 * 反向传播算法 <br>参考论文 <a href="http://download.csdn.net/download/github_37412255/10263517">《Multilabel Neural Networks with Applications to Functional Genomics and Text Categorization》</a><hr>
	 * @param layers 所有神经层
	 * @param weights 所有权重值
	 * @param bis 所有偏置值
	 * @return
	 */
	public static void backward(Layer[] layers, List<Double> target, Map<Integer, double[][]> weights, List<List<Double>> bis, String flag, double alphe) {
		
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
					y *= (1 / (double)posExample.size() * couExample.size()) * ((1 + x) * (1 - x));
				} else {  // 该标签为反例
					for (int j = 0; j < posExample.size(); j++) {
						double z = layers[layers.length-1].getNeurals().get(posExample.get(j)).getValue();
						y += Math.exp(-(x - z));
					}
					y *= -(1 / posExample.size() * couExample.size()) * ((1 + x) * (1 - x));
				}
				d.add(y);
			}
			
			Map<Integer, List<Double>> map = new HashMap<Integer, List<Double>>();
			int m = 0;
			map.put(m++, d);
			// 计算es等
			for (int i = layers.length-2; i >= 0; i--) {
				List<Double> temp = new ArrayList<Double>();
				for (int j = 0; j < layers[i].size(); j++) {
					double e = 0;
					double x = layers[i].getNeurals().get(j).getValue();
					for (int k = 0; k < layers[i+1].size(); k++) {
						// d.get(j) * weights;
						double a = d.get(k);
						double b = weights.get(i)[j][k];
						e += a * b;
					}
					e *= (1 + x) * (1 - x);
					temp.add(e);
				}
				map.put(m++, temp);
				
			}
			
			// 更新权值
			int n = 0;
			for (int i = weights.size()-1; i >= 0; i--) {
				double[][] x = weights.get(i);
				for (int j = 0; j < x.length; j++) {
					for (int k = 0; k < x[j].length; k++) {
						x[j][k] -= alphe * map.get(n).get(k) * layers[i].getNeurals().get(j).getValue();						
					}
				}
				n++;
			}
			
			// 更新偏置值 (有问题)
			// TODO 未完成
			int count = 0;
			for (int i = bis.size()-1; i >= 0; i--) {
				for (int j = 0; j < bis.get(i).size(); j++) {
					double a = bis.get(i).get(j);
					double b = map.get(count).get(j);
					a -= alphe * b;
					bis.get(i).set(j, a);
				}
				count++;
			}
		}
	}
	
	public static void main(String[] args) {
		
		ArffData arffData = MyFile.getArffData("sources/emotions.arff");
		int attCount = arffData.getAttCount();
		int labelCount = arffData.getLabelCount();
		Instance instance = arffData.getInstance();
		
		Layer inputlayer = new Layer();
		Layer outputlayer = new Layer();
		List<Double> target = new ArrayList<Double>();		
		Layer[] layers = new Layer[] {
				inputlayer, outputlayer
		};		
		Map<Integer, double[][]> weights = null;			
		List<List<Double>> bis = null;
			
		for (int i = 0; i < instance.getLineCount(); i++) {
			// 神经网络各层赋值，其实只要给输入层赋值就可以了，其他层是由输入层逐层确定的
			for (int j = 0; j < attCount; j++) {
				inputlayer.add(new Neural(instance.getAllLine().get(i).get(j)));
			}		
			for (int j = attCount; j < attCount + labelCount; j++) {
				outputlayer.add(new Neural(instance.getAllLine().get(i).get(j)));
				target.add(instance.getAllLine().get(i).get(j));
			}
			
			if (i == 0) {
				weights = new HashMap<Integer, double[][]>();
				bis = new ArrayList<List<Double>>();
				
				double[][] weight = new double[inputlayer.size()][outputlayer.size()];
				for (int k = 0; k < inputlayer.size(); k++) {
					for (int l = 0; l <outputlayer.size(); l++) {
						weight[k][l] = 1;
					}
				}
				weights.put(0, weight);
				
				for (int k = 0; k < layers.length-1; k++) {
					List<Double> bi = new ArrayList<Double>();
					for (int l = 0; l < layers[k].size(); l++) {
						bi.add(1.0);
					}
					bis.add(bi);		
				}
			}
			
			neuralNetworks.forward(layers, weights, bis);
			neuralNetworks.backward(layers, target, weights, bis, "multi-label", 0.5);
		}
		
		System.out.println("");
	}
	
}
