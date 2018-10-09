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
 * File: Layer
 * Description: Layer is an abstraction of a neural network layer
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * Layer is an abstraction of columns of neurons within a neural network;
 * neurons within a layer have similar functionality.
 *
 * @author cld028, lts010, ks061
 */
public abstract class Layer {

    /**
     * List of neurons in this layer
     */
    protected ArrayList<Neuron> neurons;

    /**
     * Link to next layer in the neural network
     */
    protected Layer nextLayer;

    /**
     * Link to previous layer in the neural network
     */
    protected Layer prevLayer;

    /**
     * Number of edges linking to the next layer
     */
    protected int numToEdges = 0;

    /**
     * Number of neurons in this layer
     */
    protected int numNeurons = 0;

    /**
     * String identifier of this layer
     */
    protected String layerID;

    /**
     * Index of this layer
     */
    protected int layerNum;

    /**
     * Number of output edges in the layer
     */
    private int numberOfEdges = 0;

    /**
     * Neural network this layer lies within
     */
    private NeuralNet neuralNet;

    /**
     * Explicit constructor that creates a layer with a particular number of
     * neurons.
     *
     * @param numNeurons number of neurons to create in this layer
     *
     * @author cld028
     */
    Layer(int numNeurons) {
        this.neurons = this.createNeurons(numNeurons);
    }

    /**
     * Explicit constructor that creates a layer with a particular number of
     * neurons
     *
     * @param numNeurons number of neurons to create in this layer
     * @param layerID identifier for the layer
     *
     * @author cld028
     */
    Layer(int numNeurons, String layerID, int layerNumber, NeuralNet nN) {
        this.neuralNet = nN;
        this.neurons = this.createNeurons(numNeurons, layerID, layerNumber);
    }

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons - total number of neurons to create
     * @return list of neurons in the layer
     *
     * @author cld028
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons);

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons - total number of neurons to create
     * @param layerID - string identifier for layer
     * @param LayerNumber
     * @return list of neurons in the layer
     *
     * @author cld028
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons,
                                                    String layerID,
                                                    int LayerNumber);

    /**
     * Connect the current layer to another layer (with this layer being on the
     * left)
     *
     * @param nextLayer - The right layer to which to connect
     *
     * @author cld028
     */
    public abstract void connectLayer(Layer nextLayer);

    /**
     * update the weights in the layer to the
     *
     * @param oldEdges
     * @param deltaWeight
     *
     * @author cld028
     */
    protected abstract void updateWeights(ArrayList<Edge> oldEdges,
                                          double deltaWeight);

    /**
     * Sets the previous layer
     *
     * @param prevLayer previous layer
     *
     * @author ks061, lts010
     */
    protected void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
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

    /**
     * Fires all neurons in the layer
     *
     * @param inputVals provides input for the neuron to be used when firing
     *
     * @author ks061, lts010
     */
    public abstract void fireNeurons(double[] inputVals);

    /**
     * Fires all neurons in the layer
     *
     * @author lts010, ks061
     */
    public abstract void fireNeurons();

    /**
     * Adjusts weights for nodes connecting to layer and then calls the learn
     * method of the previous layer
     *
     * @param deltaWeight change in weight from current layer
     *
     * @author ks061, lts010
     */
    public abstract void learn(double deltaWeight);

    /**
     * increments the number of edges in the layer
     *
     * @return an int that is to be used as the ID for a newly created edge
     *
     * @author lts010, ks061
     */
    protected int getNextEdgeNum() {
        return (++numberOfEdges);
    }

    /**
     * gets the number out edges in the layer, ie the number of edges from this
     * layer to the next layer.
     *
     * @return the number of out edges
     * @author lts010, ks061
     */
    protected int getNumberOfEdges() {
        return (numberOfEdges);
    }
}
