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
* File: ANNUtility
* Description: This file contains ANNUtility, which contains utilities to aid
*              a neural network program and client.
* ****************************************
 */
package hw03.utility;

import hw03.model.neuralnet.NeuralNet;
import hw03.model.neuralnet.logger.ANNLogger;
import hw03.model.neuralnet.neuron.Neuron;
import hw03.model.neuralnet.weightassignment.RandomWeightAssignment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * ANNUtility contains utilities to aid a neural network program and client.
 *
 * @author ks061, lts010
 */
public class ANNUtility {

    /**
     * Requests a filename from the user with a specific message until the
     * filename extension matches the specified extension and gets the filename
     * provided the file exists, can be read, and matches the specified
     * extension.
     *
     * @param extension filename extension to check for in filename entered by
     * user
     * @param prompt message with which to ask the user for a filename
     *
     * @return filename entered by user provided the file exists, can be read,
     * and matches the specified extension <code>extension</code>.
     *
     * @author ks061, lts010
     */
    public static String getInputFilename(String extension, String prompt) {
        String filename = "";
        Scanner in = new Scanner(System.in);
        Scanner fReader = new Scanner(System.in);
        boolean fileOK = false;
        while (!fileOK) {
            System.out.print(prompt);
            filename = in.nextLine();
            if (filename.endsWith(extension)) {
                File file = new File(filename);
                if (file.isFile() && file.canRead()) {
                    fileOK = true;
                }
                else {
                    System.out.println(
                            "The name you enter is not a readable file.");
                    System.out.println("Please try again.");
                }
            }
            else {
                System.out.println("The file must end in ." + extension + ".");
                System.out.println("Please try again.");
            }
        }
        return filename;
    }

    /**
     * Requests a filename from the user with a specific message until the
     * filename extension matches the specified extension and gets the filename
     * provided that the filename matches the specified extension
     *
     * @param extension filename extension to check for in filename entered by
     * user
     * @param prompt message with which to ask the user for a filename
     *
     * @return filename entered by user provided that the filename matches the
     * specified extension <code>extension</code>.
     *
     * @author ks061, lts010
     */
    public static String getOutputFilename(String extension, String prompt) {
        String filename = "";
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(prompt);
            filename = in.nextLine();
            if (filename.endsWith(extension)) {
                return filename;
            }
            else {
                System.out.println("The file must end in ." + extension + ".");
                System.out.println("Please try again.");
            }
        }
    }

    /**
     * Gives the end user the provided prompt and requests an integer
     *
     * @param prompt prompt given to end user
     * @return the integer entered by end user
     *
     * @author lts010, ks061
     */
    public static int getIntInput(String prompt) {
        Scanner in = new Scanner(System.in);
        int result = -1;
        while (true) {
            System.out.print(prompt);
            if (in.hasNextInt()) {
                result = in.nextInt();
                if (result >= 0) {
                    break;
                }
            }
            System.out.println("INVALID INPUT\n\n." + prompt);
        }
        return result;
    }

    /**
     * Gives the end user the provided prompt and requests a double
     *
     * @param prompt prompt given to end user
     * @return the double entered by end user
     *
     * @author lts010, ks061
     */
    public static double getDoubleInput(String prompt) {
        Scanner in = new Scanner(System.in);
        double result = -1.0;
        while (true) {
            System.out.print(prompt);
            if (in.hasNextDouble()) {
                result = in.nextDouble();
                if (result >= 0) {
                    break;
                }
            }
            System.out.println("INVALID INPUT\n\n." + prompt);
        }
        return result;
    }

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
     * Writes the header of the ANN log file, including the ANN authors, current
     * time and date, starting parameters, starting theta values, and starting
     * weights at the start of a training session
     *
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logHeader(NeuralNet myNet) {
        ANNLogger.write("ANN-(ks061 & lts010), " + new SimpleDateFormat(
                "MM/dd/yyyy").format(new Date()) + ", " + new SimpleDateFormat(
                "HH:mm:ss").format(new Date()));
        ANNLogger.write("---");
        ANNLogger.write("Number of input neurons: ," + Integer.toString(
                myNet.getConfiguration().getNumInputs()));
        ANNLogger.write("Number of output neurons: ," + Integer.toString(
                myNet.getConfiguration().getNumOutputs()));
        ANNLogger.write("Number of hidden layers: ," + Integer.toString(
                myNet.getConfiguration().getNumHiddenLayers()));
        ANNLogger.write(
                "Number of neurons per hidden layer: ," + Integer.toString(
                        myNet.getConfiguration().getNumNeuronsPerHiddenLayer()));
        ANNLogger.write(
                "Maximum SSE (sum of squared errors): ," + Double.toString(
                        myNet.getConfiguration().getHighestSSE()));
        ANNLogger.write("Maximum number of epochs: ," + Integer.toString(
                myNet.getConfiguration().getNumMaxEpochs()));
        ANNLogger.write("THETAS");
        for (int layerIndex = 0; layerIndex < myNet.getConfiguration().getWeights().size(); layerIndex++) {
            ANNLogger.write("Theta values for layer " + Integer.toString(
                    layerIndex) + ":," + myNet.getConfiguration().getThetas().get(
                            layerIndex).toString().substring(1,
                                                             myNet.getConfiguration().getThetas().get(
                                                                     layerIndex).toString().length() - 1));
        }
        ANNLogger.write("---");

        logWeights(myNet);
    }

    /**
     * Logs weights of each layer during a particular epoch for a particular
     * input-output set during a training session
     *
     * @param numEpoch number of the epoch training session is processing
     * @param inputOutputSet input-output set training session is processing
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logEpochAndWeights(int numEpoch, double[] inputOutputSet,
                                          NeuralNet myNet) {
        ANNLogger.write(
                "EPOCH " + Integer.toString(numEpoch) + ", " + new SimpleDateFormat(
                "MM/dd/yyyy").format(new Date()) + " at " + new SimpleDateFormat(
                "HH:mm:ss").format(new Date()));
        ANNLogger.write(
                "INPUT -," + Arrays.toString(inputOutputSet).substring(1,
                                                                       Arrays.toString(
                                                                               inputOutputSet).length() - 1));
        ANNLogger.write("---");

        logWeights(myNet);
    }

    /**
     * Logs current weights of each layer
     *
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logWeights(NeuralNet myNet) {
        String layerType = null;

        for (int layerIndex = 0; layerIndex < myNet.getConfiguration().getWeights().size(); layerIndex++) {
            if (layerIndex == 0) {
                layerType = "InputLayer";
            }
            else if (layerIndex != myNet.getConfiguration().getWeights().size() - 1) {
                layerType = "OutputLayer";
            }
            else {
                layerType = "HiddenLayer";
            }
            ANNLogger.write(layerType + ", " + Integer.toString(layerIndex));
            try {
                ANNLogger.write(
                        "EDGE WEIGHTS -," + myNet.getConfiguration().getWeights().get(
                                layerIndex).toString().substring(1,
                                                                 myNet.getConfiguration().getWeights().get(
                                                                         layerIndex).toString().length() - 1));
            } catch (StringIndexOutOfBoundsException ex) {
                ANNLogger.write("EDGE WEIGHTS -");
            }
        }

        ANNLogger.write("---");
    }

    /**
     * Logs footer of the log file
     *
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logFooter(NeuralNet myNet) {
        ANNLogger.write("Training Ended");
        ANNLogger.write(new SimpleDateFormat(
                "MM/dd/yyyy").format(new Date()) + "," + new SimpleDateFormat(
                "HH:mm:ss").format(new Date()));
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
}
