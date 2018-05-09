package org.nn.component;

import java.util.*;

/**
 * 神经网络
 * 
 * @author 龙卫兵
 * @since 2017.11.24
 * 
 */
public class NeuralNetwork implements Iterable<NeuralLayer> {
    /**
     * 神经网络所有的层
     */
	private List<NeuralLayer> allLayers;

    /**
	 * 神经网络的层数
	 */
	private int layerCount;
	
	/**
	 * 构建神经网络
	 * @param allLayers 所有的神经层--> List< NeuralLayer >
	 */
	public NeuralNetwork(List<NeuralLayer> allLayers) {
	    this.allLayers = allLayers;
	    this.layerCount = allLayers.size();
	}
	
	/**
	 * 构建神经网络
	 * @param allLayerNeuralCount 神经网络拓扑 --> int[]
	 */
	public NeuralNetwork(int[] allLayerNeuralCount) {
	    allLayers = new ArrayList<NeuralLayer>(3);
	    for (int i = 0; i < allLayerNeuralCount.length; i++) {
	        int neuralCount = allLayerNeuralCount[i];
	        NeuralLayer neural = new NeuralLayer(neuralCount);
	        allLayers.add(neural);
	    }
	    this.layerCount = allLayerNeuralCount.length;
	}
	
	/**
	 * 神经网络层数
	 * @return 神经网络层数 --> int
	 */
	public int size() {
	    return this.layerCount;
	}
	
	/**
	 * 获取神经网络所有的层
	 * @return 神经网络所有的层 --> List< NeuralLayer >
	 */
	public List<NeuralLayer> getAllLayers() {
        return allLayers;
    }

	/**
	 * 设置神经网络所有的层
	 * @param allLayers 神经网络所有的层 --> List< NeuralLayer >
	 */
    public void setAllLayers(List<NeuralLayer> allLayers) {
        this.allLayers = allLayers;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{" + "\n");
        sb.append("类型：NeuralNetwork；层数：" + layerCount + "；\n");
        for (int i = 0; i < layerCount; i++) {
            sb.append("第" + i + "层神经元个数：" + allLayers.get(i).size() + "\n");
        }
        for (int i = 0; i < layerCount; i++) {
            sb.append(allLayers.get(i).toString() + "\n");
        }
        sb.append("}");
        return sb.toString();
        
    }

    @Override
    public Iterator<NeuralLayer> iterator() {
        return new Iterator<NeuralLayer>() {
            private int firstIndex;
            
            @Override
            public boolean hasNext() {
                return firstIndex < layerCount;
            }

            @Override
            public NeuralLayer next() {
                return allLayers.get(firstIndex++);
            }  
        };
    }
    
    /**
     * 返回可迭代的神经网络：从输出层到输入层（逆序）<br>
     * 这里类似于适配器模式
     * @return 可迭代对象 --> Iterable< NeuralLayer >
     */
    public Iterable<NeuralLayer> reverse() {
        return new Iterable<NeuralLayer>() {
            @Override
            public Iterator<NeuralLayer> iterator() {
                return new Iterator<NeuralLayer>() {
                    private int lastIndex = layerCount - 1;
                    
                    @Override
                    public boolean hasNext() {
                        return lastIndex >= 0;
                    }

                    @Override
                    public NeuralLayer next() {
                        return allLayers.get(lastIndex--);
                    }
                };
            }            
        };  
    }
    
    public static void main(String[] args) {     
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[] {2, 3, 4});
        System.out.println(neuralNetwork);
        
        // for-each遍历
        for (NeuralLayer neuralLayer : neuralNetwork) {
            System.out.println(neuralLayer);
        }
        
     // for-each逆序遍历
        for (NeuralLayer neuralLayer : neuralNetwork.reverse()) {
            System.out.println(neuralLayer);
        }
    }
}
