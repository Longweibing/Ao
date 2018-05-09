package org.nn.component;

/**
 * 神经元<hr>
 * 创建方式如：<br>
 * Neural neural = new Neural(1.2125);// 输入参数为神经元的输入值，类型了double
 * @author Weibing Long<br>
 * @since 2017.11.23
 * 
 */
public class Neural {
    /**
     * 神经元的输入值
     */
	private double inputValue;
	
	/**
     * 神经元的输出值
     */
    private double outputValue;
    
    /**
     * 神经元的激活函数对象
     */
    private ActivationFunction activationFunction;
	
    /**
     * 创建输入值为inputValue的神经元
     * @param inputValue 神经元输入值
     */
	public Neural(double inputValue) {
		this.inputValue = inputValue;
	}
	
	/**
	 * 获取神经元输入值
	 * @return 神经元输入值 --> double
	 */
	public double getInputValue() {
		return inputValue;
	}

	/**
	 * 获取神经元输出值
	 * @return 神经元输出值 --> double
	 */
	public double getOutputValue() {
        return outputValue;
    }

	/**
	 * 设置神经元输出值
	 * @param outputValue 神经元输入值 --> double
	 */
    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }
    
    /**
     * 获取激活函数对象
     * @return 激活函数对象 --> ActivationFunction
     */
    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    /**
     * 设置激活函数对象
     * @param activationFunction 激活函数对象 --> ActivationFunction
     */
    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    @Override
	public String toString() {		
		return new String("[类型：Nueral；输入值：" + Double.toString(inputValue)
		           + "；输出值：" + outputValue + "]"
		       );		
	}
	
}
