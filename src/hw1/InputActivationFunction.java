/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 10, 2016
 * Time: 2:58:06 PM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: InputActivationFunction
 * Description:
 *
 * ****************************************
 */
package hw1;

/**
 *
 * @author cld028
 */
public class InputActivationFunction {

    /**
     *
     * Calculate the output of a neuron given the input value
     *
     * @param netInput - total input from previous layer
     * @return the output
     * @author
     */
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
    public double calcDervOutput(double netInput) {
        return 0;
    }
}
