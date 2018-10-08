/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 8, 2018
* Time: 12:42:23 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: LayerWithPrevLayer
* Description:
*
* ****************************************
 */
package hw1;

/**
 * LayerWithPrevLayer characterizes Layers with previous Layer pointers.
 *
 * @author ks061
 */
public interface LayerWithPrevLayer {

    /**
     * Computes net input values for each neuron in the layer
     *
     * @author ks061
     */
    public abstract void fireNeurons();
}
