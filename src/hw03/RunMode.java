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
package hw03;

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
    RUN, PAUSE, STEP_INPUT, STEP_EPOCH, TERMINATE;
}
