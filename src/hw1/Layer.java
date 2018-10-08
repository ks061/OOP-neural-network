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

    Layer(int numNeurons) {
        this.neurons = this.createNeurons(numNeurons);
    }

    Layer(int numNeurons, String layerID) {
        this.neurons = this.createNeurons(numNeurons, layerID);
    }

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons - total number of neurons to create
     * @return
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons);

    /**
     * Create neurons that will reside in layer
     *
     * @param numNeurons - total number of neurons to create
     * @param layerID - string identifier for layer
     * @return
     */
    public abstract ArrayList<Neuron> createNeurons(int numNeurons,
                                                    String layerID);

    /**
     * Connect the current layer to another layer (with this layer being on the
     * left)
     *
     * @param nextLayer - The right layer to which to connect
     */
    public abstract void connectLayer(Layer nextLayer);

    /**
     * update the weights in the layer to the
     *
     * @param oldEdges
     * @param deltaWeight
     */
    protected abstract void updateWeights(ArrayList<Edge> oldEdges,
                                          double deltaWeight);

    void setPrevLayer(InputLayer aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
