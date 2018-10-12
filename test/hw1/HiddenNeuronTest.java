/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 5:17:45 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: HiddenNeuronTest
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
public class HiddenNeuronTest {

    public HiddenNeuronTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of fire method, of class HiddenNeuron.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        HiddenNeuron instance = null;
        instance.fire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of learn method, of class HiddenNeuron.
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        HiddenNeuron instance = null;
        instance.learn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
