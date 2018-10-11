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
* Description:
*
* ****************************************
 */
package hw1;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class InputLayerTest extends TestCase {

    private NeuralNet myNet;
    private Layer myLayer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myNet = new NeuralNet();
        myLayer = myNet.getLayers().get(0);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createNeurons method, of class OutputLayer.
     */
    @Test
    public void testCreateNeurons() {
        System.out.println("createNeurons");
        assertTrue(myLayer.getNeurons().size() == 2);
    }

    /**
     * Test of connectLayer method, of class InputLayer.
     */
    @Test
    public void testConnectLayer() {
        assertTrue(myLayer.nextLayer == myNet.getLayers().get(1));
    }

}
