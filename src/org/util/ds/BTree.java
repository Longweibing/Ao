package org.util.ds;

/**
 * 二叉树
 * @author 龙卫兵
 *
 */
public class BTree {
	@SuppressWarnings("unused")
	private int[] bTree;
	private int capacity;
	private int n;
	
	public BTree() {
		capacity = 2;
		bTree = new int[capacity];
	}
	
	public BTree(int capacity) {
		this.capacity = capacity;
		bTree = new int[capacity];
	}
	
	public void insert(int value) {

	}
	
	public int delete(int value) {
		if (isEmpty()) throw new NullPointerException("元素为空");
		return value;
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public String toString() {
		return null;
	}
	
	public static void main(String[] args) {
		
	}
	
}