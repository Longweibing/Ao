package org.neuralNetwork.component;

/**
 * 神经网络激活函数
 * 
 * @author Weibing Long
 * @since 2017.11.24
 * 
 */
public class ActivationFunction {

	/**
	 * log-sigmoid激活函数
	 * @param x
	 * @return
	 */
    public double logSigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
    
    /**
     * tan-sigmoid激活函数
     * 
     * @param x
     * @return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x))
     */
    public static double tanh(double x) {
        return (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math
                .exp(-x));
    }
    
    /**
     * softplus激活函数
     * 
     * @param x
     * @return Math.log(1 + Math.exp(x))
     */
    public static double softPlus(double x) {
        return Math.log(1 + Math.exp(x));
    }
    
    /**
     * ReLU激活函数
     * 
     * @param x
     * @return Math.max(0, x)
     */
    public static double relu(double x) {
        return Math.max(0, x);
    }
    
}
