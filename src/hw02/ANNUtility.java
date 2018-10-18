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
*
* ****************************************
 */
package hw02;

import java.io.File;
import java.util.ArrayList;
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

}
