package org.multilabel;

import mulan.classifier.neural.model.ActivationFunction;

public class ActivationReLU extends ActivationFunction {

	private static final long serialVersionUID = 2614063354873084553L;
	/** Maximum value of function */
    public final static double MAX = Double.MAX_VALUE;
    /** Minimum value of function */
    public final static double MIN = 0.0;

	@Override
	public double activate(double input) {
		// TODO Auto-generated method stub
		if (input <= 0.0) {
			return MIN;
		}
		return input;
	}

	@Override
	public double derivative(double input) {
		// TODO Auto-generated method stub
		if (input < 0.0) {
			return MIN;
		}
		return 1;
	}

	@Override
	public double getMax() {
		// TODO Auto-generated method stub
		return MAX;
	}

	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		return MIN;
	}

}
