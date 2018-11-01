/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 31, 2018
* Time: 10:55:34 PM
*
* Project: csci205_proj_hw
* Package: hw03.utility
* File: ANNLoggerUtility
* Description: This file contains ANNLoggerUtility, which contains logger
*              utilities for the program.
*
* ****************************************
 */
package hw03.utility;

import hw03.model.neuralnet.NeuralNet;
import hw03.model.neuralnet.logger.ANNLogger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * ANNLoggerUtility contains logger utilities for the ANN program.
 *
 * @author ks061, lts010
 */
public class ANNLoggerUtility {

    /**
     * Writes the header of the ANN log file, including the ANN authors, current
     * time and date, starting parameters, starting theta values, and starting
     * weights at the start of a training session
     *
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logHeader(NeuralNet myNet) {
        ANNLogger.write("ANN-(ks061 & lts010), " + new SimpleDateFormat("MM/dd/yyyy").format(new Date()) + ", " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        ANNLogger.write("---");
        ANNLogger.write("Number of input neurons: ," + Integer.toString(myNet.getConfiguration().getNumInputs()));
        ANNLogger.write("Number of output neurons: ," + Integer.toString(myNet.getConfiguration().getNumOutputs()));
        ANNLogger.write("Number of hidden layers: ," + Integer.toString(myNet.getConfiguration().getNumHiddenLayers()));
        ANNLogger.write("Number of neurons per hidden layer: ," + Integer.toString(myNet.getConfiguration().getNumNeuronsPerHiddenLayer()));
        ANNLogger.write("Maximum SSE (sum of squared errors): ," + Double.toString(myNet.getConfiguration().getHighestSSE()));
        ANNLogger.write("Maximum number of epochs: ," + Integer.toString(myNet.getConfiguration().getNumMaxEpochs()));
        ANNLogger.write("THETAS");
        for (int layerIndex = 0; layerIndex < myNet.getConfiguration().getWeights().size(); layerIndex++) {
            ANNLogger.write("Theta values for layer " + Integer.toString(layerIndex) + ":," + myNet.getConfiguration().getThetas().get(layerIndex).toString().substring(1,
                                                                                                                                                                        myNet.getConfiguration().getThetas().get(layerIndex).toString().length() - 1));
        }
        ANNLogger.write("---");
        logWeights(myNet);
    }

    /**
     * Logs weights of each layer during a particular epoch for a particular
     * input-output set during a training session
     *
     * @param numEpoch number of the epoch training session is processing
     * @param inputOutputSet input-output set training session is processing
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logEpochAndWeights(int numEpoch, double[] inputOutputSet,
                                          NeuralNet myNet) {
        ANNLogger.write("EPOCH " + Integer.toString(numEpoch) + ", " + new SimpleDateFormat("MM/dd/yyyy").format(new Date()) + " at " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        ANNLogger.write("INPUT -," + Arrays.toString(inputOutputSet).substring(1,
                                                                               Arrays.toString(inputOutputSet).length() - 1));
        ANNLogger.write("---");
        logWeights(myNet);
    }

    /**
     * Logs footer of the log file
     *
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logFooter(NeuralNet myNet) {
        ANNLogger.write("Training Ended");
        ANNLogger.write(new SimpleDateFormat("MM/dd/yyyy").format(new Date()) + "," + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    /**
     * Logs current weights of each layer
     *
     * @param myNet neural network running training session to log
     *
     * @author ks061, lts010
     */
    public static void logWeights(NeuralNet myNet) {
        String layerType = null;
        for (int layerIndex = 0; layerIndex < myNet.getConfiguration().getWeights().size(); layerIndex++) {
            if (layerIndex == 0) {
                layerType = "InputLayer";
            }
            else if (layerIndex != myNet.getConfiguration().getWeights().size() - 1) {
                layerType = "OutputLayer";
            }
            else {
                layerType = "HiddenLayer";
            }
            ANNLogger.write(layerType + ", " + Integer.toString(layerIndex));
            try {
                ANNLogger.write("EDGE WEIGHTS -," + myNet.getConfiguration().getWeights().get(layerIndex).toString().substring(1,
                                                                                                                               myNet.getConfiguration().getWeights().get(layerIndex).toString().length() - 1));
            } catch (StringIndexOutOfBoundsException ex) {
                ANNLogger.write("EDGE WEIGHTS -");
            }
        }
        ANNLogger.write("---");
    }

}
