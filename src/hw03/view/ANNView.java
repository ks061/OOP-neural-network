/* *****************************************




















































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

import hw03.ANNConfig;
import hw03.ActivationFunction.SigmoidActivationFunction;
import hw03.ProgramMode;
import hw03.model.ANNModel;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/* *****************************************
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
    private final Group configGroup;
    private final Group networkGroup;
    private final Group networkPictureGroup;
    private final Stage theStage;
    private final ANNMenuBar aNNMenuBar;
=======
    private ANNModel theModel;
    private ANNModel theModel;

    private ArrayList<ArrayList<NodeCircle>> nodeCircles;
    private ArrayList<ArrayList<EdgeLine>> edgeLines;

    private Group root;
    private HBox optionsBox;

    private TextField alphaInput;
    private Label currentAlpha;
    private TextField muInput;
    private Label currentMu;

    private RadioButton sigmoid;
    private RadioButton hyperbolicTangent;
    private RadioButton step;

    private Label currentSSE;
    private Label currentEpochNum;

    private Button learn;
    private Button classify;
    private Button stepDataInstance;
    private Button stepEpoch;
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

import hw03.ANNConfig;
import hw03.ActivationFunction.SigmoidActivationFunction;
import hw03.ProgramMode;
import hw03.model.ANNModel;
import java.io.FileNotFoundException;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The view component of the ANN Visualization
 *
 * @author lts010
 */
public class ANNView {


    private TextField alphaInput;
    private Label currentAlpha;
    private TextField muInput;
    private Label currentMu;

    private RadioButton sigmoid;
    private RadioButton hyperbolicTangent;
    private RadioButton step;

    private Label currentSSE;
    private Label currentEpochNum;

    private Button learn;
    private Button classify;
    private Button stepDataInstance;
    private Button stepEpoch;
    private RadioButton epochPause;

    private final Pane root;
    private final HBox optionsBox;
    private final VBox programOptions;
    private final int minSpacing;
    //private int widthOfBox;
    //private int heightOfBox;

    private final TextField alphaInput;
    private final Label currentAlpha;
    private final TextField muInput;
    private final Label currentMu;

    private final RadioButton sigmoidBtn;
    private final RadioButton hyperbolicTangentBtn;
    private final RadioButton stepFunctionBtn;

    private final Label currentSSE;
    private final Label currentEpochNum;

    private final Button learnBtn;
    private final Button classifyBtn;
    private final Button stepBtn;
    private final RadioButton runRBtn;
    private final RadioButton epochStepRBtn;
    private final RadioButton inputStepRBtn;
    private final RadioButton terminateRBtn;

=======


