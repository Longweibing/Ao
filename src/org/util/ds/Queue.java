package org.util.ds;

import java.util.Random;

/**
 * 队列
 * @author Weibing Long
 * @since 2018.04.16
 * @param <Item> 泛型
 */
public class Queue<Item> {
	private Node root;
	private int n;
	
	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		Random random = new Random();
		System.out.println("进队列");
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt();
			queue.enqueue(x);
			System.out.println(x);
		}
		System.out.println("出队列");
		while (queue.size() > 0)
			System.out.println(queue.dequeue());
	}
	
	private class Node {
		private Item item;
		private Node next;
	}
	
    public void enqueue(Item item) {
    	if (item == null)
    		throw new NullPointerException("元素不能为空！");
    	if (root == null) {
    		root = new Node();
    		root.item = item;
    		root.next = root;
    	} else {
    		Node temp = root.next;
    		root.next = new Node();
    		root.next.item = item;
    		root = root.next;
    		root.next = temp;
    	}
    	n++;
    }
    
    public Item dequeue() {
    	if (n == 0)
    		throw new NullPointerException("队列中元素为空！");
    	Item item = root.next.item;
    	root = root.next;
    	n--;
    	return item;
    }
    
    public int size() {
    	return n;
    }
}