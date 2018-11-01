/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 31, 2018
* Time: 10:49:16 PM
*
* Project: csci205_proj_hw
* Package: hw03.utility
* File: ANNIOUtility
* Description: This file contains ANNIOUtility, which contains input and output
*              utilities for the ANN program.
* ****************************************
 */
package hw03.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * ANNIOUtility contains input and output utilities for the ANN program.
 *
 * @author ks061, lts010
 */
public class ANNIOUtility {

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
     * Imports training data from a CSV file specified by the end user
     *
     * @param csvFile file to retrieve input and/or output sets from
     *
     * @return training data, including sets of inputs and corresponding
     * expected outputs
     *
     * @author ks061, lts010
     */
    public static double[][] getData(File csvFile) {
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

}
