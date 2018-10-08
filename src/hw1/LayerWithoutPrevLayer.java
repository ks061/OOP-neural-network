/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 8, 2018
* Time: 12:44:11 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: LayerWithoutPrevLayer
* Description:
*
* ****************************************
 */
package hw1;

/**
 * LayerWithoutPrevLayer characterizes Layers without previous Layer pointers.
 *
 * @author ks061
 */
public interface LayerWithoutPrevLayer {

    /**
     * Assign input values to neurons in layer
     *
     * @param inputVals input values to assign to neurons in layer
     *
     * @author ks061
     */
    public abstract void fireNeurons(double[] inputVals);
}
