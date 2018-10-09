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

    HiddenLayer(int numNeurons) {
        super(numNeurons);
        this.learnAlg = HiddenLayer.DEFAULTLEARNINGALG;
    }

    HiddenLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
        super(numNeurons, id, layerNum, nN);
        this.learnAlg = HiddenLayer.DEFAULTLEARNINGALG;
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
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
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID) {
        ArrayList<Neuron> neurons = new ArrayList<>();
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(i));
        }
        return neurons;
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

    /**
     * Computes net input values for each neuron in the layer
     *
     * @author ks061
     */
    @Override
    public void fireNeurons() {
        throw new UnsupportedOperationException("lol."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fireNeurons(double[] inputVals) {
        throw new UnsupportedOperationException("lol"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID,
                                           int LayerNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
