package org.util.sort;

/**
 * 归并排序：此算法与快速排序有些类似，运行效率也很好，但是需要额外的空间。
 * 此外，归并对半后，分别排序，然后合并数组。而快速排序对半的依据是某个元素。
 * @author Weibing Long
 * @since 2018.04.15
 */
public class MergeSort implements Sort {

	public static void main(String[] args) {
		Sort mergetSort = new MergeSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		mergetSort.sort(x);
		for (int value : x) {
			System.out.print(value + "  ");
		}
	}
	
	@Override
	public void sort(int[] x) {
		sort(x, 0, x.length - 1);
	}

	private void sort(int[] x, int low, int high) {
		if (high - low == 0)
			return;
		if (high - low == 1) {
			if (x[low] > x[high])
				swap(x, low, high);
			return;
		}
		long sum = low + high;
		int middle = (int)(sum / 2);
		sort(x, low, middle);
		sort(x, middle + 1, high);
		merge(x, low, high);
	}

	private void merge(int[] x, int low, int high) {
		int n = high - low + 1;
		int[] temp = new int[n];
		long sum = low + high;
		int mid = (int)(sum / 2), left = low, right = mid + 1, i = 0;
		while (left <= mid && right <= high) {
			if (x[left] <= x[right]) temp[i++] = x[left++];
			else temp[i++] = x[right++];
		}
		if (left > mid)
			while (right <= high) temp[i++] = x[right++];
		if (right > high)
			while (left <= mid) temp[i++] = x[left++];
		int index = 0;
		for (i = low; i <= high; i++, index++)
			x[i] = temp[index];	
	}

	private void swap(int[] x, int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

}
