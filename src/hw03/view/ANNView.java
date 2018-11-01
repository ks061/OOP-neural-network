/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 24, 2018
* Time: 5:11:22 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: ANNView
* Description:
*
* ****************************************
 */
package hw03.view;

import hw03.model.ANNModel;
import hw03.model.neuralnet.ANNConfig;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The view component of the ANN Visualization
 *
 * @author lts010
 */
public class ANNView {

    /**
     * The model of this neural network MVC application.
     */
    private final ANNModel theModel;
    /**
     * Box for configuration specification input
     */
    private final Group configGroup;
    /**
     * Bottom of the main screen, including neural network configuration and
     * buttons
     */
    private final Group networkGroup;
    /**
     * Display of the neural network
     */
    private final Group networkPictureGroup;
    /**
     * Stage for the GUI
     */
    private final Stage theStage;
    /**
     * Menu bar for the GUI
     */
    private final ANNMenuBar aNNMenuBar;

    /**
     * Circles representing neurons in the GUI
     */
    private ArrayList<ArrayList<NodeCircle>> nodeCircles;
    /**
     * Edge lines representing edges in the GUI
     */
    private ArrayList<ArrayList<EdgeLine>> edgeLines;
    /**
     * Root of the MVC application
     */
    private final Pane root;
    /**
     * Box for neural network specifications
     */
    private final HBox optionsBox;
    /**
     * Box for controlling the program
     */
    private final VBox programOptions;
    /**
     * Minimum spacing between GUI components
     */
    private final int minSpacing;

    /**
     * Text field for user to specify an alpha value
     */
    private final TextField alphaInput;
    /**
     * Current alpha value used in training by the neural network
     */
    private final Label currentAlpha;
    /**
     * Text field to specify a mu value
     */
    private final TextField muInput;
    /**
     * Current mu value used in training by the neural network
     */
    private final Label currentMu;

    /**
     * Radio button to specify that the neural network use the Sigmoid
     * activation function during the learning process
     */
    private final RadioButton sigmoidBtn;
    /**
     * Radio button to specify that the neural network use the hyperbolic
     * tangent activation function during the learning process
     */
    private final RadioButton hyperbolicTangentBtn;
    /**
     * Radio button to specify that the neural network use the step activation
     * function during the learning process
     */
    private final RadioButton stepFunctionBtn;

    /**
     * Current sum of squared errors for the neural network
     */
    private final Text currentSSE;
    /**
     * Current number of epochs the neural network has gone through
     */
    private final Text currentEpochNum;
    /**
     * Informational notice regarding the status of the application at the top
     * of the GUI
     */
    private final Text feedbackMessage;

    /**
     * Button to have the neural network learn based on inputted data
     */
    private final Button learnBtn;
    /**
     * Button to have the neural network classify based on inputted data and a
     * configured neural network
     */
    private final Button classifyBtn;
    /**
     * Button that to steps through particular iteration(s) of the neural
     * network learn/classify functions
     */
    private final Button stepBtn;
    /**
     * Button that randomizes the weights of the neural network
     */
    private final Button randomizeBtn;
    /**
     * Button that runs the neural network
     */
    private final RadioButton runRBtn;
    /**
     * Button that steps through an entire epoch of neural network firing (and
     * learning if in training mode)
     */
    private final RadioButton epochStepRBtn;
    /**
     * Button that steps through one input set of neural network firing (and
     * learning if in training mode)
     */
    private final RadioButton inputStepRBtn;
    /**
     * Button that stops the firing (and learning if in training mode) processes
     * of the neural network
     */
    private final RadioButton terminateRBtn;

