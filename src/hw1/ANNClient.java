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
     *
     * @author ks061, lts010
     */
    enum WeightsMode {
        READ, CREATE;
    }

    /**
     * Neural network generated/used by the neural network program
     */
    private static NeuralNet myNet;

    /**
     * Gets the mode that the user wishes the neural network program to assign
     * the weights with; either read them from a file or randomly create them.
     *
     * @return mode that the user wishes the neural network program to assign
     * the weights with; either read them from a file or randomly create them.
     *
     * @author lts010, ks061
     */
    private static WeightsMode getWeightsMode() {
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
     * Gets the mode that the user wishes to run the program in; either a neural
     * network can be trained using existing input and output training data or a
     * prediction can be made based on existing input and a neural network.
     *
     * @return the mode the program runs in (training mode or classification
     * mode)
     *
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
     * Imports training data from a CSV file specified by the end user; the file
     * entered when prompted must end with .csv, or the program will not attempt
     * to scan or find the file. Additionally, if the entered file is not found,
     * the end user will be re-prompted for the filename of the CSV file with
     * the training data.
     *
     * @return training data, including sets of inputs and corresponding outputs
     *
     * @author ks061, lts010
     */
    private static double[][] getTrainingData() {
        Scanner scanner = new Scanner(System.in);
        boolean csvFound = false;
        String filename = null;
        while (!csvFound) {
            // Get CSV filename from user
            System.out.print(
                    "Enter the name of the training data file (.csv file): ");
            filename = scanner.nextLine();
            if (filename.substring(filename.lastIndexOf('.') + 1).equals("csv")) {
                File csv = new File(filename);
                try {
                    // Try to access CSV file
                    scanner = new Scanner(csv);
                    csvFound = true;
                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "The training data file has not been found. ");
                }
            }
            else {
                System.out.println(
                        "The file entered is not a .csv file. ");
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
     * Gives the end user the provided prompt and requests an integer
     *
     * @param prompt prompt given to end user
     * @return the integer entered by end user
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
    private static ArrayList<ArrayList<Double>> getRandomWeights(int numInputs,
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
            for (int i = 0; i < (numInputs * numNeuronsPerHiddenLayer); i++) { //weights from the input layer connecting to the first hidden layer
                weights.get(0).add(
                        weightAssign.assignWeight(numInputs) / numInputs);
            }
            for (int i = 1; i < numHiddenLayers; i++) { //weights for hidden layers that are connected to layer
                weights.add(new ArrayList<>());
                for (int j = 0; j < (numNeuronsPerHiddenLayer * numNeuronsPerHiddenLayer); j++) {
                    weights.get(i).add(
                            weightAssign.assignWeight(numNeuronsPerHiddenLayer) / numNeuronsPerHiddenLayer);
                }
            }
            weights.add(new ArrayList<>());
            for (int i = 0; i < numOutputs * numNeuronsPerHiddenLayer; i++) { //weights for the output layer connecting to the last hidden layer
                weights.get(weights.size() - 1).add(
                        weightAssign.assignWeight(numNeuronsPerHiddenLayer) / numNeuronsPerHiddenLayer);
            }
        }
        return weights;
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
    private static ArrayList<ArrayList<Double>> getListOfThetas(int numOutputs,
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
     * Prompts the user for and returns the number of input neurons the neural
     * network will be configured to have.
     *
     * @return number of input neurons the neural network will be configured to
     * have
     *
     * @author lts010, ks061
     */
    private static int getNumInputs() {
        return getIntInput(
                "What should the number of inputs be (as an integer)?: ");
    }

    /**
     * Prompts the user for and returns the number of output neurons the neural
     * network will be configured to have have.
     *
     * @return number of output neurons the neural network will be configured to
     * have
     *
     * @author lts010, ks061
     */
    private static int getNumOutputs() {
        return getIntInput(
                "What should the number of outputs be (as an integer)?");
    }

    /**
     * Prompts the user for and returns the number of hidden layers the neural
     * network will be configured to have.
     *
     * @return number of hidden layers the neural network will be configured to
     * have
     *
     * @author lts010, ks061
     */
    private static int getNumHiddenLayers() {
        return getIntInput(
                "What should the number of hidden layers be (as an integer)?");
    }

    /**
     * Prompts the user for and returns the number of neurons per hidden layer
     * the neural network will be configured to have
     *
     * @return number of neurons per hidden layer the neural network will be
     * configured to have
     *
     * @author lts010, ks061
     */
    private static int getNumNeuronsPerHiddenLayer() {
        return getIntInput(
                "What should the number of neurons per hidden layer be (as an integer)?");
    }

    /**
     * Prompts the user for and returns the highest acceptable SSE, i.e. sum of
     * squared errors, the neural network will be configured to have; this is
     * only used if the user selects training mode, where the neural network
     * goes through multiple iterations of training itself against the training
     * data until its SSE is below this maximum allowed SSE.
     *
     * @return highest acceptable SSE, i.e. sum of squared errors, neural
     * network will be configured with (for training mode)
     *
     * @author lts010, ks061
     */
    private static double getHighestSSE() {
        Scanner in = new Scanner(System.in);
        boolean invalidInput = true;
        String prompt = "What is the highest acceptable SSE, i.e. sum of squared errors, (as a double)?";
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
     * Serves as the main runner for this neural net program. The program
     * prompts which program mode the user would like the program to run in; the
     * program has two program modes: classification mode, which predicts what
     * the output will be based on given inputs and a pre-configured neural
     * network, or training mode, which trains the neural network program based
     * on given inputs and expected output. The program also gives options for
     * reading in weights or randomly assigning them. If the user chooses not to
     * read in a configuration of the neural network, including the weights,
     * then the program asks the user how many input neurons, how many output
     * neurons, how many hidden layers, how many neurons per hidden layer, and
     * the highest allowable SSE (if in training mode) the neural network should
     * be configured with. After, the program initializes a neural network with
     * a configuration object containing the user's
     * setup/configuration/preferences.
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
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
        inputMode = getWeightsMode();
        programMode = getProgramMode();

        if (inputMode == WeightsMode.CREATE) {
            numInputs = getNumInputs();
            numOutputs = getNumOutputs();
            numHiddenLayers = getNumHiddenLayers();
            if (numHiddenLayers == 0) { // if there are no hidden layers, do not need to know number of neurons per hidden layer
                numNeuronsPerHiddenLayer = 0;
            }
            else {
                numNeuronsPerHiddenLayer = getNumNeuronsPerHiddenLayer();
            }
            if (programMode != ProgramMode.TRAINING) { // if programMode is not training, no need for SSE
                highestSSE = 0;
            }
            else {
                highestSSE = getHighestSSE();
            }
            weights = getRandomWeights(numInputs, numOutputs, numHiddenLayers,
                                       numNeuronsPerHiddenLayer);
            thetas = getListOfThetas(numOutputs, numHiddenLayers,
                                     numNeuronsPerHiddenLayer);
        }
        else {
            ArrayList<String> configList = ConfigObject.readConfigFile();
            int thetaIndex = configList.indexOf("THETAS"); // indicates which index the thetas start at
            ArrayList<String> configListWeights = new ArrayList<>(
                    configList.subList(0,
                                       thetaIndex)); // seperates the weights (and the numbers before the weights) into a list before the string "THETA"
            ArrayList<String> configListThetas = new ArrayList<>( // seperates the thetas into a list after the string "THETA"
                    configList.subList(
                            thetaIndex + 1, configList.size()));
            ArrayList<ArrayList<Double>> weightList = strListToDoubleList( // turns the weight list into a list of list of doubles
                    configListWeights);
            thetas = strListToDoubleList(configListThetas); // turns the theta list into a list of list of doubles
            thetas.add(0, new ArrayList<>()); // adds input layer without any weights
            numInputs = (int) Math.round(weightList.get(0).get(0));
            numOutputs = (int) Math.round(weightList.get(0).get(1));
            numHiddenLayers = (int) Math.round(weightList.get(0).get(2));
            numNeuronsPerHiddenLayer = (int) Math.round(weightList.get(0).get(3));
            highestSSE = weightList.get(0).get(4);
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
