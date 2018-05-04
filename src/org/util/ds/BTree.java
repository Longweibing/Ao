package org.util.ds;

import java.util.Scanner;

/**
 * 二叉树
 * @author Weibing Long
 * @since 2018.04.19
 */
public class BTree<Value extends Comparable<Value>> {
    private Node root;
    private int n;
    
    public static void main(String[] args) {   
    }
    
   
    
	
	public BTree() {

	}
	
	public BTree(int capacity) {

	}
	
	public void insert(Value value) {
	    if (value == null) {
	        throw new NullPointerException("Value must not be null !");
	    }
	    if (root == null) {
	        root = new Node();
	        root.value = value;
	    } else {
	       Node tempRoot = root;
	       if (tempRoot.left != null) {
	           if (value.compareTo(tempRoot.left.value) > 0) {
	               
	           } else if (value.compareTo(tempRoot.left.value) < 0) {
	               
	           } else
	               return;
	       } else if (tempRoot.right != null) {
	           
	       } else {
	           
	       }
	    }
	}
	
	public boolean delete(int value) {
		return false;
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	private class Node {
	    Value value;
	    private Node left;
	    private Node right;
	}
	
}