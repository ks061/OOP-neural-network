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
 * Description: An abstraction of a neural net layer
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 *
 * @author cld028, lts010
 */
public abstract class Layer {

    /**
     * Neurons within this layer
     */
    protected ArrayList<Neuron> neurons;

    /**
     * Contains the next layer in the ANN
     */
    protected Layer nextLayer;

    /**
     * Contains the previous layer in the ANN
     */
    protected Layer prevLayer;

    /**
     * The number of edges linking to the next layer.
     */
    protected int numToEdges = 0;

    /**
     * The number of Neurons in this layer
     */
    protected int numNeurons = 0;

    /**
     *
     */
    protected String layerID;

    /**
     *
     */
    protected int layerNum;

    /**
     * Number of out edges in the layer, i
     */
    private int numberOfEdges = 0;

    /**
     * The neural net the layer refers to
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

    void setPrevLayer(InputLayer aThis) {
        this.prevLayer = aThis;
    }

    void setNextLayer(InputLayer aThis) {
        this.nextLayer = aThis;
    }

    /**
     * Fires all neuron s in the layer
     *
     * @param inputVals provides input for the neuron to be used when firing
     */
    public abstract void fireNeurons(double[] inputVals);

    /**
     * Fires all neuron s in the layer
     */
    public abstract void fireNeurons();

    /**
     * increments the number of edges in the layer
     *
     * @return an int that is to be used as the ID for a newly created edge
     *
     * @author lts010
     */
    protected int getNextEdgeNum() {
        return (++numberOfEdges);
    }

    /**
     * gets the number out edges in the layer, ie the number of edges from this
     * layer to the next layer.
     *
     * @return the number of out edges
     * @author lts010
     */
    protected int getNumberOfEdges() {
        return (numberOfEdges);
    }
}
