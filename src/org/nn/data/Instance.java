package org.nn.data;

import java.util.*;

/**
 * 封装arff的数据实例<br><br>
 * Instance封装的数据类似下面<br>
 * {@code [[0.034741,0.089665,0.091225,...,0,1,1,0,0,0],
          [0.081374,0.272747,0.085733,...,1,0,0,0,0,1],
          [0.110545,0.273567,0.08441,...,0,1,0,0,0,1],
          [0.042481,0.199281,0.093447,..,0,1,1,1,0,1]
          ...]
     }
 * @author 龙卫兵
 * @since 2018.3.28
 */
public class Instance {
	/**
	 * 所有的行数据
	 */
	private final Map<Integer, Map<Integer, Double>> allLine;
	/**
	 * 实例的行数
	 */
	private final int lineCount;
	
	public Instance(Map<Integer, Map<Integer, Double>> allLine, int lineCount) {
		this.allLine = allLine;
		this.lineCount = lineCount;
	}
	
	public Map<Integer, Map<Integer, Double>> getAllLine() {
		return allLine;
	}
	public int getLineCount() {
		return lineCount;
	}	
}
