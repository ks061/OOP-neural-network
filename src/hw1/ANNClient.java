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
 * File: ANNClient
 * Description: Main file that interacts with the user to either run the neural
 *              network program in classification mode, which predicts what the
 *              output will be based on given inputs, or training mode, which
 *              trains the neural net program based on given inputs and
 *              expected output.
 *
 * ****************************************
 */
package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ANNClient contains the main runner that interacts with the user that either
 * runs the neural network program in classification mode, which predicts what
 * the output will be based on given inputs, or training mode, which trains the
 * neural net program based on given inputs and expected output.
 *
 * @author cld028, ks061, lts010
 */
public class ANNClient {

    /**
     * Enumeration that helps distinguish whether the program will generate
     * neural net edge weights from a configuration file or randomly assign them
     */
    enum WeightsMode {
        READ, CREATE;
    }

    /**
     * Neural network generated/used by the neural network program
     */
    private static NeuralNet myNet;

    /**
     * Gets the mode the program creates the neural network with (can be READ or
     * CREATE)
     *
     * @return the mode that creates the neural network
     * @author lts010, ks061
     */
    private static WeightsMode getInputMode() {
        int response = -1;
        boolean invalidResponse = true;
        WeightsMode mode = WeightsMode.READ;
        String prompt = "Enter 1 or 2 for the desired method of creating the "
                        + "neural network\n\n"
                        + "1 -- Read a config file\n2 -- Create a new ANN\n";
        while (invalidResponse) {
            response = getIntInput(prompt);
            if (response != 1 && response != 2) {
                System.out.println("INVALID INPUT\n\n.");
            }
            else {
                invalidResponse = false;
            }
        }
        if (response == 2) {
            mode = WeightsMode.CREATE;
        }
        return mode;
    }

    /**
     * Gets the mode the program runs on (can be TRAINING or CLASSIFICATION)
     *
     * @return the mode the program runs on
     * @author lts010, ks061
     */
    private static ProgramMode getProgramMode() {
        int response = -1;
        boolean invalidResponse = true;
        ProgramMode mode = ProgramMode.TRAINING;
        String prompt = "Enter 1 or 2 for the desired mode of operation\n\n";
        prompt += "1 -- training mode\n2 -- classification mode\n";
        while (invalidResponse) {
            response = getIntInput(prompt);
            if (response != 1 && response != 2) {
                System.out.println("INVALID INPUT\n\n.");
            }
            else {
                invalidResponse = false;
            }
        }
        if (response == 2) {
            mode = ProgramMode.CLASSIFICATION;
        }
        return mode;
    }

    /**
     * Imports training data from the CSV file specified by the end user
     *
     * @return training data
     *
     * @author ks061, lts010
     */
    private static double[][] getTrainingData() {
        Scanner scanner = new Scanner(System.in);
        boolean csvFound = false;
        String filename = null;
        while (!csvFound) {
            // Get CSV filename from user
            System.out.print("Enter the name of the training data file: ");
            filename = scanner.nextLine();
            // Try to access CSV file
            File csv = new File(filename);
            try {
                scanner = new Scanner(csv);
                csvFound = true;
            } catch (FileNotFoundException ex) {
                System.out.println("The training data file has not been found. ");
            }
        }

        // Gets number of lines in file
        String line;
        String[] entriesInLine;
        long lineCountDouble = 0;
        try {
            lineCountDouble = Files.lines(Paths.get(filename)).count();
        } catch (IOException ex) {
            System.out.println(
                    "An unexpected input-output error occured when trying to "
                    + "count the number of lines in the training data file.");
            System.exit(0);
        }
        int lineCount = 0;
        if (-2147483647 <= lineCountDouble && lineCountDouble <= 2147483647) {
            lineCount = (int) lineCountDouble;
        }
        else {
            // NeuralNet.java has specified double[][], which implies that the
            // size of the array cannot be outside of the range of values an
            // int can hold.
            throw new IndexOutOfBoundsException(
                    "Number of lines in file is outside of the range of values "
                    + "an int can hold.");
        }

        // Initializes an array based on number of lines in CSV file
        double[][] trainingData = new double[lineCount][];
        for (int row = 0; scanner.hasNextLine(); row++) {
            line = scanner.nextLine();
            entriesInLine = line.split(",");
            trainingData[row] = new double[entriesInLine.length];
            for (int col = 0; col < trainingData[row].length; col++) {
                trainingData[row][col] = Double.parseDouble(
                        entriesInLine[col]);
            }
        }
        return trainingData;
    }

