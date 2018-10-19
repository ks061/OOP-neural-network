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
 * File: NeuralNetConstructionException
 * Description: This file contains NeuralNetConstructionException, which is a
 *              RuntimeException that is thrown when there is an issue in
 *              creating the neural network.
 *
 * ****************************************
 */
package hw02;

/**
 * NeuralNetConstructionException is a RuntimeException that is thrown when
 * there is an issue in creating the neural network.
 *
 * @author cld028, ks061, lts010
 */
public class NeuralNetConstructionException extends RuntimeException {

    /**
     * Constructor for NeuralNetConstructionException that takes in an
     * informative message
     *
     * @param message informative message for exception
     *
     * @author ks061, lts010
     */
    public NeuralNetConstructionException(String message) {
        super(message);
    }

}
