/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 10:50:11 AM
*
* Project: csci205_proj_hw
* Package: hw1
* File: InputNeuron
* Description: This file contains InputNeuron, which represents a neuron in the
*              the input layer of a neural network.
*
* ****************************************
 */
package hw1;

/**
 * InputNeuron represents a neuron in the input layer of a neural network.
 *
 * @author ks061
 */
public class InputNeuron extends Neuron {

    /**
     * Constructor for a neuron in the input layer of a neural network.
     *
     * @param neuronNum index of the neuron within the input layer
     * @param layerNum index of the input layer this neuron lies within
     * @param nN neural network this neuron lies within
     */
    public InputNeuron(int neuronNum, int layerNum, NeuralNet nN) {
        super(neuronNum, layerNum, nN);
    }

    /**
     * Sets the net value for the neuron (used assign input values to neurons in
     * the input layer)
     *
     * @param netValue value to set the neuron to
     *
     * @author ks061
     */
    public void setNetValue(double netValue) {
        super.setNetValue(netValue);
    }

}
