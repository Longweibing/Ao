package org.multithread;

import java.io.Serializable;

public class Commodity implements Serializable {

	private static final long serialVersionUID = -5370577074304887367L;
	private int nums = 0;
	
	public int getNums() {
		return nums;
	}
	
	public void setNums(int nums) {
		this.nums = nums;
	}

}