    /**
     * Gives the user a prompt and requests an integer
     *
     * @param prompt - the prompt given to the user
     * @return the integer given by the user
     *
     * @author lts010, ks061
     */
    private static int getIntInput(String prompt) {
        Scanner in = new Scanner(System.in);
        int result = -1;
        while (true) {
            System.out.println(prompt);
            if (in.hasNextInt()) {
                result = in.nextInt();
                if (result >= 0) {
                    break;
                }
            }
            System.out.println("INVALID INPUT\n\n." + prompt);
        }
        return (result);
    }

    /**
     * Sets up a list of lists to initialize all of the random weights
     *
     * @param numInputs - number of inputs
     * @param numOutputs - number of outputs
     * @param numHiddenLayers - number of hidden layers
     * @param numNeuronsPerHiddenLayer - number of neurons per hidden layer
     * @return all of the weights needed to construct the neural net
     *
     * @author lts010, ks061
     */
    private static ArrayList<ArrayList<Double>> getRandomWeights(int numInputs,
                                                                 int numOutputs,
                                                                 int numHiddenLayers,
                                                                 int numNeuronsPerHiddenLayer) {
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        WeightAssignment weightAssign = new RandomWeightAssignment();
        weights.add(new ArrayList<>());
        if (numHiddenLayers == 0) {
            for (int i = 0; i < (numInputs * numOutputs); i++) {
                weights.get(0).add(weightAssign.assignWeight() / numInputs);
            }
        }
        else {
            for (int i = 0; i < (numInputs * numNeuronsPerHiddenLayer); i++) { //weights from the input layer connecting to the first hidden layer
                weights.get(0).add(weightAssign.assignWeight() / numInputs);
            }
            for (int i = 1; i < numHiddenLayers; i++) { //weights for hidden layers that are connected to layer
                weights.add(new ArrayList<>());
                for (int j = 0; j < (numNeuronsPerHiddenLayer * numNeuronsPerHiddenLayer); j++) {
                    weights.get(i).add(
                            weightAssign.assignWeight() / numNeuronsPerHiddenLayer);
                }
            }
            weights.add(new ArrayList<>());
            for (int i = 0; i < numOutputs * numNeuronsPerHiddenLayer; i++) { //weights for the output layer connecting to the last hidden layer
                weights.get(weights.size() - 1).add(
                        weightAssign.assignWeight() / numNeuronsPerHiddenLayer);
            }
        }
        return weights;
    }

    private static ArrayList<ArrayList<Double>> getListOfThetas(int numInputs,
                                                                int numOutputs,
                                                                int numHiddenLayers,
                                                                int numNeuronsPerHiddenLayer) {
        ArrayList<ArrayList<Double>> listOfThetas = new ArrayList<>();
        for (int i = 0; i < numHiddenLayers; i++) {
            listOfThetas.add(new ArrayList<>());
            for (int j = 0; j < numNeuronsPerHiddenLayer; j++) {
                listOfThetas.get(i).add(Neuron.DEFAULTTHETA);
            }
        }
        listOfThetas.add(new ArrayList<>());
        for (int i = 0; i < numOutputs; i++) {
            listOfThetas.get(listOfThetas.size() - 1).add(Neuron.DEFAULTTHETA);
        }
        System.out.println(listOfThetas);
        return listOfThetas;
    }

