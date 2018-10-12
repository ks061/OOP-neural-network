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
 * @author cld028, lts010, ks061
 */
public class HiddenLayer extends Layer {

    /**
     * Number of output edges in the layer
     */
    private int numOutEdges;

    /**
     * Link to previous layer in the neural network
     */
    protected Layer prevLayer;

    /**
     * Link to next layer in the neural network
     */
    protected Layer nextLayer;

    /**
     * Explicit constructor that initializes the hidden layer with a particular
     * number of neurons, a string identifier for the layer, the index of the
     * layer, and the neural network the layer is within
     *
     * @param numNeurons number of neurons in the hidden layer
     * @param id string identifier for layer
     * @param layerNum nth layer
     * @param nN neural network hidden layer is within
     *
     * @author cld028
     */
    HiddenLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
        super(numNeurons, id, layerNum, nN);
        // this.learnAlg = HiddenLayer.DEFAULTLEARNINGALG;
    }

    /**
     * Creates and returns a list of hidden neurons
     *
     * @param numNeurons number of neurons to be created within the layer
     * @return list of neurons created within the layer
     *
     * @author lts010, ks061
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> createdNeurons = new ArrayList<>();

        Neuron neuronToAdd;
        for (int i = 0; i < numNeurons; i++) {
            neuronToAdd = new HiddenNeuron(i, this.layerNum, this.neuralNet);
            createdNeurons.add(neuronToAdd);
        }

        return createdNeurons;
    }

    /**
     * Connects this layer to the next layer
     *
     * @param nextLayer next layer
     *
     * @author ks061, lts010
     */
    @Override
    public void connectLayer(Layer nextLayer) {
        if (nextLayer instanceof InputLayer) {
            throw new NeuralNetConstructionException(
                    "Input layer cannot be linked to another input layer; only one input layer can exist in a neural network.");
        }

        for (Neuron neuron : this.neurons) {
            for (Neuron nextNeuron : nextLayer.neurons) {
                Edge edge = new Edge(this.getNeuralNet(), this.layerNum,
                                     getNextEdgeNum());
                neuron.getOutEdges().add(edge);
                edge.setFrom(neuron);
                nextNeuron.getInEdges().add(edge);
                edge.setTo(nextNeuron);
            }
        }
        this.nextLayer = nextLayer;
        if (nextLayer instanceof HiddenLayer) {
            ((HiddenLayer) nextLayer).setPrevLayer(this);
        }
        else if (nextLayer instanceof OutputLayer) {
            ((OutputLayer) nextLayer).setPrevLayer(this);
        }
    }

    /**
     * Runs the weight delta calculation for each neuron
     *
     * @author ks061, lts010
     */
    public void calculateWeightDeltas() {
    }

    /**
     * Given a set of output, use learn to actually update parameters in NN
     *
     * @author ks061, lts010
     */
    public void learn() {
//        calculateErrors();
//        double deltaWeight = this.neurons.get(0).getValue()
//                             * (1 - this.neurons.get(0).getValue())
//                             * this.outputErrors[this.t];
//        updateWeights(this.neurons.get(0).getInEdges(), deltaWeight);
        for (Neuron neuron : this.neurons) {
            ((HiddenNeuron) neuron).learn();
        }
//        Iterator it = this.neurons.iterator();
//        while (it.hasNext()) {
//            ((Neuron) it.next()).learn();
//        }
        if (prevLayer instanceof HiddenLayer) {
            ((HiddenLayer) prevLayer).learn();
        }
    }

    /**
     * Computes net input values for each neuron in the layer
     *
     * @author ks061, lts010
     */
    @Override
    public void fireNeurons() {
        for (Neuron neuron : this.neurons) {
            ((HiddenNeuron) neuron).fire();
        }
        nextLayer.fireNeurons();
    }

    /**
     * increments the number of edges in the layer
     *
     * @return an int that is to be used as the ID for a newly created edge
     *
     * @author lts010, ks061
     */
    protected int getNextEdgeNum() {
        return (numOutEdges++);
    }

    /**
     * Gets the number out edges in the layer, i.e. the number of edges from
     * this layer to the next layer.
     *
     * @return number of out edges
     * @author lts010, ks061
     */
    protected int getNumOutEdges() {
        return (this.numOutEdges);
    }

    /**
     * Sets the previous layer
     *
     * @param prevLayer previous layer
     *
     * @author ks061, lts010
     */
    protected void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }

    /**
     * Sets the next layer
     *
     * @param nextLayer next layer
     *
     * @author ks061, lts010
     */
    protected void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }
}

//    /**
//     * Creates a particular number of neurons in the hidden layer with a string
//     * identifier for the layer
//     *
//     * @param numNeurons number of neurons to be created within the layer
//     * @param layerID string identifier for the layer
//     * @return list of neurons created within the layer
//     *
//     * @author lts010, ks061
//     */
//    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID) {
//        ArrayList<Neuron> neurons = new ArrayList<>();
//        for (int i = 0; i < numNeurons; i++) {
//            neurons.add(new Neuron(i));
//        }
//        return neurons;
//    }
//    /**
//     * Explicit constructor that initializes the hidden layer with a particular
//     * number of neurons
//     *
//     * @param numNeurons
//     *
//     * @author cld028
//     */
//    HiddenLayer(int numNeurons) {
//        super(numNeurons);
//        // this.learnAlg = HiddenLayer.DEFAULTLEARNINGALG;
//    }
// /**
//     * Updates the weights of all of the prior weights
//     *
//     * @param oldEdges edges coming to output neuron in this layer
//     * @param deltaWeight change in weight
//     *
//     * @author ks061
//     */
//    protected void updateWeights(ArrayList<Edge> oldEdges, double deltaWeight) {
//        for (Edge edge : oldEdges) {
//            edge.changeWeight(NeuralNet.alpha, deltaWeight);
//        }
//        if (this.prevLayer instanceof HiddenLayer) {
//            this.prevLayer.learn(deltaWeight);
//        }
//    }
//    /**
//     * Throws UnsupportedOperationException because the hidden layer does not
//     * assign input values to its neurons
//     *
//     * @param inputVals input values
//     *
//     * @author ks061, lts010
//     */
//    @Override
//    public void fireNeurons(double[] inputVals) {
//        throw new UnsupportedOperationException(
//                "Hidden layer does not assign input values to its neurons."); //To change body of generated methods, choose Tools | Templates.
//    }
