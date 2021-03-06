package org.multilabel;

import java.util.ArrayList;
import java.util.List;
import mulan.classifier.neural.model.ActivationFunction;
import mulan.classifier.neural.model.NeuralNet;
import mulan.classifier.neural.model.Neuron;

/**
 * The implementation of Back-Propagation Multi-Label Learning (BPMLL) algorithm for neural networks.
 * The algorithm uses weights decay regularization to avoid overfitting.
 * <br><br>
 * For more information see:
 * <br>
 * Zhang, M.L., Zhou, Z.H.: Multi-label neural networks with applications to functional genomics 
 * and text categorization. IEEE Transactions on Knowledge and Data Engineering 18 (2006) 1338-1351
 * 
 * @author Jozef Vilcek
 * @version 2012.02.27
 * @see NeuralNet
 */
public class BPxMLLAlgorithm {

    private final NeuralNet neuralNet;
    private final double weightsDecayCost;

    /**
     * Creates a {@link BPMLLAlgorithm} instance.
     *
     * @param neuralNet the neural network model to learn
     * @param weightsDecayCost the weights decay cost term used for regularization.
     *                  The value must be greater than 0 and no more than 1.
     */
    public BPxMLLAlgorithm(NeuralNet neuralNet, double weightsDecayCost) {

        if (neuralNet == null) {
            throw new IllegalArgumentException("The passed neural network model is null.");
        }
        if (weightsDecayCost <= 0 || weightsDecayCost > 1) {
            throw new IllegalArgumentException("The weights decay regularization cost term must be greater " +
                    "than 0 and no more than 1. The passed value is : " + weightsDecayCost);
        }
        this.neuralNet = neuralNet;
        this.weightsDecayCost = weightsDecayCost;
    }

    /**
     * Returns the neural network which is learned/updated by the algorithm.
     *
     * @return the neural network
     */
    public NeuralNet getNetwork() {
        return neuralNet;
    }

    /**
     * Returns the value of weights decay cost term used for regularization.
     *
     * @return the weights decay cost term
     */
    public double getWeightsDecayCost() {
        return weightsDecayCost;
    }

    /**
     * Performs one learning step with given input pattern and expected output values.
     * The function outputs the error for passed input pattern.<br>
     * The input is ignored by the algorithm (can not process) if the input example has
     * assigned either all or non of the labels.
     * In this case, the function returns {@link Double#NaN}.
     *
     * @param inputPattern the input pattern for the network
     * @param expectedLabels the ideal, expected values the network should output as a
     *        response for the given input. If the i-th label class belongs to the input pattern
     *        instance, then i-th value is +1, otherwise the value is -1.
     * @param learningRate the learning rate used to update the neural network weights
     * @return the error of the network response for the passed input
     *          or {@link Double#NaN} if the passed input can not be processed.
     */
    public double learn(double[] inputPattern, double[] expectedLabels, double learningRate) {

        if (inputPattern == null || inputPattern.length != neuralNet.getNetInputSize()) {
            throw new IllegalArgumentException("Specified input pattern vector is null " +
                    "or does not match the input dimension of underlying neural network model.");
        }
        if (expectedLabels == null || expectedLabels.length != neuralNet.getNetOutputSize()) {
            throw new IllegalArgumentException("Specified expected labels vector is null " +
                    "or does not match the output dimension of underlying neural network model.");
        }

        // 1. PROPAGATE SIGNAL
        double[] networkOutputs = neuralNet.feedForward(inputPattern);
        double[] outputErrors = computeErrorsForNeurons(networkOutputs, expectedLabels);
        if (outputErrors == null) {
            return Double.NaN;
        }

        double weightsSquareSum = 0;

        // 2. UPDATE WIGHTS - error back-propagation
        int layersCount = neuralNet.getLayersCount();
        for (int layerIndex = layersCount - 1; layerIndex > 0; layerIndex--) {

            // 2a. COMPUTE ERROR TERMS
            List<Neuron> layer = neuralNet.getLayerUnits(layerIndex);
            if (layerIndex == layersCount - 1) {
                computeOutputLayerErrorTerms(layer, outputErrors);
            } else {
                List<Neuron> nextLayer = neuralNet.getLayerUnits(layerIndex + 1);
                computeHiddenLayerErrorTerms(layer, nextLayer);
            }

            // 2b. GET OUTPUTS OF NEXT LAYER (from back-propagation perspective)
            List<Neuron> previousLayer = neuralNet.getLayerUnits(layerIndex - 1);
            double[] previousLayerOut = new double[previousLayer.size()];
            int previousLayerSize = previousLayer.size();
            for (int n = 0; n < previousLayerSize; n++) {
                previousLayerOut[n] = previousLayer.get(n).getOutput();
            }

            // compute sum of weights squares for weights decay regularization
            for (Neuron neuron : layer) {
                double[] weights = neuron.getWeights();
                for (double weight : weights) {
                    weightsSquareSum += weight * weight;
                }
            }

            // 2c. UPDATE WEIGHTS OF THE LAYER
            updateWeights(layer, previousLayerOut, learningRate);
        }

        double globalError = 0;
        for (double error : outputErrors) {
            globalError += Math.abs(error);
        }
        globalError += weightsDecayCost * 0.5 * weightsSquareSum;

        return globalError;
    }

