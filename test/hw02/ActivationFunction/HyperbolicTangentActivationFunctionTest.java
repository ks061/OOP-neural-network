/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 18, 2018
* Time: 10:15:05 PM
*
* Project: csci205_proj_hw
* Package: hw02.ActivationFunction
* File: HyperbolicTangentActivationFunctionTest
* Description: JUnit Tests for class HyperbolicTangentActivationFunction
*
* ****************************************
 */
package hw02.ActivationFunction;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit Tests for class HyperbolicTangentActivationFunction
 *
 * @author lts010, ks061
 */
public class HyperbolicTangentActivationFunctionTest extends TestCase {

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
     * Test of calcOutput method, of class HyperbolicTangentActivationFunction.
     */
    @Test
    public void testCalcOutput() {
        System.out.println("calcOutput");
        double netInput = 2;
        HyperbolicTangentActivationFunction instance = new HyperbolicTangentActivationFunction();
        double expResult = Math.tanh(netInput); //this should equal the function's output
        double result = instance.calcOutput(netInput);
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of calcDervOutput method, of class
     * HyperbolicTangentActivationFunction.
     */
    @Test
    public void testCalcDervOutput() {
        System.out.println("calcDervOutput");
        double netInput = 2;
        HyperbolicTangentActivationFunction instance = new HyperbolicTangentActivationFunction();
        double expResult = 1.0 / Math.pow(Math.cosh(netInput), 2.0); //this should equal the function's output
        double result = instance.calcDervOutput(netInput);
        assertEquals(expResult, result, EPSILON);
    }

}
