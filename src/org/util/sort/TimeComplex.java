package org.util.sort;

import java.util.Date;
import java.util.Random;

/**
 * 对排序算法时间复杂度的分析
 * @author Weibing Long
 * @since 2018.04.16
 */
public class TimeComplex {
	public static void main(String[] args) {
		Sort bubbleSort = new BubbleSort();
		Sort insertSort = new InsertSort();
		Sort heapSort = new HeapSort();
		Sort mergeSort = new MergeSort();
		Sort shellSort = new ShellSort();
		Sort selectSort = new SelectSort();
		Sort quickSort = new QuickSort();
		Sort radixSort = new RadixSort();
		Random random = new Random();
		
		int[] x = new int[10000];
		for (int i = 0; i < x.length; i++)
			x[i] = random.nextInt();
//		//
		long start = new Date().getTime();
		bubbleSort.sort(x);
		long end = new Date().getTime();
		System.out.println("bubbleSort:" + (end - start));
		
		start = new Date().getTime();
		quickSort.sort(x);
		end = new Date().getTime();
		System.out.println("quickSort:" + (end - start));
		
		start = new Date().getTime();
		shellSort.sort(x);
		end = new Date().getTime();
		System.out.println("shellSort:" + (end - start));
		
		//
		start = new Date().getTime();
		insertSort.sort(x);
		end = new Date().getTime();
		System.out.println("insertSort:" + (end - start));
		
		start = new Date().getTime();
		heapSort.sort(x);
		end = new Date().getTime();
		System.out.println("heapSort:" + (end - start));
		
		start = new Date().getTime();
		mergeSort.sort(x);
		end = new Date().getTime();
		System.out.println("mergeSort:" + (end - start));
		
		start = new Date().getTime();
		selectSort.sort(x);
		end = new Date().getTime();
		System.out.println("selectSort:" + (end - start));
	
		
		
		start = new Date().getTime();
		radixSort.sort(x);
		end = new Date().getTime();
		System.out.println("radixSort:" + (end - start));
		
	}
}
