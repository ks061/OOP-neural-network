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
* Description: JUnit tests for the class OutputNeuron
*
* ****************************************
 */
package hw03.Neuron;

import hw03.model.neuralnet.neuron.OutputNeuron;
import hw03.model.neuralnet.ANNConfig;
import hw03.model.neuralnet.NeuralNet;
import hw03.model.neuralnet.ProgramMode;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class OutputNeuron
 *
 * @author lts010, ks061
 */
public class OutputNeuronTest extends TestCase {

    /**
     * Neural network
     */
    private NeuralNet myNet;
    /**
     * Output neuron
     */
    private OutputNeuron myNeuron;
    /**
     * Allowed deviation from the expected value
     */
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
        thetas.get(2).add(0.3);
        ANNConfig config = new ANNConfig(2, 1, 1, 2, 0.001, 2, weights,
                                         thetas, ProgramMode.TEST);
        double[][] data = {{1, 1, 1}};
        myNet = new NeuralNet(data, config); //construct the neural net
        myNeuron = (OutputNeuron) myNet.getLayers().get(2).getNeurons().get(0);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of fire method, of class OutputNeuron.
     *
     * @author lts010, ks061
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        myNeuron.fire();
        System.out.println(myNeuron.getNetValue());
        double expResult = 0.425557483188341;
        assertEquals(expResult, myNeuron.getNetValue(), EPSILON); //see if the netValue changed properly
    }

}
