package org.util;

import java.util.*;

import org.nn.data.*;
/**
 * 提取特征的工具类
 * @author 龙卫兵
 *
 */
public class Extractor {
	
	/**
	 * 2018.3.16
	 * @param filePath arff文件路径
	 * @param winSize 窗口大小
	 * @param way 滑动方式：1.游动 2.切分
	 * @return arffData对象
	 */
	public static ArffData getArffDataLocFea(String filePath, int winSize, String way) {
		ArffData arffData = null;
		
		// 加载实例
		arffData = MyFile.getArffData(filePath);
		int attCount = arffData.getAttCount();
		if (attCount < winSize) {
			throw new IllegalArgumentException("窗口大小设置太大了，最大应该为：" + attCount);
		}
		Instance instance = arffData.getInstance();
		int labelCount = arffData.getLabelCount();
		int n = attCount - winSize + 1;
		
		// 设置arff文件前面的描述
		String desIns = "@relation zz\n\n";
		for (int i = 0; i < n; i++) {
			desIns += "@attribute att" + (i + 1) + " numeric" + "\n";
		}
		for (int i = 0; i < labelCount; i++) {
			desIns += "@attribute fea" + (i + 1) + " {0,1}" + "\n";
		}
		desIns += "\n@data";
		
		// 设置xml文件文本
		String xmlInf = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		xmlInf += "<labels xmlns=\"http://mulan.sourceforge.net/labels\">\n";
		for (int i = 0; i < labelCount; i++) {
			xmlInf += "<label name=\"fea" + (i + 1) + "\"></label>\n";
		}
		xmlInf += "</labels>";
		
		Map<Integer, Map<Integer, Double>> allLine = instance.getAllLine();
		Map<Integer, Map<Integer, Double>> listlist = new HashMap<Integer, Map<Integer, Double>>();
		// 局部特征提取
		if (way.equals(""))
			throw new IllegalArgumentException("您没有输入局部特征的提取方式");
		else if (way.equals("walking")) {
			for (int i = 0; i < allLine.size(); i++) {
				Map<Integer, Double> line = allLine.get(i);			
				int x = 0;
				Map<Integer, Double> list = new HashMap<Integer, Double>();
				for (int j = 0; j < n; j++, x++) {
					double sum = 0;
					for (int k = x; k < winSize + x; k++) {
						sum += line.get(k);
					}
					sum /= winSize;
					list.put(j, sum);
				}
				
				// 拼接特征
				int count = 0;
				for (int j = x; j < labelCount + x; j++) {
					list.put(j, line.get(attCount + count));
					count++;
				}
				listlist.put(i, list);
				
			}	
			Instance instanceLocal = new Instance(listlist, listlist.size());
			arffData.setInstance(instanceLocal);
			arffData.setLabelCount(labelCount);
			arffData.setAttCount(n);
			arffData.setDesIns(desIns);
			arffData.setXmlInf(xmlInf);
			
		}
		else if (way.equals("segmentation")) {
			//TODO 获取局部特征的切分滑动方式未实现
		}
		else
			throw new IllegalArgumentException("您输入局部特征的提取方式有误: " + way);
		return arffData;
	}
	
	public static void main(String[] args) {
		ArffData arffData = Extractor.getArffDataLocFea("sources/data/multi-label/yeast/yeast.arff", 2, "walking");
		System.out.println(arffData.getDesIns());
	}
}
