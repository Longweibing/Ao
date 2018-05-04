package org.util.ds;

import java.util.List;
import java.util.Random;

/**
 * 栈
 * @author Weibing Long
 * @since 2018.04.16
 * @param <Item> 泛型
 */
public class Stack<Item> {
	private Node root;  // 根节点
	private int n;  // 栈元素个数
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		Random random = new Random();
		System.out.println("进栈");
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt();
			stack.push(x);
			System.out.println(x);
		}
		System.out.println("出栈");
		while (stack.size() > 0)
			System.out.println(stack.pop());
	}
	
	/**
	 * 进栈
	 * @param item 进栈元素
	 */
	public void push(Item item) {
		if (item == null)
			throw new NullPointerException("参数不能为空");
		if (root == null) {
			root = new Node();
			root.item = item;
			root.next = root;
		} else {
			Node temp = root;
			root = new Node();
			root.item = item;
			root.next = temp;
		}
		n++;
	}
	
	/**
     * push all elements in List.
     * @param items elements
     */
    public void push(List<Item> items) {
        for (Item item : items) {
            this.push(item);
        }
    }
	
	/**
	 * 出栈
	 * @return 出栈元素
	 */
	public Item pop() {
		if (n == 0)
			throw new NullPointerException("栈中元素为空！");
		Item item = root.item;
		root = root.next;
		n--;
		return item;
	}
	
	/**
	 * 栈中元素的个数
	 * @return 元素个数
	 */
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
        return n == 0;
    }
	
	/**
     * According to pop stack order.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node tempRoot = root;
        for (int i = 0; i < size(); i++) {
            sb.append(tempRoot.item);
            if (i < size() - 1) {
                sb.append(", ");
            }
            tempRoot = tempRoot.next;
        }
        sb.append("]");
        return sb.toString();
    }
	
	// 定义节点
	private class Node {
		private Item item;
		private Node next;	
	}
}
