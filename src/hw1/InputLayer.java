/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw01
 * File: InputLayer
 * Description: Represents the input layer of a neural net
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

    private String layerID;

    private Layer nextLayer;

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
        ArrayList<Neuron> neurons = new ArrayList<>();
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron("Input " + i));
        }
        return neurons;
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
        this.layerID = layerID;
        return createNeurons(numNeurons);
    }

    /**
     * Fire neurons in layer
     *
     * @param inputVals
     */
    public void fireNeurons(double[] inputVals) {
        // TODO -- integrate with Neuron's fire() method
        for (Neuron neuron : neurons) {
            neuron.fire();
        }
    }

    /**
     * Connects this layer to the next layer
     *
     * @param nextLayer next layer
     */
    @Override
    public void connectLayer(Layer nextLayer) {
        for (Neuron neuron : this.neurons) {
            for (Edge edge : neuron.getOutEdges()) {
                for (Neuron nextNeuron : nextLayer.neurons) {
                    edge.setFrom(neuron);
                    edge.setTo(nextNeuron);
                }
            }
        }
        this.nextLayer = nextLayer;
        nextLayer.setPrevLayer(this);
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
