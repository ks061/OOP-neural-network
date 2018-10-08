/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 10, 2016
 * Time: 12:55:36 PM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: InputLayer
 * Description:
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 *
 * @author cld028
 */
public class InputLayer extends Layer {

    InputLayer(int numNeurons) {
        super(numNeurons);
    }

    InputLayer(int numNeurons, String id) {
        super(numNeurons, id);
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {

    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @param layerID - A string-based identifier that can be used when creating
     * the neurons
     * @return - An array list of all newly created neurons
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID) {
    }

    /**
     * Fire neurons in layer
     *
     * @param inputVals
     */
    public void fireNeurons(double[] inputVals) {

    }

    /**
     *
     * @param nextLayer
     */
    @Override
    public void connectLayer(Layer nextLayer) {

    }

    /**
     *
     */
    public void learn() {
        throw new UnsupportedOperationException(
                "Input layer shouldn't learning!");
    }

    private void calculateErrors() {
        throw new UnsupportedOperationException(
                "Input layer shouldn't be calculating errors!");
    }

    @Override
    protected void updateWeights(ArrayList<Edge> oldEdges, double deltaWeight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
