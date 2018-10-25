/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw02
 * File: NeuralNet
 * Description: This file contains NeuralNet, which represents an artificial
 *              neural network.
 *
 * ****************************************
 */
package hw03;

import hw03.Layer.HiddenLayer;
import hw03.Layer.InputLayer;
import hw03.Layer.Layer;
import hw03.Layer.OutputLayer;
import hw03.Neuron.Neuron;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

/**
 * NeuralNet represents an artificial neural network.
 *
 * @author cld028, ks061, lts010
 */
public class NeuralNet implements Serializable {

    /**
     * Data that this neural network is fed, including input data and expected
     * output data.
     */
    private transient double[][] data;

    /**
     * Configuration of the neural network
     */
    private ANNConfig configuration;

    /**
     * Layers in this neural network
     */
    private transient ArrayList<Layer> layers;

    /**
     * default learning rate of this neural network
     */
    public static final double DEFAULT_ALPHA = 0.2;

    /**
     * learning rate of this neural network
     */
    public double alpha = DEFAULT_ALPHA;

    /**
     * Length of time to train this network
     */
    private double trainingTime;

    /**
     * The average sum of squared errors over all epochs
     */
    private double trainingAverageSSE;

    /**
     * The sum of squared errors for the last epoch
     */
    private double trainingFinalSSE;

    /**
     * The number of epochs used to train this network
     */
    private int trainingNumberOfEpochs;

    /**
     * Constructor that sets pointers to the input and/or output data array and
     * configuration and creates the input layer, any hidden layers, and output
     * layer based on preferences stored in the configuration, along with
     * creating connections between adjacent layers
     *
     * @param data input and/or output data to be used by the neural network
     * @param configuration configuration of the neural network
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author cld028, ks061, lts010
     */
    public NeuralNet(double[][] data, ANNConfig configuration) throws FileNotFoundException {
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
        this.configuration = configuration;

        initializeLayers();
    }

    /**
     * Sets the input and/or output data array to be used by the neural network
     *
     * @param data input and/or output data array to be used by the neural
     * network
     *
     * @author ks061, lts010
     */
    public void setData(double[][] data) {
        this.data = data;
    }

    /**
     * Creates the input layer, any hidden layers, and output layer based on the
     * preferences stored in the configuration, along with creating connections
     * between adjacent layers
     *
     * @author ks061, lts010
     */
    public void initializeLayers() {
        layers = new ArrayList<>();
        InputLayer inputLayer = new InputLayer(this.configuration.getNumInputs(),
                                               "I1-",
                                               0,
                                               this);
        layers.add(inputLayer);

        for (int i = 1; i < this.configuration.getNumHiddenLayers() + 1; i++) {
            layers.add(new HiddenLayer(
                    this.configuration.getNumNeuronsPerHiddenLayer(),
                    "H" + i + "-",
                    i, this));
        }

        OutputLayer outputLayer = new OutputLayer(
                this.configuration.getNumOutputs(),
                "O1-",
                layers.size(), this);
        layers.add(outputLayer);

        connectLayers();
    }

    /**
     * Creates connections between adjacent layers
     *
     * @author ks061, lts010
     */
    private void connectLayers() {
        Iterator it = layers.iterator();
        Layer currentLayer = (Layer) it.next();
        Layer nextLayer;
        while (it.hasNext()) {
            nextLayer = (Layer) it.next();
            currentLayer.connectLayer(nextLayer);
            currentLayer = nextLayer;
        }
    }

    /**
     * Asks the user whether they would like to test their trained neural
     * network on a new data set; if they would like to use a new data set, the
     * method reads in the new data set from the file that the user specifies
     *
     * @return true if the user would like to continue with classifying;
     * otherwise false
     *
     * @author ks061, lts010
     */
    private boolean shouldClassify() {
        System.out.println("The neural network is now trained.");
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Would you like to run a test data set? (y or yes for yes; anything else for no): ");
        String willClassify = in.nextLine();

        if (willClassify.equalsIgnoreCase("y") || willClassify.equalsIgnoreCase(
                "yes")) {
            System.out.print(
                    "Do you want a different file? (y or yes for yes; anything else for no): ");
            String wantsNewFile = in.nextLine();
            if (wantsNewFile.equalsIgnoreCase("y") || wantsNewFile.equalsIgnoreCase(
                    "yes")) {
                this.data = ANNClient.getData();
            }
            return true;
        }
        return false;
    }

