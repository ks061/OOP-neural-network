/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 10:48:30 AM
*
* Project: csci205_proj_hw
* Package: hw02
* File: OutputNeuron
* Description: This file contains OutputNeuron, which represents a neuron in
*              the output layer of a neural network.
*
* ****************************************
 */
package hw03.model.neuralnet.neuron;

import hw03.model.neuralnet.activationfunction.ActivationFunction;
import hw03.model.neuralnet.Edge;
import hw03.model.neuralnet.NeuralNet;

/**
 * OutputNeuron represents a neuron in the output layer of a neural network.
 *
 * @author ks061, lts010
 */
public class OutputNeuron extends Neuron {

    /**
     * Represents the theta value serving as a threshold and part of the input
     * to this neuron
     */
    private double theta;

    /**
     * Constructor for a neuron in the output layer of a neural network
     *
     * @param neuronNum index of this neuron within the output layer
     * @param layerNum index of the output layer the neuron is within
     * @param nN neural network that this neuron is within
     * @param activationFunction activation function this neuron will use to
     * calculate its net value
     *
     * @author ks061, lts010
     */
    public OutputNeuron(int neuronNum, int layerNum, NeuralNet nN,
                        ActivationFunction activationFunction) {
        super(neuronNum, layerNum, nN, activationFunction);
        if (layerNum == 0) {
            this.theta = DEFAULTTHETA;
        }
        else {
            this.theta = super.getNeuralNet().getTheta(layerNum, neuronNum);
        }
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
     * Calculates error gradient, adjusts the neuron's theta, back propagates
     * the error gradient to edges connecting to it, and stores its own theta
     * within the neural network.
     *
     * @param outputError error of the value of the output neuron
     *
     * @author lts010, ks061
     */
    public void learn(double outputError) {
        double errorGradient = 0;
        errorGradient = super.getNetValue() * (1 - super.getNetValue()) * outputError;
        setTheta(
                getTheta() + this.getNeuralNet().getAlpha() * -1 * errorGradient);
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
        return theta;
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
