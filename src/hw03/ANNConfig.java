/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 9, 2018
* Time: 11:36:53 AM
*
* Project: csci205_proj_hw
* Package: hw02
* File: ANNConfig
* Description: This file contains ANNConfig, which holds the configuration
*              for a neural network NeuralNet.
*
* ****************************************
 */
package hw03;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ANNConfig holds the configuration for a NeuralNet, including the number of
 * inputs (input neurons), the number of outputs (output neurons), the number of
 * hidden layers, the number of neurons per hidden layer, the maximum allowed
 * sum of squared error value (SSE), the maximum number of epochs neural network
 * will train before terminating the training session, list of edge weights (a
 * weight for each edge going to the next layer in each layer except the output
 * layer), and program mode (classification or training mode) that neural net
 * will run in
 *
 * @author lts010, ks061
 */
public class ANNConfig implements Serializable {

    /**
     * Number of inputs (input neurons) a neural network will be configured with
     */
    private int numInputs;
    /**
     * Number of outputs (output neurons) a neural network will be configured
     * with
     */
    private int numOutputs;
    /**
     * Number of hidden layers a neural network will be configured with
     */
    private int numHiddenLayers;
    /**
     * Number of neurons per hidden layer a neural network will be configured
     * with
     */
    private int numNeuronsPerHiddenLayer;
    /**
     * Maximum allowed sum of squared error value (SSE) a neural network will be
     * configured with (only relevant in training mode)
     */
    private double highestSSE;
    /**
     * Maximum number of epochs neural network will train before terminating the
     * training session (only relevant in training mode)
     */
    private int numMaxEpochs;
    /**
     * List of edge weights (a weight for each edge going to the next layer in
     * each layer except the output layer) a neural network will be configured
     * with
     */
    private ArrayList<ArrayList<Double>> weights;
    /**
     * List of thetas (a theta for each neuron that needs a theta) a neural
     * network will be configured with
     */
    private ArrayList<ArrayList<Double>> thetas;
    /**
     * Run mode (classification or training mode) that a neural net will run in
     */
    private transient ProgramMode programMode;

