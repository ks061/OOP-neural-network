/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 9:49:37 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: OutputLayerTest
* Description: JUnit tests for the class OutputLayer
*
* ****************************************
 */
package hw02.Layer;

import hw02.ANNConfig;
import hw02.NeuralNet;
import hw02.ProgramMode;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class OutputLayer
 *
 * @author lts010, ks061
 */
public class OutputLayerTest extends TestCase {

    private NeuralNet myNet;
    private OutputLayer myLayer;
    private static final double EPSILON = 1.0E-4;

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
        thetas.get(1).add(0.0);
        thetas.get(1).add(0.0);
        thetas.get(2).add(0.0);
        ANNConfig config = new ANNConfig(2, 1, 1, 2, 0.001, 2, weights,
                                         thetas, ProgramMode.TEST);
        double[][] data = {{1, 1, 1}};
        double[] inputs = {1.0, 1.0};
        double[] outputs = {1.0};
        myNet = new NeuralNet(data, config);
        myLayer = (OutputLayer) myNet.getLayers().get(2);
        InputLayer inputLayer = (InputLayer) myNet.getLayers().get(0);
        inputLayer.setInputs(inputs);
        myLayer.setTargetOutputs(outputs);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createNeurons method, of class OutputLayer.
     *
     * @author lts010, ks061
     */
    @Test
    public void testCreateNeurons() {
        System.out.println("createNeurons");
        assertTrue(myLayer.getNeurons().size() == 1); //the layer should only have 1 neuron
    }

    /**
     * Test of calculateSumOfSquaredErrors method, of class OutputLayer.
     *
     * @author lts010, ks061
     */
    @Test
    public void testCalculateSumOfSquaredErrors() {
        System.out.println("calculateSumOfSquaredErrors");
        myNet.getLayers().get(0).fireNeurons();
        myLayer.learn();
        double expResult = 0.3285;
        assertEquals(expResult, myLayer.calculateSumOfSquaredErrors(), EPSILON);
    }

    /**
     * Test of learn method, of class OutputLayer.
     *
     * @author lts010, ks061
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        myNet.getLayers().get(0).fireNeurons();
        myLayer.learn();
        assertEquals(-0.0874, myNet.getWeight(1, 0), EPSILON); //used the example from page 13 on homework 1
        assertEquals(-0.486, myNet.getWeight(1, 1), EPSILON);
        assertEquals(-0.028, myNet.getTheta(2, 0), EPSILON);
    }

}
