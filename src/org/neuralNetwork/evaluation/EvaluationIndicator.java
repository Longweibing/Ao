package org.neuralNetwork.evaluation;

import java.util.*;

/**
 * 评价指标
 * 
 * @author Weibing Long
 * @since 2017.11.27
 * 
 */
public class EvaluationIndicator {
	public static double AP() {
		return 0;
	}
	
	/**
	 * 多标签分类0-1损失
	 * @param predvalsRanked
	 * @param pracvals
	 * @return
	 */
	public static double oneError(List<List<Double>> predvalsRanked, List<List<Integer>> pracvals) {
		double results = 0;
		for (int i = 0; i < predvalsRanked.size(); i++) {
			double x= 0;
			for (int j = 0; j < predvalsRanked.get(i).size(); j++) {
				if (predvalsRanked.get(i).get(j) != (double) pracvals.get(i).get(j))
					x++;
			}
			if (x != 0)
				results += x / predvalsRanked.get(i).size();
			else
				results += 1;
		}
			
			
		return results /= predvalsRanked.size();
	}
	
	/**
	 * 多标签分类精确度
	 * @param predvals
	 * @param pracvals
	 * @return
	 */
	public static double accuracy(List<List<Integer>> predvals, List<List<Integer>> pracvals) {
		double results = 0;
		for (int i = 0; i < predvals.size(); i++) {
			List<Integer> preY = predvals.get(i);
			List<Integer> Y = pracvals.get(i);
			double x = 0;
			double y = 0;
			for (int j = 0; j < Y.size(); j++) {	
				if (preY.get(j) == 1 && Y.get(j) == 1)
					x++;
				if (!(preY.get(j) == 0 && Y.get(j) == 0))
					y++;			
			}
			if (y != 0)
			    results += (x / y);
		}
		return results /= predvals.size();
	}
	
	public static void main(String[] args) {
		List<List<Integer>> predvals = new ArrayList<List<Integer>>();
		List<List<Integer>> pracvals = new ArrayList<List<Integer>>();
		List<List<Double>> predvalsRanked = new ArrayList<List<Double>>();
		List<Integer> x1 = new ArrayList<Integer>();
		List<Integer> x2 = new ArrayList<Integer>();
		List<Integer> x3 = new ArrayList<Integer>();
		List<Integer> x4 = new ArrayList<Integer>();
		List<Integer> x5 = new ArrayList<Integer>();
		Integer[] elements1 = {1, 0, 0, 1};
		Integer[] elements2 = {0, 1, 0, 1};
		Integer[] elements3 = {1, 0, 0, 1};
		Integer[] elements4 = {0, 1, 0, 0};
		Integer[] elements5 = {1, 0, 0, 1};
		Collections.addAll(x1, elements1);
		Collections.addAll(x2, elements2);
		Collections.addAll(x3, elements3);
		Collections.addAll(x4, elements4);
		Collections.addAll(x5, elements5);
		predvals.add(x1);
		predvals.add(x2);
		predvals.add(x3);
		predvals.add(x4);
		predvals.add(x5);
		//------------------------------------------------------------------//
		List<Integer> y1 = new ArrayList<Integer>();
		List<Integer> y2 = new ArrayList<Integer>();
		List<Integer> y3 = new ArrayList<Integer>();
		List<Integer> y4 = new ArrayList<Integer>();
		List<Integer> y5 = new ArrayList<Integer>();
		Integer[] elements6 = {1, 0, 1, 0};
		Integer[] elements7 = {0, 1, 0, 1};
		Integer[] elements8 = {1, 0, 0, 1};
		Integer[] elements9 = {0, 1, 1, 0};
		Integer[] elements10 = {1, 0, 0, 0};
		Collections.addAll(y1, elements6);
		Collections.addAll(y2, elements7);
		Collections.addAll(y3, elements8);
		Collections.addAll(y4, elements9);
		Collections.addAll(y5, elements10);
		pracvals.add(y1);
		pracvals.add(y2);
		pracvals.add(y3);
		pracvals.add(y4);
		pracvals.add(y5);
		//--------------------------------------------------------------------------//
		List<Double> z1 = new ArrayList<Double>();
		z1.add(1.0);
		z1.add(0.0);
		z1.add(0.0);
		z1.add(1.0);
		List<Double> z2 = new ArrayList<Double>();
		z2.add(0.0);
		z2.add(1.0);
		z2.add(0.0);
		z2.add(1.0);
		List<Double> z3 = new ArrayList<Double>();
		z3.add(1.0);
		z3.add(0.0);
		z3.add(0.0);
		z3.add(1.0);
		List<Double> z4 = new ArrayList<Double>();
		z4.add(0.0);
		z4.add(1.0);
		z4.add(0.0);
		z4.add(0.0);
		List<Double> z5 = new ArrayList<Double>();
		z5.add(1.0);
		z5.add(0.0);
		z5.add(0.0);
		z5.add(1.0);
		predvalsRanked.add(z1);
		predvalsRanked.add(z2);
		predvalsRanked.add(z3);
		predvalsRanked.add(z4);
		predvalsRanked.add(z5);
		//-----------精确度-----------------------------------------------------//
		double p1 = EvaluationIndicator.accuracy(predvals, pracvals);
		System.out.println("accuracy = " + p1);
		//----------0-1损失----------------------------------------------------//
		double p2 = EvaluationIndicator.oneError(predvalsRanked, pracvals);
		System.out.println("oneError = " + p2);
	}
	 
}
