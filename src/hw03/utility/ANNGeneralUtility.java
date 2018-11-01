/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 17, 2018
* Time: 7:17:19 PM
*
* Project: csci205_proj_hw
* Package: hw02
* File: ANNGeneralUtility
* Description: This file contains ANNGeneralUtility, which contains general
*              utilities for the ANN program.
* ****************************************
 */
package hw03.utility;

import hw03.model.neuralnet.activationfunction.ActivationFunction;
import hw03.model.neuralnet.activationfunction.HyperbolicTangentActivationFunction;
import hw03.model.neuralnet.activationfunction.SigmoidActivationFunction;
import hw03.model.neuralnet.activationfunction.StepActivationFunction;
import hw03.model.neuralnet.neuron.Neuron;
import hw03.model.neuralnet.weightassignment.RandomWeightAssignment;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ANNGeneralUtility contains general utilities for the ANN program.
 *
 * @author ks061, lts010
 */
public class ANNGeneralUtility {

    /**
     * Parses doubles from each string in a list of strings and returns a list
     * of lists of doubles (doubles in each line for multiple lines)
     *
     * @param strList list of strings
     * @return list of lists of doubles (doubles in each line for multiple
     * lines)
     *
     * @see
     * <a href ="https://stackoverflow.com/questions/11009818/how-to-get-list-of-integer-from-string">stackoverflow</a>
     *
     * @author lts010, ks061
     */
    public static ArrayList<ArrayList<Double>> strListToDoubleList(
            ArrayList<String> strList) {
        Scanner strReader = null;
        ArrayList<ArrayList<Double>> doubleList = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            doubleList.add(new ArrayList<>());
            strReader = new Scanner(strList.get(i));
            while (strReader.hasNextDouble()) {
                doubleList.get(i).add(strReader.nextDouble());
            }
        }
        return doubleList;
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
    public static ArrayList<ArrayList<Double>> getDefaultListOfThetas(
            int numOutputs,
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
     * Creates and returns a 2D list of random weights for each edge in each
     * layer of the neural network. The weights are given a random value from
     * the set of all numbers within the range -2.4 divided by the number of
     * input edges to 2.4 divided by the number of input edges.
     *
     * @param numInputs number of input neurons in the neural network the
     * program will use
     * @param numOutputs number of output neurons in the neural network the
     * program will use
     * @param numHiddenLayers number of hidden layers in the neural network the
     * program will use
     * @param numNeuronsPerHiddenLayer number of neurons per hidden layer in the
     * neural network the program will use
     * @return 2D list of randomly generated weights for each edge in each layer
     * of the neural network that will be used by the program
     *
     * @author lts010, ks061
     */
    public static ArrayList<ArrayList<Double>> getRandomWeights(int numInputs,
                                                                int numOutputs,
                                                                int numHiddenLayers,
                                                                int numNeuronsPerHiddenLayer) {
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        RandomWeightAssignment weightAssign = new RandomWeightAssignment();
        weights.add(new ArrayList<>());
        if (numHiddenLayers == 0) {
            for (int i = 0; i < (numInputs * numOutputs); i++) {
                weights.get(0).add(
                        weightAssign.assignWeight(numInputs) / numInputs);
            }
        }
        else {
            for (int i = 0; i < (numInputs * numNeuronsPerHiddenLayer); i++) {
                //weights from the input layer connecting to the first hidden layer
                weights.get(0).add(
                        weightAssign.assignWeight(numInputs) / numInputs);
            }
            for (int i = 1; i < numHiddenLayers; i++) {
                //weights for hidden layers that are connected to layer
                weights.add(new ArrayList<>());
                for (int j = 0; j < (numNeuronsPerHiddenLayer * numNeuronsPerHiddenLayer); j++) {
                    weights.get(i).add(weightAssign.assignWeight(
                            numNeuronsPerHiddenLayer) / numNeuronsPerHiddenLayer);
                }
            }
            weights.add(new ArrayList<>());
            for (int i = 0; i < numOutputs * numNeuronsPerHiddenLayer; i++) {
                //weights for the output layer connecting to the last hidden layer
                weights.get(weights.size() - 1).add(weightAssign.assignWeight(
                        numNeuronsPerHiddenLayer) / numNeuronsPerHiddenLayer);
            }
        }
        return weights;
    }

    /**
     * Takes an integer, which should range from 0 to 2, and returns an integer
     * corresponding to the type of ActivationFunction, Step function for 1,
     * Hyperbolic Tangent function for 2, and Sigmoid function for 0 or any
     * other integer. The corresponding radio button is also selected.
     *
     * @param integer - an integer (should range from 0 to 2); 0 for Sigmoid
     * function, 1 for step function, 2 for hyperbolic Tangent function
     *
     * @return - an activation function that corresponds to the inputted integer
     *
     * @author lts010, ks061
     */
    public static ActivationFunction convertIntToActivationFunction(int integer) {
        switch (integer) {
            case 0:
                return new SigmoidActivationFunction();
            case 1:
                return new StepActivationFunction();
            case 2:
                return new HyperbolicTangentActivationFunction();
            default:
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
