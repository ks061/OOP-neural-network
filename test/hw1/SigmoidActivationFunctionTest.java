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
* Description:
*
* ****************************************
 */
package hw1;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class SigmoidActivationFunctionTest {

    public SigmoidActivationFunctionTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcOutput method, of class SigmoidActivationFunction.
     */
    @Test
    public void testCalcOutput() {
        System.out.println("calcOutput");
        double netInput = 2;
        SigmoidActivationFunction instance = new SigmoidActivationFunction();
        double expResult = 1 / (1 + Math.exp(-1 * 2));
        double result = instance.calcOutput(netInput);
        double error = 1.0E-12;
        assertEquals(expResult, result, error);
    }

    /**
     * Test of calcDervOutput method, of class SigmoidActivationFunction.
     */
    @Test
    public void testCalcDervOutput() {
        System.out.println("calcDervOutput");
        double netInput = 2;
        SigmoidActivationFunction instance = new SigmoidActivationFunction();
        double expResult = instance.calcOutput(2) * (1 - instance.calcOutput(2));
        double result = instance.calcDervOutput(netInput);
        double error = 1.0E-12;
        assertEquals(expResult, result, error);

    }

}
