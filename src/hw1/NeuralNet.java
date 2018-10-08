/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 7, 2016
 * Time: 6:41:23 AM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: NeuralNet
 * Description:
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
    private double[] errors;

    /**
     * Default constructor that creates the input layer, hidden layer, and
     * output layer, along with creating connections among the layers.
     *
     * @author cld028, ks061
     */
    NeuralNet() {
        InputLayer inputLayer = new InputLayer(2, "I1-");
        HiddenLayer hiddenLayer = new HiddenLayer(3, "H1-");
        OutputLayer outputLayer = new OutputLayer(1, "O1-");
        System.out.println("Connecting to in-hidden");
        inputLayer.connectLayer(hiddenLayer);
        System.out.println("Connecting to hidden-out");
        hiddenLayer.connectLayer(outputLayer);

        for (double[] inputSet : this.inputs) {
            inputLayer.fireNeurons(inputSet);
        }

        //Test your network here
    }

    /**
     * Explicit constructor that creates a NeuralNet
     *
     * @param inputs training data to be used as inputs in the neural network
     *
     * @author ks061
     */
    NeuralNet(double[][] inputs) {
        this();
        this.inputs = inputs;
    }

}