    public ANNView(ANNModel theModel) throws FileNotFoundException {
        this.root = new Group();

        this.theModel = theModel;

        // TODO figure out how to phase out use of test theta values here
        ArrayList<ArrayList<Double>> thetas = new ArrayList<>();

        initThetas(thetas);

        // TODO figure out how to phase out use of test weight values here
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();

        initWeights(weights);

        ANNConfig config = new ANNConfig(2, 1, 1, 3, 0.001, 100000, weights,
                                         thetas, ProgramMode.TEST);
        double[][] data = {{1, 1, 1}};
    public final ANNConfig makeConfig() {
        // TODO figure out how to phase out use of test theta values here
        ArrayList<ArrayList<Double>> thetas = new ArrayList<>();

        initThetas(thetas);

        // TODO figure out how to phase out use of test weight values here
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();

        initWeights(weights);

        ANNConfig config = new ANNConfig(2, 1, 1, 3, 0.1, 5000, 0.2, 0.5,
                                         weights,
                                         thetas, ProgramMode.TRAINING,
                                         new SigmoidActivationFunction());
        double[][] data = {{0, 0, 0}, {1, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        return config;
    }

    public ANNView(ANNModel theModel, Stage theStage) throws FileNotFoundException {
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
        this.aNNMenuBar = new ANNMenuBar(theStage, this.configGroup);
        //either the config group or network group will be visible
        this.configGroup.setVisible(false);
        root.getChildren().add(this.aNNMenuBar.getMenuBar());
        root.getChildren().add(this.networkGroup);
        root.getChildren().add(this.configGroup);
        root.setMinSize(600, 600);
        this.theModel = theModel;

//TODO delete the following four lines
        //double[][] data = {{0, 0, 0}, {1, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        //ANNConfig testConfig = makeConfig();
        //theModel.createNeuralNetwork(testConfig);
        //theModel.getNeuralNetwork().setData(data);
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
        currentSSE = new Label(); //tells the user what the current SSE is
        currentSSE.setPrefWidth(75);
        currentSSE.setPrefHeight(25);
        currentSSE.setAlignment(Pos.CENTER);
        currentSSE.setBorder(new Border(
                new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(
                                 4),
                                 BorderWidths.DEFAULT)));
        averageSSEBox.getChildren().add(averageSSE);
        averageSSEBox.getChildren().add(currentSSE);
        optionsBox.getChildren().add(averageSSEBox);

        VBox epochBox = new VBox(this.minSpacing); //set up the number of epochs box
        Label numEpochs = new Label("Number of epochs"); //tells the user what the box is for
        numEpochs.setAlignment(Pos.CENTER);
        currentEpochNum = new Label(); //tells the current number of epochs box
        currentEpochNum.setPrefWidth(75);
        currentEpochNum.setPrefHeight(25);
        currentEpochNum.setAlignment(Pos.CENTER);
        currentEpochNum.setBorder(new Border(
                new BorderStroke(null, BorderStrokeStyle.SOLID, new CornerRadii(
                                 4),
                                 BorderWidths.DEFAULT)));
        epochBox.getChildren().add(numEpochs);
        epochBox.getChildren().add(currentEpochNum);
        optionsBox.getChildren().add(epochBox);
        optionsBox.setVisible(false);

        networkGroup.getChildren().add(optionsBox);

        programOptions.setPadding(new Insets(this.minSpacing));
        learnBtn = new Button("LEARN");
        classifyBtn = new Button("CLASSIFY");
        stepBtn = new Button("Step through input/epoch");

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
        programOptions.getChildren().add(runRBtn);
        programOptions.getChildren().add(inputStepRBtn);
        programOptions.getChildren().add(epochStepRBtn);
        programOptions.getChildren().add(terminateRBtn);
        programOptions.setVisible(false);
        networkGroup.getChildren().add(programOptions);

//TODO delete the following line
        //MakeNetworkGraphic(testConfig);
//TODO remove this, get binding to work
//        for (ArrayList<EdgeLine> lineList : this.edgeLines) {
//            for (EdgeLine edgeLine : lineList) {
//                edgeLine.updateColor();
//            }
//        }
    }

    public ANNMenuBar getANNMenuBar() {
        return aNNMenuBar;
    }

    /**
     *
     * @param centers
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
                                        end.getY(), weight);

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
                                        end.getY(), weight);
                tempLine.setStroke(Color.BLACK);
                edgeLines.get(1).add(tempLine);
                this.networkPictureGroup.getChildren().add(tempLine);
            }
        }
    }

    public void createNodeCircles(ArrayList<ArrayList<Point>> centers,
                                  double radius) {

        //add the three layers (input, hidden, output
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

    public ArrayList<ArrayList<EdgeLine>> getEdgeLines() {
        return edgeLines;
    }

    public Pane getRootNode() {
        return root;
    }

    public Stage getTheStage() {
        return theStage;
    }

    public ANNModel getTheModel() {
        return theModel;
    }

    public Group getNetworkPictureGroup() {
        return networkPictureGroup;
    }

    public TextField getAlphaInput() {
        return alphaInput;
    }

    public Label getCurrentAlpha() {
        return currentAlpha;
    }

    public TextField getMuInput() {
        return muInput;
    }

    public Label getCurrentMu() {
        return currentMu;
    }

    public RadioButton getSigmoidBtn() {
        return sigmoidBtn;
    }

    public RadioButton getHyperbolicTangentBtn() {
        return hyperbolicTangentBtn;
    }

    public RadioButton getStepFunctionBtn() {
        return stepFunctionBtn;
    }

    public ArrayList<ArrayList<NodeCircle>> getNodeCircles() {
        return nodeCircles;
    }

    public Label getCurrentSSE() {
        return currentSSE;
    }

    public Label getCurrentEpochNum() {
        return currentEpochNum;
    }

    public RadioButton getRunRBtn() {
        return runRBtn;
    }

    public RadioButton getEpochStepRBtn() {
        return epochStepRBtn;
    }

    public RadioButton getInputStepRBtn() {
        return inputStepRBtn;
    }

    public RadioButton getTerminateRBtn() {
        return terminateRBtn;
    }

    public Button getClassifyBtn() {
        return classifyBtn;
    }

    public Button getLearnBtn() {
        return learnBtn;
    }

    public Button getStepBtn() {
        return stepBtn;
    }

    public void MakeNetworkGraphic(ANNConfig config) {

        int numInputs = config.getNumInputs();
        int numHiddenNodes = config.getNumNeuronsPerHiddenLayer();
        int numOutputs = config.getNumOutputs();
        this.networkPictureGroup.getChildren().clear();
        //TODO make sure NeuralNet is initialize in the Model and get info from there?
        //TODO Note the controller calls this method so currently the config comes from there

        //int numInputs = theModel.getNeuralNetwork().getConfiguration().getNumInputs();
        //int numHiddenNodes = theModel.getNeuralNetwork().getConfiguration().getNumNeuronsPerHiddenLayer();
        //int numOutputs = theModel.getNeuralNetwork().getConfiguration().getNumOutputs();
        int spacingBetweenLayers = 100;
        int radius = 50;
        double widthOfBox;
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
        makeNetorkGroupVisable();

    }

    public void makeNetorkGroupVisable() {
        this.configGroup.setVisible(false);
        this.networkGroup.setVisible(true);
    }

    public void makeConfigGroupVisable() {
        this.networkGroup.setVisible(false);
        this.configGroup.setVisible(true);
    }
}
