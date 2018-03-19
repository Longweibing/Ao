package org.dataStructure;
/**
 * 栈：底层为数组的栈
 * @author 龙卫兵
 * @param <Item>
 */
public class Stack<Item> {
	
	private Object[] myArray = null;//元素容器
	private int capacity = 0;//元素容量
	private int n = 0;//元素个数
	
	public Stack() {
		capacity = 2;
		myArray = new Object[capacity];
	}
	
	public Stack(int capacity) {
		this.capacity = capacity;
		myArray = new Object[capacity];
	}
	
	//插入元素
	public void push(Item item) {
		if (n >= capacity/2) {
			capacity *= 2;
			Object[] temp = new Object[capacity];
			for (int i = 0; i < capacity/2; i++)
				temp[i] = myArray[i];
			myArray = temp;
		}	
		myArray[n++] = item;
	}
	
	@SuppressWarnings("unchecked")
	public Item peek() {
		if (n == 0)
			return null;
		Item item = (Item) myArray[n-1];
		return item;
	}
	
	//删除元素
	@SuppressWarnings("unchecked")
	public Item pop() {	
		if (isEmpty()) throw new NullPointerException();
		Item item = (Item) (myArray[--n]);
		myArray[n] = null;
		return item;
	}
	
	//元素个数
	public int size() {
		return n;
	}
	
	//是否为空
	public boolean isEmpty() {
		return n == 0;
	}

	//测试
	public static void main(String[] args) {
		
	}
}