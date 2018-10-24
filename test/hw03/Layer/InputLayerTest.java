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
package hw03.Layer;

import hw03.Layer.InputLayer;
import hw03.ANNConfig;
import hw03.NeuralNet;
import hw03.NeuralNetConstructionException;
import hw03.ProgramMode;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import junit.framework.TestCase;
import static junit.framework.TestCase.fail;
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
        ANNConfig config = new ANNConfig(2, 1, 1, 2, 0.001, 2, weights,
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
     * Test of a thrown exception in InputLayer called
     * NeuralNetConstructionException
     *
     * @throws java.io.FileNotFoundException
     * @author lts010, ks061
     */
    public void testInputConnectionException() throws FileNotFoundException {
        try {
            myLayer.connectLayer(myNet.getLayers().get(0)); //input layers shouldn't connect to input layers
            fail("Improper checking for connection to InputLayer");
        } catch (NeuralNetConstructionException expected) {
        }
    }

}
