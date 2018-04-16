package org.util.sort;

/**
 * 插入排序，会产生临时数组，空间复杂度与待排序数组元素个数成正比，时间复杂度与n平方成正比。
 * @author Weibing Long
 * @since 2018.04.13
 */
public class InsertSort implements Sort {
	
	public static void main(String args[]) {
		Sort insertSort = new InsertSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		insertSort.sort(x);
		for (int value : x) {
			System.out.print(value + "  ");
		}
	}
	
	@Override
	public void sort(int[] x) {
		int[] y = new int[x.length];
		y[0] = x[0];
		for (int i = 1, j = 0; i < x.length; i++, j++) {
			for (int k = 0; k < j + 1; k++) {
				if (x[i] < y[k]) {
					int temp = x[i];
					x[i] = y[k];
					y[k] = temp;			
				}
			}
			y[j + 1] = x[i];
		}
		for (int i = 0; i < x.length; i++) {
			x[i] = y[i];
		}
	}
	
}
