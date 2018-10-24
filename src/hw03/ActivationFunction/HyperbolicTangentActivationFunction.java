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
 * File: Hyperbolic Tangent Activation Function
 * Description: This file contains HyperbolicTangentActivationFunction, which represents
 *              a Hyperbolic Tangent function used as an activation function.
 *
 * ****************************************
 */
package hw03.ActivationFunction;

/**
 * HyperbolicTangentActivationFunction represents the Hyperbolic Tangent
 * function used as an activation function.
 *
 * @see
 * <a href = "https://towardsdatascience.com/activation-functions-and-its-types-which-is-better-a9a5310cc8f">
 * </a>
 *
 * @author lts010, ks061
 */
public class HyperbolicTangentActivationFunction implements ActivationFunction {

    /**
     * Gets the value of the Hyperbolic Tangent function evaluated at the net
     * input
     *
     * @param netInput net input evaluated based off inputs from previous
     * neurons
     * @return the value of the Hyperbolic Tangent function at
     * <code>netInput</code>
     *
     * @author lts010, ks061
     */
    @Override
    public double calcOutput(double netInput) {
        return Math.tanh(netInput);
    }

    /**
     * Gets the value of the derivative of the Hyperbolic Tangent function
     * evaluated at the net input
     *
     * @param netInput net input evaluated based off inputs from previous
     * neurons
     * @return the value of the derivative of the Hyperbolic Tangent function at
     * <code>netInput</code>
     *
     * @author ks061, ls010
     */
    @Override
    public double calcDervOutput(double netInput) {
        return 1 / Math.pow(Math.cosh(netInput), 2.0);
    }
}
