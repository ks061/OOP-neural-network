/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw01
 * File: RandomWeightAssignment
 * Description: Assign weights randomly between -0.5 and 0.5
 *
 * ****************************************
 */
package hw1;

import java.util.Random;

/**
 * There are many methods for decided how to assign weights (initially) to
 * Neural Networks Use this class to implement the random weights discussed as a
 * requirement
 *
 * @author cld028
 */
public class RandomWeightAssignment implements WeightAssignment {

    /**
     *
     * @return the new weight that is assigned to an initial edge
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
}