    /**
     * Constructor that sets references to the model and stage for the neural
     * network MVC application
     *
     * @param theModel model of the neural network MVC application
     * @param theStage stage of the neural network MVC application
     *
     * @author ks061, lts010
     */
    public ANNView(ANNModel theModel, Stage theStage) {
        this.theStage = theStage;
        this.root = new Pane();
        this.configGroup = new Group();
        this.minSpacing = 15;
        this.configGroup.setLayoutX(10);
        this.configGroup.setLayoutY(25);
        this.networkGroup = new Group();
        this.networkGroup.setLayoutX(10);
        this.networkGroup.setLayoutY(25);
        this.networkPictureGroup = new Group();
        this.networkGroup.getChildren().add(networkPictureGroup);
        this.optionsBox = new HBox(this.minSpacing);
        this.optionsBox.setAlignment(Pos.CENTER);
        this.programOptions = new VBox(this.minSpacing);
        this.aNNMenuBar = new ANNMenuBar(theStage, configGroup);
        //either the config group or network group will be visible
        this.configGroup.setVisible(false);

        root.getChildren().add(this.aNNMenuBar.getMenuBar());
        HBox feedbackBox = new HBox();
        feedbackBox.setLayoutX(150);
        feedbackBox.setLayoutY(5);
        feedbackMessage = new Text("");
        feedbackMessage.setLayoutX(170);
        feedbackMessage.setLayoutY(20);
        feedbackMessage.setFont(Font.font("Verdana", 12));
        feedbackBox.getChildren().add(feedbackMessage);
        root.getChildren().add(feedbackBox);
        root.getChildren().add(this.networkGroup);
        root.getChildren().add(this.configGroup);
        root.setMinSize(725, 600);
        this.theModel = theModel;

        VBox learningRateBox = new VBox(this.minSpacing); //set up the learning rate box
        learningRateBox.setAlignment(Pos.CENTER);

        Label learningRate = new Label("Learning Rate"); //tells the user what the box is for
        learningRate.setAlignment(Pos.CENTER);

        alphaInput = new TextField(); //allows the user to change the learning rate
        alphaInput.setAlignment(Pos.CENTER);
        alphaInput.setPrefColumnCount(4);

        currentAlpha = new Label(); //tells the user what the current learning rate is
        currentAlpha.setPrefWidth(75);
        currentAlpha.setPrefHeight(25);
        currentAlpha.setAlignment(Pos.CENTER);
        currentAlpha.setBorder(new Border(
                new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(
                                 4),
                                 BorderWidths.DEFAULT)));

        learningRateBox.getChildren().add(learningRate);
        learningRateBox.getChildren().add(alphaInput);
        learningRateBox.getChildren().add(currentAlpha);
        optionsBox.getChildren().add(learningRateBox);

        VBox momentumBox = new VBox(this.minSpacing); //set up the momentum box
        momentumBox.setAlignment(Pos.CENTER);

        Label momentum = new Label("Momentum"); //tells the user what the box is for

        muInput = new TextField(); //allows the user to change the momentum
        muInput.setAlignment(Pos.CENTER);
        muInput.setPrefColumnCount(4);

