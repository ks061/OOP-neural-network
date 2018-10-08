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
 * File: OutputLayer
 * Description: Represents an output layer of a neural net
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 *
 * @author cld028
 */
public class OutputLayer extends Layer {

    private double[] targetOutput;
    private double[] outputErrors;

    private Layer prevLayer;

    OutputLayer(int numNeurons) {
    }

    OutputLayer(int numNeurons, String id) {
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {

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
    }

    /**
     *
     * @param nextLayer
     */
    @Override
    public void connectLayer(Layer nextLayer) {
        throw new UnsupportedOperationException(
                "Output layer shouldn't be connecting!");
    }

    /**
     * Given a set of output, use learn to actually update parameters in NN
     */
    public void learn() {
    }

    private void calculateErrors() {
    }

}
