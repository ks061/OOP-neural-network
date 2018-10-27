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

import hw03.NeuralNet;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * ANNModel is the model of the neural network MVC application.
 *
 * @author ks061, lts010
 */
public class ANNModel {

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
     * Property representing whether the neural network is currently paused
     * (after the neural network finishes running through an epoch if it is
     * currently running through an epoch)
     */
    private SimpleBooleanProperty propEpochPause;

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
    private SimpleBooleanProperty propStep;

    /**
     * The model behind this neural network MVC application.
     */
    public NeuralNet neuralNetwork;

    /**
     * Constructor for the model of the neural network MVC application that
     * initializes state properties
     *
     * @author ks061, lts010
     */
    public ANNModel() {
        this.propSigmoid = new SimpleBooleanProperty(true); //sigmoid function is the default
        this.propStep = new SimpleBooleanProperty(false);
        this.propHyperbolicTangent = new SimpleBooleanProperty(false);
        this.propEpochPause = new SimpleBooleanProperty(false);
    }

    /**
     * Gets whether the program is paused (after the neural network finishes
     * running through an epoch if it is currently running through an epoch)
     *
     * @return true if the program is paused (after the neural network finishes
     * running through an epoch if it is currently running through an epoch);
     * otherwise false
     *
     * @author ks061, lts010
     */
    public SimpleBooleanProperty getPropEpochPause() {
        return propEpochPause;
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
    public SimpleBooleanProperty getPropStep() {
        return propStep;
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
     * Sets the neural network to encapsulate within the MVC model
     *
     * @param neuralNetwork neural network to encapsulate within the MVC model
     *
     * @author ks061, lts010
     */
    public void setNeuralNetwork(NeuralNet neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }
}
