package org.neuralNetwork.data;

public class ArffData {
	private int labelCount;
	private int attCount;
	private Instance instance;
	
	public int getLabelCount() {
		return labelCount;
	}
	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}
	public int getAttCount() {
		return attCount;
	}
	public void setAttCount(int attCount) {
		this.attCount = attCount;
	}
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	
	
}
