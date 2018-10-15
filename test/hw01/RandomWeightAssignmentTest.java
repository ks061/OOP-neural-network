/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 10, 2018
* Time: 3:34:57 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: RandomWeightAssignmentTest
* Description: JUnit tests for the class RandomWeightAssignment
*
* ****************************************
 */
package hw01;

import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

/**
 * JUnit tests for the class RandomWeightAssignment
 *
 * @author lts010, ks061
 */
public class RandomWeightAssignmentTest extends TestCase {

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of assignWeight method, of class RandomWeightAssignment.
     *
     * @author lts010, ks061
     */
    @Test
    public void testAssignWeight() {
        System.out.println("assignWeight");
        RandomWeightAssignment instance = new RandomWeightAssignment();
        double result = instance.assignWeight();
        assertTrue(-0.5 < result && result < 0.5); //although the number is random, it should always be between -0.5 and 0.5
    }

    /**
     * Test of assignWeight method, of class RandomWeightAssignment.
     *
     * @author lts010, ks061
     */
    @Test
    public void testAssignWeight_int() {
        System.out.println("assignWeight");
        int numInputEdges = 2;
        RandomWeightAssignment instance = new RandomWeightAssignment();
        double result = instance.assignWeight(numInputEdges);
        assertTrue(-1.2 < result && result < 1.2); //although the number is random, it should always be between -2.4/m and 2.4/m where m = numInputEdges

        numInputEdges = 3;
        result = instance.assignWeight(numInputEdges);
        assertTrue(-0.8 < result && result < 0.8);
    }

}
