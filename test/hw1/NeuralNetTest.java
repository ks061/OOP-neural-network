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
* Description:
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author logan
 */
public class NeuralNetTest extends TestCase {

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
                                       thetas, ProgramMode.TRAINING);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNoDataConstructionException() {
        try {
            double[][] data = {};
            NeuralNet myNet = new NeuralNet(config, data);
            fail("Improper checking for lack of data");
        } catch (NeuralNetConstructionException expected) {
        }
    }

    public void testInconsistentDataException() {
        try {
            double[][] data = {{0.0, 1.0}, {1.0}};
            NeuralNet myNet = new NeuralNet(config, data);
            fail("Improper checking for mismatched rows of data");
        } catch (NeuralNetConstructionException expected) {
        }
    }

}
