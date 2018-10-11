/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 10:48:30 AM
*
* Project: csci205_proj_hw
* Package: hw1
* File: OutputNeuron
* Description: This file contains OutputNeuron, which represents a neuron in
*              the output layer of a neural network.
*
* ****************************************
 */
package hw1;

/**
 * OutputNeuron represents a neuron in the output layer of a neural network.
 *
 * @author ks061
 */
public class OutputNeuron extends Neuron {

    private double theta;

    /**
     * Constructor for a neuron in the output layer of a neural network.
     *
     * @param neuronNum index of this neuron within the output layer
     * @param layerNum index of the output layer the neuron is within
     * @param nN neural network that this neuron is within
     *
     * @author ks061
     */
    public OutputNeuron(int neuronNum, int layerNum, NeuralNet nN) {
        super(neuronNum, layerNum, nN);
        if (layerNum == 0) {
            this.theta = DEFAULTTHETA;
        }
        else {
            this.theta = super.getNeuralNet().getTheta(layerNum, neuronNum);
        }
    }

    /**
     * Fire the neuron (essentially calculate the net input of neuron based on
     * weighted inputs from incoming edges and using an activation function)
     *
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
        System.out.println(
                "delta_y1(0) = " + errorGradient);
        setTheta(getTheta() + NeuralNet.alpha * -1 * errorGradient);
        System.out.println(
                "theta_y1(1) = " + this.theta);
        for (Edge edge : super.getInEdges()) {
            edge.learn(errorGradient);
        }
        // System.out.println("layer num: " + super.getLayerNum());
        // System.out.println("neuron num: " + super.getNeuronNum());
        // System.out.println("theta num: " + getTheta());
        super.getNeuralNet().storeTheta(super.getLayerNum(),
                                        super.getNeuronNum(), getTheta());
    }

    /**
     * Gets the theta value for this neuron
     *
     * @return theta value for this neuron
     */
    public double getTheta() {
        return theta;
    }

    /**
     * Sets the theta value for this neuron
     *
     * @param theta theta value to set for this neuron
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

}
