/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 8, 2018
* Time: 11:14:46 AM
*
* Project: csci205_proj_hw
* Package: hw1
* File: ActivationFunction
* Description: Interface for activation function
*
* ****************************************
 */
package hw1;

/**
 *
 * @author logan
 */
public interface ActivationFunction {

    public double calcOutput(double netInput);

    public double calcDervOutput(double netInput);
}
