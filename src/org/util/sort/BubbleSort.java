package org.util.sort;

import java.util.Random;

/**
 * 冒泡排序：时间复杂度与n平方成正比
 * @author Weibing Long
 * @since 2018.04.13
 */
public class BubbleSort implements Sort {
	
	public static void main(String[] args) {
		Sort bubbleSort = new BubbleSort();
		Random random = new Random();
		int[] x = new int[10];
		for (int i = 0; i < x.length; i++)
			x[i] = random.nextInt();
		bubbleSort.sort(x);
		for (int value : x) {
			System.out.print(value + "  ");
		}
	}
	
	@Override
	public void sort(int[] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = i + 1; j < x.length; j++) {
				if (x[i] > x[j]) {
					swap(x, i, j);
				}
			}
		}
	}
	
	// 交换元素
	private void swap(int[] x, int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;			
	}

}
