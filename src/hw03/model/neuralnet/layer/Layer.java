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
 * File: Layer
 * Description: Layer is an abstraction of a neural network layer
 *
 * ****************************************
 */
package hw03.model.neuralnet.layer;

import hw03.model.neuralnet.Edge;
import hw03.model.neuralnet.NeuralNet;
import hw03.model.neuralnet.neuron.Neuron;
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
     * Constructor that creates a layer with a particular number of neurons and
     * an identifier for the layer.
     *
     * @param numNeurons number of neurons to create in this layer
     * @param layerID identifier for the layer
     * @param layerNum index of the layer amongst all of the layers within the
     * neural network
     * @param neuralNet neural network this layer is within
     *
     * @author cld028
     */
    public Layer(int numNeurons, String layerID, int layerNum,
                 NeuralNet neuralNet) {
        this.numNeurons = numNeurons;
        this.layerID = layerID;
        this.layerNum = layerNum;
        this.neuralNet = neuralNet;
        this.neurons = this.createNeurons(numNeurons);
    }

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons total number of neurons to create
     * @return list of neurons in the layer
     *
     * @author cld028
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons);

    /**
     * Connect the current layer to another layer (with this layer being on the
     * left)
     *
     * @param nextLayer right layer to which to connect
     *
     * @author cld028
     */
    public abstract void connectLayer(Layer nextLayer);

    /**
     * Fires all neurons in the layer to calculate their net input values
     *
     * @author lts010, ks061
     */
    public abstract void fireNeurons();

    /**
     * Adjusts weights for nodes connecting to layer through error calculation
     * and then calls the learn method of the previous layer (back propagation)
     *
     * @author ks061, lts010
     */
    public abstract void learn();

    /**
     * Gets a pointer to the neural network
     *
     * @return neural network
     *
     * @author lts010, ks061
     */
    public NeuralNet getNeuralNet() {
        return neuralNet;
    }

    /**
     * Gets list of neurons within the layer
     *
     * @return list of neurons within the layer
     *
     * @author lts010, ks061
     */
    public ArrayList<Neuron> getNeurons() {
        return neurons;
    }

    /**
     * Updates the weights for all of the out edges.
     *
     * @param weights the weight that should be assigned to the out edges.
     * @author lts010, ks061
     */
    public void updateOutEdgeWeights(ArrayList<Double> weights) {
        ArrayList<Edge> outEdges = new ArrayList<>();
        for (Neuron neuron : neurons) {
            outEdges.addAll(neuron.getOutEdges());
        }
        for (int i = 0; i < outEdges.size(); i++) {
            outEdges.get(i).setWeight(weights.get(i));
        }
    }

    /**
     * Updates the weights for all of the out edges.
     *
     * @return An ArrayList of the network values for the nodes in the layer.
     * @author lts010, ks061
     */
    public ArrayList<Double> getNetVals() {

        ArrayList<Double> netVals = new ArrayList<>();
        for (Neuron neuron : neurons) {
            netVals.add(neuron.getNetValue());
        }
        return netVals;
    }
}
