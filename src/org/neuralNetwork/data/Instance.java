package org.neuralNetwork.data;

import java.util.*;

public class Instance {
	
	private List<List<Double>> allLine;
	private int lineCount;

	public List<List<Double>> getAllLine() {
		return allLine;
	}

	public void setAllLine(List<List<Double>> allLine) {
		this.allLine = allLine;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}
	
	
	
}
