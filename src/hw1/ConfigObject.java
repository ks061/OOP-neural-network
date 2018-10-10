/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 9, 2018
* Time: 11:36:53 AM
*
* Project: csci205_proj_hw
* Package: hw1
* File: ConfigObject
* Description: This file contains ConfigObject, which holds the configuration
*              for a NeuralNet.
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;

// TODO: modify config to store thetas
/**
 * ConfigObject holds the configuration for a NeuralNet, including the number of
 * inputs (input neurons), the number of outputs (output neurons), the number of
 * hidden layers, the number of neurons per hidden layer, the maximum allowed
 * sum of squared error value, list of edge weights (a weight for each edge
 * going to the next layer in each layer except the output layer), and program
 * mode (classification or training mode) that neural net will run in
 *
 * @author lts010, ks061
 */
public class ConfigObject {

    /**
     * Number of inputs (input neurons)
     */
    private int numInputs;
    /**
     * Number of outputs (output neurons)
     */
    private int numOutputs;
    /**
     * Number of hidden layers
     */
    private int numHiddenLayers;
    /**
     * Number of neurons per hidden layer
     */
    private int numNeuronsPerHiddenLayer;
    /**
     * Maximum allowed sum of squared error value
     */
    private double highestSSE;
    /**
     * List of edge weights (a weight for each edge going to the next layer in
     * each layer except the output layer)
     */
    private ArrayList<ArrayList<Double>> weights;

    /**
     * Run mode (classification or training mode) that neural net will run in
     */
    private ProgramMode programMode;

    /**
     * Explicit parameter that initializes the number of inputs (input neurons),
     * the number of outputs (output neurons), the number of hidden layers, the
     * number of neurons per hidden layer, the maximum allowed sum of squared
     * error value, list of edge weights (a weight for each edge going to the
     * next layer in each layer except the output layer), and program mode
     * (classification or training mode) that neural net will run in
     *
     * @param numInputs number of inputs (input neurons)
     * @param numOutputs number of outputs (output neurons)
     * @param numHiddenLayers number of hidden layers
     * @param numNeuronsPerHiddenLayer number of neurons per hidden layer
     * @param highestSSE maximum allowed sum of squared error value
     * @param weights list of edge weights (a weight for each edge going to the
     * next layer in each layer except the output layer)
     * @param programMode program mode (classification or training mode) that
     * neural net will run in
     *
     * @author lts010, ks061
     */
    public ConfigObject(int numInputs, int numOutputs, int numHiddenLayers,
                        int numNeuronsPerHiddenLayer, double highestSSE,
                        ArrayList<ArrayList<Double>> weights,
                        ProgramMode programMode) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
        this.highestSSE = highestSSE;
        this.weights = weights;
        this.programMode = programMode;
    }

    /**
     * Gets the number of inputs (input neurons)
     *
     * @return number of inputs (input neurons)
     *
     * @author lts010, ks061
     */
    public int getNumInputs() {
        return numInputs;
    }

    /**
     * Sets the number of inputs (input neurons)
     *
     * @param numInputs number of inputs (input neurons)
     *
     * @author lts010, ks061
     */
    public void setNumInputs(int numInputs) {
        this.numInputs = numInputs;
    }

    /**
     * Gets the number of outputs (output neurons)
     *
     * @return number of outputs (output neurons)
     *
     * @author lts010, ks061
     */
    public int getNumOutputs() {
        return numOutputs;
    }

    /**
     * Sets the number of outputs (output neurons)
     *
     * @param numOutputs number of outputs (output neurons)
     *
     * @author lts010, ks061
     */
    public void setNumOutputs(int numOutputs) {
        this.numOutputs = numOutputs;
    }

    /**
     * Gets the number of hidden layers
     *
     * @return number of hidden layers
     *
     * @author lts010, ks061
     */
    public int getNumHiddenLayers() {
        return numHiddenLayers;
    }

    /**
     * Sets the number of hidden layers
     *
     * @param numHiddenLayers number of hidden layers
     *
     * @author lts010, ks061
     */
    public void setNumHiddenLayers(int numHiddenLayers) {
        this.numHiddenLayers = numHiddenLayers;
    }

    /**
     * Gets the number of neurons per hidden layer
     *
     * @return number of neurons per hidden layer
     *
     * @author lts010, ks061
     */
    public int getNumNeuronsPerHiddenLayer() {
        return numNeuronsPerHiddenLayer;
    }

    /**
     * Sets the number of neurons per hidden layer
     *
     * @param numNeuronsPerHiddenLayer number of neurons per hidden layer
     *
     * @author lts010, ks061
     */
    public void setNumNeuronsPerHiddenLayer(int numNeuronsPerHiddenLayer) {
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
    }

    /**
     * Gets the maximum allowable sum of squared error value
     *
     * @return maximum allowable sum of squared error value
     *
     * @author lts010, ks061
     */
    public double getHighestSSE() {
        return highestSSE;
    }

    /**
     * Sets the maximum allowable sum of squared error value
     *
     * @param highestSSE maximum allowable sum of squared error value
     *
     * @author lts010, ks061
     */
    public void setHighestSSE(double highestSSE) {
        this.highestSSE = highestSSE;
    }

    /**
     * Gets the list of edge weights (a weight for each edge going to the next
     * layer in each layer except the output layer)
     *
     * @return list of edge weights
     *
     * @author lts010, ks061
     */
    public ArrayList<ArrayList<Double>> getWeights() {
        return weights;
    }

    /**
     * Sets the list of edge weights (a weight for each edge going to the next
     * layer in each layer except the output layer)
     *
     * @param weights list of edge weights
     *
     * @author lts010, ks061
     */
    public void setWeights(ArrayList<ArrayList<Double>> weights) {
        this.weights = weights;
    }

    /**
     * Sets the program mode (classification or training mode) that neural net
     * will run in
     *
     * @param programMode program mode mode (classification or training mode)
     * that neural net will run in
     *
     * @author ks061, lts010
     */
    public void setProgramMode(ProgramMode programMode) {
        this.programMode = programMode;
    }

    /**
     * Gets the program mode (classification or training mode) that neural net
     * will run in
     *
     * @return program mode (classification or training mode) that neural net
     * will run in
     *
     * @author ks061, lts010
     */
    public ProgramMode getProgramMode() {
        return this.programMode;
    }

}
