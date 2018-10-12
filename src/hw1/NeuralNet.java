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
 * Description: This file contains NeuralNet, which represents an artificial
 *              neural network.
 *
 * ****************************************
 */
package hw1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * NeuralNet represents an artificial neural network.
 *
 * @author cld028, ks061
 */
public class NeuralNet {

    /**
     * Data that this neural network is fed, including input data and expected
     * output data.
     */
    private double[][] data;
    /**
     * Configuration of the neural network, including information like the
     * number of inputs (input neurons), the number of outputs (output neurons),
     * the number of hidden layers, the number of neurons per hidden layer, the
     * maximum allowed sum of squared error value (SSE), list of edge weights (a
     * weight for each edge going to the next layer in each layer except the
     * output layer), and program mode (classification or training mode) that
     * neural net will run in
     */
    private ConfigObject configuration;
    /**
     * Layers in this neural network
     */
    private ArrayList<Layer> layers;
    /**
     * Learning rate of this neural network
     */
    public static final double alpha = 0.2;

    /**
     * Constructor that creates the input layer, any hidden layers, and output
     * layer, along with creating connections among the layers.
     *
     * @param data training data, including sets of input and output training
     * data
     * @param config configuration of the neural network, including the number
     * of inputs (input neurons), the number of outputs (output neurons), the
     * number of hidden layers, the number of neurons per hidden layer, the
     * maximum allowed sum of squared error value (SSE), list of edge weights (a
     * weight for each edge going to the next layer in each layer except the
     * output layer), and program mode (classification or training mode) that
     * neural net will run in
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author cld028, ks061, lts010
     */
    public NeuralNet(double[][] data, ConfigObject config) throws FileNotFoundException {
        if (data.length == 0) {
            throw new NeuralNetConstructionException(
                    "No data has been provided.");
        }

        int dataLength = data[0].length;
        for (double[] inputSet : data) {
            if (inputSet.length != dataLength) {
                throw new NeuralNetConstructionException(
                        "Not all rows in data set have the same amount of entries.");
            }
        }

        this.data = data;
        this.configuration = config;

        layers = new ArrayList<>();
        InputLayer inputLayer = new InputLayer(config.getNumInputs(), "I1-",
                                               0,
                                               this);
        layers.add(inputLayer);
        for (int i = 1; i < this.configuration.getNumHiddenLayers() + 1; i++) {
            layers.add(new HiddenLayer(
                    this.configuration.getNumNeuronsPerHiddenLayer(),
                    "H" + i + "-",
                    i, this));
        }

        OutputLayer outputLayer = new OutputLayer(config.getNumOutputs(),
                                                  "O1-",
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

        if (this.configuration.getProgramMode() == ProgramMode.TRAINING) {
            train();
        }

        if (this.configuration.getProgramMode() == ProgramMode.CLASSIFICATION) {
            classify();
        }
    }

    /**
     * Runs the neural network in the training mode, which trains a neural
     * network based on sets of input and corresponding output values.
     *
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author ks061, lts010
     */
    public void train() throws FileNotFoundException {
        double sseTotal;
        InputLayer inputLayer = ((InputLayer) this.layers.get(0));
        OutputLayer outputLayer = ((OutputLayer) this.layers.get(
                                   this.layers.size() - 1));
        do {
            sseTotal = 0;
            for (double[] inputOutputSet : this.data) {
                inputLayer.setInputs(
                        Arrays.copyOfRange(inputOutputSet, 0,
                                           this.configuration.getNumInputs()));
                outputLayer.setTargetOutputs(
                        Arrays.copyOfRange(
                                inputOutputSet,
                                this.configuration.getNumInputs(),
                                inputOutputSet.length));
                inputLayer.fireNeurons();
                sseTotal += outputLayer.calculateSumOfSquaredErrors();
            }
        } while (sseTotal > this.configuration.getHighestSSE());
        Scanner in = new Scanner(System.in);
        System.out.println(
                "Neural network has been trained successfully with a sum of squared errors of " + sseTotal
                + ".");
        System.out.print(
                "Would you like to save the configuration of this neural network to a file? (enter y for yes; anything else for no) ");
        String willSaveToFile = in.nextLine();
        if (willSaveToFile.equalsIgnoreCase("y")) {
            ConfigObject.exportConfig(this);
        }
        System.out.println("Thanks for using the program.");
    }

    /**
     * Gets the set of predicted outputs from the neurons in the output layer
     *
     * @param outputLayer output layer
     * @return set of predicted outputs from the neurons in the output layer
     *
     * @author ks061, lts010
     */
    private ArrayList<Double> getSetOfPredictedOutputs(OutputLayer outputLayer) {
        ArrayList<Double> setOfPredictedOutputs = new ArrayList<>();
        for (Neuron neuron : outputLayer.getNeurons()) {
            setOfPredictedOutputs.add(neuron.getNetValue());
        }
        return setOfPredictedOutputs;
    }

    /**
     * Saves the set of predicted outputs from the neurons to an external .txt
     * file; it prompts the user for the output filename, etc. as needed.
     *
     * @param setsOfPredictedOutputs set of predicted outputs from the neurons
     * in the output layer
     * @throws FileNotFoundException if the file for the configuration to be
     * written to as specified by the user cannot be written to or another error
     * occurs while opening or creating the file
     * @see
     * <a href src="https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html">
     * PrintWriter </a>
     *
     * @author ks061, lts010
     */
    private void saveSetsOfPredictedOutputs(
            ArrayList<ArrayList<Double>> setsOfPredictedOutputs) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        String prompt = "What .txt file would you like to save the configuration to? ";
        System.out.print(prompt);
        String outputFile = in.next();
        PrintWriter out = new PrintWriter(outputFile);
        for (ArrayList<Double> setOfPredictedOutput : setsOfPredictedOutputs) {
            out.print(Arrays.toString(setOfPredictedOutput.toArray()));
            // System.out.println("Output: " + Arrays.toString(
            // setOfPredictedOutput.toArray()));
        }
        out.flush();
        out.close();
    }

