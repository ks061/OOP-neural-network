/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 24, 2018
* Time: 5:25:27 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: ANNController
* Description: This file contains ANNController, which represents the controller
*              of the neural network application.
* ****************************************
 */
package hw03.controller;

import hw03.ANNLogger.ANNLogger;
import hw03.ANNLogger.ANNLoggerStatus;
import hw03.ActivationFunction.ActivationFunction;
import hw03.ActivationFunction.HyperbolicTangentActivationFunction;
import hw03.ActivationFunction.SigmoidActivationFunction;
import hw03.ActivationFunction.StepActivationFunction;
import hw03.Edge;
import hw03.Layer.Layer;
import hw03.Layer.OutputLayer;
import hw03.Neuron.Neuron;
import hw03.ProgramMode;
import hw03.model.ANNModel;
import hw03.view.ANNView;
import hw03.view.EdgeLine;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 * ANNController represents the controller of the neural network MVC
 * application.
 *
 * @author lts010, ks061
 */
public class ANNController implements EventHandler<ActionEvent> {

    /**
     * The model of the neural network MVC application.
     */
    private ANNModel theModel;

    /**
     * The view of the neural network MVC application.
     */
    private ANNView theView;

    /**
     * Constructor to initialize the controller of the neural network MVC
     * application
     *
     * @param theModel pointer to the model of the neural network MVC
     * application
     * @param theView pointer to the model of the neural network MVC application
     *
     * @author ks061, lts010
     */
    public ANNController(ANNModel theModel, ANNView theView) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.getAlphaInput().setOnAction(this);
        this.theView.getMuInput().setOnAction(this);
        this.theView.getClassify().setOnAction(this);
        this.theView.getLearn().setOnAction(this);
        this.theView.getStepDataInstance().setOnAction(this);
        this.theView.getStepEpoch().setOnAction(this);

