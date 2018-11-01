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

import hw03.model.ANNModel;
import hw03.model.neuralnet.ANNConfig;
import hw03.model.neuralnet.Edge;
import hw03.model.neuralnet.NeuralNet;
import hw03.model.neuralnet.ProgramMode;
import hw03.model.neuralnet.activationfunction.ActivationFunction;
import hw03.model.neuralnet.activationfunction.HyperbolicTangentActivationFunction;
import hw03.model.neuralnet.activationfunction.SigmoidActivationFunction;
import hw03.model.neuralnet.activationfunction.StepActivationFunction;
import hw03.model.neuralnet.layer.Layer;
import hw03.model.neuralnet.logger.ANNLoggerStatus;
import hw03.model.neuralnet.neuron.Neuron;
import hw03.utility.ANNUtility;
import hw03.utility.ANNUtilityGUICompatible;
import hw03.utility.ANNViewUtility;
import hw03.view.ANNMenuBar;
import hw03.view.ANNView;
import hw03.view.EdgeLine;
import hw03.view.NodeCircle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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
     * Thread that the neural network runs on
     */
    private Thread neuralNetThread = new Thread();

    /**
     * Constructor to initialize the controller of the neural network MVC
     * application
     *
     * @param theModel pointer to the model of the neural network MVC
     * application
     * @param theView pointer to the view of the neural network MVC application
     *
     * @author ks061, lts010
     */
    public ANNController(ANNModel theModel, ANNView theView) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.getAlphaInput().setOnAction(this);
        this.theView.getMuInput().setOnAction(this);
        this.theView.getClassifyBtn().setOnAction(this);
        this.theView.getLearnBtn().setOnAction(this);
        this.theView.getStepBtn().setOnAction(this);
        this.theView.getRandomizeBtn().setOnAction(this);

        this.theView.getRunRBtn().setOnAction(this);
        //TODO the next 3 and their functions below can probably be deleted
        //These buttons are now hand by binding them to the variable that
        //they set.
        // this.theView.getInputStepRBtn().setOnAction(this);
        //this.theView.getEpochStepRBtn().setOnAction(this);
        //this.theView.getTerminateRBtn().setOnAction(this);

        this.theView.getANNMenuBar().getLoadConfigFileMI().setOnAction(this);
        this.theView.getANNMenuBar().getSaveConfigFileMI().setOnAction(this);
        this.theView.getANNMenuBar().getLoadTestFileMI().setOnAction(this);
        this.theView.getANNMenuBar().getLoadTrainingFileMI().setOnAction(this);
        this.theView.getANNMenuBar().getConfigMI().setOnAction(this);
        this.theView.getANNMenuBar().getExitMI().setOnAction(this);
        this.theView.getANNMenuBar().getCancelBtn().setOnAction(this);
        this.theView.getANNMenuBar().getSubmitBtn().setOnAction(this);

        this.createButtonBindings();
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
        if (event.getSource() == this.theView.getAlphaInput()) {
            setNewAlpha();
        }
        else if (event.getSource() == this.theView.getMuInput()) {
            setNewMu();
        }
        else if (event.getSource() == this.theView.getLearnBtn()) {
            startLearnThread();

        }
        else if (event.getSource() == this.theView.getClassifyBtn()) {
            startClassify();
        }
        else if (event.getSource() == this.theView.getStepBtn()) {
            theModel.getNeuralNetwork().notifyNeuralNet();
        }
        else if (event.getSource() == this.theView.getRandomizeBtn()) {
            randomizeWeights();
        }
        else if (event.getSource() == this.theView.getRunRBtn()) {
            theModel.getNeuralNetwork().notifyNeuralNet();
            System.out.println("RunRBtn");
        }
        else if (event.getSource() == this.theView.getANNMenuBar().getExitMI()) {
            System.exit(0);

        }
        else if (event.getSource() == this.theView.getANNMenuBar().getConfigMI()) {
            this.theView.makeConfigGroupVisible();
            this.theView.getANNMenuBar().getConfigInfo();

        }
        else if (event.getSource() == this.theView.getANNMenuBar().getLoadConfigFileMI()) {
            loadConfigFile();

        }
        else if (event.getSource() == this.theView.getANNMenuBar().getSaveConfigFileMI()) {
            saveConfigFile();

        }
        else if (event.getSource() == this.theView.getANNMenuBar().getLoadTrainingFileMI()) {
            loadTrainingFile();
        }
        else if (event.getSource() == this.theView.getANNMenuBar().getLoadTestFileMI()) {
            LoadTestFileMI();

        }
        else if (event.getSource() == this.theView.getANNMenuBar().getCancelBtn()) {
            theModel.getFeedbackMessageProp().setValue("");
            this.theView.makeNetworkGroupVisible();
        }
        else if (event.getSource() == this.theView.getANNMenuBar().getSubmitBtn()) {
            processConfigRequest();
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
            this.theModel.getNeuralNetwork().getConfiguration().setActivationFunction(
                    newActivationFunction);
        }
        else if (theModel.getPropStepFunction().get() && !(currentActivationFunction instanceof StepActivationFunction)) {
            StepActivationFunction newActivationFunction = new StepActivationFunction();
            for (Layer layer : theModel.getNeuralNetwork().getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
            this.theModel.getNeuralNetwork().getConfiguration().setActivationFunction(
                    newActivationFunction);
        }
        else if (theModel.getPropHyperbolicTangent().get() && !(currentActivationFunction instanceof HyperbolicTangentActivationFunction)) {
            HyperbolicTangentActivationFunction newActivationFunction = new HyperbolicTangentActivationFunction();
            for (Layer layer : theModel.getNeuralNetwork().getLayers()) {
                for (Neuron neuron : layer.getNeurons()) {
                    neuron.setActivationFunction(newActivationFunction);
                }
            }
            this.theModel.getNeuralNetwork().getConfiguration().setActivationFunction(
                    newActivationFunction);
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
                theModel.getTheConfig().setAlpha(newAlpha);
                theModel.getFeedbackMessageProp().setValue(
                        "alpha successfully updated");
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",
                                               this.theView.getAlphaInput().getText()));
            alert.show();
            theModel.getFeedbackMessageProp().setValue(
                    "Alpha not updated, invalid number");
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
            theModel.getFeedbackMessageProp().setValue("");
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
                theModel.getTheConfig().setMu(newMu);
                theModel.getFeedbackMessageProp().setValue(
                        "Mu successfully updated");
            }
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.setContentText(String.format("Can not convert \"%s\"",
                                               this.theView.getMuInput().getText()));
            alert.show();
            theModel.getFeedbackMessageProp().setValue(
                    "Mu not updated, invalid number");
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
                    neuronNum).setText(new Text(text));
        }
        for (Neuron neuron : theModel.getNeuralNetwork().getLayers().get(
                ANNModel.OUTPUT_LAYER_INDEX).getNeurons()) {
            int neuronNum = neuron.getNeuronNum();
            double netValue = neuron.getNetValue();
            String text = String.format("%f", netValue);
            theView.getNodeCircles().get(ANNModel.OUTPUT_LAYER_INDEX).get(
                    neuronNum).setText(new Text(text));
        }
    }

    /**
     * Updates bindings for the CircleNode text and EdgeLine Color. This is
     * called whenever the configuration of the neural network changes. Since
     * the graphic depiction of the neural net changes, the number of nodes and
     * edges are likely to have changed.
     *
     * @param weights weights of the edges in the updated neural network display
     *
     * @author ks061, lts010
     */
    public void initNetorkBindings(ArrayList<ArrayList<Double>> weights) {
        System.out.println("initializing network bindings");
        ArrayList<ArrayList<EdgeLine>> edgeLines = theView.getEdgeLines();
        ArrayList<ArrayList<SimpleDoubleProperty>> propWeights = theModel.getPropWeights();

        for (int i = 0; i < edgeLines.get(0).size(); i++) {
            edgeLines.get(0).get(i).strokeProperty().bind(Bindings.
                    when(propWeights.get(0).get(i).greaterThan(0)).
                    then(Color.GREEN).
                    otherwise(Color.RED));
        }
        for (int i = 0; i < edgeLines.get(1).size(); i++) {
            edgeLines.get(1).get(i).strokeProperty().bind(Bindings.
                    when(propWeights.get(1).get(i).greaterThan(0)).
                    then(Color.GREEN).
                    otherwise(Color.RED));
        }
        ArrayList<ArrayList<NodeCircle>> nodeCircles = theView.getNodeCircles();
        ArrayList<ArrayList<StringProperty>> nodeTextProps = theModel.getNodeTextProp();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < nodeCircles.get(i).size(); j++) {
                nodeCircles.get(i).get(j).getText().textProperty().
                        bind(nodeTextProps.get(i).get(j));
            }
        }
    }

    /**
     * Creates all of the bindings to the view that do not need to be updated
     * when the configuration changes.
     *
     * @author ks061, lts010
     */
    public void createButtonBindings() {
        this.theModel.getPropSigmoid().bind(
                theView.getSigmoidBtn().selectedProperty());
        this.theModel.getPropStepFunction().bind(
                theView.getStepFunctionBtn().selectedProperty());
        this.theModel.getPropHyperbolicTangent().bind(
                theView.getHyperbolicTangentBtn().selectedProperty());
        this.theModel.getStepEpoch().bind(
                theView.getEpochStepRBtn().selectedProperty());
        this.theModel.getStepInput().bind(
                theView.getInputStepRBtn().selectedProperty());
        this.theModel.getTerminate().bind(
                theView.getTerminateRBtn().selectedProperty());

        ArrayList<ArrayList<NodeCircle>> nodeCircles = theView.getNodeCircles();
        theView.getCurrentEpochNum().textProperty().bind(
                theModel.getNumEpochsProp());
        theView.getFeedbackMessage().textProperty().bind(
                theModel.getFeedbackMessageProp());

    }

    /**
     * Saves all configuration information of a neural net to a .txt file
     *
     * @param nN neural network whose configuration will be exported
     * @throws java.io.FileNotFoundException if the file for the configuration
     * to be written to as specified by the user cannot be written to or another
     * error occurs while opening or creating the file
     * @see
     * <a href=https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html>
     * PrintWriter </a>
     *
     * @author lts010, ks061
     */
    public void exportConfig(NeuralNet nN) throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("SaveConfiguration");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "Text Files", "*.txt"));
        File outputFile = fileChooser.showSaveDialog(theView.getTheStage());
        if (outputFile == null) {
            System.out.println("didn't get a filename.");
            return;
        }

        PrintWriter pWriter = new PrintWriter(outputFile);
        pWriter.printf("%d.0 %d.0 %d.0 %d.0 %f %d.0 %f %f %d\n",
                       nN.getConfiguration().getNumInputs(),
                       nN.getConfiguration().getNumOutputs(),
                       nN.getConfiguration().getNumHiddenLayers(),
                       nN.getConfiguration().getNumNeuronsPerHiddenLayer(),
                       nN.getConfiguration().getHighestSSE(),
                       nN.getConfiguration().getNumMaxEpochs(),
                       nN.getConfiguration().getAlpha(),
                       nN.getConfiguration().getMu(),
                       ANNUtilityGUICompatible.convertActivationFunctionToInt(
                               nN.getConfiguration().getActivationFunction()));
        ArrayList<ArrayList<Double>> weights = nN.getConfiguration().getWeights();
        String weightLayer;
        for (ArrayList<Double> weightList : weights) {
            weightLayer = "";
            for (double weight : weightList) {
                weightLayer += weight + " ";
            }
            pWriter.printf("%s\n", weightLayer);
        }
        pWriter.printf("%s\n", "THETAS");
        List<ArrayList<Double>> thetas = nN.getConfiguration().getThetas().subList(
                1,
                nN.getConfiguration().getThetas().size());
        String thetaLayer;
        for (ArrayList<Double> thetaList : thetas) {
            thetaLayer = "";
            for (double theta : thetaList) {
                thetaLayer += theta + " ";
                System.out.println("writing thetas");
            }
            pWriter.printf("%s\n", thetaLayer);
        }
        pWriter.flush();
        pWriter.close();
    }

    /**
     * Starts running the neural network in classification mode
     *
     * @author ks061, lts010
     */
    private void startClassify() {
        theModel.getFeedbackMessageProp().setValue("");
        if (this.theModel.getTheData() == null) {
            ANNViewUtility.showInputAlert("You must load data first",
                                          "Select the file menu to load data");
            return;
        }
        //make sure the NeuralNet has the data
        theModel.getNeuralNetwork().setData(theModel.getTheData());
        if (neuralNetThread.isAlive()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Active Thread");
            alert.setHeaderText("Cannot start learning process!");
            alert.setContentText(String.format(
                    "Neural Net is running another process",
                    this.theView.getAlphaInput().getText()));
            alert.show();
        }
        else {
            Task classifyTask = new Task<Void>() {
                @Override
                public Void call() throws FileNotFoundException {
                    updateActivationFunction();
                    theModel.getNeuralNetwork().getConfiguration().setProgramMode(
                            hw03.model.neuralnet.ProgramMode.CLASSIFICATION);
                    hw03.model.neuralnet.logger.ANNLogger.setSwitch(
                            ANNLoggerStatus.OFF);
                    theModel.getNeuralNetwork().classify();
                    return null;
                }
            };
            neuralNetThread = new Thread(classifyTask);
            neuralNetThread.setDaemon(true);
            neuralNetThread.start();
            theModel.getFeedbackMessageProp().setValue(
                    "Classifying cycle started");
        }
    }

    private void processConfigRequest() {
        theModel.getFeedbackMessageProp().setValue("");

        int numInputs = ANNViewUtility.extractPositiveIntFromText(
                theView.getANNMenuBar().getNumInputsTextField().getText(),
                ANNModel.DEFAULT_NUM_INPUTS,
                "the number of inputs must be a positive integer.");
        if (numInputs == -1) {
            numInputs = ANNModel.DEFAULT_NUM_INPUTS;
        }
        int numHiddenNodes = ANNViewUtility.extractPositiveIntFromText(
                theView.getANNMenuBar().getNumHiddenTextField().getText(),
                ANNModel.DEFAULT_HIDDEN_NEURONS_IN_HIDDEN_LAYER,
                "the number of hidden nodes must be a positive integer.");
        if (numHiddenNodes
            == -1) {
            numHiddenNodes = ANNModel.DEFAULT_HIDDEN_NEURONS_IN_HIDDEN_LAYER;
        }
        int numOutputs = ANNViewUtility.extractPositiveIntFromText(
                theView.getANNMenuBar().getNumOutTextField().getText(),
                ANNModel.DEFAULT_NUM_OUTPUT,
                "the number of output nodes must be a positive integer.");
        if (numOutputs
            == -1) {
            numOutputs = ANNModel.DEFAULT_NUM_OUTPUT;
        }
        int maxEpochs = ANNViewUtility.extractPositiveIntFromText(
                theView.getANNMenuBar().getMaxEpochTextField().getText(),
                ANNModel.DEFAULT_MAX_NUM_EPOCHS,
                "the maximum number of epochs be a positive integer.");
        if (maxEpochs
            == -1) {
            maxEpochs = ANNModel.DEFAULT_MAX_NUM_EPOCHS;
        }

        // TODO extract this chunk of code to a new method
        double maxSSE = -1;
        boolean formatOK = true;
        String sSEString = theView.getANNMenuBar().getMaxSSETextField().getText();
        if (sSEString
            == null || sSEString.isEmpty()) {
            maxSSE = ANNModel.DEFAULT_MAX_SSE;
        }

        else {
            try {
                maxSSE = Double.parseDouble(sSEString);
            } catch (NumberFormatException e) {
                formatOK = false;
            }
        }
        if (!formatOK || maxSSE
                         <= 0) {
            ANNViewUtility.showInputAlert("SSE must be a positive number",
                                          (sSEString + " cannot be converted to a positive number."));
            return;
        }

        ArrayList<ArrayList<Double>> weights = ANNUtility.getRandomWeights(
                numInputs,
                numOutputs,
                ANNModel.NUM_HIDDEN_LAYERS,
                numHiddenNodes);
        ArrayList<ArrayList<Double>> thetas = ANNUtilityGUICompatible.getListOfThetas(
                numOutputs, ANNModel.NUM_HIDDEN_LAYERS,
                numHiddenNodes);

        double alpha = hw03.model.neuralnet.NeuralNet.DEFAULT_ALPHA;
        double mu = hw03.model.neuralnet.Edge.DEFAULTMU;
        ActivationFunction activationFunction = new SigmoidActivationFunction();

        theView.getSigmoidBtn()
                .setSelected(true);

        theModel.setTheConfig(
                new ANNConfig(numInputs, numOutputs, ANNModel.NUM_HIDDEN_LAYERS,
                              numHiddenNodes,
                              maxSSE, maxEpochs, alpha, mu,
                              weights, thetas,
                              hw03.model.neuralnet.ProgramMode.TRAINING,
                              activationFunction));
        theModel.createNeuralNetwork();

        theView.getCurrentAlpha()
                .setText(String.format("%f",
                                       this.theModel.getNeuralNetwork().getConfiguration().getAlpha()));
        theView.getCurrentMu()
                .setText(String.format("%f",
                                       this.theModel.getNeuralNetwork().getConfiguration().getMu()));
        if (theModel.getTheData()
            != null) {
            theModel.getNeuralNetwork().setData(theModel.getTheData());
        }

        this.theView.makeNetworkGraphic(theModel.getTheConfig());
        initNetorkBindings(theModel.getTheConfig().getWeights());
        System.out.println(
                "MenuBar Config submitButton");

        theModel.getFeedbackMessageProp()
                .setValue(
                        "New Neural Net created with your parameters");
    }

    private void loadTrainingFile() {
        theModel.getFeedbackMessageProp().setValue("");
        double[][] result;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Training File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Training Data", "*.csv"));
        File trainingFile = fileChooser.showOpenDialog(
                this.theView.getTheStage());
        if (trainingFile == null) {
            ANNViewUtility.showInputAlert("Error", "Error openning file");
        }
        else {
            result = ANNUtilityGUICompatible.getData(
                    trainingFile);
            //if empty getData should have already notified the user
            if (result.length > 0) {
                theModel.setTheData(result);
            }
        };
        theModel.getFeedbackMessageProp().setValue(
                "The training data has been loaded.");
    }

    private void LoadTestFileMI() {
        theModel.getFeedbackMessageProp().setValue("");
        double[][] result;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Input/Test Data");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Input/Test Data", "*.csv"));
        File testFile = fileChooser.showOpenDialog(
                this.theView.getTheStage());
        if (testFile == null) {
            ANNViewUtility.showInputAlert("Error", "Error openning file");
        }
        else {
            result = ANNUtilityGUICompatible.getData(testFile);
            //if empty getData should have already notified the user
            if (result.length > 0) {
                theModel.setTheData(result);
            }
        }
        theModel.getTheConfig().setProgramMode(ProgramMode.CLASSIFICATION);
        theModel.getFeedbackMessageProp().setValue(
                "The test data has been loaded.");
    }

    private void loadConfigFile() {
        theModel.getFeedbackMessageProp().setValue("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open config file");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Text Files", "*.txt"));
        File configFile = fileChooser.showOpenDialog(theView.getTheStage());
        if (configFile != null) {
            try {
                theModel.setTheConfig(
                        ANNUtilityGUICompatible.createConfigurationFromFile(
                                configFile));

            } catch (FileNotFoundException ex) {
                // This should not happen.
                Logger.getLogger(ANNMenuBar.class
                        .getName()).log(
                                Level.SEVERE, null, ex);
            }
        }
        theModel.createNeuralNetwork();
        String alpha = String.format("%f",
                                     this.theModel.getNeuralNetwork().getConfiguration().getAlpha());
        String mu = String.format("%f",
                                  this.theModel.getNeuralNetwork().getConfiguration().getMu());
        theView.getCurrentAlpha().setText(alpha);
        theView.getCurrentMu().setText(mu);
        ActivationFunction activationFunction = this.theModel.getNeuralNetwork().getConfiguration().getActivationFunction();
        if (activationFunction instanceof SigmoidActivationFunction) {
            this.theView.getSigmoidBtn().setSelected(true);
        }
        else if (activationFunction instanceof StepActivationFunction) {
            this.theView.getStepFunctionBtn().setSelected(
                    true);
        }
        else {
            this.theView.getHyperbolicTangentBtn().setSelected(
                    true);
        }
        if (theModel.getTheData() != null) {
            theModel.getNeuralNetwork().setData(theModel.getTheData());
        }
        this.theView.makeNetworkGraphic(theModel.getTheConfig());
        initNetorkBindings(theModel.getTheConfig().getWeights());
        theModel.getFeedbackMessageProp().setValue(
                "New Neural Net created with the loaded Config");
    }

    private void startLearnThread() {
        theModel.getFeedbackMessageProp().setValue("");
        if (theModel.getTheData() == null) {
            ANNViewUtility.showInputAlert("You must load data first",
                                          "Select the file menu to load data");
            return;
        }
        if (neuralNetThread.isAlive()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Active Thread");
            alert.setHeaderText("Cannot start learning process!");
            alert.setContentText(String.format(
                    "Neural Net is running another process",
                    this.theView.getAlphaInput().getText()));
            alert.show();
            return;
        }
        else {
            //make sure the NeuralNet has the data
            theModel.getNeuralNetwork().setData(theModel.getTheData());
            updateActivationFunction();
            Task learnTask = new Task<Void>() {
                @Override
                public Void call() throws FileNotFoundException {
                    updateActivationFunction();
                    theModel.getNeuralNetwork().getConfiguration().setProgramMode(
                            hw03.model.neuralnet.ProgramMode.TRAINING);
                    hw03.model.neuralnet.logger.ANNLogger.setSwitch(
                            ANNLoggerStatus.OFF);
                    theModel.getNeuralNetwork().train();
                    return null;
                }
            };
            neuralNetThread = new Thread(learnTask);
            neuralNetThread.setDaemon(true);
            neuralNetThread.start();
            theModel.getFeedbackMessageProp().setValue("Learning cycle started");
        }
    }

    private void saveConfigFile() {
        theModel.getFeedbackMessageProp().setValue("");
        if (theModel.getNeuralNetwork() == null) {
            ANNViewUtility.showInputAlert("No Configuraton Loaded",
                                          "You must load or create a config before you can save");
            return;
        }
        try {
            exportConfig(theModel.getNeuralNetwork());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ANNController.class
                    .getName()).log(Level.SEVERE,
                                    null, ex);
        }
        theModel.getFeedbackMessageProp().setValue("Config file has been saved");
    }

    private void randomizeWeights() {
        theModel.getFeedbackMessageProp().setValue("");
        ArrayList<ArrayList<Double>> weights = ANNUtility.getRandomWeights(
                theModel.getTheConfig().getNumInputs(),
                theModel.getTheConfig().getNumOutputs(),
                1,
                theModel.getTheConfig().getNumNeuronsPerHiddenLayer());
        theModel.getNeuralNetwork().updateWeights(weights);

    }
}
