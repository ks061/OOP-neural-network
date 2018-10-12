/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw01
 * File: Neuron
 * Description: This file contains Neuron, which is an abstraction of a neuron
 *              in a neural network.
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * Neuron is an abstraction of a neuron in a neural network.
 *
 * @author cld028, ks061, lts010
 */
public abstract class Neuron {

    /**
     * Represents the index of the neuron within the input layer
     */
    private final int neuronNum;
    /**
     * Represents the index of the input layer this neuron lies within
     */
    private final int layerNum;
    /**
     * Represents the neural network this neuron lies within
     */
    private final NeuralNet neuralNet;

    /**
     * Represents the edges that serve as inputs to this neuron
     */
    private final ArrayList<Edge> inEdges;
    /**
     * Represents the edges that serve as outputs to this neuron
     */
    private final ArrayList<Edge> outEdges;

    /**
     * Represents the net value of this neuron
     */
    private double netValue;
    /**
     * Represents the activation function used by the neuron to calculate its
     * net output
     */
    private ActivationFunction activationFunction;

    /**
     * A default theta (threshold) value
     */
    public final static double DEFAULTTHETA = 0.1;

    /**
     * Constructor that creates a neuron with a particular identifier
     *
     * @param neuronNum numerical identifier for the neuron
     * @param layerNum numerical identifier for the layer it is in
     * @param nN the neural net the neuron is in
     *
     * @author ks061, lts010
     */
    public Neuron(int neuronNum, int layerNum, NeuralNet nN) {
        this.neuronNum = neuronNum;
        this.layerNum = layerNum;
        this.inEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
        this.neuralNet = nN;
        this.activationFunction = new SigmoidActivationFunction();
    }

    /**
     * Gets the index of this neuron within its layer
     *
     * @return index of this neuron within its layer
     *
     * @author ks061, lts010
     */
    public int getNeuronNum() {
        return neuronNum;
    }

    /**
     * Gets the index of the layer that this neuron lies within in the neural
     * network
     *
     * @return index of the layer that this neuron lies within in the neural
     * network
     *
     * @author ks061, lts010
     */
    public int getLayerNum() {
        return layerNum;
    }

    /**
     * Gets the neural network that this neuron lies within
     *
     * @return neural network that this neuron lies within
     *
     * @author ks061, lts010
     */
    public NeuralNet getNeuralNet() {
        return neuralNet;
    }

    /**
     * Gets the list of edges serving as input channels to the neuron
     *
     * @return list of edges serving as input channels to the neuron
     *
     * @author ks061, lts010
     */
    public ArrayList<Edge> getInEdges() {
        return inEdges;
    }

    /**
     * Gets the list of edges serving as output channels to the neuron
     *
     * @return list of edges serving as output channels to the neuron
     *
     * @author ks061, lts010
     */
    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }

    /**
     * Sets the net value for the neuron (used assign input values to neurons in
     * the input layer)
     *
     * @param netValue net value to set for this neuron
     *
     * @author ks061, lts010
     */
    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    /**
     * Gets the net value of the neuron
     *
     * @return net value of the neuron
     *
     * @author ks061, lts010
     */
    public double getNetValue() {
        return this.netValue;
    }

    /**
     * Gets the activation function this neuron will use to get its net value
     *
     * @return activation function this neuron will use to get its net value
     *
     * @author ks061, lts010
     */
    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    /**
     * Sets the activation function this neuron will use to get its net value
     *
     * @param activationFunction activation function this neuron will use to get
     * its net value
     *
     * @author ks061, lts010
     */
    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

}
