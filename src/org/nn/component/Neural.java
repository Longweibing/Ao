package org.nn.component;

/**
 * 神经层
 * 
 * @author Weibing Long<br>
 * @since 2017.11.23
 * 
 */
public class Neural {
	private double value;
	
	public Neural(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {		
		return new String(Double.toString(value));		
	}
	
}
