package org.neuralNetwork.data;

import java.util.*;

public class Instance {
	
	private Map<Integer, Map<Integer, Double>> allLine;
	private int lineCount;

	public Map<Integer, Map<Integer, Double>> getAllLine() {
		return allLine;
	}

	public void setAllLine(Map<Integer, Map<Integer, Double>> allLine) {
		this.allLine = allLine;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}
	
	
	
}
