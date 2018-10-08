/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 9, 2016
 * Time: 6:31:37 PM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: OutputLayer
 * Description:
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
