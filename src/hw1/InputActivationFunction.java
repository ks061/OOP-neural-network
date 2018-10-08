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
 * File: InputActivationFunction
 * Description: Activation Functions for the Input
 *
 * ****************************************
 */
package hw1;

/**
 *
 * @author cld028, ks061, lts010
 */
public class InputActivationFunction implements ActivationFunction {

    /**
     *
     * Calculate the output of a neuron given the input value
     *
     * @param netInput - total input from previous layer
     * @return the output
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
     * Calculate the derivative for activation function
     *
     * @param netInput - total input from previous layer
     * @return
     */
    @Override
    public double calcDervOutput(double netInput) {
        return 0;
    }
}
