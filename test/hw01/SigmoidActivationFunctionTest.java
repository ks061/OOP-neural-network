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
* File: SigmoidActivationFunctionTest
* Description: JUnit tests for the SigmoidActivationFunction
*
* ****************************************
 */
package hw01;

import hw01.SigmoidActivationFunction;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the SigmoidActivationFunction
 *
 * @author lts010, ks061
 */
public class SigmoidActivationFunctionTest extends TestCase {

    /**
     * Allowed deviation from the expected value
     */
    private static final double EPSILON = 1.0E-12;

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of calcOutput method, of class SigmoidActivationFunction.
     *
     * @author lts010, ks061
     */
    @Test
    public void testCalcOutput() {
        System.out.println("calcOutput");
        double netInput = 2;
        SigmoidActivationFunction instance = new SigmoidActivationFunction();
        double expResult = 1 / (1 + Math.exp(-1 * netInput)); //the method should always equal this value
        double result = instance.calcOutput(netInput);
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of calcDervOutput method, of class SigmoidActivationFunction.
     *
     * @author lts010, ks061
     */
    @Test
    public void testCalcDervOutput() {
        System.out.println("calcDervOutput");
        double netInput = 2;
        SigmoidActivationFunction instance = new SigmoidActivationFunction();
        double expResult = instance.calcOutput(netInput) * (1 - instance.calcOutput(
                                                            netInput)); //the method should always equal this value
        double result = instance.calcDervOutput(netInput);
        assertEquals(expResult, result, EPSILON);

    }

}
