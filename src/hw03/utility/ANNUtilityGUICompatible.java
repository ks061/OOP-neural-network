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
* File: ANNUtilityGUICompatible
* Description: This file contains ANNUtilityGUICompatible, which
*              contains a collection of utilities that do not have command line
*              components to them and are geared towards working with/returning
*              information to the MVC ANN application.
* ****************************************
 */
package hw03.utility;

import hw03.ANNConfig;
import hw03.ActivationFunction.ActivationFunction;
import hw03.ActivationFunction.HyperbolicTangentActivationFunction;
import hw03.ActivationFunction.SigmoidActivationFunction;
import hw03.ActivationFunction.StepActivationFunction;
import hw03.Neuron.Neuron;
import hw03.ProgramMode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ANNUtilityGUICompatible contains a collection of utilities that do not have
 * command line components to them and are geared towards working with/returning
 * information to the MVC ANN application.
 *
 * @author ks061, lts010
 */
public class ANNUtilityGUICompatible {

    /**
     * Imports training data from a CSV file specified by the end user; Removes
     * any input sets that have input values outside the range [0,1]. The UI
     * will not allow the
     *
     * @return training data, including sets of inputs and corresponding outputs
     *
     * @author ks061, lts010
     */
    public static double[][] getData(File csvFile) {
        double[][] temp = new double[0][];
        Scanner scanner;
        try {
            // Try to access CSV file
            scanner = new Scanner(csvFile);
        } catch (FileNotFoundException ex) {
            //this shouldn't happen because the file picker only will
            //pick .csv files that exist.
            ANNViewUtility.showInputAlert("Error openning file", ex.toString());
            return new double[0][];
        }
        long lineCountDouble = 0;
        try {
            lineCountDouble = Files.lines(Paths.get(csvFile.toString())).count();
        } catch (IOException ex) {
            ANNViewUtility.showInputAlert("IO Error Occured", ex.toString());
            return new double[0][];
        }
        int lineCount = 0;
        if (-2147483647 <= lineCountDouble && lineCountDouble <= 2147483647) {
            lineCount = (int) lineCountDouble;
        }
        else {
            // NeuralNet.java has specified double[][], which implies that the
            // size of the array cannot be outside of the range of values an
            // int can hold.
            ANNViewUtility.showInputAlert("Error to may lines in file",
                                          "the data set is too large");
            return new double[0][];
        }
        // Initializes an array based on number of lines in CSV file
        String line;
        String[] entriesInLine;
        double[][] data = new double[lineCount][];
        for (int row = 0; scanner.hasNextLine(); row++) {
            line = scanner.nextLine();
            entriesInLine = line.split(",");
            data[row] = new double[entriesInLine.length];
            for (int col = 0; col < data[row].length; col++) {
                try {
                    data[row][col] = Double.parseDouble(entriesInLine[col]);
                } catch (NumberFormatException ex) {
                    break;
                }
            }
        }
        return data;
    }

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
        ArrayList<ArrayList<Double>> weightList = ANNUtility.strListToDoubleList(
                configListWeights);
        ArrayList<ArrayList<Double>> thetas = ANNUtility.strListToDoubleList(
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
        ActivationFunction activationFunction = convertIntToActivationFunction(
                activationInt);
        //TODO fix so that TRAINING mode isn't hardcoded below
        //TODO add to config file?  if so does comand line need to be fixed?
        return new ANNConfig(numInputs, numOutputs,
                             numHiddenLayers,
                             numNeuronsPerHiddenLayer,
                             highestSSE, numMaxEpochs, alpha, mu, weights,
                             thetas, ProgramMode.TRAINING, activationFunction);
    }

    /**
     * Takes an integer, which should range from 0 to 2, and returns an integer
     * corresponding to the type of ActivationFunction, Step function for 1,
     * Hyperbolic Tangent function for 2, and Sigmoid function for 0 or any
     * other integer. The corresponding radio button is also selected.
     *
     * @param integer - an integer (should range from 0 to 2)
     * @return - an activation function that corresponds to the inputted integer
     *
     * @author lts010, ks061
     */
    public static ActivationFunction convertIntToActivationFunction(int integer) {
        if (integer == 0) {
            return new SigmoidActivationFunction();
        }
        else if (integer == 1) {
            return new StepActivationFunction();
        }
        else if (integer == 2) {
            return new HyperbolicTangentActivationFunction();
        }
        else {
            return new SigmoidActivationFunction();
        }
    }

    /**
     * Takes an ActivationFunction and returns a corresponding integer, if it is
     * a Sigmoid function it returns 0, if it is a Step function it returns 1,
     * otherwise returns 2
     *
     * @param activationFunction - an activation function
     * @return - an integer that corresponds to what kind of activation function
     * is inputted
     *
     * @author lts010, ks061
     */
    public static int convertActivationFunctionToInt(
            ActivationFunction activationFunction) {
        if (activationFunction instanceof SigmoidActivationFunction) {
            return 0;
        }
        else if (activationFunction instanceof StepActivationFunction) {
            return 1;
        }
        else {
            return 2;
        }
    }

}
