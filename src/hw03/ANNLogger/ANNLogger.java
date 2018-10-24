/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 18, 2018
* Time: 8:14:15 AM
*
* Project: csci205_proj_hw
* Package: hw02
* File: ANNLogger
* Description: This file contains the ANNLogger, which logs the neural network
*              program session to a CSV file.
*
* ****************************************
 */
package hw03.ANNLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ANNLogger logs the neural network program session to a CSV file
 *
 * @author ks061, lts010
 */
public class ANNLogger {

    /**
     * Represents whether the logger is on or off (printing out to a .txt file
     * or not).
     */
    private static ANNLoggerStatus loggerStatus;

    /**
     * Filename of the file that the ANNLogger will write to
     */
    private static String filename;

    /**
     * Cannot create a new instance of Logger; managed via the getInstance
     * method
     *
     * @author ks061, lts010
     */
    private ANNLogger() {
    }

    /**
     * Sets the logger on or off with an enum value ANNLoggerStatus
     *
     * @param specifiedLoggerStatus turning the logger on or off
     *
     * @author ks061, lts010
     */
    public static void setSwitch(ANNLoggerStatus specifiedLoggerStatus) {
        loggerStatus = specifiedLoggerStatus;
    }

    /**
     * Takes in a filename and sets the file of the ANNLogger instance to that
     *
     * @param specifiedFilename file to output the logger to
     *
     * @author ks061, lts010
     */
    public static void setFile(String specifiedFilename) {
        filename = specifiedFilename;
    }

    /**
     * Writes a string to a new line at the bottom of the specified log file
     *
     * @param strToWriteToNewLine string to append as a new line to the log file
     *
     * @see
     * <a href="http://www.java67.com/2015/07/how-to-append-text-to-existing-file-in-java-example.html">
     * java67.com </a>
     *
     * @author ks061, lts010
     */
    public static void write(String strToWriteToNewLine) {
        if (loggerStatus == null) {
            System.out.println("Error: Logger status has not been set.");
            return;
        }
        if (loggerStatus == ANNLoggerStatus.OFF) {
            return;
        }
        if (filename == null) {
            System.out.println(
                    "Error: No file specified for the logger to write to.");
            return;
        }
        PrintWriter printWriter = null;
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(strToWriteToNewLine);
            printWriter.flush();
        } catch (IOException io) {
            System.out.println("Error: Logger could not log output to file.");
        } finally {
            try {
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (NullPointerException npe) {
            } catch (IOException io) {
                System.out.println(
                        "Error: Logger could not close output stream.");
            }
        }
    }
}
