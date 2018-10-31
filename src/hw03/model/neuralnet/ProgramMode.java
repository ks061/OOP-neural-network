/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 9, 2018
* Time: 7:30:48 PM
*
* Project: csci205_proj_hw
* Package: hw02
* File: ProgramMode
* Description: This file contains the enumeration ProgramMode, which is an
*              enumeration that that helps distinguish whether the user wants
*              to run the neural network program in training mode or
*              classification mode (a test mode is also available)
*
* ****************************************
 */
package hw03.model.neuralnet;

/**
 * Enumeration that helps distinguish whether the user wants to run the neural
 * network program in training mode or classification mode (a test mode is also
 * available)
 *
 * @author ks061, lts010
 */
public enum ProgramMode {

    /**
     * State in which the neural network runs in training mode, where the neural
     * network should train the neural network using input and expected output
     */
    TRAINING,
    /**
     * State in which the neural network runs in classification mode, where the
     * neural network should make predictions using the neural network model
     * from sets of inputs
     */
    CLASSIFICATION,
    /**
     * State in which the neural network is being tested using JUnit tests
     */
    TEST;
}
