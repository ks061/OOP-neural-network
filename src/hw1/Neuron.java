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
 * Description: Represents the neuron in a neural net
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * Class for individual neuron elements
 *
 * @author cld028, ks061, lts010
 *
 */
public abstract class Neuron {

    private final int neuronNum;
    private final int layerNum; //the number for the layer that is neuron is in.
    private final NeuralNet neuralNet;

    private final ArrayList<Edge> inEdges;
    private final ArrayList<Edge> outEdges;

    private double netValue;
    private ActivationFunction activationFunction;

    /**
     * A default theta (threshold) value
     */
    public final static double DEFAULTTHETA = 0.1;

    /**
     * Explicit constructor that creates a neuron with a particular identifier
     *
     * @param neuronNum - numerical identifier for the neuron
     * @param layerNum - numerical identifier for the layer it's in
     * @param nN - the neural net the neuron is in
     *
     * @author ks061, lts010
     */
    Neuron(int neuronNum, int layerNum, NeuralNet nN) {
        this.neuronNum = neuronNum;
        this.layerNum = layerNum;
        this.inEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
        this.activationFunction = new SigmoidActivationFunction();
        this.neuralNet = nN;
    }

    /**
     * Gets the index of this neuron within its layer
     *
     * @return index of this neuron within its layer
     */
    public int getNeuronNum() {
        return neuronNum;
    }

    /**
     * Gets the index of the layer within the neural network
     *
     * @return index of the layer within the neural network
     */
    public int getLayerNum() {
        return layerNum;
    }

    /**
     * Gets the neural network that this neuron lies within
     *
     * @return neural network that this neuron lies within
     */
    public NeuralNet getNeuralNet() {
        return neuralNet;
    }

    /**
     * Gets the list of edges serving as input channels to the neuron
     *
     * @return list of edges serving as input channels to the neuron
     *
     * @author ks061
     */
    public ArrayList<Edge> getInEdges() {
        return inEdges;
    }

    /**
     * Gets the list of edges serving as output channels to the neuron
     *
     * @return list of edges serving as output channels to the neuron
     *
     * @author ks061
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
     * @author ks061
     */
    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    /**
     * Gets the net value of the neuron
     *
     * @return net value of the neuron
     *
     * @author ks061
     */
    public double getNetValue() {
        return this.netValue;
    }

    /**
     * Gets the activation function this neuron will use to get its net value
     *
     * @return activation function this neuron will use to get its net value
     */
    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    /**
     * Sets the activation function this neuron will use to get its net value
     *
     * @param activationFunction activation function this neuron will use to get
     * its net value
     */
    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

}
