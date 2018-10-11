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

    /**
     * Number of output edges in the layer
     */
    private int numOutEdges;

    /**
     * Link to next layer in the neural network
     */
    protected Layer nextLayer;

    /**
     * Explicit constructor that creates the input layer with a particular
     * number of neurons and an ID,
     *
     * @param numNeurons number of neurons to be generated in this layer
     * @param id identifier of the layer
     * @param layerNum the layer index number (always 0 for the input layer)
     * @param nN the neuralNet
     *
     * @author ks061, lts010
     */
    InputLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
        super(numNeurons, id, layerNum, nN);
    }

    /**
     * Creates and returns a list of input neurons
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
     *
     * @author ks061
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> createdNeurons = new ArrayList<>();

        Neuron neuronToAdd;
        for (int i = 0; i < numNeurons; i++) {
            neuronToAdd = new InputNeuron(i, this.layerNum,
                                          this.neuralNet);
            createdNeurons.add(neuronToAdd);
        }

        return createdNeurons;
    }

    /**
     * Populates the input neurons within this input layer with input values
     * from the training data.
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
        if (nextLayer instanceof InputLayer) {
            throw new NeuralNetConstructionException(
                    "Input layer cannot be linked to another input layer; only one input layer can exist in a neural network.");
        }

        for (Neuron neuron : this.neurons) {
            for (Neuron nextNeuron : nextLayer.neurons) {
                Edge edge = new Edge(this.getNeuralNet(), this.layerNum,
                                     getNextEdgeNum());
                neuron.getOutEdges().add(edge);
                edge.setFrom(neuron);
                nextNeuron.getInEdges().add(edge);
                edge.setTo(nextNeuron);
            }
        }
        this.nextLayer = nextLayer;
        if (nextLayer instanceof HiddenLayer) {
            ((HiddenLayer) nextLayer).setPrevLayer(this);
        }
        else if (nextLayer instanceof OutputLayer) {
            ((OutputLayer) nextLayer).setPrevLayer(this);
        }
    }

    /**
     * Sets set of input values
     *
     * @param inputs set of input values
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Does nothing; done learning once reached the input layer.
     *
     * @author cld028
     */
    @Override
    public void learn() {
        throw new UnsupportedOperationException(
                "Input layer shouldn't be learning!");
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
     * increments the number of edges in the layer
     *
     * @return an int that is to be used as the ID for a newly created edge
     *
     * @author lts010, ks061
     */
    protected int getNextEdgeNum() {
        return (numOutEdges++);
    }

    /**
     * Gets the number out edges in the layer, i.e. the number of edges from
     * this layer to the next layer.
     *
     * @return number of out edges
     * @author lts010, ks061
     */
    protected int getNumOutEdges() {
        return (this.numOutEdges);
    }

    /**
     * Sets the next layer
     *
     * @param nextLayer next layer
     *
     * @author ks061, lts010
     */
    protected void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

}
