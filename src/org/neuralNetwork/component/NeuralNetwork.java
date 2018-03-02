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
	
	
	
}
