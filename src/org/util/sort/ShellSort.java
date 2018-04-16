package org.util.sort;

/**
 * 希尔排序。（为啥叫希尔排序呢？---- 我叫希尔，排序是我写的。有毛病吗？）
 * @author Weibing Long
 * @since 2018.04.13
 */
public class ShellSort implements Sort {

	public static void main(String[] args) {
		Sort shellSort = new ShellSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		shellSort.sort(x);
		for (int value : x) {
			System.out.print(value + "  ");
		}
	}
	
	@Override
	public void sort(int[] x) {
		int stepSize = x.length / 3;  // 步子大小
		// 每一次循环就是一趟排序
		while (stepSize >= 1) {
			for (int i = 0; i < stepSize; i++) {
				int n = 0;
				// 计算待比较元素的个数，为了确定临时数组长度
				for (int j = i; j < x.length; j = j + stepSize)
					n++;
				int[] temp = new int[n];
				for (int j = i, k = 0; j < x.length; j = j + stepSize, k++) {
					temp[k] = x[j];
				}
				// 对元素进行插入排序
				Sort insertSort = new InsertSort();
				insertSort.sort(temp);
				for (int j = i, k = 0; j < x.length; j = j + stepSize, k++) {
					x[j] = temp[k];
				}
			}
			stepSize--;  // 步子减一
		}
	}
}
