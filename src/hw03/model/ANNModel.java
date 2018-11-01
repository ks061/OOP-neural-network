/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 27, 2018
* Time: 12:43:58 AM
*
* Project: csci205_proj_hw
* Package: hw03.model
* File: ANNModel
* Description: This file contains ANNModel, which represents the model of the
*              neural network MVC application.
* ****************************************
 */
package hw03.model;

import hw03.model.neuralnet.ANNConfig;
import hw03.model.neuralnet.NeuralNet;
import java.util.ArrayList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ANNModel is the model of the neural network MVC application.
 *
 * @author ks061, lts010
 */
public class ANNModel {

    /**
     * Default number of input neurons for the neural network in the MVC model
     */
    public static final int DEFAULT_NUM_INPUTS = 2;
    /**
     * Number of hidden layers for the neural network in the MVC model
     */
    public static final int NUM_HIDDEN_LAYERS = 1;
    /**
     * Default number of hidden neurons in the hidden layer for the neural
     * network in the MVC model
     */
    public static final int DEFAULT_HIDDEN_NEURONS_IN_HIDDEN_LAYER = 3;
    /**
     * Default number of output neurons for the neural network in the MVC model
     */
    public static final int DEFAULT_NUM_OUTPUT = 1;
    /**
     * Default allowed maximum epochs for the neural network in the MVC model
     * (used while training the neural network)
     */
    public static final int DEFAULT_MAX_NUM_EPOCHS = 50000;
    /**
     * Default allowed maximum sum of squared errors for the neural network in
     * the MVC model (used while training the neural network)
     */
    public static final double DEFAULT_MAX_SSE = 0.1;

    /**
     * Represents the index of the input layer within the modeled neural network
     */
    public static final int INPUT_LAYER_INDEX = 0;
    /**
     * Represents the index of the hidden layer within the modeled neural
     * network
     */
    public static final int HIDDEN_LAYER_INDEX = 1;
    /**
     * Represents the index of the output layer within the modeled neural
     * network
     */
    public static final int OUTPUT_LAYER_INDEX = 2;

    /**
     * Property representing whether the neural network is currently in the mode
     * where it steps through one epoch at a time.
     */
    private SimpleBooleanProperty propStepEpoch;

    /**
     * Property representing whether the neural network is currently in the mode
     * where it steps through one input at a time.
     */
    private SimpleBooleanProperty propStepInput;

    /**
     * Property representing whether the neural network show terminate the
     * current learn or classify task
     */
    private SimpleBooleanProperty propTerminate;

    /**
     * Property representing that the neural network will use the hyperbolic
     * tangent activation function in training mode
     */
    private SimpleBooleanProperty propHyperbolicTangent;
    /**
     * Property representing that the neural network will use the Sigmoid
     * activation function in training mode
     */
    private SimpleBooleanProperty propSigmoid;
    /**
     * Property representing that the neural network will use the step
     * activation function in training mode
     */
    private SimpleBooleanProperty propStepFunction;

    /**
     * The model behind this neural network MVC application.
     */
    private NeuralNet neuralNetwork;
    /**
     * Input-output sets of data
     */
    private double[][] theData;
    /**
     * Configuration of the neural network
     */
    private ANNConfig theConfig;

    private ArrayList<ArrayList<SimpleDoubleProperty>> propWeights;
    private ArrayList<ArrayList<StringProperty>> nodeTextProp;
    private final StringProperty averageSSEProp;
    private final StringProperty numEpochsProp;
    private final StringProperty feedbackMessageProp;

    /**
     * Constructor for the model of the neural network MVC application that
     * initializes state properties
     *
     * @author ks061, lts010
     */
    public ANNModel() {
        this.propSigmoid = new SimpleBooleanProperty(true); //sigmoid function is the default
        this.propStepFunction = new SimpleBooleanProperty(false);
        this.propHyperbolicTangent = new SimpleBooleanProperty(false);
        this.propStepEpoch = new SimpleBooleanProperty(false);
        this.propStepInput = new SimpleBooleanProperty(false);
        this.propTerminate = new SimpleBooleanProperty(false);
        this.averageSSEProp = new SimpleStringProperty("");
        this.numEpochsProp = new SimpleStringProperty("");
        this.feedbackMessageProp = new SimpleStringProperty("");
        this.nodeTextProp = new ArrayList<>();

    }

