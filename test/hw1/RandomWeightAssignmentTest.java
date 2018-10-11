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
* Description:
*
* ****************************************
 */
package hw1;

import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class RandomWeightAssignmentTest {

    public RandomWeightAssignmentTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of assignWeight method, of class RandomWeightAssignment.
     */
    @Test
    public void testAssignWeight() {
        System.out.println("assignWeight");
        RandomWeightAssignment instance = new RandomWeightAssignment();
        double result = instance.assignWeight();
        assertTrue(-0.5 < result && result < 0.5);
    }

    /**
     * Test of assignWeight method, of class RandomWeightAssignment.
     */
    @Test
    public void testAssignWeight_int() {
        System.out.println("assignWeight");
        int numInputEdges = 2;
        RandomWeightAssignment instance = new RandomWeightAssignment();
        double result = instance.assignWeight(numInputEdges);
        assertTrue(-1.2 < result && result < 1.2);

        numInputEdges = 3;
        result = instance.assignWeight(numInputEdges);
        assertTrue(-0.8 < result && result < 0.8);
    }

}
