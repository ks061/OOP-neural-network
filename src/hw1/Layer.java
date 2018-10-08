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
 * @author cld028
 */
public abstract class Layer {

    /**
     * Neurons within this layer
     */
    protected ArrayList<Neuron> neurons;

    /**
     * Explicit constructor that creates a layer with a particular number of
     * neurons.
     *
     * @param numNeurons number of neurons to create in this layer
     *
     * @author cld028
     */
    Layer(int numNeurons, int numOutEdges) {
        this.neurons = this.createNeurons(numNeurons, numOutEdges);
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
    Layer(int numNeurons, String layerID, int numOutEdges) {
        this.neurons = this.createNeurons(numNeurons, layerID, numOutEdges);
    }

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons - total number of neurons to create
     * @param numOutEdges - number of out edges each neuron should have
     * @return list of neurons in the layer
     *
     * @author cld028
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons,
                                                    int numOutEdges);

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons - total number of neurons to create
     * @param layerID - string identifier for layer
     * @param numOutEdges - number of out edges each neuron should have
     * @return list of neurons in the layer
     *
     * @author cld028
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons,
                                                    String layerID,
                                                    int numOutEdges);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public abstract void fireNeurons(double[] inputVals);

    public abstract void fireNeurons();
}
