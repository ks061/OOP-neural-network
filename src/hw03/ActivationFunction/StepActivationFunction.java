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
 * File: StepActivationFunction
 * Description: This file contains StepActivationFunction, which represents a
 *              step function used as an activation function.
 *
 * ****************************************
 */
package hw03.ActivationFunction;

/**
 * StepActivationFunction represents a step function used as an activation
 * function.
 *
 * @author cld028, ks061, lts010
 */
public class StepActivationFunction implements ActivationFunction {

    /**
     * Gets the value of the activation function evaluated at the net input
     *
     * @param netInput net input evaluated based off inputs from previous
     * neurons
     * @return the value of the activation function at <code>netInput</code>
     *
     * @author lts010, ks061
     */
    @Override
    public double calcOutput(double netInput) {
        if (netInput >= 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Gets the value of the derivative of the activation function evaluated at
     * the net input
     *
     * @param netInput net input evaluated based off inputs from previous
     * neurons
     * @return the value of the derivative of the activation function at
     * <code>netInput</code>
     *
     * @author ks061, lts010
     */
    @Override
    public double calcDervOutput(double netInput) {
        return 0;
    }
}