    /**
     * Gets whether the Neural Network is in the mode where it pauses after each
     * epoch
     *
     * @return true if the Neural Network is in the mode where it pauses after
     * each epoch otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getStepEpoch() {
        return propStepEpoch;
    }

    /**
     * Gets whether the Neural Network is in the mode where it pauses after each
     * input
     *
     * @return true if the Neural Network is in the mode where it pauses after
     * each epoch otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getStepInput() {
        return propStepInput;
    }

    /**
     * Gets whether the Neural Network is in the mode where stop after
     * processing the current input.
     *
     * @return true if the Neural Network is in the mode where stop after
     * processing the current input.t otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getTerminate() {
        return propTerminate;
    }

    /**
     * Gets whether the neural network is using the hyperbolic tangent
     * activation function while training
     *
     * @return true if the neural network is using the hyperbolic tangent
     * activation function while training; otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getPropHyperbolicTangent() {
        return propHyperbolicTangent;
    }

    /**
     * Gets whether the neural network is using the Sigmoid activation function
     * while training
     *
     * @return true if the neural network is using the Sigmoid activation
     * function while training; otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getPropSigmoid() {
        return propSigmoid;
    }

    /**
     * Gets whether the neural network is using the step activation function
     * while training
     *
     * @return true if the neural network is using the step activation function
     * while training; otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getPropStepFunction() {
        return propStepFunction;
    }

    /**
     * Gets the neural network encapsulated within the MVC model
     *
     * @return neural network encapsulated within the MVC model
     *
     * @author ks061, lts010
     */
    public NeuralNet getNeuralNetwork() {
        return neuralNetwork;
    }

    /**
     * Gets the input-output sets imported into the model
     *
     * @return input-output sets imported into the model
     *
     * @author ks061, lts010
     */
    public double[][] getTheData() {
        return theData;
    }

    /**
     * Gets the neural network configuration
     *
     * @return neural network configuration
     *
     * @author ks061, lts010
     */
    public ANNConfig getTheConfig() {
        return theConfig;
    }

    /**
     * Sets the input-output sets for the model
     *
     * @param theData input-output sets for the model
     *
     * @author ks061, lts010
     */
    public void setTheData(double[][] theData) {
        this.theData = theData;
    }

    /**
     * Sets the neural network configuration
     *
     * @param theConfig neural network configuration
     *
     * @author ks061, lts010
     */
    public void setTheConfig(ANNConfig theConfig) {
        this.theConfig = theConfig;
    }