    /**
     * Gets the number of inputs for a neural network
     *
     * @return integer
     * @author lts010, ks061
     */
    private static int getNumInputs() {
        return getIntInput(
                "What should the number of inputs be (as an integer)?: ");
    }

    /**
     * Gets the number of outputs for a neural network
     *
     * @return integer
     * @author lts010, ks061
     */
    private static int getNumOutputs() {
        return getIntInput(
                "What should the number of outputs be (as an integer)?");
    }

    /**
     * Gets the number of hidden layers for a neural network
     *
     * @return integer
     * @author lts010, ks061
     */
    private static int getNumHiddenLayers() {
        return getIntInput(
                "What should the number of hidden layers be (as an integer)?");
    }

    /**
     * Gets the number of neurons per hidden layer for a neural network
     *
     * @return integer
     * @author lts010, ks061
     */
    private static int getNumNeuronsPerHiddenLayer() {
        return getIntInput(
                "What should the number of neurons per hidden layer be (as an integer)?");
    }

    /**
     * Gets the highest acceptable SSE for a neural network
     *
     * @return double
     * @author lts010, ks061
     */
    private static double getHighestSSE() {
        Scanner in = new Scanner(System.in);
        boolean invalidInput = true;
        String prompt = "What is the highest acceptable SSE (as a double)?";
        double result = -1;
        while (invalidInput) {
            System.out.println(prompt);

            if (in.hasNextDouble()) {
                result = in.nextDouble();
                invalidInput = false;
            }
            else {
                System.out.println(
                        "INVALID INPUT\n\n." + prompt);
            }
        }
        return (result);
    }

