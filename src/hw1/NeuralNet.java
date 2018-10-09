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
 * File: NeuralNet
 * Description: Represents an artificial neural net
 *
 * ****************************************
 */
package hw1;

import java.util.*;

/**
 *
 * @author cld028, ks061
 */
public class NeuralNet {

    private ArrayList<Layer> layers;
    private double[][] inputs;
    private double[] expectedOutputs;
    private double[] errors;
    private ConfigObject configuration;

    /**
     * Default constructor that creates the input layer, hidden layer, and
     * output layer, along with creating connections among the layers.
     *
     * @author cld028, ks061, lts010
     */
    NeuralNet(double[][] inputs, double[] expectedOutputs) {
        if (inputs.length == 0) {
            throw new NeuralNetConstructionException(
                    "No inputs have been provided.");
        }
        if (expectedOutputs.length == 0) {
            throw new NeuralNetConstructionException(
                    "No expected outputs have been provided.");
        }
        if (inputs.length != expectedOutputs.length) {
            throw new NeuralNetConstructionException(
                    "Each input set does not have a corresponding output set.");
        }

        int numInputs = inputs[0].length;
        for (double[] inputSet : inputs) {
            if (inputSet.length != numInputs) {
                throw new NeuralNetConstructionException(
                        "Not all input sets have the same amount of input entries.");
            }
        }

        this.inputs = inputs;
        this.expectedOutputs = expectedOutputs;

        InputLayer inputLayer = new InputLayer(numInputs, "I1-", 0, this, inputs);
        OutputLayer outputLayer = new OutputLayer(1, "O1-", 1, this,
                                                  expectedOutputs);
        // HiddenLayer hiddenLayer = new HiddenLayer(3, "H1-");
        // System.out.println("Connecting to in-hidden");
        //inputLayer.connectLayer(hiddenLayer);
        //System.out.println("Connecting to hidden-out");
        // hiddenLayer.connectLayer(outputLayer);
        inputLayer.connectLayer(outputLayer);

        for (int t = 0; t < this.inputs.length; t++) {
            inputLayer.setT(t);
            outputLayer.setT(t);
            inputLayer.fireNeurons();
        }

        //Test your network here
    }

    /**
     * Default constructor that creates the input layer, hidden layer, and
     * output layer, along with creating connections among the layers. Weights
     * are provided initially.
     *
     * @author cld028, ks061, lts010
     */
    NeuralNet(double[][] inputs, ConfigObject config) {
        if (inputs.length == 0) {
            throw new NeuralNetConstructionException(
                    "No inputs have been provided.");
        }
        if (expectedOutputs.length == 0) {
            throw new NeuralNetConstructionException(
                    "No expected outputs have been provided.");
        }
        if (inputs.length != expectedOutputs.length) {
            throw new NeuralNetConstructionException(
                    "Each input set does not have a corresponding output set.");
        }

        int numInputs = inputs[0].length;
        for (double[] inputSet : inputs) {
            if (inputSet.length != numInputs) {
                throw new NeuralNetConstructionException(
                        "Not all input sets have the same amount of input entries.");
            }
        }
        this.configuration = config;
        this.inputs = inputs;
        ArrayList<Layer> layers = new ArrayList<>();
        InputLayer inputLayer = new InputLayer(numInputs, "I1-", 0, this);
        layers.add(inputLayer);
        for (int i = 1; i < this.configuration.getNumHiddenLayers() + 1; i++) {
            layers.add(new HiddenLayer(
                    this.configuration.getNumNeuronsPerHiddenLayer(),
                    "H" + i + "-",
                    i, this));
        }

        OutputLayer outputLayer = new OutputLayer(config.getNumOutputs(), "O1-",
                                                  layers.size(), this);
        layers.add(outputLayer);
        Iterator it = layers.iterator();
        Layer currentLayer = (Layer) it.next();
        Layer nextLayer;
        while (it.hasNext()) {
            nextLayer = (Layer) it.next();
            currentLayer.connectLayer(nextLayer);
            currentLayer = nextLayer;

        }

        for (double[] inputSet : this.inputs) {
            inputLayer.fireNeurons(inputSet);
            // Read output layer
            // Back propogate
            // etc.
        }

        //Test your network here
    }

    public void storeWeight(int layerNum, int edgeNum, double newWeight) {
        this.configuration.getWeights().get(layerNum).set(edgeNum, newWeight);
    }

    public double getWeight(int layerNum, int edgeNum) {
        return this.configuration.getWeights().get(layerNum).get(edgeNum);
    }

    public ConfigObject getConfiguration() {
        return this.configuration;
    }
}
