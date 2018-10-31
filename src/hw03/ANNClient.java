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
package hw03;

import hw03.ANNLogger.ANNLoggerStatus;
import hw03.utility.ANNUtility;
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
     *
     * @author ks061, lts010
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
        System.out.println();
        if (userDecision.equalsIgnoreCase("y") || userDecision.equalsIgnoreCase(
                "yes")) {
            return true;
        }
        return false;
    }
}
