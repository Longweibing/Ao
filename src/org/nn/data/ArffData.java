package org.nn.data;

/**
 * arff数据对象
 * @author 龙卫兵
 *
 */
public class ArffData {
	private int labelCount;
	private int attCount;
	private Instance instance;
	/**
	 * arff文件非数据部分的文本，即数据前面的所有文本
	 */
	private String desIns;
	/**
	 * arff文件对应的xml文本
	 */
	private String xmlInf;
	
	public int getLabelCount() {
		return labelCount;
	}
	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}
	public int getAttCount() {
		return attCount;
	}
	public void setAttCount(int attCount) {
		this.attCount = attCount;
	}
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	public String getDesIns() {
		return desIns;
	}
	public void setDesIns(String desIns) {
		this.desIns = desIns;
	}
	public String getXmlInf() {
		return xmlInf;
	}
	public void setXmlInf(String xmlInf) {
		this.xmlInf = xmlInf;
	}
	
	
}
