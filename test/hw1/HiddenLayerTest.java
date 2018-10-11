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

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author logan
 */
public class HiddenLayerTest extends TestCase {

    private NeuralNet myNet;
    private Layer myLayer;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myNet = new NeuralNet();
        myLayer = myNet.getLayers().get(1);
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

}
