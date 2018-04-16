package org.util.sort;

/**
 * 快速排序。它是一种基于交换排序的排序算法，目前公认最好的排序算法。发明者是C. A. R. Hoare<hr>
 * 算法思想：第一个元素作为基准，将大于它的元素排在右边，小于它的元素
 *           排在左边。然后用同样的方式对左右两边的数据进行整理，直到只剩两个元素的比较为止。
 *           比较两个元素，将小的排在左边。<br>
 *           
 *           该算法涉及到分治思想和递归思想。
 * @author Weibing Long
 *
 */
public class QuickSort implements Sort {
	
	public static void main(String[] args) {
		QuickSort quickSort = new QuickSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		quickSort.sort(x);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + "  ");
		}
	}

	@Override
	public void sort(int[] x) {
		sort(x, 0, x.length - 1);
	}
	
	private void sort(int[] x, int low, int high) {
		if (low >= high)
			return;
		// x在low和high指定的范围元素仅两个
		if (high - low == 1) {
			if (x[low] > x[high])
				swap(x, low, high);
			return;
		}
		
		int pivot = x[low];  // 基准元素
		int left = low + 1;
		int right = high;
		while (left < right) {
			while (left < right && left <= high) {
				if (x[left] > pivot)
					break;
				left++;
			}
			
			while (left <= right && right > low) {
				if (x[right] < pivot)
					break;
				right--;
			}
			
			if (left < right)
				swap(x, left, right);
		}
		swap(x, low, right);
		sort(x, low, right - 1);
		sort(x, right + 1, high);
	}
	
	// 交换数组在left和right处两个元素的值
	private void swap(int[] x, int left, int right) {
		int temp = x[left];
		x[left] = x[right];
		x[right] = temp;
	}

}
