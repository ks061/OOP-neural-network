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
 * Description: Main file, interacts with the user
 *
 * ****************************************
 */
package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cld028, ks061, lts010
 */
public class ANNClient {

    enum Mode {
        READ, CREATE, TRAINING, CLASSIFICATION;
    }

    /**
     * Neural network generated/used by the ANNClient
     */
    private static NeuralNet myNet;

    /**
     * Asks for a file containing input values and prints and writes them to a
     * file
     *
     * @param config - a configuration object containing all the important
     * variables for the neural network
     *
     * @author lts010
     */
    public static void classificationMode(ConfigObject config) {
        double[][] inputs = readInputData(config.getNumInputs());

    }

    /**
     * Runs the training mode feature of the program.
     *
     * @author ks061
     */
    public static void trainingMode() {
        Object[] trainingData = getTrainingData();
        myNet = new NeuralNet((double[][]) trainingData[0],
                              (double[]) trainingData[1]);
    }

    private static double[][] readInputData(int numInputs) {
        Scanner in = new Scanner(System.in);
        boolean fileFound = false;
        String filename = null;
        while (!fileFound) {
            // Get CSV filename from user
            System.out.print("Enter the name of the training data file: ");
            filename = in.nextLine();
            // Try to access CSV file
            File f = new File(filename);
            try {
                in = new Scanner(f);
                fileFound = true;
            } catch (FileNotFoundException ex) {
                System.out.println("The input data has not been found.");
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
            System.out.println(
                    "Number of lines in file is outside of the range of values "
                    + "an int can hold.");
            System.exit(0);
        }

        // Initializes an array based on number of lines in CSV file
        double[][] inputs = new double[lineCount][];
        // Reads in input data from scanner
        for (int row = 0; in.hasNextLine(); row++) {
            line = in.nextLine();
            entriesInLine = line.split(",");
            inputs[row] = new double[entriesInLine.length - 1];
            for (int col = 0; col < inputs[row].length; col++) {
                inputs[row][col] = Double.parseDouble(
                        entriesInLine[col]);
            }
        }
        return inputs;
    }

    /**
     * Gets the mode the program creates the neural network with (can be READ or
     * CREATE)
     *
     * @return the mode that creates the neural network
     * @author lts010
     */
    public static Mode getInputMode() {
        int response = -1;
        boolean invalidResponse = true;
        Mode mode = Mode.READ;
        String prompt = "Enter 1 or 2 for the desired method of creating the neural network\n\n";
        prompt += "1 -- Read a config file\n2 -- Create a new ANN\n";
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
            mode = Mode.CREATE;
        }
        return mode;
    }

    /**
     * Gives the user a prompt and requests an integer
     *
     * @param prompt - the prompt given to the user
     * @return the integer given by the user
     *
     * @author lts010
     */
    public static int getIntInput(String prompt) {
        Scanner in = new Scanner(System.in);
        boolean invalidInput = true;
        int result = -1;
        while (invalidInput) {
            System.out.println(prompt);

            if (in.hasNextInt()) {
                result = in.nextInt();
                invalidInput = false;
            }
            else {
                System.out.println("INVALID INPUT\n\n." + prompt);
            }
        }
        return (result);
    }

    /**
     * Gets the number of inputs for a neural network
     *
     * @return integer
     * @author lts010
     */
    public static int getNumInputs() {
        return getIntInput(
                "What should the number of inputs be (as an integer)? :");
    }

    /**
     * Sets up a list of lists to initialize all of the random weights
     *
     * @param numInputs - number of inputs
     * @param numOutputs - number of outputs
     * @param numHiddenLayers - number of hidden layers
     * @param numNeuronsPerHiddenLayer - number of neurons per hidden layer
     * @return all of the weights needed to construct the neural net
     */
    private static ArrayList<ArrayList<Double>> getRandomWeights(int numInputs,
                                                                 int numOutputs,
                                                                 int numHiddenLayers,
                                                                 int numNeuronsPerHiddenLayer) {
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
        WeightAssignment weightAssign = new RandomWeightAssignment();
        weights.add(new ArrayList<Double>());
        if (numHiddenLayers == 0) {
            for (int i = 0; i < (numInputs * numOutputs); i++) {
                weights.get(0).add(weightAssign.assignWeight());
            }
        }
        else {
            for (int i = 0; i < (numInputs * numNeuronsPerHiddenLayer); i++) { //weights from the input layer connecting to the first hidden layer
                weights.get(0).add(weightAssign.assignWeight());
            }
            for (int i = 1; i < numHiddenLayers; i++) { //weights for hidden layers that are connected to layer
                weights.add(new ArrayList<Double>());
                for (int j = 0; j < (numNeuronsPerHiddenLayer * numNeuronsPerHiddenLayer); j++) {
                    weights.get(i).add(weightAssign.assignWeight());
                }
            }
            weights.add(new ArrayList<Double>());
            for (int i = 0; i < numOutputs * numNeuronsPerHiddenLayer; i++) { //weights for the output layer connecting to the last hidden layer
                weights.get(weights.size() - 1).add(weightAssign.assignWeight());
            }
        }
        System.out.println(weights);
        return weights;
    }