    /**
     * Updates properties that will be used to display the neural network,
     * including the weights, output values, expected output values, average sum
     * of squared errors, and number of epochs executed.
     *
     * @param weights weights of neural net edges
     * @param netValues net value of each neuron in the neural network
     * @param expectedOutputs values expected to be generated by neural network
     * in output neurons
     * @param averageSSE average sum of squared errors
     * @param numEpochsExecuted number of epochs that the neural network has
     * executed
     *
     * @author ks061, lts010
     */
    public void updateProperties(ArrayList<ArrayList<Double>> weights,
                                 ArrayList<ArrayList<Double>> netValues,
                                 double[] expectedOutputs, double averageSSE,
                                 int numEpochsExecuted) {
        //set the edges weights
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < weights.get(i).size(); j++) {
                propWeights.get(i).get(j).setValue(weights.get(i).get(j));
            }
        }
        //set the Input values
        for (int i = 0; i < netValues.get(0).size(); i++) {
            nodeTextProp.get(0).get(i).setValue("Input =\n"
                                                + String.format("%5f",
                                                                netValues.get(0).get(
                                                                        i)));
        }
        //set the netValues for the hidden layer
        for (int i = 0; i < netValues.get(1).size(); i++) {
            nodeTextProp.get(1).get(i).setValue("Current =\n"
                                                + String.format("%5f",
                                                                netValues.get(1).get(
                                                                        i)));
        }

        // set current and target outputs
        for (int i = 0; i < netValues.get(2).size(); i++) {
            nodeTextProp.get(2).get(i).setValue("Current =\n"
                                                + String.format("%5f",
                                                                netValues.get(1).get(
                                                                        i))
                                                + "\nTarget =\n"
                                                + String.format("%5f",
                                                                expectedOutputs[i]));
        }
        //set the averageSSE and number of Epochs already completed
        averageSSEProp.setValue(String.format("%5f", averageSSE));
        numEpochsProp.setValue(String.format("%d", numEpochsExecuted));
    }

    /**
     * Updates weighs that will be used to display the neural network,
     *
     * @param weights weights of neural net edges
     *
     * @author ks061, lts010
     */
    public void updateWeightProperties(ArrayList<ArrayList<Double>> weights) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < weights.get(i).size(); j++) {
                propWeights.get(i).get(j).setValue(weights.get(i).get(j));
            }

        }
    }

    /**
     * Creates the neural network within the MVC model and Creates the
     * properties that will be used to update the display.
     *
     * @author ks061, lts010
     */
    public void createNeuralNetwork() {
        ArrayList<ArrayList<Double>> weights = this.theConfig.getWeights();
        this.propWeights = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            this.propWeights.add(new ArrayList<>());
            for (Double weight : weights.get(i)) {
                this.propWeights.get(i).add(new SimpleDoubleProperty(weight));
            }
        }
        this.nodeTextProp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            nodeTextProp.add(new ArrayList<>());
        }
        for (int i = 0; i < theConfig.getNumInputs(); i++) {
            nodeTextProp.get(0).add(new SimpleStringProperty("Input " + i));
        }
        for (int i = 0; i < theConfig.getNumNeuronsPerHiddenLayer(); i++) {
            nodeTextProp.get(1).add(new SimpleStringProperty("Hidden " + i));
        }
        for (int i = 0; i < theConfig.getNumOutputs(); i++) {
            nodeTextProp.get(2).add(new SimpleStringProperty("Output " + i));
        }
        System.out.println("about to create the new net");
        this.neuralNetwork = new NeuralNet(this.theConfig, this);
    }

    /**
     * Updates the property of the message in the status notification bar at the
     * top of the GUI
     *
     * @param msg property of the message in the status notification bar at the
     * top of the GUI
     */
    public void updateMessage(String msg) {
        feedbackMessageProp.setValue(msg);
    }

    /**
     * Gets a property of the message in the status notification bar at the top
     * of the GUI
     *
     * @return property of the message in the status notification bar at the top
     * of the GUI
     */
    public StringProperty getFeedbackMessageProp() {
        return feedbackMessageProp;
    }

    /**
     * Gets the property of the weights of the edges in the MVC model
     *
     * @return property of the weights of the edges in the MVC model
     */
    public ArrayList<ArrayList<SimpleDoubleProperty>> getPropWeights() {
        return propWeights;
    }

    /**
     * Gets the property of the average sum of squared errors in the MVC model
     *
     * @return property of the average sum of the squared errors in the MVC
     * model
     */
    public StringProperty getAverageSSEProp() {
        return averageSSEProp;
    }

    /**
     * Gets the property of the number of epochs that the neural network has run
     * through
     *
     * @return property of the number of epochs that the neural network has run
     * through
     */
    public StringProperty getNumEpochsProp() {
        return numEpochsProp;
    }

    /**
     *
     * @return
     */
    public ArrayList<ArrayList<StringProperty>> getNodeTextProp() {
        return nodeTextProp;
    }

}
