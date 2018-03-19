package org.neuralNetwork.component;

import java.util.*;

/**
 * 神经网络
 * 
 * @author Weibing Long</br>
 * @since 2017.11.24
 * 
 */
public class NeuralNetwork {

	private final Layer inputLayer;
	
	private final Map<Integer, Layer> hiddenLayers;
	
	private final Layer outputLayer;
	
	private final int iSize;
	
	private final int oSize;
	
	private final int hlSize;
	
	private final int size;
	
	public NeuralNetwork(Layer inputLayer, Map<Integer, Layer> hiddenLayers, Layer outputLayer) {
		if (null == inputLayer)
			throw new NullPointerException("");
		if (null == hiddenLayers)
			throw new NullPointerException("");
		if (null == outputLayer)
			throw new NullPointerException("");
		
		this.inputLayer = inputLayer;
		this.hiddenLayers = hiddenLayers;
		this.outputLayer = outputLayer;
		this.iSize = inputLayer.size();
		this.hlSize = hiddenLayers.size();
		this.oSize = outputLayer.size();
		this.size = hiddenLayers.size() + 2;
	}

	
	public Layer getInputLayer() {
		return inputLayer;
	}

	public Map<Integer, Layer> getHiddenLayer() {
		return hiddenLayers;
	}

	public Layer getOutputLayer() {
		return outputLayer;
	}


	public int getiSize() {
		return iSize;
	}


	public int getoSize() {
		return oSize;
	}


	public int getHlSize() {
		return hlSize;
	}


	public Map<Integer, Layer> getHiddenLayers() {
		return hiddenLayers;
	}


	public int getSize() {
		return size;
	}
	
	public static void main(String[] args) {
		// 构建神经网络
		//	    1. 构建神经元
		Neural neural1 = new Neural(0.25);
		Neural neural2 = new Neural(-0.75);
		Neural neural3 = new Neural(-0.45);
		//	    2. 构建神经层
		Layer inputLayer = new Layer();
		inputLayer.add(neural1);
		inputLayer.add(neural2);
		inputLayer.add(neural3);
		Layer hiddenLayer = new Layer(256);
		Layer hiddenLayer1 = new Layer(256);
		Layer outputLayer = new Layer(1);
		Map<Integer, Layer> hiddenLayers = new HashMap<Integer, Layer>();
		hiddenLayers.put(0, hiddenLayer);
		hiddenLayers.put(1, hiddenLayer1);
		NeuralNetwork neuralNetwork = new NeuralNetwork(inputLayer, hiddenLayers, outputLayer);
		// 打印结果
		System.out.println("神经网络层数: " + neuralNetwork.getSize());
		System.out.println("输入层结点：" + neuralNetwork.getInputLayer());
		System.out.println("隐藏层层数：" + neuralNetwork.getHiddenLayer().size());
		for (int i = 0; i < neuralNetwork.getHiddenLayer().size(); i++) {
			System.out.println("隐藏层 " + i + "：" + neuralNetwork.getHiddenLayer().get(i));
		}
		System.out.println("输出层： " + neuralNetwork.getOutputLayer());	
	}
	
}