    /**
     * Gets the number of outputs for a neural network
     *
     * @return integer
     * @author lts010
     */
    public static int getNumOutputs() {
        return getIntInput(
                "What should the number of outputs be (as an integer)?");
    }

    /**
     * Gets the number of hidden layers for a neural network
     *
     * @return integer
     * @author lts010
     */
    public static int getNumHiddenLayers() {
        return getIntInput(
                "What should the number of hidden layers be (as an integer)?");
    }

    /**
     * Gets the number of neurons per hidden layer for a neural network
     *
     * @return integer
     * @author lts010
     */
    public static int getNumNeuronsPerHiddenLayer() {
        return getIntInput(
                "What should the number of neurons per hidden layer be (as an integer)?");
    }

    /**
     * Gets the highest acceptable SSE for a neural network
     *
     * @return double
     * @author lts010
     */
    public static double getHighestSSE() {
        Scanner in = new Scanner(System.in);
        boolean invalidInput = true;
        String prompt = "What is the highest acceptable SSE (as a double?";
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
     * Gets the mode the program runs on (can be TRAINING or CLASSIFICATION)
     *
     * @return the mode the program runs on
     * @author lts010
     */
    public static Mode getRunMode() {
        int response = -1;
        boolean invalidResponse = true;
        Mode mode = Mode.TRAINING;
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
            mode = Mode.CLASSIFICATION;
        }
        return mode;
    }

    /**
     * Imports training data from the CSV file specified by the end user
     *
     * @return training data
     *
     * @author ks061
     */
    public static Object[] getTrainingData() {
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
            System.out.println(
                    "Number of lines in file is outside of the range of values "
                    + "an int can hold.");
            System.exit(0);
        }

        // Initializes an array based on number of lines in CSV file
        double[][] trainingInputs = new double[lineCount][];
        double[] trainingExpectedOutputs = new double[lineCount];
        // Reads in input data from scanner
        for (int row = 0; scanner.hasNextLine(); row++) {
            line = scanner.nextLine();
            entriesInLine = line.split(",");
            trainingInputs[row] = new double[entriesInLine.length - 1];
            for (int col = 0; col < trainingInputs[row].length; col++) {
                if (col == trainingInputs[row].length - 1) {
                    trainingExpectedOutputs[row] = Double.parseDouble(
                            entriesInLine[col]);
                }
                else {
                    trainingInputs[row][col] = Double.parseDouble(
                            entriesInLine[col]);
                }
            }
        }
        return new Object[]{trainingInputs, trainingExpectedOutputs};
    }

    /**
     * Reads a config file
     *
     * @return a double array list that provides the number of inputs, outputs,
     * and the weights
     * @throws java.io.FileNotFoundException
     */
    public static ArrayList<ArrayList<Double>> readConfigFile() throws FileNotFoundException {
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
        return strListToDoubleList(configList);
    }

    /**
     * Converts a list of strings to a list of lists of doubles
     *
     * @param strList - a list of strings
     * @return - a 2D array of double
     * @author - lts010
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
     * Saves all relevant information of a neural net to a file
     *
     * @param nN - a Neural Net
     * @throws java.io.FileNotFoundException
     * @author - lts010
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
        out.close();
    }

    /**
     * Create a new neural net and either test w/in neural net, or create
     * another function to control testing of the neural network
     *
     * @param args the command line arguments
     * @author lts010, ks061
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        int numInputs;
        int numOutputs;
        int numHiddenLayers;
        int numNeuronsPerHiddenLayer;
        double highestSSE;
        ArrayList<ArrayList<Double>> weights;
        Mode runMode;
        Mode inputMode;
        inputMode = getInputMode();

        if (inputMode == Mode.CREATE) {
            numInputs = getNumInputs();
            numOutputs = getNumOutputs();
            numHiddenLayers = getNumHiddenLayers();
            numNeuronsPerHiddenLayer = getNumNeuronsPerHiddenLayer();
            highestSSE = getHighestSSE();
            weights = getRandomWeights(numInputs, numOutputs, numHiddenLayers,
                                       numNeuronsPerHiddenLayer);
        }
        else {
            ArrayList<ArrayList<Double>> configList = readConfigFile();
            numInputs = (int) Math.round(configList.get(0).get(0));
            numOutputs = (int) Math.round(configList.get(0).get(1));
            numHiddenLayers = (int) Math.round(configList.get(0).get(2));
            numNeuronsPerHiddenLayer = (int) Math.round(configList.get(0).get(3));
            highestSSE = (int) Math.round(configList.get(0).get(4));
            weights = new ArrayList<ArrayList<Double>>(configList.subList(1,
                                                                          configList.size()));
        }
        ConfigObject config = new ConfigObject(numInputs, numOutputs,
                                               numHiddenLayers,
                                               numNeuronsPerHiddenLayer,
                                               highestSSE, weights);
        runMode = getRunMode();
        if (runMode == Mode.CLASSIFICATION) {
            classificationMode(config);
        }
        else {
            trainingMode();
        }
    }
}