    /**
     * Runs the neural network in classification mode, which predicts output
     * values based on a set of input values and an already configured neural
     * network.
     *
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author ks061, lts010
     */
    public void classify() throws FileNotFoundException {
        InputLayer inputLayer = ((InputLayer) this.layers.get(0));
        OutputLayer outputLayer = ((OutputLayer) this.layers.get(
                                   this.layers.size() - 1));
        ArrayList<ArrayList<Double>> setsOfPredictedOutputs = new ArrayList<>();
        for (double[] inputOutputSet : this.data) {
            inputLayer.setInputs(
                    Arrays.copyOfRange(inputOutputSet, 0,
                                       this.configuration.getNumInputs()));
            inputLayer.fireNeurons();
            System.out.println("For the set of inputs " + Arrays.toString(
                    Arrays.copyOfRange(inputOutputSet, 0,
                                       this.configuration.getNumInputs())) + ", the set of outputs is: " + Arrays.toString(
                    getSetOfPredictedOutputs(outputLayer).toArray()));
            setsOfPredictedOutputs.add(getSetOfPredictedOutputs(outputLayer));
        }
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Would you like to save the predicted outputs to a file? (enter y for yes; anything else for no) ");
        String willSaveToFile = in.nextLine();
        if (willSaveToFile.equalsIgnoreCase("y")) {
            saveSetsOfPredictedOutputs(setsOfPredictedOutputs);
        }
        System.out.println("Thanks for using the program.");
    }

    /**
     * Stores the weight of a particular edge in a particular layer
     *
     * @param layerNum layer that the edge resides in
     * @param edgeNum numerical identifier of the edge
     * @param newWeight weight that will be stored for the edge
     *
     * @author lts010, ks061
     */
    public void storeWeight(int layerNum, int edgeNum, double newWeight) {
        this.configuration.getWeights().get(layerNum).set(edgeNum, newWeight);
    }

    /**
     * Gets the weight of a particular edge in a particular layer
     *
     * @param layerNum layer that the edge resides in
     * @param edgeNum numerical identifier of the edge
     *
     * @return weight of an edge in layerNumth layer and has a numerical
     * identifier of edgeNum
     *
     * @author lts010, ks061
     */
    public double getWeight(int layerNum, int edgeNum) {
        return this.configuration.getWeights().get(layerNum).get(edgeNum);
    }

    /**
     * Stores the theta of a particular neuron in a particular layer
     *
     * @param layerNum layer that the neuron resides in
     * @param neuronNum numerical identifier of the neuron
     * @param newTheta theta that will be stored for the neuron
     *
     * @author lts010, ks061
     */
    public void storeTheta(int layerNum, int neuronNum, double newTheta) {
        this.configuration.getThetas().get(layerNum).set(neuronNum, newTheta);
    }

    /**
     * Gets the theta of a particular neuron in a particular layer
     *
     * @param layerNum layer that the neuron resides in
     * @param neuronNum numerical identifier of the neuron
     *
     * @return theta of a neuron in layerNumth layer and has a numerical
     * identifier of neuronNum
     *
     * @author lts010, ks061
     */
    public double getTheta(int layerNum, int neuronNum) {
        return this.configuration.getThetas().get(layerNum).get(neuronNum);
    }

    /**
     * Gets the configuration for this neural network
     *
     * @return configuration for this neural network
     *
     * @author ks061, lts010
     */
    public ConfigObject getConfiguration() {
        return this.configuration;
    }

    /**
     * Gets list of layers in the neural network
     *
     * @return list of layers in the neural network
     *
     * @author lts010, ks061
     */
    public ArrayList<Layer> getLayers() {
        return layers;
    }

}
