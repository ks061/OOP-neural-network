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
* Description:
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class HiddenLayerTest extends TestCase {

    private NeuralNet myNet;
    private HiddenLayer myLayer;

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
        myLayer = (HiddenLayer) myNet.getLayers().get(1);
        InputLayer inputLayer = (InputLayer) myNet.getLayers().get(0);
        inputLayer.setInputs(Arrays.copyOfRange(data, 0, config.getNumInputs()));
        OutputLayer outputLayer = (OutputLayer) myNet.getLayers().get(1);
        outputLayer.setTargetOutputs(Arrays.copyOfRange(
                data,
                config.getNumInputs(),
                data.length));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createNeurons method, of class HiddenLayer.
     */
    @Test
    public void testCreateNeurons() {
        System.out.println("createNeurons");
        assertTrue(myLayer.getNeurons().size() == 2);

    }

    /**
     * Test of connectLayer method, of class HiddenLayer.
     */
    @Test
    public void testConnectLayer() {
        System.out.println("connectLayer");
        assertTrue(myLayer.nextLayer == myNet.getLayers().get(2));

    }

    /**
     * Test of learn method, of class HiddenLayer.
     */
    @Test
    public void testLearn() {
        myNet.getLayers().get(0).fireNeurons();
        fail("lol");
    }

}