        currentMu = new Label(); //tells the user what the current momentum is
        currentMu.setPrefWidth(75);
        currentMu.setPrefHeight(25);
        currentMu.setAlignment(Pos.CENTER);
        currentMu.setBorder(new Border(
                new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(
                                 4),
                                 BorderWidths.DEFAULT)));

        momentumBox.getChildren().add(momentum);
        momentumBox.getChildren().add(muInput);
        momentumBox.getChildren().add(currentMu);
        optionsBox.getChildren().add(momentumBox);

        VBox activationFunctions = new VBox(this.minSpacing); //lets the user decide which activation function to use
        ToggleGroup activationGroup = new ToggleGroup();
        Label activationLabel = new Label("Activation Functions"); //describes this part of the GUI to the user
        sigmoidBtn = new RadioButton("Sigmoid"); //lets the user choose the sigmoidBtn function
        sigmoidBtn.setToggleGroup(activationGroup);
        stepFunctionBtn = new RadioButton("Step"); //lets the user choose the stepFunctionBtn function
        stepFunctionBtn.setToggleGroup(activationGroup);
        hyperbolicTangentBtn = new RadioButton("Hyperbolic Tangent"); //lets the user choose the hyperbolic tangent function
        hyperbolicTangentBtn.setToggleGroup(activationGroup);
        activationFunctions.getChildren().add(activationLabel);
        activationFunctions.getChildren().add(sigmoidBtn);
        activationFunctions.getChildren().add(stepFunctionBtn);
        activationFunctions.getChildren().add(hyperbolicTangentBtn);
        optionsBox.getChildren().add(activationFunctions);

        VBox averageSSEBox = new VBox(this.minSpacing); //set up the averageSSE box
        Label averageSSE = new Label("Average SSE"); //tells the user what this box is for
        averageSSE.setAlignment(Pos.CENTER);
        currentSSE = new Text();
        averageSSEBox.getChildren().add(averageSSE);
        averageSSEBox.getChildren().add(currentSSE);
        optionsBox.getChildren().add(averageSSEBox);

        VBox epochBox = new VBox(this.minSpacing); //set up the number of epochs box
        Label numEpochs = new Label("Number of epochs"); //tells the user what the box is for
        numEpochs.setAlignment(Pos.CENTER);
        currentEpochNum = new Text();
        epochBox.getChildren().add(numEpochs);
        epochBox.getChildren().add(currentEpochNum);
        optionsBox.getChildren().add(epochBox);
        optionsBox.setVisible(false);

        networkGroup.getChildren().add(optionsBox);

        programOptions.setPadding(new Insets(this.minSpacing));
        learnBtn = new Button("LEARN");
        classifyBtn = new Button("CLASSIFY");
        stepBtn = new Button("Step through input/epoch");
        randomizeBtn = new Button("Randomize Weights");

        ToggleGroup runModeGroup = new ToggleGroup();
        runRBtn = new RadioButton("Resume");
        runRBtn.setToggleGroup(runModeGroup);
        inputStepRBtn = new RadioButton("Step through each input");
        inputStepRBtn.setToggleGroup(runModeGroup);
        epochStepRBtn = new RadioButton("Step through each epoch");
        epochStepRBtn.setToggleGroup(runModeGroup);
        terminateRBtn = new RadioButton("Teminate Execution");
        terminateRBtn.setToggleGroup(runModeGroup);

        programOptions.getChildren().add(learnBtn);
        programOptions.getChildren().add(classifyBtn);

        programOptions.getChildren().add(stepBtn);
        programOptions.getChildren().add(randomizeBtn);
        programOptions.getChildren().add(runRBtn);
        programOptions.getChildren().add(inputStepRBtn);
        programOptions.getChildren().add(epochStepRBtn);
        programOptions.getChildren().add(terminateRBtn);
        programOptions.setVisible(false);
        networkGroup.getChildren().add(programOptions);
    }

    /**
     * Gets the neural network menu bar
     *
     * @return neural network menu bar
     *
     * @author ks061, lts010
     */
    public ANNMenuBar getANNMenuBar() {
        return aNNMenuBar;
    }

    /**
     * Creates the edge lines within the GUI
     *
     * @param centers centers of the edge lines within the GUI
     *
     * @author lts010, ks061
     */
    public void createEdgeLines(ArrayList<ArrayList<Point>> centers) {

        this.edgeLines.add(new ArrayList<>());
        this.edgeLines.add(new ArrayList<>());
        double x;
        double y;
        EdgeLine tempLine;
        Point start;
        Point end;
        double weight;

        for (int i = 0; i < centers.get(0).size(); i++) {
            for (int j = 0; j < centers.get(1).size(); j++) {
                start = centers.get(0).get(i);
                end = centers.get(1).get(j);
                weight = theModel.getNeuralNetwork().getWeight(0,
                                                               (i * centers.get(
                                                                        1).size()) + j);
                tempLine = new EdgeLine(start.getX(), start.getY(), end.getX(),
                                        end.getY());

                tempLine.setStroke(Color.BLACK);
                edgeLines.get(0).add(tempLine);
                this.networkPictureGroup.getChildren().add(tempLine);
            }
        }
        for (int i = 0; i < centers.get(1).size(); i++) {
            for (int j = 0; j < centers.get(2).size(); j++) {
                start = centers.get(1).get(i);
                end = centers.get(2).get(j);
                weight = theModel.getNeuralNetwork().getWeight(1,
                                                               (i * centers.get(
                                                                        2).size()) + j);
                tempLine = new EdgeLine(start.getX(), start.getY(), end.getX(),
                                        end.getY());
                tempLine.setStroke(Color.BLACK);
                edgeLines.get(1).add(tempLine);
                this.networkPictureGroup.getChildren().add(tempLine);
            }
        }
    }

    /**
     * Creates circle objects representing neurons within the GUI
     *
     * @param centers centers of the circles representing neurons within the GUI
     * @param radius radius of the circles representing neurons within the GUI
     *
     * @author ks061, lts010
     */
    public void createNodeCircles(ArrayList<ArrayList<Point>> centers,
                                  double radius) {

        // Adds the three layers (input, hidden, output)
        this.nodeCircles.add(new ArrayList<>());
        this.nodeCircles.add(new ArrayList<>());
        this.nodeCircles.add(new ArrayList<>());
        double x;
        double y;
        String[] nodeTypes = {"Input ", "Hidden ", "Output "};
        NodeCircle tempCircle;
        Text tempText;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < centers.get(i).size(); j++) {
                x = centers.get(i).get(j).getX();
                y = centers.get(i).get(j).getY();
                //TODO display the following text?
                tempText = new Text(nodeTypes[i] + (j + 1));
                //tempText.setTextAlignment(TextAlignment.CENTER);
                tempCircle = new NodeCircle(x, y, radius);
                tempCircle.setText(tempText);
                nodeCircles.get(i).add(tempCircle);
                this.networkPictureGroup.getChildren().add(tempCircle);
                tempCircle.getText().getLayoutX();
                this.networkPictureGroup.getChildren().add(tempCircle.getText());
            }
        }
    }

    /**
     * Gets the edge lines
     *
     * @return edges lines
     *
     * @author ks061, lts010
     */
    public ArrayList<ArrayList<EdgeLine>> getEdgeLines() {
        return edgeLines;
    }

    /**
     * Gets the root node of the GUI
     *
     * @return root node of the GUI
     *
     * @author ks061, lts010
     */
    public Pane getRootNode() {
        return root;
    }

    /**
     * Gets the stage of the GUI
     *
     * @return stage of the GUI
     *
     * @author ks061, lts010
     */
    public Stage getTheStage() {
        return theStage;
    }

    /**
     * Gets the model of the neural network MVC application
     *
     * @return model of the neural network MVC application
     *
     * @author ks061, lts010
     */
    public ANNModel getTheModel() {
        return theModel;
    }

    /**
     * Gets the group of components in the neural network graphical display
     *
     * @return group of components in the neural network graphical display
     *
     * @author ks061, lts010
     */
    public Group getNetworkPictureGroup() {
        return networkPictureGroup;
    }

    /**
     * Gets the text field for the user to specify the alpha value the neural
     * network should use in its learning
     *
     * @return text field for the user to specify the alpha value the neural
     * network should use in its learning
     *
     * @author ks061, lts010
     */
    public TextField getAlphaInput() {
        return alphaInput;
    }

    /**
     * Gets a label display of the alpha value currently used by the neural
     * network
     *
     * @return label display of the alpha value currently used by the neural
     * network
     *
     * @author ks061, lts010
     */
    public Label getCurrentAlpha() {
        return currentAlpha;
    }

    /**
     * Gets a text field for the user to specify the mu value the neural network
     * should use in its learning
     *
     * @return text field for the user to specify the mu value the neural
     * network should use in its learning
     *
     * @author ks061, lts010
     */
    public TextField getMuInput() {
        return muInput;
    }

    /**
     * Gets a label display of the mu value currently used by the neural network
     *
     * @return label display of the mu value currently used by the neural
     * network
     *
     * @author ks061, lts010
     */
    public Label getCurrentMu() {
        return currentMu;
    }

    /**
     * Gets a radio button for the user to select the neural network to use the
     * Sigmoid activation function whilst calculating its output values
     *
     * @return radio button for the user to select the neural network to use the
     * Sigmoid activation function whilst calculating its output values
     *
     * @author ks061, lts010
     */
    public RadioButton getSigmoidBtn() {
        return sigmoidBtn;
    }

    /**
     * Gets the radio button for the user to select the neural network to use
     * the hyperbolic tangent activation function whilst calculating its output
     * values
     *
     * @return radio button for the user to select the neural network to use the
     * hyperbolic tangent activation function whilst calculating its output
     * values
     *
     * @author ks061, lts010
     */
    public RadioButton getHyperbolicTangentBtn() {
        return hyperbolicTangentBtn;
    }

    /**
     * Gets the radio button for the user to select the neural network to use
     * the step activation function whilst calculating its output values
     *
     * @return radio button for the user to select the neural network to use the
     * step activation function whilst calculating its output values
     *
     * @author ks061, lts010
     */
    public RadioButton getStepFunctionBtn() {
        return stepFunctionBtn;
    }

    /**
     * Gets the circles representing neurons in the GUI
     *
     * @return circles representing neurons in the GUI
     *
     * @author ks061, lts010
     */
    public ArrayList<ArrayList<NodeCircle>> getNodeCircles() {
        return nodeCircles;
    }

    /**
     * Gets the current sum of squared errors of the neural network
     *
     * @return sum of squared errors of the neural network
     *
     * @author ks061, lts010
     */
    public Text getCurrentSSE() {
        return currentSSE;
    }

    /**
     * Gets the current number of epochs that the neural network has run through
     *
     * @return current number of epochs that the neural network has run through
     *
     * @author ks061, lts010
     */
    public Text getCurrentEpochNum() {
        return currentEpochNum;
    }

    /**
     * Gets the informational notice regarding the status of the application at
     * the top of the GUI
     *
     * @return informational notice regarding the status of the application at
     * the top of the GUI
     *
     * @author ks061, lts010
     */
    public Text getFeedbackMessage() {
        return feedbackMessage;
    }

    /**
     * Gets the radio button to allow the user to select running the program
     *
     * @return radio button to allow the user to select running the program
     *
     * @author ks061, lts010
     */
    public RadioButton getRunRBtn() {
        return runRBtn;
    }

    /**
     * Gets the radio button to allow the user to select having the neural
     * network run through a complete set of data once
     *
     * @return radio button to allow the user to select having the neural
     * network run through a complete set of data once
     *
     * @author ks061, lts010
     */
    public RadioButton getEpochStepRBtn() {
        return epochStepRBtn;
    }

    /**
     * Gets the radio button to allow the user to select having the neural
     * network run through one set of data
     *
     * @return radio button to allow the user to select having the neural
     * network run through one set of data
     *
     * @author ks061, lts010
     */
    public RadioButton getInputStepRBtn() {
        return inputStepRBtn;
    }

    /**
     * Gets a radio button that allows the user to terminate the program
     *
     * @return radio button that allows the user to terminate the program
     *
     * @author ks061, lts010
     */
    public RadioButton getTerminateRBtn() {
        return terminateRBtn;
    }

    /**
     * Gets a button that, when clicked, runs the neural network in classify
     * mode
     *
     * @return button that, when clicked, runs the neural network in classify
     * mode
     *
     * @author ks061, lts010
     */
    public Button getClassifyBtn() {
        return classifyBtn;
    }

    /**
     * Gets a button that, when clicked, runs the neural network in training
     * mode
     *
     * @return button that, when clicked, runs the neural network in training
     * mode
     *
     * @author ks061, lts010
     */
    public Button getLearnBtn() {
        return learnBtn;
    }

    /**
     * Gets a button that allows the user to select stepping through the
     * firing/learn process in the neural network
     *
     * @return button that allows the user to select stepping through the
     * firing/learn process in the neural network
     *
     * @author ks061, lts010
     */
    public Button getStepBtn() {
        return stepBtn;
    }

    /**
     * Gets a button that, when clicked, randomizes the weights of the neural
     * network
     *
     * @return button that, when clicked, randomizes the weights of the neural
     * network
     *
     * @author ks061, lts010
     */
    public Button getRandomizeBtn() {
        return randomizeBtn;
    }

    /**
     * Makes the neural network GUI representation
     *
     * @param config configuration for the neural network
     *
     * @author ks061, lts010
     */
    public void makeNetworkGraphic(ANNConfig config) {

        int numInputs = config.getNumInputs();
        int numHiddenNodes = config.getNumNeuronsPerHiddenLayer();
        int numOutputs = config.getNumOutputs();
        this.networkPictureGroup.getChildren().clear();

        int spacingBetweenLayers = 100;
        int radius = 50;
        double widthOfBox;
        double heightOfBox;

        this.edgeLines = new ArrayList<>();
        this.nodeCircles = new ArrayList<>();

        int maxNodes = numInputs;
        if (maxNodes < numHiddenNodes) {
            maxNodes = numHiddenNodes;
        }
        if (maxNodes < numOutputs) {
            maxNodes = numOutputs;
        }

        widthOfBox = (6 * radius + 2 * spacingBetweenLayers) + 2 * this.minSpacing;
        heightOfBox = ((2 * maxNodes * radius) + (1 + maxNodes) * this.minSpacing);
        theStage.setMinHeight(heightOfBox + 200);
        this.optionsBox.setTranslateY(heightOfBox);
        this.programOptions.setTranslateX(widthOfBox);

        ArrayList<ArrayList<Point>> centers = new ArrayList<>();

        //add the three layers (input, hidden, output
        centers.add(new ArrayList<>());
        centers.add(new ArrayList<>());
        centers.add(new ArrayList<>());
        double verticalSpacing = (heightOfBox - (2 * radius * numInputs)) / (numInputs + 1);

        //store the centers for the first layer
        double x = this.minSpacing + radius;
        double y = verticalSpacing + radius;
        centers.get(0).add(new Point(x, y));
        for (int i = 1; i < numInputs; i++) {
            y += verticalSpacing + 2 * radius;
            centers.get(0).add(new Point(x, y));
        }

        //store the centers for the second layer;
        verticalSpacing = (heightOfBox - (2 * radius * numHiddenNodes)) / (numHiddenNodes + 1);
        x += spacingBetweenLayers + 2 * radius;
        y = verticalSpacing + radius;
        centers.get(1).add(new Point(x, y));
        for (int i = 1; i < numHiddenNodes; i++) {
            y += verticalSpacing + 2 * radius;
            centers.get(1).add(new Point(x, y));
        }

        //store the centers for the second layer;
        verticalSpacing = (heightOfBox - (2 * radius * numOutputs)) / (numOutputs + 1);
        x += spacingBetweenLayers + 2 * radius;
        y = verticalSpacing + radius;
        centers.get(2).add(new Point(x, y));
        for (int i = 1; i < numOutputs; i++) {
            y += verticalSpacing + 2 * radius;
            centers.get(2).add(new Point(x, y));
        }

        createEdgeLines(centers);
        createNodeCircles(centers, radius);
        this.programOptions.setVisible(true);
        this.optionsBox.setVisible(true);
        makeNetworkGroupVisible();
    }

    /**
     * Makes the main program view with the neural network visible; hides the
     * configuration specification view
     *
     * @author ks061, lts010
     */
    public void makeNetworkGroupVisible() {
        this.configGroup.setVisible(false);
        this.networkGroup.setVisible(true);
    }

    /**
     * Makes the configuration specification view visible; hides the main
     * program view with the neural network
     *
     * @author ks061, lts010
     */
    public void makeConfigGroupVisible() {
        this.networkGroup.setVisible(false);
        this.configGroup.setVisible(true);
    }
}