    /**
     * Runs the neural network in the training mode, which trains a neural
     * network based on sets of input and corresponding output values
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
        double sseTotal = 0;
        double sseEpochTotal;
        int maxEpochs = configuration.getNumMaxEpochs();
        long startTime = System.nanoTime();
        int numEpoch = 0;
        InputLayer inputLayer = ((InputLayer) this.layers.get(0));
        OutputLayer outputLayer = ((OutputLayer) this.layers.get(
                                   this.layers.size() - 1));
        do {
            sseEpochTotal = 0;
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
                sseEpochTotal += outputLayer.calculateSumOfSquaredErrors();

                ANNUtility.logEpochAndWeights(numEpoch, inputOutputSet, this);
            }
            sseTotal += sseEpochTotal;
            numEpoch++;
        } while (sseEpochTotal > this.configuration.getHighestSSE() && numEpoch <= maxEpochs);

        if (numEpoch > maxEpochs) {
            System.out.println(
                    "\nUnable to train neural network in " + maxEpochs + " iterations.\n");
            System.out.println("Thanks for using the program.");
            return;
        }

        this.trainingTime = (System.nanoTime() - startTime) / 1000000000.0;
        this.trainingFinalSSE = sseEpochTotal;
        this.trainingAverageSSE = sseTotal / numEpoch;
        this.trainingNumberOfEpochs = numEpoch;
        System.out.println("Neural network has been trained successfully");
        System.out.println("with the following perfomance peramenters:");
        System.out.println(
                "\tFinal sum of squared errors: " + this.trainingFinalSSE);
        System.out.println(
                "\tAverage sum of squared errors: " + this.trainingAverageSSE);
        System.out.println(
                "\tNumber of epochs used: " + this.trainingNumberOfEpochs);
        System.out.println("\tTraining time (seconds): " + this.trainingTime);

        ANNUtility.logFooter(this);

        if (shouldClassify()) {
            classify();
        }
    }

    /**
     * Runs the neural network in classification mode, which predicts output
     * values based on a set of input values and an already configured neural
     * network.
     *
     * @return set of predicted outputs for each output field and for each input
     * set
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author ks061, lts010
     */
    public ArrayList<ArrayList<Double>> classify() throws FileNotFoundException {
        InputLayer inputLayer = ((InputLayer) this.layers.get(0));
        OutputLayer outputLayer = ((OutputLayer) this.layers.get(
                                   this.layers.size() - 1));

        boolean shouldTestData = false;
        double sse = 0;
        if (data[0].length == this.configuration.getNumInputs() + this.configuration.getNumOutputs()) {
            shouldTestData = true;
        }
        else {
            outputLayer.setTargetOutputs(new double[0]);
        }

        ArrayList<ArrayList<Double>> setsOfPredictedOutputs = new ArrayList<>();

        for (double[] inputOutputSet : this.data) {
            inputLayer.setInputs(
                    Arrays.copyOfRange(inputOutputSet, 0,
                                       this.configuration.getNumInputs()));
            if (shouldTestData) {
                outputLayer.setTargetOutputs(
                        Arrays.copyOfRange(
                                inputOutputSet,
                                this.configuration.getNumInputs(),
                                inputOutputSet.length));
            }
            inputLayer.fireNeurons();
            System.out.println("For the set of inputs " + Arrays.toString(
                    Arrays.copyOfRange(inputOutputSet, 0,
                                       this.configuration.getNumInputs())) + ", the set of outputs is: " + Arrays.toString(
                    getSetOfPredictedOutputs(outputLayer).toArray()));
            setsOfPredictedOutputs.add(getSetOfPredictedOutputs(outputLayer));

            if (shouldTestData) {
                sse += outputLayer.calculateSumOfSquaredErrors();
            }
        }

        if (shouldTestData) {
            System.out.println(
                    "The total sum of squared errors for all inputs is " + sse);
        }
        return setsOfPredictedOutputs;
    }

    /**
     * Gets the set of predicted outputs from the neurons in the output layer
     *
     * @param outputLayer output layer
     *
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
     * @return weight of an edge in the <code>layerNum</code>-th layer and has a
     * numerical identifier of edgeNum
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
    public ANNConfig getConfiguration() {
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

    /**
     * Gets the learning rate of this neural network
     *
     * @return the learning rate of this neural network
     * @author lts010
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * Sets the learning rate of this neural network
     *
     * @param alpha - the new learning rate of this neural network
     * @author lts010
     */
    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

}
