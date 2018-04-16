package org.multilabel;

import mulan.classifier.neural.model.ActivationFunction;

public class ActivationLU extends ActivationFunction {

	private static final long serialVersionUID = -6396167351046196963L;
	/** Maximum value of function */
    public final static double MAX = +1.0;
    /** Minimum value of function */
    public final static double MIN = -1.0;
	
	@Override
	public double activate(double input) {
		// TODO Auto-generated method stub
		return input;
	}

	@Override
	public double derivative(double input) {
		// TODO Auto-generated method stub
		return 1.0;
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
