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

import java.util.*;

/**
 *
 * @author cld028, ks061
 */
public class NeuralNet {

    private double[][] data;
    private ConfigObject configuration;
    private ArrayList<Layer> layers;
    private double[] errors;

    protected static double alpha = 0.2;

    /**
     * A default learning rate if you decide to use this within the neurons
     */
    public final static double DEFAULTALPHA = 0.2;

    /**
     * Explicit constructor that creates the input layer, any hidden layers, and
     * output layer, along with creating connections among the layers. Used only
     * for JUnit Testing
     *
     * @author lts010
     */
    NeuralNet() {
        ArrayList<ArrayList<Double>> weights = new ArrayList<ArrayList<Double>>();
        weights.add(new ArrayList<Double>());
        weights.add(new ArrayList<Double>());
        weights.get(0).add(-0.3);
        weights.get(0).add(0.2);
        weights.get(0).add(0.1);
        weights.get(0).add(-0.2);
        weights.get(1).add(-0.1);
        weights.get(1).add(-0.5);
        ArrayList<ArrayList<Double>> thetas = new ArrayList<ArrayList<Double>>();
        thetas.add(new ArrayList<Double>());
        thetas.add(new ArrayList<Double>());
        thetas.get(0).add(0.1);
        thetas.get(0).add(0.1);
        thetas.get(1).add(0.1);
        ConfigObject config = new ConfigObject(2, 1, 1, 2, 0.001, weights,
                                               thetas, ProgramMode.TRAINING);
        double[][] data = {{1, 1, 1}};

        this.data = data;
        this.configuration = config;
        ArrayList<Layer> layers = new ArrayList<>();
        InputLayer inputLayer = new InputLayer(config.getNumInputs(), "I1-", 0,
                                               this);
        layers.add(inputLayer);
        for (int i = 1; i < this.configuration.getNumHiddenLayers() + 1; i++) {
            layers.add(new HiddenLayer(
                    this.configuration.getNumNeuronsPerHiddenLayer(),
                    "H" + i + "-",
                    i, this));
        }

        OutputLayer outputLayer = new OutputLayer(config.getNumOutputs(), "O1-",
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
        this.layers = layers;
    }

    /**
     * Explicit constructor that creates the input layer, any hidden layers, and
     * output layer, along with creating connections among the layers.
     *
     * @author cld028, ks061, lts010
     */
    NeuralNet(double[][] data, ConfigObject config) {
        if (data.length == 0) {
            throw new NeuralNetConstructionException(
                    "No data has been provided.");
        }

        int dataLength = data[0].length;
        for (double[] inputSet : data) {
            // TODO remove
            System.out.println(Arrays.toString(inputSet));
            if (inputSet.length != dataLength) {
                throw new NeuralNetConstructionException(
                        "Not all rows in data set have the same amount of entries.");
            }
        }
        this.data = data;
        this.configuration = config;

        for (ArrayList<Double> layer : config.getWeights()) {
            System.out.println(layer);
        }
        System.out.println("initial weights are:  ");
        ArrayList<Layer> layers = new ArrayList<>();
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
        for (ArrayList<Double> layer : config.getWeights()) {
            System.out.println(layer);
        }
        do {
            for (double[] inputOutputSet : this.data) {
                inputLayer.setInputs(
                        Arrays.copyOfRange(inputOutputSet, 0,
                                           config.getNumInputs()));
                outputLayer.setTargetOutputs(Arrays.copyOfRange(
                        inputOutputSet,
                        config.getNumInputs(),
                        inputOutputSet.length));
                inputLayer.fireNeurons();
                // System.out.println(outputLayer.calculateSumOfSquaredErrors());
                // Read output layer
                // Back propogate
                // etc.
            }
        } while (outputLayer.calculateSumOfSquaredErrors() > config.getHighestSSE());
        // TODO: change while back to above line
        // } while (i < 10);
        System.out.println("finial weights are:  ");
        for (ArrayList<Double> layer : config.getWeights()) {
            System.out.println(layer);
        }

        //Test your network here
    }

    /**
     * Stores the weight of a particular edge in a particular layer.
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
     * Gets the weight of a particular edge in a particular layer.
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
     * Stores the theta of a particular neuron in a particular layer.
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
     * Gets the theta of a particular neuron in a particular layer.
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
        return this.configuration.getThetas().get(layerNum - 1).get(neuronNum);
    }

    /**
     * Gets the configuration for this neural network
     *
     * @return configuration for this neural network
     */
    public ConfigObject getConfiguration() {
        return this.configuration;
    }

    /**
     * Gets list of layers
     *
     * @return list of layers
     * @author lts010
     */
    public ArrayList<Layer> getLayers() {
        return layers;
    }

}
