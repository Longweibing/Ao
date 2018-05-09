package org.nn.component;

import java.util.*;

/**
 * 神经层
 * @author Weibing Long
 * @since 2018.5.09
 */
public class NeuralLayer implements Iterable<Neural> {
	
	/**
	 * 该神经层上所有的神经元
	 */
    private List<Neural> allNeurals;

    /**
     * 该神经层上神经元的个数
     */
	private int neuralNumbers = 0;
	
	/**
	 * 创建神经层对象
	 * @param allNeurals 所有的神经元 --> List< Neural >
	 */
	public NeuralLayer(List<Neural> allNeurals) {
	    this.allNeurals = allNeurals;
	    this.neuralNumbers = allNeurals.size();
	}
	
	/**
	 * 创建神经元对象，只需指定神经元个数即可，自动将神经元输入值设置为默认值0.0
	 * @param neuralNumbers 定神经元个数 --> int
	 */
	public NeuralLayer(int neuralNumbers) {
	    this.allNeurals = new LinkedList<Neural>();
	    for (int i = 0; i < neuralNumbers; i++) {
	        Neural tempNeural = new Neural(0.0);
	        this.allNeurals.add(tempNeural);
	    }
	    this.neuralNumbers = allNeurals.size();
	}

	/**
	 * 获取神经元个数
	 * @return 神经元个数 --> int
	 */
	public int size() {
		return neuralNumbers;
	}
	
	/**
	 * 获取所有的神经元
	 * @return 所有的神经元 --> List< Neural >
	 */
	public List<Neural> getAllNeurals() {
        return allNeurals;
    }
	
	/**
	 * 设置所有的神经元
	 * @param allNeurals 所有的神经元 --> List< Neural >
	 */
	public void setAllNeurals(List<Neural> allNeurals) {
        this.allNeurals = allNeurals;
    }
	
	@Override
	public String toString() {
		String layer = "[";
		for (int i = 0; i < size(); i++) {
		    layer += allNeurals.get(i).toString();
		    if (i < size() - 1) {
		        layer += "，";
		    } 
		}
		layer = layer + "]";
		return layer;
	}

    @Override
    public Iterator<Neural> iterator() {
        return new Iterator<Neural>() {
            private int firstIndex = 0;
            
            @Override
            public boolean hasNext() {
                return firstIndex < allNeurals.size();
            }

            @Override
            public Neural next() {
                return allNeurals.get(firstIndex++);
            }
            
        };
    }
    
    public static void main(String[] args) {
        // 创建神经层方式一：
        NeuralLayer inputLayer = new NeuralLayer(10);
        System.out.println(inputLayer);
        
        // 创建神经层方式一：
        List<Neural> allNeural = new LinkedList<Neural>();
        for (int i = 0; i < 10; i++) {
            allNeural.add(new Neural(1.0));
        }
        NeuralLayer hiddenLayer = new NeuralLayer(allNeural);
        System.out.println(hiddenLayer);
        
        // 神经层的for-each用法
        for (Neural neural : hiddenLayer) {
            System.out.println(neural);
        }
    }
}
