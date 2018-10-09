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
* Description: Holds configuration for the neural net
*
* ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 *
 * @author lts010
 */
public class ConfigObject {

    private int numInputs;
    private int numOutputs;
    private int numHiddenLayers;
    private int numNeuronsPerHiddenLayer;
    private double highestSSE;
    private ArrayList<ArrayList<Double>> weights;

    public ConfigObject(int numInputs, int numOutputs, int numHiddenLayers,
                        int numNeuronsPerHiddenLayer, double highestSSE,
                        ArrayList<ArrayList<Double>> weights) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
        this.highestSSE = highestSSE;
        this.weights = weights;
    }

    public int getNumInputs() {
        return numInputs;
    }

    public void setNumInputs(int numInputs) {
        this.numInputs = numInputs;
    }

    public int getNumOutputs() {
        return numOutputs;
    }

    public void setNumOutputs(int numOutputs) {
        this.numOutputs = numOutputs;
    }

    public int getNumHiddenLayers() {
        return numHiddenLayers;
    }

    public void setNumHiddenLayers(int numHiddenLayers) {
        this.numHiddenLayers = numHiddenLayers;
    }

    public int getNumNeuronsPerHiddenLayer() {
        return numNeuronsPerHiddenLayer;
    }

    public void setNumNeuronsPerHiddenLayer(int numNeuronsPerHiddenLayer) {
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
    }

    public double getHighestSSE() {
        return highestSSE;
    }

    public void setHighestSSE(double highestSSE) {
        this.highestSSE = highestSSE;
    }

    public ArrayList<ArrayList<Double>> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<ArrayList<Double>> weights) {
        this.weights = weights;
    }

}
