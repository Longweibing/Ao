package org.util.sort;

/**
 * 堆排序，基于选择排序（基本操作是选择，或者理解为时间主要花费在选择操作）。
 * 对于给定的int类型数组，首先将它组织为最大堆（堆是一种数据结构，数据结构就是数据的组织方式。最大堆满足：是一个完全二叉树，而且父节点比直接左右子树节点值大。）
 * 然后将最后一个元素与第一个元素交换。这是一次迭代。第二次的时候，组织最大堆的时候，不包括最后一个元素，因为它的位置已经确定了。
 * 第二次迭代再将第一个元素与倒数第二个元素交换位置，以此类推。
 * @author Weibing Long
 * @since 2018.04.15
 */
public class HeapSort implements Sort {

	public static void main(String[] args) {
		Sort heapSort = new HeapSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		heapSort.sort(x);
		for (int value : x) {
			System.out.print(value + "  ");
		}
	}
	
	@Override
	public void sort(int[] x) {
		// 从后往前，将最大的值依次赋值
		for (int i = x.length - 1, j = 0; i > 0; i--, j++) {
			// 建大顶堆
			int y = 0;
			// 当前节点左右子树都存在
			while (2 * y + 2 < x.length - j) {
				int maxIndex = y;
				if (x[maxIndex] < x[2 * y + 1])
					maxIndex = 2 * y + 1;
				if (x[maxIndex] < x[2 * y + 2])
					maxIndex = 2 * y + 2;
				// 使父节点最大
				if (maxIndex != y) {
					swap(x, maxIndex, y);
					backtrace(x, y);
				}
				y++;
			}
			// 当前节点存在左子树，不存在右子树，而且做子树节点值更大。
			if (2 * y + 1 < x.length - j && x[y] < x[2 * y + 1]) {
				swap(x, 2 * y + 1, y);
				backtrace(x, y);
			}
			//交换元素
			swap(x, i, 0);
		}
		
	}
	
	// 交换元素
	private void swap(int[] x, int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
	
	// 回溯操作，主要是从当前索引curindex开始，将较大的值从父节点一直传送到根节点。
	private void backtrace(int[] x, int curindex) {
		int i1 = curindex;
		int i2 = (i1 - 1) / 2;
		while (i2 >= 0 && x[i1] > x[i2]) {
			swap(x, i1, i2);
			i1 = i2;
			i2 = (i1 - 1) / 2;
			if (i1 == 0) break;
		}
	}
}
