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
 * Description: This file contains OutputLayer, which represents the last
 *              column of neurons within a neural network.
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * OutputLayer represents the last column of neurons within a neural network.
 *
 * @author cld028, lts010
 */
public class OutputLayer extends Layer {

    private double[] targetOutputs;
    private double[] outputErrors;

    /**
     * Link to previous layer in the neural network
     */
    protected Layer prevLayer;

    /**
     * Explicit constructor that creates a particular number of neurons in the
     * layer, sets an identifier for the layer, and passes in the target output
     * values from data set.
     *
     * @param numNeurons number of neurons to create in the layer
     * @param id identifier for the layer
     * @param targetOutput target output values from data set
     *
     * @author cld028
     */
    public OutputLayer(int numNeurons, String id, int layerNumber, NeuralNet nN) {
        super(numNeurons, id, layerNumber, nN);
    }

    /**
     * Creates and returns a list of output neurons
     *
     * @param numNeurons total number of neurons to be created within layer
     * @return list of all newly created neurons
     * @author lts010, ks061
     */
    @Override
    public ArrayList<Neuron> createNeurons(int numNeurons) {
        ArrayList<Neuron> createdNeurons = new ArrayList<>();

        Neuron neuronToAdd;
        for (int i = 0; i < numNeurons; i++) {
            neuronToAdd = new OutputNeuron(i, this.layerNum,
                                           this.neuralNet);
            createdNeurons.add(
                    neuronToAdd);
        }

        return createdNeurons;
    }

    /**
     * Computes the net input for each neuron in the layer
     *
     * @author - lts010, ks061
     */
    @Override
    public void fireNeurons() {
        int neuronIndex = 0;
        for (Neuron neuron : this.neurons) {
            ((OutputNeuron) neuron).fire();
            if (super.getNeuralNet().getConfiguration().getProgramMode() == ProgramMode.TRAINING) {
                neuronIndex = neuron.getNeuronNum();
                this.outputErrors[neuronIndex] = this.targetOutputs[neuronIndex] - neuron.getNetValue();
            }
            //System.out.println("neuronIndex = " + neuronIndex);
            //System.out.print(
            //       "Output Errors = " + Arrays.toString(outputErrors));
            //System.out.print("   Target Outputs = " + Arrays.toString(
            //       targetOutputs));
            // System.out.println(" Output Errors = " + Arrays.toString(outputErrors));
        }
        if (super.getNeuralNet().getConfiguration().getProgramMode() == ProgramMode.TRAINING) {
            learn();
        }
    }

    public double calculateSumOfSquaredErrors() {
        double sumOfSquaredErrors = 0;
        for (double error : this.outputErrors) {
            sumOfSquaredErrors += error * error;
        }
        return sumOfSquaredErrors;
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
     * Adjusts weights for nodes connecting to layer and then calls the learn
     * method of the previous layer
     */
    @Override
    public void learn() {
//        calculateErrors();
        //        double deltaWeight = this.neurons.get(0).getValue()
        //                             * (1 - this.neurons.get(0).getValue())
        //                             * this.outputErrors[this.t];
        //        for (Edge edge : oldEdges) {
        //            edge.changeWeight(NeuralNet.alpha, deltaWeight);
        //        }
        //        updateWeights(this.neurons.get(0).getInEdges(), deltaWeight);
        //        if (this.prevLayer instanceof HiddenLayer) {
        //            this.prevLayer.learn();
        //        }

        for (Neuron neuron : this.neurons) {
            int neuronID = neuron.getNeuronNum();
            this.outputErrors[neuronID] = targetOutputs[neuronID] - this.neurons.get(
                    neuronID).getNetValue();
//            System.out.println(
//                    "E(0) = " + this.outputErrors[neuronID]);
            ((OutputNeuron) neuron).learn(this.outputErrors[neuronID]);
        }
//        Iterator it = this.neurons.iterator();
//        while (it.hasNext()) {
//            ((Neuron) it.next()).learn();
//        }

        if (prevLayer instanceof HiddenLayer) {
            ((HiddenLayer) prevLayer).learn();
        }
    }

    public void setTargetOutputs(double[] targetOutputs) {
        this.targetOutputs = targetOutputs;
        this.outputErrors = new double[this.targetOutputs.length];
    }

    /**
     * Sets the previous layer
     *
     * @param prevLayer previous layer
     *
     * @author ks061, lts010
     */
    public void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }

}

//    /**
//     *
//     * @param numNeurons - Total number of neurons to be created within layer
//     * @param layerID - A string-based identifier that can be used when creating
//     * the neurons
//     * @return - An array list of all newly created neurons
//     * @author - lts010
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
//    /**
//     * Updates the weights of all of the prior weights
//     *
//     * @param oldEdges edges coming to output neuron in this layer
//     * @param deltaWeight change in weight
//     *
//     * @author ks061
//     */
//    @Override
//    protected void updateWeights(ArrayList<Edge> oldEdges, double deltaWeight) {
//        for (Edge edge : oldEdges) {
//            edge.changeWeight(NeuralNet.alpha, deltaWeight);
//        }
//    }
//    OutputLayer(int numNeurons) {
//        super(numNeurons);
//    }
/**
 * Calculates output errors after forward propagation complete
 *
 * @author ks061
 */
//  private void calculateErrors() {
//     outputErrors[this.t] = this.neurons.get(0).getValue() - targetOutputs[this.t];
 // }
