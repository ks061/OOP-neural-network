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
*              for a neural network NeuralNet.
*
* ****************************************
 */
package hw01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ConfigObject holds the configuration for a NeuralNet, including the number of
 * inputs (input neurons), the number of outputs (output neurons), the number of
 * hidden layers, the number of neurons per hidden layer, the maximum allowed
 * sum of squared error value (SSE), list of edge weights (a weight for each
 * edge going to the next layer in each layer except the output layer), and
 * program mode (classification or training mode) that neural net will run in
 *
 * @author lts010, ks061
 */
public class ConfigObject {

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
     * configured with
     */
    private double highestSSE;
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
    private ProgramMode programMode;

    /**
     * Constructor that initializes the number of inputs (input neurons), the
     * number of outputs (output neurons), the number of hidden layers, the
     * number of neurons per hidden layer, the maximum allowed sum of squared
     * error value, list of edge weights (a weight for each edge going to the
     * next layer in each layer except the output layer), and program mode
     * (classification or training mode) that neural net will run in
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
     * Reads a .txt file, a neural network configuration file, that can be read
     * as a ConfigObject
     *
     * @return list of Strings that provides the number of inputs, outputs, and
     * the weights
     *
     * @author lts010, ks061
     */
    public static ArrayList<String> readConfigFile() {
        Scanner in = new Scanner(System.in);
        Scanner fReader = new Scanner(System.in);
        boolean fileFound = false;
        while (!fileFound) {

            System.out.print(
                    "Enter the filename of the configuration file: ");
            String filename = in.nextLine();
            if (filename.substring(filename.lastIndexOf('.') + 1).equals("txt")) {
                try {
                    File f = new File(filename);
                    fReader = new Scanner(f);
                    fileFound = true;
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found. Try again.");
                }
            }
            else {
                System.out.println(
                        "The file entered is not a .txt file. ");
            }
        }
        ArrayList<String> configList = new ArrayList<>();
        while (fReader.hasNextLine()) {
            configList.add(fReader.nextLine());
        }
        return configList;
    }

    /**
     * Saves all configuration information of a neural net to a .txt file
     *
     * @param nN neural network whose configuration will be exported
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author lts010, ks061
     */
    public static void exportConfig(NeuralNet nN) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String prompt = "What .txt file would you like to save the configuration to? ";
        System.out.print(prompt);
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
        List<ArrayList<Double>> thetas = nN.getConfiguration().getThetas().subList(
                1, nN.getConfiguration().getThetas().size());
        String thetaLayer;
        for (ArrayList<Double> thetaList : thetas) {
            thetaLayer = "";
            for (double theta : thetaList) {
                thetaLayer += theta + " ";
            }
            out.printf("%s\n", thetaLayer);
        }
        out.flush();
        out.close();
    }

}
