/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 7, 2016
 * Time: 6:40:50 AM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: ANNClient
 * Description:
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
 * @author cld028
 */
public class ANNClient {

    /**
     * Imports training data from the CSV file specified by the end user
     *
     * @param myNet neural network
     * @return training data
     */
    public static double[][] getTrainingData(NeuralNet myNet) {
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
     * Runs the training mode feature of the program.
     *
     * @param myNet neural network
     */
    public static void trainingMode(NeuralNet myNet) {
        double[][] trainingData = getTrainingData(myNet);
        myNet = new NeuralNet(trainingData);
    }

    /**
     * Create a new neural net and either test w/in neural net, or create
     * another function to control testing of the neural network
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NeuralNet myNet = new NeuralNet();
    }

}
