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
 * File: InputLayer
 * Description: Represents the input layer of a neural net
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 *
 * @author cld028, ks061
 */
public class InputLayer extends Layer {

    private double[] inputs;

    /**
     * Explicit constructor that creates the input layer with a particular
     * number of neurons and an ID,
     *
     * @param numNeurons number of neurons to be generated in this layer
     * @param id identifier of the layer
     * @param layerNum the layer index number (always 0 for the input layer)
     * @param nN the neuralNet
     *
     * @author ks061, lts010
     */
    InputLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
        super(numNeurons, id, layerNum, nN);
    }

    /**
     *
     * @param numNeurons - Total number of neurons to be created within layer
     * @return - An array list of all newly created neurons
     *
     * @author ks061
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> neurons = new ArrayList<>();
        System.out.println("number of input Neurons = " + numNeurons);

        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(i));
        }
        return neurons;
    }

    /**
     * In the input layer the fireNeurons method simply populates the input
     * neurons with the input data.
     *
     * @author ks061, lts010
     */
    @Override
    public void fireNeurons() {
        if (this.inputs.length != this.neurons.size()) {
            throw new NeuralNetConstructionException(
                    "The number of input values and number of neurons in the "
                    + "input layer of the neural network do not match.");
        }
        // System.out.print("setting input values = ");
        int numInputs = this.inputs.length;
        for (int i = 0; i < numInputs; i++) {
            this.neurons.get(i).setNetValue(this.inputs[i]);
            //  System.out.print("  " + this.neurons.get(i).getNetValue());
        }
        // System.out.println();
        nextLayer.fireNeurons();
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
        nextLayer.setPrevLayer(this);
    }

    /**
     * Does nothing; done learning once reached the input layer.
     *
     * @author cld028
     */
    public void learn() {
    }

    /**
     * Throws an UnsupportedOperationException instance because the input layer
     * should not be calculating errors.
     *
     * @author cld028
     */
    private void calculateErrors() {
        throw new UnsupportedOperationException(
                "Input layer shouldn't be calculating errors!");
    }

    void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

}

//    /**
//     * Explicit constructor that creates the input layer with a particular
//     * number of neurons.
//     *
//     * @param numNeurons number of neurons to be generated in this layer
//     *
//     * @author ks061
//     */
//    InputLayer(int numNeurons) {
//        super(numNeurons);
//    }
//
//    /**
//     * Explicit constructor that creates the input layer with a particular
//     * number of neurons and an ID.
//     *
//     * @param numNeurons number of neurons to be generated in this layer
//     * @param id identifier of the layer
//     *
//     * @author ks061
//     */
//    InputLayer(int numNeurons, String id, int layerNum, NeuralNet nN) {
//        super(numNeurons, id, layerNum, nN);
//    }
//
//
//    /**
//     *
//     * @param numNeurons - Total number of neurons to be created within layer
//     * @param layerID - A string-based identifier that can be used when creating
//     * the neurons
//     * @return - An array list of all newly created neurons
//     *
//     * @author ks061
//     */
//    public ArrayList<Neuron> createNeurons(int numNeurons, String layerID) {
//        this.layerID = layerID;
//        return createNeurons(numNeurons);
//    }
//    /**
//     * Sets t, representing that the neural network is currently working through
//     * the t-th row of input
//     *
//     * @param t number that represents that the neural network is currently
//     * working through the t-th row of input
//     *
//     * @author ks061
//     */
//    public void setT(int t) {
//        this.t = t;
//    }
