package org.util.sort;

/**
 * 选择排序：一趟遍历将最小的数记录下来，然后与第一个元素交换位置。
 * (注意与冒泡排序的区别，最坏情况下，虽然时间复杂度一样N平方，但是选择排序更有效)
 * 这里理解如有错误，欢迎指正！
 * @author Weibing Long
 * @since 2018.04.14
 */
public class SelectSort implements Sort {
	
	public static void main(String[] args) {
		Sort selectSort = new SelectSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		selectSort.sort(x);
		for (int value : x) {
			System.out.print(value + "  ");
		}
	}

	@Override
	public void sort(int[] x) {		
		for (int i = 0; i < x.length; i++) {
			int min = i;
			for (int j = i + 1; j < x.length; j++) {
				if (x[j] < x[min]) {
					min = j;
				}
			}
			if (min != i)
				swap(x, min, i);
		}	
	}
	
	private void swap(int[] x, int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

}
