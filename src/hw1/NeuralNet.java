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

import java.util.ArrayList;

/**
 *
 * @author cld028, ks061
 */
public class NeuralNet {

    private ArrayList<Layer> layers;
    private double[][] inputs;
    private double[] expectedOutputs;
    private double[] errors;

    /**
     * Default constructor that creates the input layer, hidden layer, and
     * output layer, along with creating connections among the layers.
     *
     * @author cld028, ks061
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

        OutputLayer outputLayer = new OutputLayer(expectedOutputs.length, "O1-");
        InputLayer inputLayer = new InputLayer(numInputs, "I1-",
                                               outputLayer.neurons.size());
        // HiddenLayer hiddenLayer = new HiddenLayer(3, "H1-");
        // System.out.println("Connecting to in-hidden");
        //inputLayer.connectLayer(hiddenLayer);
        //System.out.println("Connecting to hidden-out");
        // hiddenLayer.connectLayer(outputLayer);
        inputLayer.connectLayer(outputLayer);

        for (double[] inputSet : this.inputs) {
            inputLayer.fireNeurons(inputSet);
            outputLayer.fireNeurons();
            // Read output layer
            // Back propogate
            // etc.
        }

        //Test your network here
    }

    public static void main(String args[]) {
        double[][] inputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[] outputs = {0.0, 1.0, 1.0, 1.0};
        NeuralNet net = new NeuralNet(inputs, outputs);
    }
}
