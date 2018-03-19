package org.util;

import java.io.*;
import java.util.*;

import org.neuralNetwork.data.ArffData;
import org.neuralNetwork.data.Instance;

public class MyFile {	
	private MyFile() {		
	}
	
	/**
	 * 按行读文件，把文件内容返回到一个字符串列表中
	 * @param filePath 文件的路径
	 * @return 文件中的所有内容
	 */
	public static List<String> read(String filePath) {
		List<String> allLine = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
	        String line = "";
	        while ((line = br.readLine())  != null) {
	        	allLine.add(line);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allLine;
	}
	
	/**
	 * 将内容写入到文件
	 * @param list 写入的内容
	 * @param filePath 文件路径
	 */
	public static void write(final List<String> list, String filePath) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
			int i = 0;
			for (String line : list) {
				bw.append(line);
				i++;
				if (i < list.size())
					bw.newLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public static ArffData getArffData(String filePath) {
		ArffData arffData = new ArffData();
		List<String> data = MyFile.read(filePath);
		int attCount = 0;
		int labelCount = 0;
		int x = 0;
		String desIns = "";
		for (int i = 0; i < data.size(); i++) {
			desIns += data.get(i);
			if (data.get(i).contains("attribute") && data.get(i).contains("{0,1}")) {
				labelCount++;
			}	
			else if (data.get(i).contains("attribute")) {
				attCount++;
			}
			else if (data.get(i).contains("@data")) {
				x = i + 1;
				break;
			}
			desIns += "\n";
		}
		
		Map<Integer, Map<Integer, Double>> allLine = new HashMap<Integer, Map<Integer, Double>>();
		// 添加数据
		int n = 0;
		for (int i = x; i < data.size(); i++) {
			Map<Integer, Double> line = new HashMap<Integer, Double>();
			String[] y = data.get(i).split(",");
			for (int j = 0; j < y.length; j++) {
				line.put(j, Double.parseDouble(y[j]));
			}
			allLine.put(n++, line);
		}
		
		Instance instance = new Instance();
		instance.setAllLine(allLine);
		instance.setLineCount(allLine.size());
		arffData.setInstance(instance);
		arffData.setAttCount(attCount);
		arffData.setLabelCount(labelCount);
		arffData.setDesIns(desIns);
		return arffData;
	}

	/**
	 * 
	 * @param arffData
	 * @param arffFilePath
	 * @param xmlFilePath
	 */
	public static void writeArffAndXml(ArffData arffData, String arffFilePath, String xmlFilePath) {
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>(); // xml文件内容
		// 添加arff文件前面的描述
		list.add(arffData.getDesIns());
		Map<Integer, Map<Integer, Double>> map = arffData.getInstance().getAllLine();
		// 添加数据
		// 每一行
		for (int i = 0; i < map.size(); i++) {
			Map<Integer, Double> temp = map.get(i);
			String s = "";
			// 每一列
			for (int j = 0; j < temp.size(); j++) {
				if (temp.get(j) == 1.0 || temp.get(j) == 0.0) {
					double a = temp.get(j);
					int aa = (int)a;
					s += String.valueOf(aa);
				} else {
					s += temp.get(j);
				}
				if (j != temp.size() - 1) {
					s += ",";
				}
			}
			list.add(s);
		}
		MyFile.write(list, arffFilePath);
		
		// 写xml文件
		list2.add(arffData.getXmlInf());
		MyFile.write(list2, xmlFilePath);
		System.out.println("写入成功");
		
	}
	
	public static void main(String[] args) {
//		ArffData arffData = MyFile.getArffData("sources/emotions.arff");
//		Instance instance = arffData.getInstance();
//		Map<Integer, Map<Integer, Double>> allLine = instance.getAllLine();
//		for (int i = 0; i < allLine.size(); i++) {
//			Map<Integer, Double> theLine = allLine.get(i);
//			System.out.println(theLine);
//		}
		ArffData arffData = Features.getLocFeaArffData("sources/emotions.arff", 3, "walking");
		MyFile.writeArffAndXml(arffData, "sources/emotion_localfeature.arff", "sources/emotion_localfeature.xml");
	}
}