        this.theModel.getPropSigmoid().bind(
                theView.getSigmoid().selectedProperty());
        this.theModel.getPropStep().bind(theView.getStep().selectedProperty());
        this.theModel.getPropHyperbolicTangent().bind(
                theView.getHyperbolicTangent().selectedProperty());
        this.theModel.getPropEpochPause().bind(
                theView.getEpochPause().selectedProperty());
    }

    /**
     * Handles events that occur in the application
     *
     * @param event event that occurs in the application
     *
     * @author ks061, lts010
     */
    @Override
    public void handle(ActionEvent event) {
        updateActivationFunction();
        if (event.getSource() == this.theView.getAlphaInput()) {
            setNewAlpha();
        }
        else if (event.getSource() == this.theView.getMuInput()) {
            setNewMu();
        }
        else if (event.getSource() == this.theView.getLearn()) {
            Task learnTask = new Task<Void>() {
                @Override
                public Void call() throws FileNotFoundException {
                    ANNLogger.setSwitch(ANNLoggerStatus.OFF);
                    theModel.getNeuralNetwork().train();
                    return null;
                }
            };
            Thread learningThread = new Thread(learnTask);
            learningThread.setDaemon(true);
            learningThread.start();

        }
        else if (event.getSource() == this.theView.getClassify()) {
            System.out.println("2");
        }
        else if (event.getSource() == this.theView.getStepDataInstance()) {
            System.out.println("3");
        }
        else if (event.getSource() == this.theView.getStepEpoch()) {
            System.out.println("4");
        }
        if (this.theModel.getNeuralNetwork().getConfiguration().getProgramMode() == ProgramMode.TRAINING) {
            // TODO unused reference to outputLayer, why do we need this?
            OutputLayer outputLayer = (OutputLayer) this.theModel.getNeuralNetwork().getLayers().get(
                    ANNModel.OUTPUT_LAYER_INDEX);
            this.theView.getCurrentSSE().setText(String.format("%f",
                                                               this.theModel.getNeuralNetwork().getTrainingAverageSSE()));
            this.theView.getCurrentEpochNum().setText(String.format("%d",
                                                                    this.theModel.getNeuralNetwork().getTrainingNumberOfEpochs()));
        }
    }

    /**
     * Updates activation functions stored in each neuron based on user
     * selection encapsulated within the MVC model ANNModel
     *
     * @author ks061, lts010
     */
    public void updateActivationFunction() {
        ActivationFunction currentActivationFunction = this.theModel.getNeuralNetwork().getLayers().get(
                ANNModel.INPUT_LAYER_INDEX).getNeurons().get(0).getActivationFunction();

        if (theModel.getPropSigmoid().get() && !(currentActivationFunction instanceof SigmoidActivationFunction)) {
            SigmoidActivationFunction newActivationFunction = new SigmoidActivationFunction();
            for (Layer layer : theModel.getNeuralNetwork().getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
        }
        else if (theModel.getPropStep().get() && !(currentActivationFunction instanceof StepActivationFunction)) {
            StepActivationFunction newActivationFunction = new StepActivationFunction();
            for (Layer layer : theModel.getNeuralNetwork().getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
        }
        else if (theModel.getPropHyperbolicTangent().get() && !(currentActivationFunction instanceof HyperbolicTangentActivationFunction)) {
            HyperbolicTangentActivationFunction newActivationFunction = new HyperbolicTangentActivationFunction();
            for (Layer layer : theModel.getNeuralNetwork().getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
        }
    }

    /**
     * Sets the alpha value for neural network of the program based on the alpha
     * value entered in the corresponding text box within the GUI
     *
     * @author ks061, lts010
     */
    public void setNewAlpha() {
        try {
            String alpha = theView.getAlphaInput().getText();
            if (alpha.length() > 0) {
                double newAlpha = Double.parseDouble(alpha);
                this.theModel.getNeuralNetwork().setAlpha(newAlpha);
                theView.getCurrentAlpha().setText(alpha);
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",
                                               this.theView.getAlphaInput().getText()));
            alert.show();
        }
    }

    /**
     * Sets the momentum value for the neural network of the program based on
     * the momentum value entered in the corresponding text box within the GUI.
     *
     * @author lts010, ks061
     */
    public void setNewMu() {
        try {
            String mu = theView.getMuInput().getText();
            if (mu.length() > 0) {
                double newMu = Double.parseDouble(mu);
                ArrayList<Neuron> inputNeurons = theModel.getNeuralNetwork().getLayers().get(
                        ANNModel.INPUT_LAYER_INDEX).getNeurons();
                ArrayList<Neuron> hiddenNeurons = theModel.getNeuralNetwork().getLayers().get(
                        ANNModel.HIDDEN_LAYER_INDEX).getNeurons();
                for (Neuron inputNeuron : inputNeurons) {
                    for (Edge edge : inputNeuron.getOutEdges()) {
                        edge.setMu(newMu);
                    }
                }
                for (Neuron hiddenNeuron : hiddenNeurons) {
                    for (Edge edge : hiddenNeuron.getOutEdges()) {
                        edge.setMu(newMu);
                    }
                }
                theView.getCurrentMu().setText(mu);
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",
                                               this.theView.getMuInput().getText()));
            alert.show();
        }
    }

    /**
     * Updates the color for each edge line based on the edge weight associated
     * with the provided edge and layer number (red for negative weights, green
     * for positive, and blue for zero)
     *
     * @author ks061, lts010
     */
    public void updateEdgeColors() {

        for (ArrayList<EdgeLine> edges : theView.getEdgeLines()) {
            for (EdgeLine edgeLine : edges) {
                edgeLine.updateColor();
            }
        }
    }

    /**
     * Updates the neuron values displayed in the view
     *
     * @author ks061, lts010
     */
    public void updateNeuronValues() {
        for (Neuron neuron : theModel.getNeuralNetwork().getLayers().get(
                ANNModel.INPUT_LAYER_INDEX).getNeurons()) {
            int neuronNum = neuron.getNeuronNum();
            double netValue = neuron.getNetValue();
            String text = String.format("%f", netValue);
            theView.getNodeCircles().get(ANNModel.INPUT_LAYER_INDEX).get(
                    neuronNum).setText(text);
        }
        for (Neuron neuron : theModel.getNeuralNetwork().getLayers().get(
                ANNModel.OUTPUT_LAYER_INDEX).getNeurons()) {
            int neuronNum = neuron.getNeuronNum();
            double netValue = neuron.getNetValue();
            String text = String.format("%f", netValue);
            theView.getNodeCircles().get(ANNModel.OUTPUT_LAYER_INDEX).get(
                    neuronNum).setText(text);
        }
    }
}