    /**
     * Returns the error of the neural network for given input. This is value of error function
     * computed from network output value for given input and expected,
     * ideal output for given input.<br>
     * The input is ignored by the algorithm (can not process), if the input example has
     * assigned either all or non of labels.
     * In this case, the function returns {@link Double#NaN}.
     *
     * @param inputPattern the input pattern to be processed
     * @param expectedLabels the ideal, expected values the network should output as a
     *        response for the given input. If the ith label class belongs to the input pattern
     *        instance, then ith value is +1, otherwise the value is -1.
     * @return the error of the network response for the passed input
     *         or {@link Double#NaN} if the passed input can not be processed
     */
    public double getNetworkError(double[] inputPattern, double[] expectedLabels) {

        double[] networkOutputs = neuralNet.feedForward(inputPattern);
        double[] outputErrors = computeErrorsForNeurons(networkOutputs, expectedLabels);
        if (outputErrors == null) {
            return Double.NaN;
        }

        // compute sum of weights squares for weights decay regularization
        double weightsSquareSum = 0;
        int layersCount = neuralNet.getLayersCount();
        for (int layerIndex = 1; layerIndex < layersCount; layerIndex++) {
            List<Neuron> layer = neuralNet.getLayerUnits(layerIndex);
            for (Neuron neuron : layer) {
                double[] weights = neuron.getWeights();
                for (double weight : weights) {
                    weightsSquareSum += weight * weight;
                }
            }
        }

        double globalError = 0;
        for (double error : outputErrors) {
            globalError += Math.abs(error);
        }
        globalError += weightsDecayCost * 0.5 * weightsSquareSum;

        return globalError;
    }

    private void updateWeights(List<Neuron> layer, double[] layerInputs, double learningRate) {

        // w(t+1) = w(t) + a*dw(t) + m*w(t-1) ... dw(t) = e(t)*in(t)
        int layerSize = layer.size();
        for (int n = 0; n < layerSize; n++) {
            Neuron neuron = layer.get(n);
            double[] weights = neuron.getWeights();
            double error = neuron.getError();
            int inputsCount = layerInputs.length;

            double currentDelta = 0;
            for (int i = 0; i < inputsCount; i++) {
                currentDelta = learningRate * error * layerInputs[i];
                weights[i] += currentDelta - weightsDecayCost * weights[i];
            }
            // update bias weight (bias input is assumed to be +1)
            currentDelta = learningRate * error * neuron.getBiasInput();
            weights[inputsCount] += currentDelta - weightsDecayCost * weights[inputsCount];
        }
    }

    private void computeOutputLayerErrorTerms(List<Neuron> outLayer, double[] outputErrors) {

        int neuronsInLayer = outLayer.size();
        for (int n = 0; n < neuronsInLayer; n++) {
            Neuron neuron = outLayer.get(n);
            ActivationFunction layerFunction = neuron.getActivationFunction();
            double errorTerm = outputErrors[n] * layerFunction.derivative(neuron.getNeuronInput());
            neuron.setError(errorTerm);
        }
    }

