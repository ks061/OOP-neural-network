/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 9, 2016
 * Time: 7:43:16 PM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: SigmoidActivationFunction
 * Description:
 *
 * ****************************************
 */
package hw1;

/**
 *
 * @author cld028
 */
public class SigmoidActivationFunction {

    /**
     *
     * @param netInput - total input from previous layer
     * @return
     */
    public double calcOutput(double netInput) {
        double outputVal = 1 / (1 + Math.exp(-netInput));
        return outputVal;
    }

    /**
     *
     * @param netInput - total input from previous layer
     * @return
     */
    public double calcDervOutput(double netInput) {
    }
}
