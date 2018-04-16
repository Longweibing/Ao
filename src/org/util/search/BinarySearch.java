package org.util.search;

/**
 * 折半查找，使用折半查找的前提是元素已经排序
 * @author Weibing Long
 * @since 2018.04.13
 */
public class BinarySearch implements Search {
	
	public static void main(String[] args) {
		BinarySearch binarySearch = new BinarySearch();
		int[] x = new int[] {
				2, 3, 5, 7, 10, 11, 18, 19, 23
		};
	    boolean result = binarySearch.search(x, 3);
	    System.out.println(result);
	}

	@Override
	public boolean search(int[] x, int key) {
		if (x == null)
			throw new NullPointerException("数组对象不能为空");
		if (x.length == 1 && key == x[0])
			return true;
		int left = 0;
		int right = x.length - 1;
		int middle = 0;
		while (left <= right) {
			long sum = right + left;
			middle = (int)(sum / 2);
			if (key > x[middle])
				left = middle + 1;
			else if (key < x[middle])
				right = middle - 1;
			else
				return true;
		}
		return false;
	}
}
