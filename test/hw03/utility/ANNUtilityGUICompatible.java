/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 18, 2018
* Time: 6:41:23 PM
*
* Project: csci205_proj_hw
* Package: hw02
* File: ANNUtilityGUICompatible
* Description: JUnit tests for the class ANNClient
*
* ****************************************
 */
package hw03.utility;

import hw01.Neuron;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class ANNClient
 *
 * @author lts010, ks061
 */
public class ANNUtilityGUICompatible extends TestCase {

    public ANNUtilityGUICompatible() {
    }

    @Override
    public void setUp() {
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of getRandomWeights method, of class ANNClient.
     *
     * @throws java.lang.Exception
     * @author lts010, ks061
     */
    @Test
    public void testGetRandomWeights() throws Exception {
        System.out.println("getRandomWeights");
        ArrayList<ArrayList<Double>> result = ANNUtility.getRandomWeights(2, 1,
                                                                          2,
                                                                          2);
        assertTrue(result.size() == 3); //there should be three layers of edge weights
        assertTrue(result.get(0).size() == 4); //first layer should have 4 edges
        assertTrue(result.get(1).size() == 4); //second layer should have 4 edges
        assertTrue(result.get(2).size() == 2); //third layer should have 2 edges
    }

    /**
     * Test of getDefaultListOfThetas method, of class ANNClient.
     *
     * @throws java.lang.Exception
     * @author lts010, ks061
     */
    @Test
    public void testDefaultListOfThetas() throws Exception {
        System.out.println("getDefaultListOfThetas");
        ArrayList<ArrayList<Double>> result = ANNUtility.getDefaultListOfThetas(
                1,
                2,
                2);
        assertTrue(result.size() == 4);
        assertTrue(result.get(1).get(0) == Neuron.DEFAULTTHETA); //thetas should be the default theta
        assertTrue(result.get(0).size() == 0); //first layer should have no thetas
        assertTrue(result.get(1).size() == 2); //second layer is a hidden layer, should have two thetas
        assertTrue(result.get(2).size() == 2); //third layer is also a hidden layer
        assertTrue(result.get(3).size() == 1); //last layer is a output layer with one neuron, has one theta
    }

}
