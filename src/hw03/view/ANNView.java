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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * The view component of the ANN Visualization
 *
 * @author lts010
 */
public class ANNView {

    private ArrayList<ArrayList<Circle>> nodeCircles;
    private ArrayList<ArrayList<Line>> edgeLines;
    private Group root;
    private NeuralNet theModel;
    private HBox optionsBox;
    private TextField alphaInput;
    private Label currentAlpha;
    private TextField muInput;
    private Label currentMu;
    private RadioButton sigmoid;
    private RadioButton hyperbolicTangent;
    private RadioButton step;

    public ANNView() throws FileNotFoundException {

        ArrayList<ArrayList<Double>> thetas = new ArrayList<>();
        thetas.add(new ArrayList<>());
        thetas.add(new ArrayList<>());
        thetas.get(1).add(0.1);
        thetas.get(1).add(0.1);
        thetas.get(1).add(0.1);
        thetas.add(new ArrayList<>());
        thetas.get(2).add(0.1);
        ArrayList<ArrayList<Double>> weights = new ArrayList<>();
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
        ANNConfig config = new ANNConfig(2, 1, 1, 3, 0.001, 100000, weights,
                                         thetas, ProgramMode.TEST);
        double[][] data = {{1, 1, 1}};
        theModel = new NeuralNet(data, config);

        int numInputs = theModel.getConfiguration().getNumInputs();
        int numHiddenNodes = theModel.getConfiguration().getNumNeuronsPerHiddenLayer();
        int numOutputs = theModel.getConfiguration().getNumOutputs();
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

        for (ArrayList<Line> lineList : this.edgeLines) {
            for (Line line : lineList) {
                int layerNum = this.edgeLines.indexOf(lineList);
                int edgeNum = lineList.indexOf(line);
                updateEdgeColor(line, layerNum, edgeNum);
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
        momentum.setAlignment(Pos.CENTER);

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

        VBox activationFunctions = new VBox(minSpacing);
        Label activationLabel = new Label("Activation Functions");
        sigmoid = new RadioButton("Sigmoid");
        step = new RadioButton("Step");
        hyperbolicTangent = new RadioButton("Hyperbolic Tangent");
        activationFunctions.getChildren().add(activationLabel);
        activationFunctions.getChildren().add(sigmoid);
        activationFunctions.getChildren().add(step);
        activationFunctions.getChildren().add(hyperbolicTangent);
        optionsBox.getChildren().add(activationFunctions);

        optionsBox.setTranslateY(heightOfBox);
        root.getChildren().add(optionsBox);

    }

    public void createEdgeLines(ArrayList<ArrayList<Point>> centers) {

        this.edgeLines.add(new ArrayList<>());
        this.edgeLines.add(new ArrayList<>());
        double x;
        double y;
        Line tempLine;

        for (Point start : centers.get(0)) {
            for (Point end : centers.get(1)) {
                tempLine = new Line(start.getX(), start.getY(), end.getX(),
                                    end.getY());
                tempLine.setStroke(Color.BLACK);
                edgeLines.get(0).add(tempLine);
                this.root.getChildren().add(tempLine);
            }
        }
        for (Point one : centers.get(1)) {
            for (Point two : centers.get(2)) {
                tempLine = new Line(one.getX(), one.getY(), two.getX(),
                                    two.getY());
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
        Circle tempCircle;
        Text tempText;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < centers.get(i).size(); j++) {
                x = centers.get(i).get(j).getX();;
                y = centers.get(i).get(j).getY();
                //TODO display the following text?
                tempText = new Text(nodeTypes[i] + (j + 1));
                tempText.setLayoutX(x - (radius / 3));
                tempText.setLayoutY(y);
                //tempText.setTextAlignment(TextAlignment.CENTER);
                tempCircle = new Circle(x, y, radius);
                tempCircle.setFill(Color.WHITE);
                tempCircle.setStroke(Color.BLACK);
                nodeCircles.get(i).add(tempCircle);
                this.root.getChildren().add(tempCircle);
                this.root.getChildren().add(tempText);
            }
        }
    }

    /**
     * Updates the color of a line based on the edge weight associated with the
     * provided edge and layer number (red for negative weights, green for
     * positive, and blue for zero)
     *
     * @param line - the line we want to change the color of, associated with a
     * specific edge
     * @param layerNum - the number of the layer that the associated edge is
     * contained in
     * @param edgeNum - the number of the edge that the line is associated with
     */
    public void updateEdgeColor(Line line, int layerNum, int edgeNum) {
        double weight = theModel.getWeight(layerNum, edgeNum);
        if (weight > 0) {
            line.setStroke(Color.GREEN);
        }
        else if (weight == 0) {
            line.setStroke(Color.BLUE);
        }
        else {
            line.setStroke(Color.RED);
        }
        this.edgeLines.get(layerNum).set(edgeNum, line);

    }

    public ArrayList<ArrayList<Line>> getEdgeLines() {
        return edgeLines;
    }

    public Group getRootNode() {
        return root;
    }

    public NeuralNet getMyNet() {
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

}
