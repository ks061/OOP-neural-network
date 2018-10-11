/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 10, 2018
* Time: 3:34:58 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: StepActivationFunctionTest
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
public class StepActivationFunctionTest {

    public StepActivationFunctionTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcOutput method, of class StepActivationFunction.
     */
    @Test
    public void testCalcOutput() {
        System.out.println("calcOutput");
        StepActivationFunction instance = new StepActivationFunction();

        double netInput = 1.0;
        double expResult = 1.0;
        double result = instance.calcOutput(netInput);
        assertEquals(expResult, result, 0.0);

        netInput = 0.0;
        expResult = 1.0;
        result = instance.calcOutput(netInput);
        assertEquals(expResult, result, 0.0);

        netInput = -1.0;
        expResult = 0.0;
        result = instance.calcOutput(netInput);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calcDervOutput method, of class StepActivationFunction.
     */
    @Test
    public void testCalcDervOutput() {
        System.out.println("calcDervOutput");
        double netInput = 0.0;
        StepActivationFunction instance = new StepActivationFunction();
        double expResult = 0.0;
        double result = instance.calcDervOutput(netInput);
        assertEquals(expResult, result, 0.0);
    }

}
