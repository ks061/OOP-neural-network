/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 22, 2018
* Time: 9:38:43 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: NeuralNetView
* Description:
*
* ****************************************
 */
package hw03.view;

import hw03.ANNConfig;
import hw03.Layer.Layer;
import hw03.NeuralNet;
import hw03.Neuron.Neuron;
import hw03.ProgramMode;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author lts010
 */
public class NeuralNetView {

    private Group root;
    private ArrayList<ArrayList<Line>> lines;
    private ArrayList<Circle> inputLayer;
    private ArrayList<Circle> hiddenLayer;
    private ArrayList<Circle> outputLayer;
    private NeuralNet myNet;
    private ArrayList<ArrayList<Label>> neuronLbls;

    public NeuralNetView() throws FileNotFoundException {
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
        myNet = new NeuralNet(data, config);
        this.root = new Group();

        inputLayer = initializeLayer(myNet.getLayers().get(0));
        hiddenLayer = initializeLayer(myNet.getLayers().get(1));
        outputLayer = initializeLayer(myNet.getLayers().get(2));

        neuronLbls = new ArrayList<>();
        neuronLbls.add(initializeLayerLabels(inputLayer));
        neuronLbls.add(initializeLayerLabels(outputLayer));

        lines = new ArrayList<>();
        lines.add(new ArrayList<>());
        for (Circle inputNeuron : inputLayer) {
            for (Circle hiddenNeuron : hiddenLayer) {
                Line line = updateLinePosition(inputNeuron,
                                               hiddenNeuron);
                lines.get(0).add(line);
            }

        }

        lines.add(new ArrayList<>());
        for (Circle hiddenNeuron : hiddenLayer) {
            for (Circle outputNeuron : outputLayer) {
                Line line = updateLinePosition(hiddenNeuron,
                                               outputNeuron);
                lines.get(1).add(line);
            }

        }

        for (ArrayList<Line> lineList : lines) {
            root.getChildren().addAll(lineList);
        }

        for (ArrayList<Line> lineList : lines) {
            for (Line line : lineList) {
                int layerNum = lines.indexOf(lineList);
                int edgeNum = lineList.indexOf(line);
                updateEdgeColor(line, layerNum, edgeNum);
            }
        }

        this.root.getChildren().addAll(inputLayer);
        this.root.getChildren().addAll(hiddenLayer);
        this.root.getChildren().addAll(outputLayer);

        for (ArrayList<Label> labelList : neuronLbls) {
            this.root.getChildren().addAll(labelList);
        }
    }

    /**
     * Connects two circles with a line
     *
     * @param c1 - the first circle
     * @param c2 - the second circle
     * @return a line connecting the two circles
     */
    public Line updateLinePosition(Circle c1,
                                   Circle c2) {
        Line line = new Line();
        line.setStrokeWidth(3);
        line.setStartX(c1.getCenterX());
        line.setStartY(c1.getCenterY());
        line.setEndX(c2.getCenterX());
        line.setEndY(c2.getCenterY());
        return line;

    }

    /**
     * Creates a list of circles organized to represent the neurons of the given
     * layer
     *
     * @param layer - any Layer
     * @return a list of circles representing the neurons of the given layer
     */
    public ArrayList<Circle> initializeLayer(Layer layer) {
        ArrayList<Circle> layerView = new ArrayList<>();
        double circleRadius = 40;
        double circleSpacing = 65;
        for (Neuron neuron : layer.getNeurons()) {
            Circle newNeuron = new Circle(circleRadius, Color.GREY);
            newNeuron.setCenterY(
                    97 + ((circleRadius + circleSpacing) * neuron.getNeuronNum()));
            newNeuron.setCenterX(
                    194 + ((circleRadius + circleSpacing) * neuron.getLayerNum()));
            layerView.add(newNeuron);
        }
        return layerView;
    }

    public ArrayList<Label> initializeLayerLabels(ArrayList<Circle> circleList) {
        ArrayList<Label> labelList = new ArrayList<>();
        for (Circle circle : circleList) {
            Label label = new Label();
            label.setText("lol");
            label.getLayoutX();
            label.getLayoutY();
            circle.getCenterX();
            circle.getCenterY();
            label.setMinWidth(circle.getRadius());
            label.setMinHeight(circle.getRadius());
            System.out.println(label.getWidth());
            label.setTranslateX(circle.getCenterX());
            label.setTranslateY(circle.getCenterY());
            label.getLayoutX();
            label.getLayoutY();
            labelList.add(label);
        }
        return labelList;
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
        double weight = myNet.getWeight(layerNum, edgeNum);
        if (weight > 0) {
            line.setStroke(Color.GREEN);
        }
        else if (weight == 0) {
            line.setStroke(Color.BLUE);
        }
        else {
            line.setStroke(Color.RED);
        }
        lines.get(layerNum).set(edgeNum, line);

    }

    public Group getRootNode() {
        return this.root;
    }

    public NeuralNet getMyNet() {
        return this.myNet;
    }

    public ArrayList<ArrayList<Line>> getLines() {
        return lines;
    }

}
