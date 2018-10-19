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
* File: HiddenLayerTest
* Description: JUnit tests for the class HiddenLayer
*
* ****************************************
 */
package hw02.Layer;

import hw02.ANNConfig;
import hw02.NeuralNet;
import hw02.NeuralNetConstructionException;
import hw02.ProgramMode;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
import org.junit.Test;

/**
 * JUnit tests for the class HiddenLayer
 *
 * @author lts010, ks061
 */
public class HiddenLayerTest extends TestCase {

    /**
     * A neural network
     */
    private NeuralNet myNet;
    /**
     * A layer in the neural network
     */
    private HiddenLayer myLayer;
    /**
     * Allowed deviation from expected value
     */
    private static final double EPSILON = 1.0E-5;

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
        myNet = new NeuralNet(data, config); //create the neural net
        myLayer = (HiddenLayer) myNet.getLayers().get(1);
        InputLayer inputLayer = (InputLayer) myNet.getLayers().get(0);
        inputLayer.setInputs(inputs); //give inputs
        OutputLayer outputLayer = (OutputLayer) myNet.getLayers().get(2);
        outputLayer.setTargetOutputs(outputs); //give outputs

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createNeurons method, of class HiddenLayer.
     *
     * @author lts010, ks061
     */
    @Test
    public void testCreateNeurons() {
        System.out.println("createNeurons");
        assertTrue(myLayer.getNeurons().size() == 2); //the layer was constructed with two neurons

    }

    /**
     * Test of learn method, of class HiddenLayer.
     *
     * @author lts010, ks061
     */
    @Test
    public void testLearn() {
        System.out.println("learn");
        myNet.getLayers().get(0).fireNeurons(); //fire the neurons in the input layer
        myNet.getLayers().get(2).learn(); //make the output layer learn
        assertEquals(-0.3006, myNet.getWeight(0, 0), EPSILON); //on page 13 of hw1, the results of one round of backwards propagation were this
        assertEquals(0.19659, myNet.getWeight(0, 1), EPSILON);
        assertEquals(0.09939, myNet.getWeight(0, 2), EPSILON);
        assertEquals(-0.20341, myNet.getWeight(0, 3), EPSILON);
        assertEquals(0.0006, myNet.getTheta(1, 0), EPSILON);
        assertEquals(0.0034, myNet.getTheta(1, 1), EPSILON);
    }

    /**
     * Test of a thrown exception in HiddenLayer called
     * NeuralNetConstructionException
     *
     * @throws java.io.FileNotFoundException
     * @author lts010, ks061
     */
    public void testInputConnectionException() throws FileNotFoundException {
        try {
            myLayer.connectLayer(myNet.getLayers().get(0)); //hidden layers shouldn't connect to input layers
            fail("Improper checking for connection to InputLayer");
        } catch (NeuralNetConstructionException expected) {
        }
    }
}