    private void computeHiddenLayerErrorTerms(List<Neuron> layer, List<Neuron> nextLayer) {

        int neuronsInLayer = layer.size();
        int nextLayerNeuronsCount = nextLayer.size();

        for (int n = 0; n < neuronsInLayer; n++) {
            Neuron neuron = layer.get(n);
            double sum = 0;
            for (int k = 0; k < nextLayerNeuronsCount; k++) {
                Neuron nextNeuron = nextLayer.get(k);
                double[] nextNeuronWeights = nextNeuron.getWeights();
                sum += nextNeuron.getError() * nextNeuronWeights[n];
            }
            ActivationFunction neuronFunction = neuron.getActivationFunction();
            double errorTerm = sum * neuronFunction.derivative(neuron.getNeuronInput());
            neuron.setError(errorTerm);
        }
    }

    /**
     * Computes errors for each output neurons separately according formula: <br><br>
     *
     * Ei = --- (1/|Yi|*|Yi'|)*SUM{exp(-(Ci - Cl))}   ... if ith is from Yi set (is label)
     *      |               where l is from Yi'
     *      |
     *      |-- (-1/|Yi|*|Yi'|)*SUM{exp(-(Ck - Ci))}  ... if ith is from Yi' set (is not label)
     *                      where k is from Yi
     *
     * Note that these are not error terms used in network weights updates.
     *
     * @param networkOutputs the output of the network, which represents network belief for labels assignment
     * @param expectedLabels the ideal, expected output for labels assignment which network should output
     * @return error for each neuron or null if can not be computed (either Yi or Yi' is empty set)
     */
    private double[] computeErrorsForNeurons(double[] networkOutputs, double[] expectedLabels) {

        List<Integer> isLabel = new ArrayList<Integer>();
        List<Integer> isNotLabel = new ArrayList<Integer>();
        int labelsCount = expectedLabels.length;
        for (int index = 0; index < labelsCount; index++) {
            if (expectedLabels[index] == 1) {
                isLabel.add(index);
            } else {
                isNotLabel.add(index);
            }
        }

        // compute error terms for output neurons
        double[] neuronsErrors = null;
        if (isLabel.size() != 0 && isNotLabel.size() != 0) {
            neuronsErrors = new double[labelsCount];
            for (int index = 0; index < labelsCount; index++) {
                double error = 0;
//                if (isLabel.contains(index)) {
//                    for (int isNotLabelIndex : isNotLabel) {
//                        error += Math.exp(-((networkOutputs[index] + 1) / (networkOutputs[isNotLabelIndex] + 1) * (networkOutputs[index] - networkOutputs[isNotLabelIndex])));         
//                    }
//                } else {
//                    for (int isLabelIndex : isLabel) {
//                        error -= Math.exp(-((networkOutputs[isLabelIndex] + 1) / (networkOutputs[index] + 1)) * (networkOutputs[isLabelIndex] - networkOutputs[index]));
//                    }
//                }
                
                if (isLabel.contains(index)) {
                    for (int isNotLabelIndex : isNotLabel) {
                        error += Math.exp(-(networkOutputs[index] - networkOutputs[isNotLabelIndex]));         
                    }
                } else {
                    for (int isLabelIndex : isLabel) {
                        error -= Math.exp(-(networkOutputs[isLabelIndex] - networkOutputs[index]));
                    }
                }
                
//                if (isLabel.contains(index)) {
//                    for (int isNotLabelIndex : isNotLabel) {
//                        error += Math.exp(-(networkOutputs[index] + 1) / (networkOutputs[isNotLabelIndex] + 1));         
//                    }
//                } else {
//                    for (int isLabelIndex : isLabel) {
//                        error -= Math.exp(-((networkOutputs[isLabelIndex] + 1) / (networkOutputs[index] + 1)));
//                    }
//                }
                
                error *= 1.0 / (isLabel.size() * isNotLabel.size());
                neuronsErrors[index] = error;
            }
        }

        return neuronsErrors;
    }
}
