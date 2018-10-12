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
package hw1;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class Edge
 *
 * @author lts010
 */
public class EdgeTest extends TestCase {

    private NeuralNet myNet;
    private Edge myEdge;
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
        ConfigObject config = new ConfigObject(2, 1, 1, 2, 0.001, weights,
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
     * @author lts010
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        double errorGradient = 0.1;
        double expResult = -.459999;
        myEdge.learn(errorGradient); //run the method
        double weight = (myEdge.getWeight());
        assertEquals(expResult, weight, EPSILON); // check to see if the weight changed properly

        double weightTimesDelta = myEdge.getWeightTimesDelta();
        expResult = -0.05;
        assertEquals(expResult, weightTimesDelta, EPSILON); //check to see if weightTimesDelta changed properly

        assertEquals(weight, myNet.getWeight(1, 1));
    }

}
