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
* Description: JUnit tests for the class HiddenNeuron
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class HiddenNeuron
 *
 * @author lts010, ks061
 */
public class HiddenNeuronTest extends TestCase {

    private NeuralNet myNet;
    private HiddenNeuron myNeuron;
    private static final double EPSILON = 1.0E-12;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        weights.add(new ArrayList<>());
        weights.add(new ArrayList<>());
        weights.get(0).add(-0.3);
        weights.get(0).add(0.2);
        weights.get(0).add(0.1);
        weights.get(0).add(-0.2);
        weights.get(1).add(-0.1);
        weights.get(1).add(-0.5);
        ArrayList<ArrayList<Double>> thetas = new ArrayList<>();
        thetas.add(new ArrayList<>());
        thetas.add(new ArrayList<>());
        thetas.add(new ArrayList<>());
        thetas.get(1).add(0.1);
        thetas.get(1).add(0.1);
        thetas.get(2).add(0.1);
        ConfigObject config = new ConfigObject(2, 1, 1, 2, 0.001, weights,
                                               thetas, ProgramMode.TEST);
        double[][] data = {{1, 1, 1}};
        myNet = new NeuralNet(data, config); //create the neural net
        myNeuron = (HiddenNeuron) myNet.getLayers().get(1).getNeurons().get(0);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of fire method, of class HiddenNeuron.
     *
     * @author lts010, ks061
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        myNeuron.fire(); //fire the neuron
        double expResult = 0.47502081252106;
        assertEquals(expResult, myNeuron.getNetValue(), EPSILON); //see if the netValue changed properly

    }
}
