/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 10, 2018
* Time: 3:34:56 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: EdgeTest
* Description: JUnit tests for the class Edge
*
* ****************************************
 */
package hw02;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class Edge
 *
 * @author lts010, ks061
 */
public class EdgeTest extends TestCase {

    /**
     * Neural network
     */
    private NeuralNet myNet;
    /**
     * An edge in the neural network connecting neurons in adjacent edges
     */
    private Edge myEdge;
    /**
     * Allowed deviation from expected value
     */
    private static final double EPSILON = 1.0E-6;

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
        ANNConfig config = new ANNConfig(2, 1, 1, 2, 0.001, 2, weights,
                                         thetas, ProgramMode.TEST);
        double[][] data = {{1, 1, 1}};
        myNet = new NeuralNet(data, config); //construct the neural net
        myEdge = myNet.getLayers().get(1).getNeurons().get(1).getOutEdges().get(
                0);
        myEdge.getFrom().setNetValue(2);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of learn method, of class Edge.
     *
     * @author lts010, ks061
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        double errorGradient = 0.1;
        double expResult = -.459999;
        myEdge.learn(errorGradient); //run the method
        double weight = (myEdge.getWeight());
        assertEquals(expResult, weight, EPSILON); // check to see if the weight changed properly
        double weightTimesDelta = myEdge.getWeightTimesErrorGradient();
        expResult = -0.046;
        assertEquals(expResult, weightTimesDelta, EPSILON); //check to see if weightTimesDelta changed properly
        assertEquals(weight, myNet.getWeight(1, 1));

        //now we test the momentum part of the learn method
        expResult = -0.399999;
        myEdge.learn(errorGradient);
        weight = (myEdge.getWeight());
        assertEquals(expResult, weight, EPSILON);
        weightTimesDelta = myEdge.getWeightTimesErrorGradient();
        expResult = -0.04;
        assertEquals(expResult, weightTimesDelta, EPSILON);
        assertEquals(weight, myNet.getWeight(1, 1));
    }

}
