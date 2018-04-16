package org.nn.evaluation;
// TODO 可能有错误
import java.util.*;

import org.util.MyFile;

/**
 * 评价指标<br><br>
 * 相关论文：
 * <a href="http://www.cs.princeton.edu/courses/archive/spr08/cos424/readings/SchapireSinger2000.pdf">
 * BoosTexter: A Boosting-based System for Text Categorization</a>
 * @author 龙卫兵
 * @since 2018.3.28
 * 
 */
public class Evaluator {
	/**
	 * 平均精确度
	 * @param predvalsRanked 预测值
	 * @param pracvals 实际值
	 * @return 平均精确度值
	 */
	public static double AP(List<List<Double>> predvalsRanked, List<List<Double>> pracvals) {
		double output = 0;
		int countLabel = predvalsRanked.get(0).size();
		int countIns = predvalsRanked.size();
		// 遍历实例
		for (int i = 0; i < countIns; i++) {
			double resultIns = 0;
			int countError = 0;
			int countCorrent = 0;
			// 从后往前遍历标签
			for (int j = countLabel - 1; j >= 0; j--) {
				if (!predvalsRanked.get(i).get(j).equals(1.0)) {
					countError++;
				} else {
					for (int k = j; k > 0; k--) {
						resultIns += ((double)(k - 1) / k);
					}
				}
			}
			countCorrent = countLabel - countError;
			if (countCorrent != 0)
				output += (resultIns / countCorrent);
		}
		output /= countIns;
		return output;
	}
	
	/**
	 * 1错误率
	 * @param predvalsRanked 已经排好序的预测标签
	 * @param pracvals 相应的实际标签
	 * @return 损失值
	 */
	public static double oneError(List<List<Double>> predvalsRanked, List<List<Double>> pracvals) {
		double output = 0;
		int countIns = predvalsRanked.size();
		int countError = 0;
		int countLabel = predvalsRanked.get(0).size();
		// 多标签
		if (countLabel > 1) {
			// 遍历实例的第一个标签值
			for (int i = 0; i < countIns; i++) {
				boolean flag = !pracvals.get(i).get(0).equals(1.0);
				if (flag)
					countError++;
			}
		} else if (countLabel == 1) {  // 单标签
			// 遍历实例标签值
			for (int i = 0; i < countIns; i++) {
				boolean flag = !predvalsRanked.get(i).get(0).equals(pracvals.get(i).get(0));
				if (flag)
					countError++;
			}
		} else {
			throw new IllegalArgumentException("标签个数不能小于1");
		}
		// 求平均值
		output = (double)countError / countIns;
		return output;
	}
	
	/**
	 * 标签正确率
	 * @param predvals 预测值
	 * @param pracvals 实际值
	 * @return 正确率结果
	 */
	public static double accuracy(List<List<Double>> predvals, List<List<Double>> pracvals) {
		double output = 0;
		int countIns = predvals.size();
		// 遍历实例
		for (int i = 0; i < countIns; i++) {
			List<Double> preY = predvals.get(i);
			List<Double> Y = pracvals.get(i);
			int countTrue = 0;
			int countLabel = Y.size();
			//遍历标签
			for (int j = 0; j < countLabel; j++) {
				boolean flag = preY.get(j).equals(Y.get(j));
				if (flag)
					countTrue++;
			}
			output += ((double)countTrue / countLabel);
		}
		return output /= countIns;
	}
	
	/**
	 * 排序损失
	 * @param predvalsRanked 预测值
	 * @param pracvals 实际值
	 * @return 排序损失值
	 */
	public static double rLoss(List<List<Double>> predvalsRanked, List<List<Double>> pracvals) {
		double output = 0;
		return output;
	}
	
	/**
	 * 覆盖率
	 * @param predvalsRanked 预测值
	 * @param pracvals 实际值
	 * @return 覆盖率值
	 */
	public static double coverage(List<List<Double>> predvalsRanked, List<List<Double>> pracvals) {
		double output = 0;
		int countIns = predvalsRanked.size();		
		int countLabel = predvalsRanked.get(0).size();
		if (countLabel > 1) {
			int countCoverage = 0;
			// 遍历实例
			for (int i = 0; i < countIns; i++) {
				int countTer = 0;  // 从标签后面开始遍历的个数
				// 从后向前遍历标签
				for (int j = countLabel - 1; j >= 0; j--)
					if (predvalsRanked.get(i).get(j) != 1.0)
						countTer++;
					else break;
				countCoverage = countLabel - countTer;
				output += countCoverage;
			}
		} else if (countLabel == 1) {
			// 遍历实例
			for (int i = 0; i < countIns; i++) {
				if (predvalsRanked.get(i).get(0).equals(pracvals.get(i).get(0))) {
					output++;
				}	
			}
		} else {
			throw new IllegalArgumentException("标签个数不能小于1");
		}
		// 求平均值
		output /= countIns;
		return output;
	}
	
	/**
	 * 汉明损失
	 * @param predvalsRanked 预测值
	 * @param pracvals 实际值
	 * @return 汉明损失值
	 */
	public static double hLoss(List<List<Double>> predvalsRanked, List<List<Double>> pracvals) {
		double output = 0;
		return output;
	}
	public static void main(String[] args) {
		List< List< Double>> pracvals = new ArrayList< List< Double>>();
		List< List< Double>> predvals = new ArrayList< List< Double>>();

		// 读取预测标签值和实际标签值文件
		List< String> list = MyFile.read("sources/labels/predict_value3");
		List< String> list2 = MyFile.read("sources/labels/practice_value3");

		// 添加排过序后的预测值
		for (int i = 0; i < list.size(); i++) {
			List< Double> temp = new ArrayList< Double>();
			String[] lineLabel = list.get(i).split(" ");
			for (String label : lineLabel) {
				temp.add(Double.parseDouble(label.trim()));
			}
			predvals.add(temp);
		}

		// 添加实际值
		for (int i = 0; i < list2.size(); i++) {
			List< Double> temp = new ArrayList< Double>();
			String[] lineLabel = list2.get(i).split(" ");
			for (String label : lineLabel) {
				temp.add(Double.parseDouble(label.trim()));
			}
			pracvals.add(temp);
		}

		//-----------精确度-----------------------------------------------------//
		double p1 = Evaluator.accuracy(predvals, pracvals);
		System.out.println("accuracy = " + p1);
		//-----------1错误率-----------------------------------------------------//
		double p2 = Evaluator.oneError(predvals, pracvals);
		System.out.println("oneError = " + p2);
		//-----------覆盖率-----------------------------------------------------//
		double p3 = Evaluator.coverage(predvals, pracvals);
		System.out.println("coverage = " + p3);
		//-----------平均精确度-----------------------------------------------------//
		double p4 = Evaluator.AP(predvals, pracvals);
		System.out.println("AP = " + p4);
	}
	 
}
