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
 * File: Sigmoid Activation Function
 * Description: Activates inputs using a sigmoid function
 *
 * ****************************************
 */
package hw1;

/**
 *
 * @author cld028
 */
public class SigmoidActivationFunction implements ActivationFunction {

    /**
     *
     * @param netInput - total input from previous layer
     * @return
     */
    @Override
    public double calcOutput(double netInput) {
        double outputVal = 1 / (1 + Math.exp(-netInput));
        return outputVal;
    }

    /**
     *
     * @param netInput - total input from previous layer
     * @return
     */
    @Override
    public double calcDervOutput(double netInput) {
    }
}
