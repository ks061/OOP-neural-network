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
 * @author cld028
 */
public class NeuralNet {

    private ArrayList<Layer> layers;
    private double[][] inputs;
    private double[] errors;

    NeuralNet() {
        InputLayer inputLayer = new InputLayer(2, "I1-");
        HiddenLayer hiddenLayer = new HiddenLayer(3, "H1-");
        OutputLayer outputLayer = new OutputLayer(1, "O1-");
        System.out.println("Connecting to in-hidden");
        inputLayer.connectLayer(hiddenLayer);
        System.out.println("Connecting to hidden-out");
        hiddenLayer.connectLayer(outputLayer);

        inputLayer.fireNeurons(this.inputs);
        //Test your network here
    }

    /**
     * Explicit constructor that creates a NeuralNet
     *
     * @param inputs training data to be used as inputs in the neural network
     */
    NeuralNet(double[][] inputs) {
        this();
        this.inputs = inputs;
    }

}
