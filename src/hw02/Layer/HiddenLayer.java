/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw02
 * File: HiddenLayer
 * Description: This file contains HiddenLayer, which represents a hidden layer
 *              in a neural network.
 *
 * ****************************************
 */
package hw02.Layer;

import hw02.Edge;
import hw02.NeuralNet;
import hw02.NeuralNetConstructionException;
import hw02.Neuron.Neuron;
import hw02.Neuron.HiddenNeuron;
import java.util.ArrayList;

/**
 * HiddenLayer represents a hidden layer in a neural network.
 *
 * @author cld028, lts010, ks061
 */
public class HiddenLayer extends Layer {

    /**
     * Number of output edges in the layer
     */
    private int numOutEdges;

    /**
     * Link to previous layer in the neural network
     */
    protected Layer prevLayer;

    /**
     * Link to next layer in the neural network
     */
    protected Layer nextLayer;

    /**
     * Constructor that initializes a hidden layer with a particular number of
     * neurons, a string identifier for the layer, the index of the layer, and
     * the neural network the layer is within
     *
     * @param numNeurons number of neurons in the hidden layer
     * @param id string identifier for layer
     * @param layerNum nth layer
     * @param nN neural network hidden layer is within
     *
     * @author cld028
     */
    public HiddenLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
        super(numNeurons, id, layerNum, nN);
    }

    /**
     * Creates and returns a list of hidden neurons
     *
     * @param numNeurons number of neurons to be created within the layer
     * @return list of neurons created within the layer
     *
     * @author lts010, ks061
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> createdNeurons = new ArrayList<>();

        Neuron neuronToAdd;
        for (int i = 0; i < numNeurons; i++) {
            neuronToAdd = new HiddenNeuron(i, this.layerNum, this.neuralNet);
            createdNeurons.add(neuronToAdd);
        }

        return createdNeurons;
    }

    /**
     * Connects this layer to the next layer such that this layer and the next
     * layer have next and previous pointers respectively to one another
     *
     * @param nextLayer next adjacent layer within the neural network
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
     * Each neuron is called to learn, i.e. calculate and back propagate its
     * error data. Then, if the previous layer pointer of this layer refers to a
     * layer that is a hidden layer (rather than an input layer), it will call
     * the next layer to learn.
     *
     * @author ks061, lts010
     */
    public void learn() {
        for (Neuron neuron : this.neurons) {
            ((HiddenNeuron) neuron).learn();
        }
        if (prevLayer instanceof HiddenLayer) {
            ((HiddenLayer) prevLayer).learn();
        }
    }

    /**
     * Computes net input values for each neuron in the layer
     *
     * @author ks061, lts010
     */
    @Override
    public void fireNeurons() {
        for (Neuron neuron : this.neurons) {
            ((HiddenNeuron) neuron).fire();
        }
        nextLayer.fireNeurons();
    }

    /**
     * Increments the number of edges in the layer
     *
     * @return index to be used as the ID for a newly created edge
     *
     * @author lts010, ks061
     */
    public int getNextEdgeNum() {
        return (numOutEdges++);
    }

    /**
     * Gets the number output edges in the layer, i.e. the number of edges from
     * this layer to the next layer.
     *
     * @return number of output edges in this layer
     *
     * @author lts010, ks061
     */
    public int getNumOutEdges() {
        return (this.numOutEdges);
    }

    /**
     * Sets a pointer to the previous layer
     *
     * @param prevLayer previous layer
     *
     * @author ks061, lts010
     */
    public void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }

    /**
     * Sets a pointer to the next layer
     *
     * @param nextLayer next layer
     *
     * @author ks061, lts010
     */
    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }
}