    /**
     * Converts a list of strings to a list of lists of doubles
     *
     * @see
     * <a href ="https://stackoverflow.com/questions/11009818/how-to-get-list-of-integer-from-string">https://stackoverflow.com/questions/11009818/how-to-get-list-of-integer-from-string</a>
     *
     * @param strList - a list of strings
     * @return - a 2D array of double
     *
     * @author lts010, ks061
     */
    public static ArrayList<ArrayList<Double>> strListToDoubleList(
            ArrayList<String> strList) {
        Scanner strReader;
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
     * Create a new neural net and either test w/in neural net, or create
     * another function to control testing of the neural network
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     *
     * @author lts010, ks061
     */
    public static void main(String[] args) throws FileNotFoundException {
        int numInputs;
        int numOutputs;
        int numHiddenLayers;
        int numNeuronsPerHiddenLayer;
        double highestSSE;
        ArrayList<ArrayList<Double>> weights;
        ArrayList<ArrayList<Double>> thetas;
        ProgramMode programMode;
        WeightsMode inputMode;
        inputMode = getInputMode();
        programMode = getProgramMode();

        if (inputMode == WeightsMode.CREATE) {
            numInputs = getNumInputs();
            numOutputs = getNumOutputs();
            numHiddenLayers = getNumHiddenLayers();
            if (numHiddenLayers == 0) { //if there is no hidden layers we don't need to know the number of neurons per hidden layer
                numNeuronsPerHiddenLayer = 0;
            }
            else {
                numNeuronsPerHiddenLayer = getNumNeuronsPerHiddenLayer();
            }
            if (programMode != ProgramMode.TRAINING) { //if programMode is not training we don't need an SSE
                highestSSE = 0;
            }
            else {
                highestSSE = getHighestSSE();
            }
            weights = getRandomWeights(numInputs, numOutputs, numHiddenLayers,
                                       numNeuronsPerHiddenLayer);
            thetas = getListOfThetas(numInputs, numOutputs, numHiddenLayers,
                                     numNeuronsPerHiddenLayer);
        }
        else {
            ArrayList<String> configList = ConfigObject.readConfigFile();
            int thetaIndex = configList.indexOf("THETAS"); //need to know which index the thetas start at
            ArrayList<String> configListWeights = new ArrayList<>(
                    configList.subList(0,
                                       thetaIndex)); //seperate the weights (and the numbers before the weights) into a list before the string "THETA"
            ArrayList<String> configListThetas = new ArrayList<>( //seperate the thetas into a list after the string "THETA"
                    configList.subList(
                            thetaIndex + 1, configList.size()));
            ArrayList<ArrayList<Double>> weightList = strListToDoubleList( //turn the weight list into a list of list of doubles
                    configListWeights);
            thetas = strListToDoubleList(configListThetas); //turn the theta list into a list of list of doubles
            numInputs = (int) Math.round(weightList.get(0).get(0));
            numOutputs = (int) Math.round(weightList.get(0).get(1));
            numHiddenLayers = (int) Math.round(weightList.get(0).get(2));
            numNeuronsPerHiddenLayer = (int) Math.round(weightList.get(0).get(3));
            highestSSE = (int) Math.round(weightList.get(0).get(4));
            weights = new ArrayList<>(weightList.subList(1,
                                                         configListWeights.size()));
        }
        ConfigObject config = new ConfigObject(numInputs, numOutputs,
                                               numHiddenLayers,
                                               numNeuronsPerHiddenLayer,
                                               highestSSE, weights, thetas,
                                               programMode);

        double[][] trainingData = getTrainingData();
        myNet = new NeuralNet(trainingData, config);
    }
}

//    /**
//     * Reads input data from the training file
//     *
//     * @param numInputs number of inputs
//     * @return input data
//     *
//     * @author lts010, ks061
//     */
//    private static double[][] readInputData(int numInputs) {
//        Scanner in = new Scanner(System.in);
//        boolean fileFound = false;
//        String filename = null;
//        while (!fileFound) {
//            // Get CSV filename from user
//            System.out.print("Enter the name of the training data file: ");
//            filename = in.nextLine();
//            // Try to access CSV file
//            File f = new File(filename);
//            try {
//                in = new Scanner(f);
//                fileFound = true;
//            } catch (FileNotFoundException ex) {
//                System.out.println("The input data has not been found.");
//            }
//        }
//        // Gets number of lines in file
//        String line;
//        String[] entriesInLine;
//        long lineCountDouble = 0;
//        try {
//            lineCountDouble = Files.lines(Paths.get(filename)).count();
//        } catch (IOException ex) {
//            System.out.println(
//                    "An unexpected input-output error occured when trying to "
//                    + "count the number of lines in the training data file.");
//            System.exit(0);
//        }
//        int lineCount = 0;
//        if (-2147483647 <= lineCountDouble && lineCountDouble <= 2147483647) {
//            lineCount = (int) lineCountDouble;
//        }
//        else {
//            // NeuralNet.java has specified double[][], which implies that the
//            // size of the array cannot be outside of the range of values an
//            // int can hold.
//            System.out.println(
//                    "Number of lines in file is outside of the range of values "
//                    + "an int can hold.");
//            System.exit(0);
//        }
//
//        // Initializes an array based on number of lines in CSV file
//        double[][] inputs = new double[lineCount][];
//        // Reads in input data from scanner
//        for (int row = 0; in.hasNextLine(); row++) {
//            line = in.nextLine();
//            entriesInLine = line.split(",");
//            inputs[row] = new double[entriesInLine.length - 1];
//            for (int col = 0; col < inputs[row].length; col++) {
//                inputs[row][col] = Double.parseDouble(
//                        entriesInLine[col]);
//            }
//        }
//        return inputs;
//    }
//    /**
//     * Asks for a file containing input values and prints and writes them to a
//     * file
//     *
//     * @param config - a configuration object containing all the important
//     * variables for the neural network
//     *
//     * @author lts010, ks061
//     */
//    public static void classificationMode(ConfigObject config) {
//    }
//
//    /**
//     * Runs the training mode feature of the program.
//     *
//     * @author ks061, lts010
//     */
//    public static void trainingMode() {
//    }
