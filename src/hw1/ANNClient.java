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
import java.util.Scanner;

/**
 *
 * @author cld028
 */
public class ANNClient {

    /**
     * Asks for a file containing input values and prints and writes them to a
     * file
     *
     * @param inputOption - the option for how the neural network is created
     * (can be "read or "create")
     * @param numInputs - number of inputs for the neural net (needed if
     * inputOption is create)
     * @param numOutputs - number of outputs for the neural net (needed if the
     * inputOption is create)
     *
     * @author lts010
     */
    public static void classificationMode(String inputOption, int numInputs,
                                          int numOutputs) {
        Scanner in = new Scanner(System.in);
        Scanner fReader = new Scanner(System.in);
        boolean csvFound = false;
        if (inputOption.equals("read")) {
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
     * Create a new neural net and either test w/in neural net, or create
     * another function to control testing of the neural network
     *
     * @param args the command line arguments
     * @author lts010
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean invalidInput = true;
        String inputOption = "";
        String modeOption = "";
        int numInputs = 0;
        int numOutputs = 0;

        while (invalidInput) { //determine inputOption
            System.out.println(
                    "Do you want to create a new ANN, or read in a config file?");
            System.out.println(
                    "Type in 'create' for the former option, or 'read' for the latter option:");
            inputOption = in.nextLine();
            if (inputOption.equals("create") || inputOption.equals("read")) {
                break; //input is now valid
            }
            else {
                System.out.println("INVALID INPUT. Try again.");
            }
        }

        if (inputOption.equals("create")) //if we create a new ANN, we need numInputs and numOutputs
        {
            while (invalidInput) {
                System.out.println(
                        "What should the number of inputs be (as an integer)? :");
                if (in.hasNextInt()) {
                    numInputs = in.nextInt();
                }
                else {
                    System.out.println(
                            "INVALID INPUT. Reinput number of inputs.");
                    continue;
                }
                System.out.println(
                        "What should the number of outputs be (as an integer)?");
                if (in.hasNextInt()) {
                    numOutputs = in.nextInt();
                    break; //input is now valid
                }
                else {
                    System.out.println(
                            "INVALID INPUT. Reinput number of inputs and outputs.");
                }
            }
        }

        while (invalidInput) { //determine modeOption
            System.out.println(
                    "Type 'training' for training mode, and 'classification' for classification mode:");
            modeOption = in.nextLine();
            if (modeOption.equals("training") || modeOption.equals(
                    "classification")) {
                break; //input is now valid
            }
            else {
                System.out.println("INVALID INPUT. Try again.");
            }
        }

        if (modeOption.equals("classfication")) {
            classificationMode(inputOption, numInputs, numOutputs);
        }
        //run trainingMode method if modeOption == trainingMode
    }
}
