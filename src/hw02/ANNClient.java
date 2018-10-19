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
 * File: ANNClient
 * Description: Main file that interacts with the user to either run the neural
 *              network program in classification mode, which predicts what the
 *              output will be based on given inputs, or training mode, which
 *              trains the neural net program based on given inputs and
 *              expected output.
 * ****************************************
 */
package hw02;

import hw02.ANNLogger.ANNLogger;
import hw02.ANNLogger.ANNLoggerStatus;
import hw02.Neuron.Neuron;
import hw02.WeightAssignment.RandomWeightAssignment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
     * Default value for the highest sum of squared errors value used by the
     * neural network initialized by this neural network program (only used in
     * training mode)
     */
    private static final double DEFAULT_HIGHEST_SSE = 0.1;
    /**
     * Default value for the maximum number of epochs run through by the neural
     * network initialized by this neural network program (only used in training
     * mode)
     */
    private static final int DEFAULT_MAX_EPOCHS = 100000;

    /**
     * Enumeration that helps distinguish whether the program will read in
     * neural net edge weights from a configuration file, read in edge weights
     * from a serialized file, or randomly assign them
     *
     * @author ks061, lts010
     */
    enum InputMode {
        READCONFIG, READANN, CREATE;
    }

    /**
     * Neural network generated/used by the neural network program
     */
    private static NeuralNet myNet;

    /**
     * Gets the mode that the user wishes the neural network program to assign
     * the weights with; either read them from a configuration file (.txt), read
     * them in from a serialized file (.ser), or randomly create them.
     *
     * @return mode that the user wishes the neural network program to assign
     * the weights with; either read them from a configuration file (.txt), read
     * them in from a serialized file (.ser), or randomly create them.
     *
     * @author lts010, ks061
     */
    private static InputMode getInputMode() {
        int response = -1;
        boolean invalidResponse = true;
        InputMode mode = InputMode.CREATE;
        String prompt = "Enter 1, 2, or 3 for the desired method of creating the "
                        + "neural network\n\n" + "\n1 -- Create a new ANN"
                        + "\n2 -- Read a config file\n3 -- Load a saved ANN\n\n";

        while (invalidResponse) {
            response = ANNUtility.getIntInput(prompt);
            if (response < 1 || response > 3) {
                System.out.println("INVALID INPUT\n\n.");
            }
            else {
                invalidResponse = false;
            }
        }
        if (response == 2) {
            mode = InputMode.READCONFIG;
        }
        if (response == 3) {
            mode = InputMode.READANN;
        }

        System.out.println();

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
        prompt += "1 -- training mode\n2 -- classification mode\n\n";

        while (invalidResponse) {
            response = ANNUtility.getIntInput(prompt);
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

        System.out.println();

        return mode;
    }

    /**
     * Imports training data from a CSV file specified by the end user; the file
     * entered when prompted must end with .csv, or the program will not attempt
     * to scan or find the file. Additionally, if the entered file is not found,
     * the end user will be re-prompted for the filename of the CSV file with
     * the training data. Removes any input sets that have input values outside
     * the range [0,1].
     *
     * @return training data, including sets of inputs and corresponding outputs
     *
     * @author ks061, lts010
     */
    public static double[][] getData() {
        Scanner scanner = new Scanner(System.in);
        boolean csvFound = false;
        String filename = null;
        while (!csvFound) {
            // Get CSV filename from user
            System.out.print(
                    "Enter the name of the ANN data file (.csv file): ");
            filename = scanner.nextLine();
            if (filename.endsWith(".csv")) {
                File csv = new File(filename);
                try {
                    // Try to access CSV file
                    scanner = new Scanner(csv);
                    csvFound = true;
                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "The ANN data file has not been found. ");
                }
            }
            else {
                System.out.println(
                        "The file entered is not a .csv file. ");
            }

        }

        // Gets number of lines in file
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
        String line;
        String[] entriesInLine;
        double[][] data = new double[lineCount][];
        for (int row = 0; scanner.hasNextLine(); row++) {
            line = scanner.nextLine();
            entriesInLine = line.split(",");
            data[row] = new double[entriesInLine.length];
            for (int col = 0; col < data[row].length; col++) {
                try {
                    data[row][col] = Double.parseDouble(
                            entriesInLine[col]);
                } catch (NumberFormatException ex) {
                    break;
                }
            }
        }

        return data;
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
     * Prompts the user for and returns the number of input neurons the neural
     * network will be configured to have.
     *
     * @return number of input neurons the neural network will be configured to
     * have
     *
     * @author lts010, ks061
     */
    private static int getNumInputs() {
        return ANNUtility.getIntInput(
                "What should be the number of inputs (as an integer)?: ");
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
        return ANNUtility.getIntInput(
                "What should be the number of outputs (as an integer)?: ");
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
        return ANNUtility.getIntInput(
                "What should be the number of hidden layers (as an integer)?: ");
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
        return ANNUtility.getIntInput(
                "What should be the number of neurons per hidden layer (as an integer)?: ");
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
        double highestSSE = ANNUtility.getDoubleInput(
                "What should be the highest acceptable SSE, i.e. sum of squared errors, (as a double)? \n(Enter 0 for default value): ");
        if (highestSSE == 0) {
            return DEFAULT_HIGHEST_SSE;
        }
        return highestSSE;
    }

    /**
     * Prompts the user for and gets the maximum number of epochs, i.e. the
     * number of times the neural network will train itself on the training data
     * if the sum of squared errors calculated by the neural network is less
     * than or equal to the set maximum sum of squared errors for the neural
     * network; this is only used if the user selects training mode, where the
     * neural network goes through multiple iterations of training itself
     * against the training data until its SSE is below this maximum allowed
     * SSE.
     *
     * @return maximum number of epochs, i.e. the number of times the neural
     * network will train itself on the training data if the sum of squared
     * errors calculated by the neural network is less than or equal to the set
     * maximum sum of squared errors for the neural network
     */
    private static int getNumMaxEpochs() {
        int numMaxEpochs = ANNUtility.getIntInput(
                "What should be the maximum number of epochs that training mode runs (as an integer)?\n(Enter 0 for default value): ");
        if (numMaxEpochs == 0) {
            return DEFAULT_MAX_EPOCHS;
        }
        return numMaxEpochs;
    }

    /**
     * Reads a .txt file, a neural network configuration file, that can be read
     * as a ANNConfig
     *
     * @return list of Strings that provides the number of inputs, outputs, and
     * the weights
     *
     * @author lts010, ks061
     */
    public static ArrayList<String> readConfigFile() {
        Scanner in = new Scanner(System.in);
        Scanner fReader = new Scanner(System.in);
        boolean fileFound = false;
        while (!fileFound) {
            System.out.print("Enter the filename of the configuration file: ");
            String filename = in.nextLine();
            if (filename.endsWith(".txt")) {
                try {
                    File f = new File(filename);
                    fReader = new Scanner(f);
                    fileFound = true;
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found. Try again.");
                }
            }
            else {
                System.out.println("The file entered is not a .txt file.");
            }
        }
        ArrayList<String> configList = new ArrayList<>();
        while (fReader.hasNextLine()) {
            configList.add(fReader.nextLine());
        }
        return configList;
    }

    /**
     * Saves all configuration information of a neural net to a .txt file
     *
     * @param nN neural network whose configuration will be exported
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author lts010, ks061
     */
    public static void exportConfig(NeuralNet nN) throws FileNotFoundException {
        String prompt = "What .txt file would you like to save the configuration to? ";
        String outputFile = ANNUtility.getOutputFilename(".txt", prompt);
        PrintWriter out = new PrintWriter(outputFile);
        out.printf("%d.0 %d.0 %d.0 %d.0 %f %d.0\n",
                   nN.getConfiguration().getNumInputs(),
                   nN.getConfiguration().getNumOutputs(),
                   nN.getConfiguration().getNumHiddenLayers(),
                   nN.getConfiguration().getNumNeuronsPerHiddenLayer(),
                   nN.getConfiguration().getHighestSSE(),
                   nN.getConfiguration().getNumMaxEpochs());
        ArrayList<ArrayList<Double>> weights = nN.getConfiguration().getWeights();
        String weightLayer;
        for (ArrayList<Double> weightList : weights) {
            weightLayer = "";
            for (double weight : weightList) {
                weightLayer += weight + " ";
            }
            out.printf("%s\n", weightLayer);
        }
        out.printf("%s\n", "THETAS");
        List<ArrayList<Double>> thetas = nN.getConfiguration().getThetas().subList(
                1,
                nN.getConfiguration().getThetas().size());
        String thetaLayer;
        for (ArrayList<Double> thetaList : thetas) {
            thetaLayer = "";
            for (double theta : thetaList) {
                thetaLayer += theta + " ";
            }
            out.printf("%s\n", thetaLayer);
        }
        out.flush();
        out.close();
    }

    /**
     * Reads in a serializable file (.ser) and returns the serialized neural
     * network within the file.
     *
     * @param filename name of the file containing the serialized ANN
     * @throws java.io.FileNotFoundException if filename represents a directory
     * rather than a regular file, the file does not exist, or the serializable
     * file could not be read for some other reason
     * @throws java.lang.ClassNotFoundException if classes needed to
     * de-serialize the neural network could not be found, such as NeuralNet and
     * ANNConfig
     * @return neural network de-serialized from the file
     *
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html#FileInputStream-java.lang.String-">
     * FileInputStream </a>
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html#readObject--">
     * ObjectInputStream </a>
     *
     * @author lts010, ks061
     *
     */
    public static NeuralNet deserializeANN(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        String aNNFilename = filename;
        if (!aNNFilename.endsWith(".ser")) {
            throw new NeuralNetConstructionException(
                    "Filename for loading Neural Network must end in .ser");
        }
        FileInputStream inputStream = new FileInputStream(aNNFilename);
        ObjectInputStream configStream = new ObjectInputStream(inputStream);
        NeuralNet nN = (NeuralNet) configStream.readObject();

        configStream.close();
        inputStream.close();

        return nN;
    }

    /**
     * Saves serialized version of the neural network to the specified filename.
     * Adds .ser to fileName if it doesn't already end in .ser.
     *
     * @param filename name of the file to serialize the ANN to
     * @throws java.io.FileNotFoundException if the specified filename
     * represents a directory or cannot be created for some other reason
     *
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html#FileOutputStream-java.lang.String-">
     * FileOutputStream </a>
     * @see
     * <a href="https://stackoverflow.com/questions/10433214/file-extension-for-a-serialized-object">
     * stackoverflow </a>
     *
     * @author lts010, ks061
     */
    public static void serializeANN(String filename) throws FileNotFoundException, IOException {
        String aNNFileame = filename;
        if (!aNNFileame.endsWith(".ser")) {
            aNNFileame += ".ser";
        }
        FileOutputStream aNNfile = new FileOutputStream(filename);
        ObjectOutputStream outputStream = new ObjectOutputStream(aNNfile);
        outputStream.writeObject(myNet);
        outputStream.close();
        aNNfile.close();
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
    private static void saveSetsOfPredictedOutputs(
            ArrayList<ArrayList<Double>> setsOfPredictedOutputs) throws FileNotFoundException {
        String prompt = "What .txt file would you like to save the configuration to?: ";
        String outputFilename = ANNUtility.getOutputFilename(".txt", prompt);
        PrintWriter out = new PrintWriter(outputFilename);
        for (ArrayList<Double> setOfPredictedOutput : setsOfPredictedOutputs) {
            out.print(Arrays.toString(setOfPredictedOutput.toArray()) + "\n");
        }
        out.flush();
        out.close();
    }

    /**
     * Prompts the user whether the neural network training session should be
     * saved to a serialized file, saved to a configuration file, or neither and
     * processes the user's request; the user will be asked for a .ser filename
     * or .txt filename if it has been specified that the neural network should
     * be saved to a serialized file or configuration file respectively.
     *
     * @throws IOException
     *
     * @author ks061, lts010
     */
    private static void outputTrainingConfiguration() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Do you want to save a serialized version or save a configuration file of this neural network? "
                + "\n(enter serial for serialized; config for configuration file; anything else for neither): ");
        String saveToFileChoice = in.nextLine();
        if (saveToFileChoice.equalsIgnoreCase("serial")) {
            String prompt = "Please select a filename (with .ser) to serialize to: ";
            serializeANN(ANNUtility.getOutputFilename(".ser", prompt));
        }
        else if (saveToFileChoice.equalsIgnoreCase("config")) {
            exportConfig(myNet);
        }
        System.out.println("Thanks for using the program.");
    }

    /**
     * Prompts the user whether the outputs predicted by the neural network
     * should be saved to an output file; saves the set of predicted outputs
     * from the neurons to an external .txt file, and it prompts the user for
     * the output filename, etc. as needed
     *
     * @param setsOfPredictedOutputs set of predicted outputs from the neurons
     * in the output layer
     * @throws FileNotFoundException if the file for the configuration to be
     * written to as specified by the user cannot be written to or another error
     * occurs while opening or creating the file
     *
     * @author ks061, lts010
     */
    private static void outputPredictedOutputs(
            ArrayList<ArrayList<Double>> setsOfPredictedOutputs) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Would you like to save the predicted outputs to a file? (enter y or yes for yes; anything else for no): ");
        String willSaveToFile = in.nextLine();
        if (willSaveToFile.equalsIgnoreCase("y") || willSaveToFile.equalsIgnoreCase(
                "yes")) {
            saveSetsOfPredictedOutputs(setsOfPredictedOutputs);
        }
        System.out.println("Thanks for using the program.");
    }

    /**
     * Gets user input for number of inputs, number of outputs, number of hidden
     * layers, number of neurons per hidden layer (if there is at least one
     * hidden layer), the highest allowed sum of squared errors (if in training
     * mode), and the most number of epochs that the neural network should train
     * through (if in training mode), generates lists of random weight values
     * and default theta values, and returns a configuration for the neural
     * network with said data
     *
     * @param programMode whether the program is running in training mode or
     * classification mode
     *
     * @return configuration for the neural network
     *
     * @author ks061, lts010
     */
    private static ANNConfig initializeConfigurationForCreateMode(
            ProgramMode programMode) {
        int numInputs = getNumInputs();
        int numOutputs = getNumOutputs();
        int numHiddenLayers = getNumHiddenLayers();
        int numNeuronsPerHiddenLayer = 0;
        int numMaxEpochs = 0;
        double highestSSE = 0;

        if (numHiddenLayers != 0) {
            numNeuronsPerHiddenLayer = getNumNeuronsPerHiddenLayer();
        }
        if (programMode != ProgramMode.TRAINING) { // if programMode is not training, no need for SSE
            highestSSE = Double.MAX_VALUE;
        }
        else {
            highestSSE = getHighestSSE();
            numMaxEpochs = getNumMaxEpochs();
        }

        ArrayList<ArrayList<Double>> weights = getRandomWeights(numInputs,
                                                                numOutputs,
                                                                numHiddenLayers,
                                                                numNeuronsPerHiddenLayer);
        ArrayList<ArrayList<Double>> thetas = getDefaultListOfThetas(numOutputs,
                                                                     numHiddenLayers,
                                                                     numNeuronsPerHiddenLayer);
        return new ANNConfig(numInputs, numOutputs,
                             numHiddenLayers,
                             numNeuronsPerHiddenLayer,
                             highestSSE, numMaxEpochs,
                             weights, thetas,
                             programMode);
    }

    /**
     * Reads the configuration file and gets a ANNConfig object containing the
     * configuration information read in from the file
     *
     * @param programMode whether the program is running in training mode or
     * classification mode
     *
     * @return configuration for the neural network
     *
     * @author ks061, lts010
     */
    private static ANNConfig initializeConfigurationForReadConfigMode(
            ProgramMode programMode) {
        ArrayList<String> configList = readConfigFile();
        int thetaIndex = configList.indexOf("THETAS"); // indicates which index the thetas start at
        ArrayList<String> configListWeights = new ArrayList<>(
                configList.subList(0,
                                   thetaIndex)); // seperates the weights (and the numbers before the weights) into a list before the string "THETA"
        ArrayList<String> configListThetas = new ArrayList<>( // seperates the thetas into a list after the string "THETA"
                configList.subList(
                        thetaIndex + 1, configList.size()));
        ArrayList<ArrayList<Double>> weightList = ANNUtility.strListToDoubleList( // turns the weight list into a list of list of doubles
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
        return new ANNConfig(numInputs, numOutputs,
                             numHiddenLayers,
                             numNeuronsPerHiddenLayer,
                             highestSSE, numMaxEpochs,
                             weights, thetas,
                             programMode);
    }

    /**
     * Gets the preference of the user whether the logger should log to an
     * output file and returns this preference in the form of a LoggerMode
     *
     * @return ANNLoggerStatus preference of the user whether the logger should
     * log to an output file
     *
     * @author ks061, lts010
     */
    private static ANNLoggerStatus shouldLog() {
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Turn log mode on or off (y or yes for yes; anything else for no): ");
        String userChoice = in.nextLine();
        if (userChoice.equalsIgnoreCase("y") || userChoice.equalsIgnoreCase(
                "yes")) {
            return ANNLoggerStatus.ON;
        }
        return ANNLoggerStatus.OFF;
    }

    /**
     * Asks if the user wishes to keep running the ANN program/client
     *
     * @return true if the user wishes to keep running the program; otherwise
     * false
     *
     * @author ks061, lts010
     */
    private static boolean shouldContinueRunning() {
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Do you want to keep running this ANN program? (enter y or yes for yes; anything else for no): ");
        String userDecision = in.nextLine();
        if (userDecision.equalsIgnoreCase("y") || userDecision.equalsIgnoreCase(
                "yes")) {
            return true;
        }
        return false;
    }

    /**
     * Serves as the main runner for this neural net program. The program
     * prompts which program mode the user would like the program to run in; the
     * program has two program modes: classification mode, which predicts what
     * the output will be based on given inputs and a pre-configured neural
     * network, or training mode, which trains the neural network program based
     * on given inputs and expected output. The program also gives options for
     * reading in weights (from either a .txt configuration file or .ser
     * serialized file) or randomly assigning them. If the user chooses not to
     * read in a configuration of the neural network, including the weights and
     * theta values, then the program asks the user how many input neurons, how
     * many output neurons, how many hidden layers, how many neurons per hidden
     * layer, the highest allowable SSE (if in training mode) the neural
     * network, and the maximum number of epochs to run (if in training mode)
     * should be configured with; the weights are then randomly assigned and
     * theta values are set to the default theta value specified as a field in
     * Neuron. The user is also asked whether they would like to log the program
     * (if in training mode). After, the program initializes a neural network
     * with a configuration object containing the user's
     * setup/configuration/preferences.
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @throws java.lang.ClassNotFoundException
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author lts010, ks061
     */
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
        boolean programRunning = true;
        while (programRunning) {
            ProgramMode programMode;
            InputMode inputMode;
            inputMode = getInputMode();
            programMode = getProgramMode();
            ANNConfig config = null;

            if (inputMode == InputMode.CREATE) {
                config = initializeConfigurationForCreateMode(programMode);
            }
            else if (inputMode == InputMode.READCONFIG) {
                config = initializeConfigurationForReadConfigMode(programMode);
            }

            double[][] data = getData();

            if (inputMode == InputMode.CREATE || inputMode == InputMode.READCONFIG) {

                myNet = new NeuralNet(data, config);
            }
            else {
                String filename = ANNUtility.getInputFilename(".ser",
                                                              "Enter the name of the serialized ANN file (.ser file): ");
                myNet = deserializeANN(filename);

                myNet.setData(data);
                myNet.initializeLayers();
                myNet.getConfiguration().setProgramMode(programMode);
            }

            if (myNet.getConfiguration().getProgramMode() == ProgramMode.TRAINING) {
                ANNLogger.setSwitch(shouldLog());
                ANNLogger.setFile("ANNTrainingLog.csv");
                ANNUtility.logHeader(myNet);

                myNet.train();

                outputTrainingConfiguration();
            }

            if (myNet.getConfiguration().getProgramMode() == ProgramMode.CLASSIFICATION) {
                ArrayList<ArrayList<Double>> setsOfPredictedOutputs = myNet.classify();

                outputPredictedOutputs(setsOfPredictedOutputs);
            }

            programRunning = shouldContinueRunning();
        }
    }

}
