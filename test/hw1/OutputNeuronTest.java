/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 5:01:04 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: OutputNeuronTest
* Description:
*
* ****************************************
 */
package hw1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class OutputNeuronTest {

    public OutputNeuronTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of fire method, of class OutputNeuron.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        OutputNeuron instance = null;
        instance.fire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of learn method, of class OutputNeuron.
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        double outputError = 0.0;
        OutputNeuron instance = null;
        instance.learn(outputError);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
