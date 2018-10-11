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
* File: NeuronTest
* Description:
*
* ****************************************
 */
package hw1;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class NeuronTest extends TestCase {

    private Neuron myNeuron;
    private NeuralNet myNet;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myNet = new NeuralNet();
        myNeuron = myNet.getLayers().get(1).getNeurons().get(1);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of fire method, of class Neuron.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        myNeuron.fire();
        double expNetValue = 0.5;
        assertTrue(expNetValue == myNeuron.getNetValue());
    }

    /**
     * Test of learn method, of class Neuron.
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        myNeuron.learn();
        double expTheta = 0.0;
        assertEquals(myNeuron.getTheta(), expTheta, 1.0E-12);
    }
}
