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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
     * List of thetas (a theta for each neuron that needs a theta)
     */
    private ArrayList<ArrayList<Double>> thetas;
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
     * @param thetas list of thetas (a theta for each neuron that needs a theta)
     * @param programMode program mode (classification or training mode) that
     * neural net will run in
     *
     * @author lts010, ks061
     */
    public ConfigObject(int numInputs, int numOutputs, int numHiddenLayers,
                        int numNeuronsPerHiddenLayer, double highestSSE,
                        ArrayList<ArrayList<Double>> weights,
                        ArrayList<ArrayList<Double>> thetas,
                        ProgramMode programMode) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
        this.highestSSE = highestSSE;
        this.weights = weights;
        this.thetas = thetas;
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
     * Gets the list of thetas (a theta for each neuron that needs a theta)
     *
     * @return list of thetas
     *
     * @author ks061, lts010
     */
    public ArrayList<ArrayList<Double>> getThetas() {
        return thetas;
    }

    /**
     * Sets the list of thetas (a theta for each neuron that needs a theta)
     *
     * @param thetas - list of thetas
     *
     * @author ks061, lts010
     */
    public void setThetas(ArrayList<ArrayList<Double>> thetas) {
        this.thetas = thetas;
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

    /**
     * Reads a config file
     *
     * @return a double array list that provides the number of inputs, outputs,
     * and the weights
     * @throws java.io.FileNotFoundException
     *
     * @author lts010, ks061
     */
    public static ArrayList<String> readConfigFile() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Scanner fReader = new Scanner(System.in);
        boolean fileFound = false;
        while (!fileFound) {
            try {
                System.out.println(
                        "Enter the filename of the configuration file: ");
                String filename = in.nextLine();
                File f = new File(filename);
                fReader = new Scanner(f);
                fileFound = true;
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. Try again.");
            }
        }
        ArrayList<String> configList = new ArrayList<>();
        while (fReader.hasNextLine()) {
            configList.add(fReader.nextLine());
        }
        return configList;
    }

    /**
     * Saves all relevant information of a neural net to a file
     *
     * @param nN - a Neural Net
     * @throws java.io.FileNotFoundException
     *
     * @author lts010, ks061
     */
    public void exportConfig(NeuralNet nN) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String prompt = "What .txt file would you like to save the configuration to? ";
        System.out.println(prompt);
        String outputFile = in.next();
        PrintWriter out = new PrintWriter(outputFile);
        out.printf("%d.0 %d.0 %d.0 %d.0 %f\n",
                   nN.getConfiguration().getNumInputs(),
                   nN.getConfiguration().getNumOutputs(),
                   nN.getConfiguration().getNumHiddenLayers(),
                   nN.getConfiguration().getNumNeuronsPerHiddenLayer(),
                   nN.getConfiguration().getHighestSSE());
        ArrayList<ArrayList<Double>> weights = nN.getConfiguration().getWeights();
        String weightLayer;
        for (ArrayList<Double> weightList : weights) {
            weightLayer = "";
            for (double weight : weightList) {
                weightLayer += weight + " ";
            }
            out.printf("%s\n", weightLayer);
        }
        out.printf("%s\n", "THETAS");
        ArrayList<ArrayList<Double>> thetas = nN.getConfiguration().getThetas();
        String thetaLayer;
        for (ArrayList<Double> thetaList : thetas) {
            thetaLayer = "";
            for (double theta : thetaList) {
                thetaLayer += theta + " ";
            }
            out.printf("%s\n", thetaLayer);
        }
        out.close();
    }

}
