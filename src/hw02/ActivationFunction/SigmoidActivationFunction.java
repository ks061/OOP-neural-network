/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw02
 * File: Sigmoid Activation Function
 * Description: This file contains SigmoidActivationFunction, which represents
 *              a Sigmoid function used as an activation function.
 *
 * ****************************************
 */
package hw02.ActivationFunction;

/**
 * SigmoidActivationFunction represents the Sigmoid function used as an
 * activation function.
 *
 * @author cld028, lts010, ks061
 */
public class SigmoidActivationFunction implements ActivationFunction {

    /**
     * Gets the value of the Sigmoid function evaluated at the net input
     *
     * @param netInput net input evaluated based off inputs from previous
     * neurons
     * @return the value of the Sigmoid function at <code>netInput</code>
     *
     * @author lts010, ks061
     */
    @Override
    public double calcOutput(double netInput) {
        return 1 / (1 + Math.exp(-netInput));
    }

    /**
     * Gets the value of the derivative of the Sigmoid function evaluated at the
     * net input
     *
     * @param netInput net input evaluated based off inputs from previous
     * neurons
     * @return the value of the derivative of the Sigmoid function at
     * <code>netInput</code>
     *
     * @author ks061, ls010
     */
    @Override
    public double calcDervOutput(double netInput) {
        return calcOutput(netInput) * (1 - calcOutput(netInput));
    }
}
