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
 * File: HiddenLayer
 * Description: Represents the hidden layer of an artificial neural net
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * Class used to create hidden layers. You may want to use a variable to store
 * which learning algorithm you are using.
 *
 * @author cld028
 */
public class HiddenLayer extends Layer {

    private Learnable learnAlg;
    private double[] outputErrors;

    private Layer prevLayer;
    private Layer nextLayer;

    HiddenLayer(int numNeurons) {
        super(numNeurons);
        this.learnAlg = HiddenLayer.DEFAULTLEARNINGALG;
    }

    HiddenLayer(int numNeurons, String id) {
        super(numNeurons, id);
        this.learnAlg = HiddenLayer.DEFAULTLEARNINGALG;
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
     * @param nextLayer - Layer to the right of current layer
     */
    @Override
    public void connectLayer(Layer nextLayer) {
    }

    /**
     * Calculate the delta weights that will be applied later (during learning)
     */
    public void calculateWeightDeltas() {
    }

    /**
     * learn within your layer (dependent on the learning alg)
     */
    public void learn() {
    }

    private void calculateErrors() {
    }

}
