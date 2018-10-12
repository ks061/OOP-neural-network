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
* Description: JUnit tests for the class StepActivationFunction
*
* ****************************************
 */
package hw1;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class StepActivationFunction
 *
 * @author lts010
 */
public class StepActivationFunctionTest extends TestCase {

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of calcOutput method, of class StepActivationFunction.
     *
     * @author lts010
     */
    @Test
    public void testCalcOutput() {
        System.out.println("calcOutput");
        StepActivationFunction instance = new StepActivationFunction();

        double netInput = 1.0;
        double expResult = 1.0;
        double result = instance.calcOutput(netInput); //returns 1 if netInput > 0
        assertEquals(expResult, result, 0.0);

        netInput = 0.0;
        expResult = 1.0;
        result = instance.calcOutput(netInput); //returns 1 if netInput = 0
        assertEquals(expResult, result, 0.0);

        netInput = -1.0;
        expResult = 0.0;
        result = instance.calcOutput(netInput); //returns 0 if netInput < 0
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calcDervOutput method, of class StepActivationFunction.
     *
     * @author lts010
     */
    @Test
    public void testCalcDervOutput() {
        System.out.println("calcDervOutput");
        double netInput = 0.0;
        StepActivationFunction instance = new StepActivationFunction();
        double expResult = 0.0;
        double result = instance.calcDervOutput(netInput); //should always return 0
        assertEquals(expResult, result, 0.0);
    }

}
