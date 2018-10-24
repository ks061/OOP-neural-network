/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 10:50:17 AM
*
* Project: csci205_proj_hw
* Package: hw02
* File: HiddenNeuron
* Description: The file contains HiddenNeuron, which represents a neuron in a
*              hidden layer of a neural network.
*
* ****************************************
 */
package hw03.Neuron;

import hw03.ActivationFunction.ActivationFunction;
import hw03.Edge;
import hw03.NeuralNet;

/**
 * HiddenNeuron represents a neuron in a hidden layer of a neural network.
 *
 * @author ks061, lts010
 */
public class HiddenNeuron extends Neuron {

    /**
     * Represents the theta value serving as a threshold and part of the input
     * to this neuron
     */
    private double theta;

    /**
     * Constructor for a neuron in a hidden layer of a neural network
     *
     * @param neuronNum index of the neuron within a hidden layer
     * @param layerNum index of the hidden layer this neuron lies within
     * @param nN neural network this neuron lies within
     * @param activationFunction activation function this neuron will use to
     * calculate its net value
     *
     * @author ks061, lts010
     */
    public HiddenNeuron(int neuronNum, int layerNum, NeuralNet nN,
                        ActivationFunction activationFunction) {
        super(neuronNum, layerNum, nN, activationFunction);
        this.theta = super.getNeuralNet().getTheta(layerNum, neuronNum);
    }

    /**
     * Fires the neuron; calculates the net input of the neuron based on
     * weighted inputs from incoming edges and obtaining the value of this net
     * input from the activation function defined in the Neuron class.
     *
     * @author lts010, ks061
     */
    public void fire() {
        double net = 0.0;
        for (Edge inEdge : super.getInEdges()) {
            net += inEdge.getWeightedValue();
        }
        net -= this.theta;
        super.setNetValue(super.getActivationFunction().calcOutput(net));
    }

    /**
     * Calculates the error gradient by weighting the error gradients back
     * propagated by its output edges, adjusts the neuron's theta value, back
     * propagates the error gradient to edges connecting to it, and stores its
     * own theta within the neural network.
     *
     * @author lts010, ks061
     */
    public void learn() {
        double errorGradient = 0;
        double weightedErrorGradients = 0;
        for (Edge edge : super.getOutEdges()) {
            weightedErrorGradients += edge.getWeightTimesErrorGradient();
        }

        errorGradient = super.getNetValue() * (1 - super.getNetValue()) * weightedErrorGradients;
        setTheta(getTheta() + NeuralNet.alpha * -1 * errorGradient);

        for (Edge edge : super.getInEdges()) {
            edge.learn(errorGradient);
        }

        super.getNeuralNet().storeTheta(super.getLayerNum(),
                                        super.getNeuronNum(), getTheta());
    }

    /**
     * Gets the theta value for this neuron
     *
     * @return theta value for this neuron
     *
     * @author ks061, lts010
     */
    public double getTheta() {
        return this.theta;
    }

    /**
     * Sets the theta value for this neuron
     *
     * @param theta theta value to set for this neuron
     *
     * @author ks061, lts010
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }
}
