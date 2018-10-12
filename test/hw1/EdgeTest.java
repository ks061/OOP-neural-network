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
* Description:
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class EdgeTest extends TestCase {

    private NeuralNet myNet;
    private Edge myEdge;

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
                                               thetas, ProgramMode.TRAINING);
        double[][] data = {{1, 1, 1}};
        myNet = new NeuralNet(config, data);
        myEdge = myNet.getLayers().get(1).getNeurons().get(1).getOutEdges().get(
                0);
        myEdge.getFrom().setNetValue(2);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getWeightedValue method, of class Edge.
     */
    @Test
    public void testGetWeightedValue() {
        System.out.println("getWeightedValue");
        double result = myEdge.getWeightedValue();
        double expResult = -1.0;
        double error = 1.0E-12;
        assertEquals(expResult, result, error);
    }

    /**
     * Test of learn method, of class Edge.
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        double errorGradient = 2.0;
        double expResult = 0.30000000000000004;
        double error = 1.0E-12;
        myEdge.learn(errorGradient);
        double weight = (myEdge.getWeightedValue() / myEdge.getFrom().getNetValue());
        assertEquals(0.30000000000000004, weight, error);

        double weightTimesDelta = myEdge.getWeightTimesDelta();
        expResult = -1.0;
        assertEquals(expResult, weightTimesDelta, error);

        assertEquals(weight, myNet.getWeight(1, 1));
    }

}
