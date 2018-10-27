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
import hw03.Edge;
import hw03.NeuralNet;
import hw03.ProgramMode;
import hw03.model.ANNModel;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The view component of the ANN Visualization
 *
 * @author lts010
 */
public class ANNView {

    /**
     * The model of this neural network MVC application.
     */
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
    private RadioButton epochPause;

    private void initThetas(ArrayList<ArrayList<Double>> thetas) {
        thetas.add(new ArrayList<>());
        thetas.add(new ArrayList<>());
        thetas.get(1).add(0.1);
        thetas.get(1).add(0.1);
        thetas.get(1).add(0.1);
        thetas.add(new ArrayList<>());
        thetas.get(2).add(0.1);
    }

    private void initWeights(ArrayList<ArrayList<Double>> weights) {
        weights.add(new ArrayList<>());
        weights.get(0).add(-0.3);
        weights.get(0).add(0.2);
        weights.get(0).add(0.1);
        weights.get(0).add(-0.2);
        weights.get(0).add(-0.1);
        weights.get(0).add(-0.5);
        weights.add(new ArrayList<>());
        weights.get(1).add(-0.1);
        weights.get(1).add(-0.5);
        weights.get(1).add(0.0);
    }

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

        theModel.setNeuralNetwork(new NeuralNet(data, config));

        int numInputs = theModel.getNeuralNetwork().getConfiguration().getNumInputs();
        int numHiddenNodes = theModel.getNeuralNetwork().getConfiguration().getNumNeuronsPerHiddenLayer();
        int numOutputs = theModel.getNeuralNetwork().getConfiguration().getNumOutputs();
        int minSpacing = 15;
        int spacingBetweenLayers = 100;
        int radius = 50;
        double widthOfBox;
        double heightOfBox;
        Point upperLeftOfBox = new Point(100.0, 100.0);

        this.edgeLines = new ArrayList<>();
        this.nodeCircles = new ArrayList<>();

        int maxNodes = numInputs;
        if (maxNodes < numHiddenNodes) {
            maxNodes = numHiddenNodes;
        }
        if (maxNodes < numOutputs) {
            maxNodes = numOutputs;
        }

        widthOfBox = (6 * radius + 2 * spacingBetweenLayers) + 2 * minSpacing;
        heightOfBox = ((2 * maxNodes * radius) + (1 + maxNodes) * minSpacing);

        this.root = new Group();

        ArrayList<ArrayList<Point>> centers = new ArrayList<>();

        //add the three layers (input, hidden, output
        centers.add(new ArrayList<>());
        centers.add(new ArrayList<>());
        centers.add(new ArrayList<>());
        double verticalSpacing = (heightOfBox - (2 * radius * numInputs)) / (numInputs + 1);

        //store the centers for the first layer
        double x = minSpacing + radius;
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

        for (ArrayList<EdgeLine> lineList : this.edgeLines) {
            for (EdgeLine edgeLine : lineList) {
                edgeLine.updateColor();
            }
        }

        optionsBox = new HBox(minSpacing);
        optionsBox.setAlignment(Pos.CENTER);

        VBox learningRateBox = new VBox(minSpacing); //set up the learning rate box
        learningRateBox.setAlignment(Pos.CENTER);

        Label learningRate = new Label("Learning Rate"); //tells the user what the box is for
        learningRate.setAlignment(Pos.CENTER);

        alphaInput = new TextField(); //allows the user to change the learning rate
        alphaInput.setAlignment(Pos.CENTER);
        alphaInput.setPrefColumnCount(4);

        currentAlpha = new Label(String.format("%.1f", NeuralNet.DEFAULT_ALPHA)); //tells the user what the current learning rate is
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

        VBox momentumBox = new VBox(minSpacing); //set up the momentum box
        momentumBox.setAlignment(Pos.CENTER);

        Label momentum = new Label("Momentum"); //tells the user what the box is for

        muInput = new TextField(); //allows the user to change the momentum
        muInput.setAlignment(Pos.CENTER);
        muInput.setPrefColumnCount(4);

        currentMu = new Label(String.format("%.1f", Edge.DEFAULTMU)); //tells the user what the current momentum is
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

        VBox activationFunctions = new VBox(minSpacing); //lets the user decide which activation function to use
        ToggleGroup activationGroup = new ToggleGroup();
        Label activationLabel = new Label("Activation Functions"); //describes this part of the GUI to the user
        sigmoid = new RadioButton("Sigmoid"); //lets the user choose the sigmoid function
        sigmoid.setToggleGroup(activationGroup);
        sigmoid.setSelected(true); //sigmoid is the default so we want the button to be selected on start up
        step = new RadioButton("Step"); //lets the user choose the step function
        step.setToggleGroup(activationGroup);
        hyperbolicTangent = new RadioButton("Hyperbolic Tangent"); //lets the user choose the hyperbolic tangent function
        hyperbolicTangent.setToggleGroup(activationGroup);
        activationFunctions.getChildren().add(activationLabel);
        activationFunctions.getChildren().add(sigmoid);
        activationFunctions.getChildren().add(step);
        activationFunctions.getChildren().add(hyperbolicTangent);
        optionsBox.getChildren().add(activationFunctions);

        VBox averageSSEBox = new VBox(minSpacing); //set up the averageSSE box
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

        VBox epochBox = new VBox(minSpacing); //set up the number of epochs box
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

        optionsBox.setTranslateY(heightOfBox);
        root.getChildren().add(optionsBox);

        VBox programOptions = new VBox(minSpacing);
        learn = new Button("LEARN");
        classify = new Button("CLASSIFY");
        stepDataInstance = new Button("Step through one data instance");
        stepEpoch = new Button("Step through one epoch");
        epochPause = new RadioButton("Pause After Each Epoch");
        programOptions.getChildren().add(learn);
        programOptions.getChildren().add(classify);
        programOptions.setTranslateX(widthOfBox);
        programOptions.getChildren().add(stepDataInstance);
        programOptions.getChildren().add(stepEpoch);
        programOptions.getChildren().add(epochPause);
        root.getChildren().add(programOptions);
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
                this.root.getChildren().add(tempLine);
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
                this.root.getChildren().add(tempLine);
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
                this.root.getChildren().add(tempCircle);
                tempCircle.getText().getLayoutX();
                this.root.getChildren().add(tempCircle.getText());
            }
        }
    }

    public ArrayList<ArrayList<EdgeLine>> getEdgeLines() {
        return edgeLines;
    }

    public Group getRootNode() {
        return root;
    }

    public ANNModel getTheModel() {
        return theModel;
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

    public RadioButton getSigmoid() {
        return sigmoid;
    }

    public RadioButton getHyperbolicTangent() {
        return hyperbolicTangent;
    }

    public RadioButton getStep() {
        return step;
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

    public RadioButton getEpochPause() {
        return epochPause;
    }

    public Button getStepEpoch() {
        return stepEpoch;
    }

    public Button getClassify() {
        return classify;
    }

    public Button getLearn() {
        return learn;
    }

    public Button getStepDataInstance() {
        return stepDataInstance;
    }

}
