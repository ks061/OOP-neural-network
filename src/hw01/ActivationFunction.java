/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 8, 2018
* Time: 11:14:46 AM
*
* Project: csci205_proj_hw
* Package: hw1
* File: ActivationFunction
* Description: This file contains ActivationFunction, which is an interface
*              that defines what functionalities each activation function used
*              in NeuralNet should have.
*
* ****************************************
 */
package hw01;

/**
 * ActivationFunction is an interface that defines what functionalities each
 * activation function used in NeuralNet should have.
 *
 * @author lts010, ks061
 */
public interface ActivationFunction {

    /**
     * Calculates the output based on the activation function
     *
     * @param netInput argument of the activation function, i.e. the net input
     * calculated for a neuron
     * @return value of the activation function at <code>netInput</code>
     *
     * @author ks061, lts010
     */
    public double calcOutput(double netInput);

    /**
     * Calculates the output based on the derivative of the activation function
     *
     * @param netInput argument of the activation function, i.e. the net input
     * calculated for a neuron
     * @return value of the derivative of the activation function at
     * <code>netInput</code>
     *
     * @author ks061, lts010
     */
    public double calcDervOutput(double netInput);
}
