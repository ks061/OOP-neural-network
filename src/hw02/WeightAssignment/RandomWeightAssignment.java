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
 * File: RandomWeightAssignment
 * Description: This file contains RandomWeightAssignment, which is a utility
 *              that contains different algorithms for generating random
 *              weights for the edges.
 *
 * ****************************************
 */
package hw02.WeightAssignment;

import java.util.Random;

/**
 * RandomWeightAssignment is a utility that contains different algorithms for
 * generating random weights for the edges.
 *
 * @author cld028, ks061, lts010
 */
public class RandomWeightAssignment implements WeightAssignment {

    /**
     * Gets a random weight from -0.5 to 0.5 (exclusive on the bounds)
     *
     * @return the new weight that is assigned to an initial edge
     *
     * @author cld028
     */
    @Override
    public double assignWeight() {
        Random rnd = new Random();
        boolean pos = rnd.nextBoolean();
        //Get random double 0 < rndNum < 0.5
        double rndNum = rnd.nextDouble();
        rndNum = rndNum / 2;
        if (pos) {
            return (rndNum);
        }
        else {
            return (-rndNum);
        }
    }

    /**
     * Gets a random weight from -2.4 divided by the number of input edges to
     * 2.4 divided by the number of input edges (exclusive on the bounds)
     *
     * @param numInputEdges number of input edges
     * @return the new weight that is assigned to an initial edge
     *
     * @author cld028, ks061, lts010
     */
    public double assignWeight(int numInputEdges) {
        Random rnd = new Random();
        boolean pos = rnd.nextBoolean();
        //Get random double 0 < rndNum < 1.0
        double rndNum = rnd.nextDouble();
        rndNum = rndNum * (2.4 / numInputEdges);
        if (pos) {
            return (rndNum);
        }
        else {
            return (-rndNum);
        }
    }

}
