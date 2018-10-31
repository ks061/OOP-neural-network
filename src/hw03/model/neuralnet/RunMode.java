/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 27, 2018
* Time: 9:57:20 PM
*
* Project: csci205_proj_hw
* Package: hw03
* File: RunMode
* Description:
*
* ****************************************
 */
package hw03.model.neuralnet;

/**
 * RunMode indicates the run mode of the Neural Net RUN - Normal run mode PAUSE
 * the NeuralNet is temporally pause usually to update parameters like mu, alpha
 * weights etc. STEP_INPUT Execution will pause at the end of each input loop.
 * STEP_EPOCH Execution will pause at the end of each epoch loop in training
 * mode. TERMINATE The NeuralNet will end execution
 *
 * @author lts010
 */
public enum RunMode {
    /**
     * State that represents the neural network running through all of the input
     * and/or output sets (either in training or classification mode) until
     * training or classification is fully finished.
     */
    RUN,
    /**
     * State that represents the neural network pausing all
     * forward-propagation/back-propagation processes
     */
    PAUSE,
    /**
     * State that represents the neural network running through a single input
     * and/or output set (either in training or classification mode)
     */
    STEP_INPUT,
    /**
     * State that represents the neural network running through all of the input
     * and/or output sets (either in training or classification mode) once
     * through.
     */
    STEP_EPOCH,
    /**
     * State that represents the termination of the operation of the neural
     * network.
     */
    TERMINATE;
}
