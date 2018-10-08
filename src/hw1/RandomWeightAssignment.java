/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 9, 2016
 * Time: 3:56:39 PM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: RandomWeightAssignment
 * Description:
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
    public static double assignWeight() {
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
