package org.nn.component;

import java.util.*;

/**
 * 神经层
 * @author Weibing Long
 *
 */
public class Layer {
	
	private Map<Integer, Neural> neurals;
	
	private int size = 0;
	
	public Layer(Map<Integer, Neural> map) {
		if (null == map) throw new NullPointerException("");
		if (map.size() < 1) throw new IllegalArgumentException("");
		else size = map.size();
		
		neurals = map;
	}

	public Layer(int n) {
		if (n < 1) throw new IllegalArgumentException("");
		neurals = new HashMap<Integer, Neural>(n);
		for (int i = 0; i < n; i++) {
			neurals.put(i, new Neural(0));
		}		
		this.size = n;
	}
	
	public Layer() {
		neurals = new HashMap<Integer, Neural>();
		size = 0;
	}

	public Map<Integer, Neural> getNeurals() {
		return neurals;
	}
	
	public void add(Neural neural) {
		neurals.put(size, neural);
		size += 1;
	}

	/**
	 * 
	 * @return 神经元个数
	 */
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		String layer = "[";

		for (int i = 0; i < neurals.size(); i++) {
			if (null == neurals.get(i))
				throw new NullPointerException("");
			if (i == neurals.size()-1)
				layer = layer + neurals.get(i);
			else
				layer = layer + neurals.get(i) + ", ";
		}
		
		layer = layer + "]";
		return layer;
		
	}
	
}
