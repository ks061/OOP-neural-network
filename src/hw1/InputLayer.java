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
 * @author cld028, ks061
 */
public class InputLayer extends Layer {

    private double[] inputs;

    private int t;

    /**
     * Explicit constructor that creates the input layer with a particular
     * number of neurons and an ID, as well as specifying the set of inputs.
     *
     * @param numNeurons number of neurons to be generated in this layer
     * @param id identifier of the layer
     * @param inputs set of inputs
     *
     * @author ks061
     */
    InputLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
        super(numNeurons, id, layerNum, nN);
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
     *
     * @author ks061
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> neurons = new ArrayList<>();
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(i));
        }
        return neurons;
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @param layerID - A string-based identifier that can be used when creating
     * the neurons
     * @return - An array list of all newly created neurons
     *
     * @author ks061
     */
    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID) {
        this.layerID = layerID;
        return createNeurons(numNeurons);
    }

    /**
     * Fire neurons in input layer layer
     *
     *
     * @author ks061, lts010
     */
    @Override
    public void fireNeurons() {
        if (this.inputs.length != this.neurons.size()) {
            throw new NeuralNetConstructionException(
                    "The number of input values and number of neurons in the "
                    + "input layer of the neural network do not match.");
        }

        int numInputs = this.inputs.length;
        for (int i = 0; i < numInputs; i++) {
            this.neurons.get(i).setNetValue(this.inputs[i]);
        }
        nextLayer.fireNeurons();
    }

    /**
     * Connects this layer to the next layer
     *
     * @param nextLayer next layer
     *
     * @author ks061, lts010
     */
    @Override
    public void connectLayer(Layer nextLayer) {

        for (Neuron neuron : this.neurons) {
            for (Neuron nextNeuron : nextLayer.neurons) {
                Edge edge = new Edge(getNextEdgeNum());
                neuron.getOutEdges().add(edge);
                edge.setFrom(neuron);
                nextNeuron.getInEdges().add(edge);
                edge.setTo(nextNeuron);
            }
        }
        this.nextLayer = nextLayer;
        nextLayer.setPrevLayer(this);
    }

    /**
     * Does nothing; done learning once reached the input layer.
     *
     * @author cld028
     */
    public void learn() {
    }

    /**
     * Throws an UnsupportedOperationException instance because the input layer
     * should not be calculating errors.
     *
     * @author cld028
     */
    private void calculateErrors() {
        throw new UnsupportedOperationException(
                "Input layer shouldn't be calculating errors!");
    }

    /**
     * Throws an UnsupportedOperationException instance because the input layer
     * can't fire neurons without input values.
     *
     * @author cld028, ks061
     */
    @Override
    public void fireNeurons() {
        throw new UnsupportedOperationException(
                "Input layer can't fire neurons without input values");
    }

    @Override
    protected void updateWeights(ArrayList<Edge> oldEdges, double deltaWeight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Sets t, representing that the neural network is currently working through
     * the t-th row of input
     *
     * @param t number that represents that the neural network is currently
     * working through the t-th row of input
     *
     * @author ks061
     */
    public void setT(int t) {
        this.t = t;
    }

    void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

}

//    /**
//     * Explicit constructor that creates the input layer with a particular
//     * number of neurons.
//     *
//     * @param numNeurons number of neurons to be generated in this layer
//     *
//     * @author ks061
//     */
//    InputLayer(int numNeurons) {
//        super(numNeurons);
//    }
//
//    /**
//     * Explicit constructor that creates the input layer with a particular
//     * number of neurons and an ID.
//     *
//     * @param numNeurons number of neurons to be generated in this layer
//     * @param id identifier of the layer
//     *
//     * @author ks061
//     */
//    InputLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
//        super(numNeurons, id, layerNum, nN);
//    }
