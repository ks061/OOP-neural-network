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

    private String layerID;

    private double[] targetOutput;
    private double[] outputErrors;

    private int t;

    OutputLayer(int numNeurons) {
        super(numNeurons);
    }

    OutputLayer(int numNeurons, String id) {
        super(numNeurons, id);
    }

    /**
     * Explicit constructor that creates a particular number of neurons in the
     * layer, sets an identifier for the layer, and passes in the target output
     * values from data set.
     *
     * @param numNeurons number of neurons to create in the layer
     * @param id identifier for the layer
     * @param targetOutput target output values from data set
     *
     * @author ks061
     */
    OutputLayer(int numNeurons, String id, double[] targetOutput) {
        super(numNeurons, id);
        this.targetOutput = targetOutput;
        this.outputErrors = new double[this.targetOutput.length];
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
     * @author - lts010
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> neurons = new ArrayList<>();
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(i));
        }
        return neurons;
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @param layerID - A string-based identifier that can be used when creating
     * the neurons
     * @return - An array list of all newly created neurons
     * @author - lts010
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID) {
        this.layerID = layerID;
        return createNeurons(numNeurons);
    }

    /**
     * Computes the net input for each neuron in the layer
     *
     * @author - lts010, ks061
     */
    @Override
    public void fireNeurons() {
        for (Neuron neuron : this.neurons) {
            neuron.fire();
        }
        learn();
    }

    /**
     * Throws an UnsupportedOperationException because the output layer should
     * not be connecting
     *
     * @param nextLayer layer that this layer will forward-propagate to
     *
     * @author cld028
     */
    @Override
    public void connectLayer(Layer nextLayer) {
        throw new UnsupportedOperationException(
                "Output layer shouldn't be connecting!");
    }

    /**
     * Given a set of output, use learn to actually update parameters in NN
     *
     * @author ks061
     */
    public void learn() {
        calculateErrors();
        double deltaWeight = this.neurons.get(0).getValue()
                             * (1 - this.neurons.get(0).getValue())
                             * this.outputErrors[this.t];
        updateWeights(this.neurons.get(0).getInEdges(), deltaWeight);
    }

    /**
     * Calculates output errors after forward propagation complete
     *
     * @author ks061
     */
    private void calculateErrors() {
        outputErrors[this.t] = this.neurons.get(0).getValue() - targetOutput[this.t];
    }

    /**
     * Updates the weights of all of the prior weights
     *
     * @param oldEdges edges coming to output neuron in this layer
     * @param deltaWeight change in weight
     *
     * @author ks061
     */
    protected void updateWeights(ArrayList<Edge> oldEdges, double deltaWeight) {
        for (Edge edge : oldEdges) {
            edge.changeWeight(edge.getFrom().getAlpha(), deltaWeight);
        }
        if (this.prevLayer instanceof HiddenLayer) {
            this.prevLayer.learn();
        }
    }

    public void fireNeurons(double[] inputVals) {
        throw new UnsupportedOperationException(
                "Output layers don't fire neurons this way");
    }

    /**
     * Sets t, representing that the neural network is currently working through
     * the t-th row of input
     *
     * @param t number that represents that the neural network is currently
     * working through the t-th row of input
     *
     * @author ks061
     */
    public void setT(int t) {
        this.t = t;
    }

}
