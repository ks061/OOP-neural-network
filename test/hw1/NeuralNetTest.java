/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 7:09:25 PM
*
* Project: csci205_proj_hw
* Package: hw1
* File: NeuralNetTest
* Description: JUnit tests for the class NeuralNet
*
* ****************************************
 */
package hw1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 * JUnit tests for the class NeuralNet
 *
 * @author lts010, ks061
 */
public class NeuralNetTest extends TestCase {

    /**
     * Configuration for the neural network
     */
    private ConfigObject config;

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
        this.config = new ConfigObject(2, 1, 1, 2, 0.001, weights,
                                       thetas, ProgramMode.TEST); //create the config object, but not the data
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of a thrown exception in NeuralNet called
     * NeuralNetConstructionException
     *
     * @throws java.io.FileNotFoundException
     * @author lts010, ks061
     */
    public void testNoDataConstructionException() throws FileNotFoundException {
        try {
            double[][] data = {};
            NeuralNet myNet = new NeuralNet(data, config); //create the neural net with no data, which should throw an exception
            fail("Improper checking for lack of data");
        } catch (NeuralNetConstructionException expected) {
        }
    }

    /**
     * Test of a thrown exception in NeuralNet called
     * NeuralNetConstructionException
     *
     * @throws java.io.FileNotFoundException
     * @author lts010, ks061
     */
    public void testInconsistentDataException() throws FileNotFoundException {
        try {
            double[][] data = {{0.0, 1.0}, {1.0}}; //create the neural net with inconsistent amounts of data for each epoch, which should throw an exception

            NeuralNet myNet = new NeuralNet(data, config);
            fail("Improper checking for mismatched rows of data");
        } catch (NeuralNetConstructionException expected) {
        }
    }

}
