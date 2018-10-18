/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 7, 2018
* Time: 10:16:57 PM
*
* Project: csci205_proj_hw
* Package: hw02
* File: WeightAssignment
* Description: This file contains WeightAssignment, which is an
*              interface/generalization of classes that provides weights for edges.
*
* ****************************************
 */
package hw02.WeightAssignment;

/**
 * WeightAssignment generalizes classes that provide weights to edges.
 *
 * @author lts010, ks061
 */
public interface WeightAssignment {

    /**
     * Provides weights to edge
     *
     * @return weight for edge
     *
     * @author ks061, lts010
     */
    public double assignWeight();
}
