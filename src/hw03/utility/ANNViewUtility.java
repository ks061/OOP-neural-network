/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 26, 2018
* Time: 11:32:24 PM
*
* Project: csci205_proj_hw
* Package: hw03.utility
* File: ANNViewUtility
* Description: This file contains ANNViewUtility, which contains a collection
*              of utilities for use by the view in the MVC ANN application.
* ****************************************
 */
package hw03.utility;

import javafx.scene.control.Alert;

/**
 * ANNViewUtility contains a collection of utilities for use by the view in the
 * MVC ANN application.
 *
 * @author ks061, lts010
 */
public class ANNViewUtility {

    /**
     * Gets an integer, if positive, from a string; if the parsed integer is not
     * positive, returns -1. If the string is empty, a specified default value
     * is returned
     *
     * @param input string from which an integer will be parsed from
     * @param defaultValue default value returned if the integer could not be
     * parsed from the string
     * @param errorMessage error message displayed if the parsed integer value
     * is negative or cannot be parsed as an integer
     *
     * @return positive integer parsed from string; -1 if integer extracted is
     * negative or an integer value could not be parsed from the string
     *
     * @author ks061, lts010
     */
    public static int extractPositiveIntFromText(String input, int defaultValue,
                                                 String errorMessage) {
        if (input == null || input.isEmpty()) {
            return defaultValue;
        }
        int result;
        try {
            result = Integer.parseInt(input);
            if (result > 0) {
                return result;
            }
            showInputAlert(errorMessage,
                           input + " cannot be parsed as a positive integer because it is a negative integer.");
        } catch (NumberFormatException e) {
            showInputAlert(errorMessage,
                           input + " cannot be parsed as a positive integer.");
        }
        return -1;
    }

    /**
     * Shows an input alert box
     *
     * @param headerText header for the alert box
     * @param contentText content for the alert box
     *
     * @author ks061, lts010
     */
    public static void showInputAlert(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

}
