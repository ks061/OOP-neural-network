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
* File: InputLayerTest
* Description: JUnit tests for the class InputLayer
*
* ****************************************
 */
package hw01;

import hw01.InputLayer;
import hw01.NeuralNet;
import hw01.ConfigObject;
import hw01.ProgramMode;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * JUnit tests for the class InputLayer
 *
 * @author lts010, ks061
 */
public class InputLayerTest extends TestCase {

    /**
     * Neural network
     */
    private NeuralNet myNet;
    /**
     * A layer in the neural network
     */
    private InputLayer myLayer;

    @Override
    public void setUp() throws Exception {
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
        myLayer = (InputLayer) myNet.getLayers().get(0);

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
        assertTrue(myLayer.getNeurons().size() == 2); //Input layer was constructed with two neurons
    }

    /**
     * Test of connectLayer method, of class InputLayer.
     *
     * @author lts010, ks061
     */
    @Test
    public void testConnectLayer() {
        assertTrue(myLayer.nextLayer == myNet.getLayers().get(1)); //if the layers connected properly then we should be able to refer to the next layer in the net this way
    }

}
