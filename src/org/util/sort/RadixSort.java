package org.util.sort;

import java.util.*;

/**
 * 基数排序：基于桶排序，从个位、十位、百位。。。。。。迭代，每次迭代的时候，
 * 如果某位是5，就存入对应的0到9中的编号为5的桶中，然后依次重新放入原数组中。终止条件为所有的数
 * 都超过了最高位。
 * 实现细节：针对可能存在负数，所以先找出原数组中最小的数，然后将所有的数都加上该数的绝对值后
 * 再求对应位的数，这样保证了负数为自然数，而且它们与正数的相对关系不发生变化。具体代码体现在
 * 这里：((x[i] + Math.abs(minvalue)) / offset) % 10
 * @author Weibing Long
 * @since 2018.04.16
 */
public class RadixSort implements Sort {

	public static void main(String[] args) {
		Sort radixSort = new RadixSort();
		int[] x = new int[] {
			100, 40, 60, 87, 34, 11, 56, 0, 2, 57, 50, 40, 34
		};
		radixSort.sort(x);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + "  ");
		}
	}
	
	@Override
	public void sort(int[] x) {
		sort(x, 1);
	}

	private void sort(int[] x, int offset) {
		// 创建从0到9的十个桶
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < 10; i++)
			map.put(i, new ArrayList<Integer>());
		int minvalue = min(x);
		// 填桶
		for (int i = 0; i < x.length; i++) {
			// (x[i] + Math.abs(minvalue)) 保证所有的数都为正数，而且相对大小不变
			long sum = ((x[i] + Math.abs(minvalue)) / offset);  // 防止溢出
			map.get((int)(sum % 10)).add(i);
		}
		offset *= 10;
		// 终止条件
		if ((Math.abs(minvalue) + max(x)) * 10 < offset)
			return;
		else {
			int[] temp = new int[x.length];
			int m = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < map.get(i).size(); j++) {
					temp[m++] = x[map.get(i).get(j)]; 
				}
			}
			for (int i = m; i < x.length; i++) {
				temp[i] = x[i];
			}
			for (int i = 0; i < x.length; i++) {
				x[i] = temp[i];
			}
			sort(x, offset);
		}
	}
	
	// 求数组中最小的数
	private int min(int[] x) {
		int min = Integer.MAX_VALUE;
		for (int value : x) {
			if (min > value)
				min = value;
		}
		return min;
	}
	// 求数组中最小的数
	private int max(int[] x) {
		int max = Integer.MIN_VALUE;
		for (int value : x) {
			if (max < value)
				max = value;
		}
		return max;
	}

}
