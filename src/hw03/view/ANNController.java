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
* Description:
*
* ****************************************
 */
package hw03.view;

import hw03.ActivationFunction.ActivationFunction;
import hw03.ActivationFunction.HyperbolicTangentActivationFunction;
import hw03.ActivationFunction.SigmoidActivationFunction;
import hw03.ActivationFunction.StepActivationFunction;
import hw03.Edge;
import hw03.Layer.Layer;
import hw03.Layer.OutputLayer;
import hw03.NeuralNet;
import hw03.Neuron.Neuron;
import hw03.ProgramMode;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 *
 * @author lts010, ks061
 */
public class ANNController implements EventHandler<ActionEvent> {

    /**
     * The view of this neural network MVC application.
     */
    private ANNView theView;
    /**
     * The model behind this neural network MVC application.
     */
    private NeuralNet theModel;
    //TODO should the next two be here?
    private ArrayList<ArrayList<NodeCircle>> nodeCircles;
    private ArrayList<ArrayList<EdgeLine>> edgeLines;
    private SimpleBooleanProperty propSigmoid;
    private SimpleBooleanProperty propStep;
    private SimpleBooleanProperty propHyperbolicTangent;
    private SimpleBooleanProperty propEpochPause;

    public ANNController(ANNView theView) {
        this.theView = theView;
        this.theModel = this.theView.getMyNet();
        this.nodeCircles = this.theView.getNodeCircles();
        this.edgeLines = this.theView.getEdgeLines();
        this.theView.getAlphaInput().setOnAction(this);
        this.theView.getMuInput().setOnAction(this);
        this.theView.getClassify().setOnAction(this);
        this.theView.getLearn().setOnAction(this);
        this.theView.getStepDataInstance().setOnAction(this);
        this.theView.getStepEpoch().setOnAction(this);

        this.propSigmoid = new SimpleBooleanProperty(true); //sigmoid function is the default
        this.propStep = new SimpleBooleanProperty(false);
        this.propHyperbolicTangent = new SimpleBooleanProperty(false);
        this.propEpochPause = new SimpleBooleanProperty(false);

        propSigmoid.bind(theView.getSigmoid().selectedProperty());
        propStep.bind(theView.getStep().selectedProperty());
        propHyperbolicTangent.bind(
                theView.getHyperbolicTangent().selectedProperty());
        propEpochPause.bind(theView.getEpochPause().selectedProperty());
    }

    @Override
    public void handle(ActionEvent event) {
        updateActivationFunction();
        if (event.getSource() == this.theView.getAlphaInput()) {
            try {
                String alpha = theView.getAlphaInput().getText();
                if (alpha.length() > 0) {
                    double newAlpha = Double.parseDouble(alpha);
                    this.theModel.setAlpha(newAlpha);
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
        else if (event.getSource() == this.theView.getMuInput()) {
            try {
                String mu = theView.getMuInput().getText();
                if (mu.length() > 0) {
                    theView.getCurrentMu().setText(mu);
                    setNewMu(mu);
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
        else if (event.getSource() == this.theView.getLearn()) {
            System.out.println("1");
        }
        else if (event.getSource() == this.theView.getClassify()) {
            try {
                System.out.println("2");
                theModel.classify();
                updateNodeValues();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ANNController.class.getName()).log(Level.SEVERE,
                                                                    null, ex);
            }
        }
        else if (event.getSource() == this.theView.getStepDataInstance()) {
            System.out.println("3");
        }
        else if (event.getSource() == this.theView.getStepEpoch()) {
            System.out.println("4");
        }
        if (this.theModel.getConfiguration().getProgramMode() == ProgramMode.TRAINING) {
            OutputLayer outputLayer = (OutputLayer) this.theModel.getLayers().get(
                    2);
            this.theView.getCurrentSSE().setText(String.format("%f",
                                                               this.theModel.getTrainingAverageSSE()));
            this.theView.getCurrentEpochNum().setText(String.format("%d",
                                                                    this.theModel.getTrainingNumberOfEpochs()));
        }
    }

    public void updateActivationFunction() {
        ActivationFunction currentActivationFunction = this.theModel.getLayers().get(
                0).getNeurons().get(0).getActivationFunction();
        if (propSigmoid.get() && !(currentActivationFunction instanceof SigmoidActivationFunction)) {
            SigmoidActivationFunction newActivationFunction = new SigmoidActivationFunction();
            for (Layer layer : theModel.getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
        }
        else if (propStep.get() && !(currentActivationFunction instanceof StepActivationFunction)) {
            StepActivationFunction newActivationFunction = new StepActivationFunction();
            for (Layer layer : theModel.getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
        }
        else if (propHyperbolicTangent.get() && !(currentActivationFunction instanceof HyperbolicTangentActivationFunction)) {
            HyperbolicTangentActivationFunction newActivationFunction = new HyperbolicTangentActivationFunction();
            for (Layer layer : theModel.getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
        }
    }

    public boolean checkActivationFunction(ActivationFunction activationFunction) {
        return false;
    }

    /**
     * Changes the momentum constant for every edge in theModel
     *
     * @param mu
     * @author lts010
     */
    public void setNewMu(String mu) {
        double newMu = Double.parseDouble(mu);
        ArrayList<Neuron> inputNeurons = theModel.getLayers().get(0).getNeurons();
        ArrayList<Neuron> hiddenNeurons = theModel.getLayers().get(1).getNeurons();
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
    }

    /**
     *
     * @author ks061, lts010
     */
    public void updateEdgeColors() {

        for (ArrayList<EdgeLine> edges : edgeLines) {
            for (EdgeLine edgeLine : edges) {
                edgeLine.updateColor();
            }
        }
    }

    public void updateNodeValues() {
        for (Neuron neuron : theModel.getLayers().get(0).getNeurons()) {
            int layerNum = 0;
            int neuronNum = neuron.getNeuronNum();
            double netValue = neuron.getNetValue();
            String text = String.format("%f", netValue);
            theView.getNodeCircles().get(layerNum).get(neuronNum).setText(text);
        }
        for (Neuron neuron : theModel.getLayers().get(2).getNeurons()) {
            int layerNum = 2;
            int neuronNum = neuron.getNeuronNum();
            double netValue = neuron.getNetValue();
            String text = String.format("%f", netValue);
            theView.getNodeCircles().get(layerNum).get(neuronNum).setText(text);
        }
    }
}
