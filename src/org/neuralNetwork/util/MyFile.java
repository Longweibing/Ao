package org.neuralNetwork.util;

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
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).contains("attribute") && data.get(i).contains("{0,1}"))
				labelCount++;
			else if (data.get(i).contains("attribute"))
				attCount++;
			else if (data.get(i).contains("@data")) {
				x = i + 1;
				break;
			}	
		}
		
		List<List<Double>> allLine = new ArrayList<List<Double>>();
		
		for (int i = x; i < data.size(); i++) {
			List<Double> line = new ArrayList<Double>();
			String[] y = data.get(i).split(",");
			for (int j = 0; j < y.length; j++) {
				line.add(Double.parseDouble(y[j]));
			}
			allLine.add(line);
		}
		
		Instance instance = new Instance();
		instance.setAllLine(allLine);
		instance.setLineCount(allLine.size());
		arffData.setInstance(instance);
		arffData.setAttCount(attCount);
		arffData.setLabelCount(labelCount);		
		return arffData;
	}
	
	public static void main(String[] args) {
		List<String> allLine = MyFile.read("sources/emotions.arff");
		for (String string : allLine) {
			System.out.println(string);
		}
	}
}