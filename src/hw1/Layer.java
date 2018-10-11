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

// TODO: move methods from layer subclasses
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
     * Neural network this layer lies within
     */
    protected NeuralNet neuralNet;

    /**
     * Explicit constructor that creates a layer with a particular number of
     * neurons
     *
     * @param numNeurons number of neurons to create in this layer
     * @param layerID identifier for the layer
     *
     * @author cld028
     */
    Layer(int numNeurons, String layerID, int layerNum, NeuralNet neuralNet) {
        this.numNeurons = numNeurons;
        this.layerID = layerID;
        this.layerNum = layerNum;
        this.neuralNet = neuralNet;
        this.neurons = this.createNeurons(numNeurons);
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
     * Connect the current layer to another layer (with this layer being on the
     * left)
     *
     * @param nextLayer - The right layer to which to connect
     *
     * @author cld028
     */
    public abstract void connectLayer(Layer nextLayer);

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
     * @author ks061, lts010
     */
    public abstract void learn();

    /**
     * Gets the neural net
     *
     * @return the neural net
     * @author lts010, ks061
     */
    public NeuralNet getNeuralNet() {
        return neuralNet;
    }

    /**
     * Get list of neurons
     *
     * @return list of neurons
     * @author lts010
     */
    public ArrayList<Neuron> getNeurons() {
        return neurons;
    }

}

//    /**
//     * Explicit constructor that creates a layer with a particular number of
//     * neurons.
//     *
//     * @param numNeurons number of neurons to create in this layer
//     *
//     * @author cld028
//     */
//    Layer(int numNeurons) {
//        this.neurons = this.createNeurons(numNeurons);
//    }
//    /**
//     * Create neurons that will reside in layer
//     *
//     * @param numNeurons - total number of neurons to create
//     * @param layerID - string identifier for layer
//     * @param LayerNumber
//     * @return list of neurons in the layer
//     *
//     * @author cld028
//     */
//    public abstract ArrayList<Neuron> createNeurons(int numNeurons,
//                                                    String layerID,
//                                                    int LayerNumber);
//    /**
//     * Fires all neurons in the layer
//     *
//     * @param inputVals provides input for the neuron to be used when firing
//     *
//     * @author ks061, lts010
//     */
//    public abstract void fireNeurons(double[] inputVals);
//    /**
//     * update the weights in the layer to the
//     *
//     * @param oldEdges
//     * @param deltaWeight
//     *
//     * @author cld028
//     */
//    protected abstract void updateWeights(ArrayList<Edge> oldEdges,
//                                          double deltaWeight);