    /**
     * Constructor that initializes the number of inputs (input neurons), the
     * number of outputs (output neurons), the number of hidden layers, the
     * number of neurons per hidden layer, the maximum allowed sum of squared
     * error value, the maximum number of epochs neural network will train
     * before terminating the training session, list of edge weights (a weight
     * for each edge going to the next layer in each layer except the output
     * layer), and program mode (classification or training mode) that neural
     * net will run in
     *
     * @param numInputs number of inputs (input neurons) a neural network will
     * be configured with
     * @param numOutputs number of outputs (output neurons) a neural network
     * will be configured with
     * @param numHiddenLayers number of hidden layers a neural network will be
     * configured with
     * @param numNeuronsPerHiddenLayer number of neurons per hidden layer a
     * neural network will be configured with
     * @param highestSSE maximum allowed sum of squared error value a neural
     * network will be configured with
     * @param numMaxEpochs maximum number of epochs neural network will train
     * before terminating the training session
     * @param weights list of edge weights (a weight for each edge going to the
     * next layer in each layer except the output layer) a neural network will
     * be configured with
     * @param thetas list of thetas (a theta for each neuron in a hidden layer
     * or output layer) a neural network will be configured with
     * @param programMode program mode (classification or training mode) that a
     * neural network will be configured with neural net will run in
     *
     * @author lts010, ks061
     */
    public ANNConfig(int numInputs, int numOutputs, int numHiddenLayers,
                     int numNeuronsPerHiddenLayer, double highestSSE,
                     int numMaxEpochs,
                     ArrayList<ArrayList<Double>> weights,
                     ArrayList<ArrayList<Double>> thetas,
                     ProgramMode programMode) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
        this.highestSSE = highestSSE;
        this.numMaxEpochs = numMaxEpochs;
        this.weights = weights;
        this.thetas = thetas;
        this.programMode = programMode;
    }

    public ANNConfig(int i, int i0, int i1, int i2, double d, int i3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Gets the number of inputs (input neurons) a neural network is configured
     * with
     *
     * @return number of inputs (input neurons) a neural network is configured
     * with
     *
     * @author lts010, ks061
     */
    public int getNumInputs() {
        return numInputs;
    }

    /**
     * Sets the number of inputs (input neurons) a neural network is configured
     * with
     *
     * @param numInputs number of inputs (input neurons) a neural network is
     * configured with
     *
     * @author lts010, ks061
     */
    public void setNumInputs(int numInputs) {
        this.numInputs = numInputs;
    }

    /**
     * Gets the number of outputs (output neurons) a neural network is
     * configured with
     *
     * @return number of outputs (output neurons) a neural network is configured
     * with
     *
     * @author lts010, ks061
     */
    public int getNumOutputs() {
        return numOutputs;
    }

    /**
     * Sets the number of outputs (output neurons) a neural network is
     * configured with
     *
     * @param numOutputs number of outputs (output neurons) a neural network is
     * configured with
     *
     * @author lts010, ks061
     */
    public void setNumOutputs(int numOutputs) {
        this.numOutputs = numOutputs;
    }

    /**
     * Gets the number of hidden layers a neural network is configured with
     *
     * @return number of hidden layers a neural network is configured with
     *
     * @author lts010, ks061
     */
    public int getNumHiddenLayers() {
        return numHiddenLayers;
    }

    /**
     * Sets the number of hidden layers a neural network is configured with
     *
     * @param numHiddenLayers number of hidden layers a neural network is
     * configured with
     *
     * @author lts010, ks061
     */
    public void setNumHiddenLayers(int numHiddenLayers) {
        this.numHiddenLayers = numHiddenLayers;
    }

    /**
     * Gets the number of neurons per hidden layer a neural network is
     * configured with
     *
     * @return number of neurons per hidden layer a neural network is configured
     * with
     *
     * @author lts010, ks061
     */
    public int getNumNeuronsPerHiddenLayer() {
        return numNeuronsPerHiddenLayer;
    }

    /**
     * Sets the number of neurons per hidden layer a neural network is
     * configured with
     *
     * @param numNeuronsPerHiddenLayer number of neurons per hidden layer a
     * neural network is configured with
     *
     * @author lts010, ks061
     */
    public void setNumNeuronsPerHiddenLayer(int numNeuronsPerHiddenLayer) {
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
    }

    /**
     * Gets the maximum allowable sum of squared error value (SSE) a neural
     * network is configured with
     *
     * @return maximum allowable sum of squared error value (SSE) a neural
     * network is configured with
     *
     * @author lts010, ks061
     */
    public double getHighestSSE() {
        return highestSSE;
    }

    /**
     * Sets the maximum allowable sum of squared error value (SSE) a neural
     * network is configured with
     *
     * @param highestSSE maximum allowable sum of squared error value (SSE) a
     * neural network is configured with
     *
     * @author lts010, ks061
     */
    public void setHighestSSE(double highestSSE) {
        this.highestSSE = highestSSE;
    }

    /**
     * Gets the list of edge weights (a weight for each edge going to the next
     * layer in each layer except the output layer) a neural network is
     * configured with
     *
     * @return list of edge weights a neural network is configured with
     *
     * @author lts010, ks061
     */
    public ArrayList<ArrayList<Double>> getWeights() {
        return weights;
    }

    /**
     * Sets the list of edge weights (a weight for each edge going to the next
     * layer in each layer except the output layer) a neural network is
     * configured with
     *
     * @param weights list of edge weights a neural network is configured with
     *
     * @author lts010, ks061
     */
    public void setWeights(ArrayList<ArrayList<Double>> weights) {
        this.weights = weights;
    }

    /**
     * Gets the list of thetas (a theta for each neuron in a hidden layer or
     * output layer) a neural network is configured with
     *
     * @return list of thetas a neural network is configured with
     *
     * @author ks061, lts010
     */
    public ArrayList<ArrayList<Double>> getThetas() {
        return thetas;
    }

    /**
     * Sets the list of thetas (a theta for each neuron in a hidden layer or
     * output layer) a neural network is configured with
     *
     * @param thetas list of thetas a neural network is configured with
     *
     * @author ks061, lts010
     */
    public void setThetas(ArrayList<ArrayList<Double>> thetas) {
        this.thetas = thetas;
    }

    /**
     * Sets the program mode (classification or training mode) that neural net
     * is configured to run in
     *
     * @param programMode program mode mode (classification or training mode)
     * that neural net is configured to run in
     *
     * @author ks061, lts010
     */
    public void setProgramMode(ProgramMode programMode) {
        this.programMode = programMode;
    }

    /**
     * Gets the program mode (classification or training mode) that neural net
     * is configured to run in
     *
     * @return program mode (classification or training mode) that neural net is
     * configured to run in
     *
     * @author ks061, lts010
     */
    public ProgramMode getProgramMode() {
        return this.programMode;
    }

    /**
     * Sets the maximum number of epochs that the neural network will train
     * before terminating its training session of the network
     *
     * @param numMaxEpochs maximum number of epochs that the neural network will
     * train before terminating its training session of the network
     *
     * @author lts010, ks061
     */
    public void setNumMaxEpochs(int numMaxEpochs) {
        this.numMaxEpochs = numMaxEpochs;
    }

    /**
     * Gets the maximum number of epochs that the neural network will train
     * before terminating its training session of the network
     *
     * @return maximum number of epochs that the neural network will train
     * before terminating its training session of the network
     *
     * @author lts010, ks061
     */
    public int getNumMaxEpochs() {
        return numMaxEpochs;
    }
}
