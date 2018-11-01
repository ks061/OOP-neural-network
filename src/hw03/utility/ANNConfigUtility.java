/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 27, 2018
* Time: 3:03:41 AM
*
* Project: csci205_proj_hw
* Package: hw03.utility
* File: ANNConfigUtility
* Description: This file contains ANNConfigUtility, which
*              contains a collection of utilities that deal related to
*              configurations for the neural network.
* ****************************************
 */
package hw03.utility;

import hw03.model.neuralnet.ANNConfig;
import hw03.model.neuralnet.ProgramMode;
import hw03.model.neuralnet.activationfunction.ActivationFunction;
import hw03.model.neuralnet.neuron.Neuron;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ANNConfigUtility contains a collection of utilities that deal related to
 * configurations for the neural network.
 *
 * @author ks061, lts010
 */
public class ANNConfigUtility {

    /**
     * Gets a 2D list with default theta values for each neuron in each layer in
     * the neural network; the default theta value is set as a constant field in
     * the Neuron class.
     *
     * @param numOutputs number of output neurons in the neural network the
     * program will use
     * @param numHiddenLayers number of hidden layers in the neural network the
     * program will use
     * @param numNeuronsPerHiddenLayer number of neurons per hidden layer in the
     * neural network the program will use
     *
     * @return 2D list with default theta values for each neuron in each layer
     * in the neural network; the default theta value is set as a constant field
     * in the Neuron class.
     *
     * @author ks061, lts010
     */
    public static ArrayList<ArrayList<Double>> getListOfThetas(int numOutputs,
                                                               int numHiddenLayers,
                                                               int numNeuronsPerHiddenLayer) {
        ArrayList<ArrayList<Double>> listOfThetas = new ArrayList<>();
        listOfThetas.add(new ArrayList<>());
        for (int i = 1; i < numHiddenLayers + 1; i++) {
            listOfThetas.add(new ArrayList<>());
            for (int j = 0; j < numNeuronsPerHiddenLayer; j++) {
                listOfThetas.get(i).add(Neuron.DEFAULTTHETA);
            }
        }
        listOfThetas.add(new ArrayList<>());
        for (int i = 0; i < numOutputs; i++) {
            listOfThetas.get(listOfThetas.size() - 1).add(Neuron.DEFAULTTHETA);
        }
        return listOfThetas;
    }

    /**
     * Creates a neural network configuration read in from a specified file
     *
     * @param file file to import neural network configuration from
     * @throws FileNotFoundException if the specified file could not be found
     *
     * @return neural network configuration as read in from the specified file
     *
     * @author ks061, lts010
     */
    public static ANNConfig createConfigurationFromFile(File file) throws FileNotFoundException {
        ArrayList<String> configList = new ArrayList<>();
        //  File f = new File(filename);
        Scanner fReader = new Scanner(file);
        while (fReader.hasNextLine()) {
            configList.add(fReader.nextLine());
        }
        int thetaIndex = configList.indexOf("THETAS"); // indicates which index the thetas start at
        ArrayList<String> configListWeights = new ArrayList<>(
                configList.subList(0,
                                   thetaIndex)); // seperates the weights (and the numbers before the weights) into a list before the string "THETA"
        ArrayList<String> configListThetas = new ArrayList<>( // seperates the thetas into a list after the string "THETA"
                configList.subList(thetaIndex + 1, configList.size()));
        ArrayList<ArrayList<Double>> weightList = ANNGeneralUtility.strListToDoubleList(
                configListWeights);
        ArrayList<ArrayList<Double>> thetas = ANNGeneralUtility.strListToDoubleList(
                configListThetas); // turns the theta list into a list of list of doubles
        thetas.add(0, new ArrayList<>()); // adds input layer without any weights
        int numInputs = (int) Math.round(weightList.get(0).get(0));
        int numOutputs = (int) Math.round(weightList.get(0).get(1));
        int numHiddenLayers = (int) Math.round(weightList.get(0).get(2));
        int numNeuronsPerHiddenLayer = (int) Math.round(weightList.get(0).get(3));
        double highestSSE = weightList.get(0).get(4);
        int numMaxEpochs = (int) Math.round(weightList.get(0).get(5));
        ArrayList<ArrayList<Double>> weights = new ArrayList<>(
                weightList.subList(1,
                                   configListWeights.size()));
        double alpha = weightList.get(0).get(6);
        double mu = weightList.get(0).get(7);
        int activationInt = (int) Math.round(weightList.get(0).get(8));
        ActivationFunction activationFunction = ANNGeneralUtility.convertIntToActivationFunction(
                activationInt);
        //TODO fix so that TRAINING mode isn't hardcoded below
        //TODO add to config file?  if so does comand line need to be fixed?
        return new ANNConfig(numInputs, numOutputs,
                             numHiddenLayers,
                             numNeuronsPerHiddenLayer,
                             highestSSE, numMaxEpochs, alpha, mu, weights,
                             thetas, ProgramMode.TRAINING, activationFunction);
    }

}
