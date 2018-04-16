package org.nn.component;

import java.util.*;

/**
 * 神经网络
 * 
 * @author 龙卫兵
 * @since 2017.11.24
 * 
 */
public class NeuralNetwork {
	/**
	 * 输入层
	 */
	private Layer inputLayer;
	/**
	 * 隐藏层
	 */
	private final Map<Integer, Layer> hiddenLayers;
	/**
	 * 输出层
	 */
	private final Layer outputLayer;
	/**
	 * 输入层神经元个数
	 */
	private final int iSize;
	/**
	 * 输出层神经元个数
	 */
	private final int oSize;
	/**
	 * 隐藏层层数
	 */
	private final int hlSize;
	/**
	 * 神经元层数
	 */
	private final int size;
	
	/**
	 * 创建神经网络
	 * @param allLayer 神经网络拓扑，按顺序每个值分别为对应层的神经元个数
	 */
	public NeuralNetwork(int[] allLayer) {
		// 创建输入层
		this.inputLayer = new Layer(allLayer[0]);
		this.iSize = allLayer[0];
		// 创建隐藏层
		hiddenLayers = new HashMap<Integer, Layer>();
		for (int i = 1; i < allLayer.length - 1; i++) {
			this.hiddenLayers.put(i-1, new Layer(allLayer[i]));
		}
		this.hlSize = allLayer.length - 2;
		// 创建输出层
		this.outputLayer = new Layer(allLayer[allLayer.length - 1]);
		this.oSize = allLayer.length - 1;
		// 神经网络层数
		this.size = allLayer.length;
	}
	/**
	 * 获取输入层对象
	 * @return 输入层对象
	 */
	public Layer getInputLayer() {
		return inputLayer;
	}
	/**
	 * 获取隐藏层对象
	 * @return 隐藏层对象
	 */
	public Map<Integer, Layer> getHiddenLayer() {
		return hiddenLayers;
	}
	/**
	 * 获取输出层对象
	 * @return 输出层对象
	 */
	public Layer getOutputLayer() {
		return outputLayer;
	}
	/**
	 * 获取输入层神经元个数
	 * @return 输入层神经元个数
	 */
	public int getiSize() {
		return iSize;
	}
	/**
	 * 获取输出层神经元个数
	 * @return 输出层神经元个数
	 */
	public int getoSize() {
		return oSize;
	}
	/**
	 * 获取隐藏层层数
	 * @return 隐藏层层数
	 */
	public int getHlSize() {
		return hlSize;
	}
	/**
	 * 获取隐藏层
	 * @return 隐藏层
	 */
	public Map<Integer, Layer> getHiddenLayers() {
		return hiddenLayers;
	}
	/**
	 * 获取神经元层数
	 * @return 神经元层数
	 */
	public int getSize() {
		return size;
	}
	/**
	 * 设置输入层
	 * @param input 输入层各神经元输入值
	 */
	public void setInputLayer(double[] input) {
		Layer x = new Layer();
		for (int i = 0; i < input.length; i++) {
			x.add(new Neural(input[i]));
		}
		this.inputLayer = x;
	}
	
	/**
	 * 创建神经网络demo
	 * @param args 字符串数组
	 */
	public static void main(String[] args) {
		NeuralNetwork nn = new NeuralNetwork(new int[] {2, 3, 10, 7, 6, 4, 5});
		// 打印结果
		System.out.println("神经网络层数: " + nn.getSize());
		System.out.println("输入层结点：" + nn.getInputLayer());
		System.out.println("隐藏层层数：" + nn.getHiddenLayer().size());
		for (int i = 0; i < nn.getHiddenLayer().size(); i++) {
			System.out.println("隐藏层 " + i + "：" + nn.getHiddenLayer().get(i));
		}
		System.out.println("输出层： " + nn.getOutputLayer());	
	}
	
}
