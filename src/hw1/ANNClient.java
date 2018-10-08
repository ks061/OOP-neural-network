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
import java.nio.file.Files;
import java.nio.file.Paths;
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
     * @param inputMode - the mode with which we create the neural network with
     * @param numInputs - number of inputs for the neural net (needed if
     * inputOption is create)
     * @param numOutputs - number of outputs for the neural net (needed if the
     * inputOption is create)
     *
     * @author lts010
     */
    public static void classificationMode(Mode inputMode, int numInputs,
                                          int numOutputs) {
        Scanner in = new Scanner(System.in);
        Scanner fReader = new Scanner(System.in);
        boolean csvFound = false;
        if (inputMode.equals("read")) {
            while (!csvFound) {
                try {
                    System.out.println("Enter the name of the config file: ");
                    File csv = new File(in.nextLine());
                    fReader = new Scanner(csv);
                    break; //input is now valid
                } catch (FileNotFoundException ex) {
                    System.out.println("INVALID INPUT. Try again.");
                }
            }
        }
        /**
         * TO DO Read the config file if inputOption == read; Ask for input
         * data, and read it; Create the neural network; Feed forward the neural
         * net; Print and write the outputs
         */
    }

    /**
     * Imports training data from the CSV file specified by the end user
     *
     * @return training data
     *
     * @author ks061
     */
    public static double[][] getTrainingData() {
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
        double[][] trainingData = new double[lineCount][];
        // Reads in input data from scanner
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
     * Runs the training mode feature of the program.
     *
     * @author ks061
     */
    public static void trainingMode() {
        double[][] trainingData = getTrainingData();
        myNet = new NeuralNet(trainingData);
    }

    /**
     * Create a new neural net and either test w/in neural net, or create
     * another function to control testing of the neural network
     *
     * @param args the command line arguments
     * @author lts010, ks061
     */
    public static void main(String[] args) {
        int numInputs = 0;
        int numOutputs = 0;
        Mode runMode;
        Mode inputMode;
        inputMode = getInputMode();

        if (inputMode == Mode.CREATE) {
            numInputs = getNumInputs();
            numOutputs = getNumOutputs();
        }

        runMode = getRunMode();
        if (runMode == Mode.CLASSIFICATION) {
            classificationMode(inputMode, numInputs, numOutputs);
        }
        else {
            trainingMode();
        }
    }
}
