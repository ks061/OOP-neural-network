/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Logan Stiles and Kartikeya Sharma
 * Date: Oct 10, 2018
 * Time: 5:00:00 PM
 *
 * Project: 205-FA18Class
 * Package: hw01
 * File: Neuron
 * Description: Represents the neuron in a neural net
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * Class for individual neuron elements
 *
 * @author cld028, ks061, lts010
 *
 */
public class Neuron {

    private ArrayList<Edge> inEdges;
    private ArrayList<Edge> outEdges;
    private String id = "Neuron";
    private int numberId;
    //   private int layerNum; //the number for the layer that is neuron is in.
    private WeightAssignment weightAssign;
    private ActivationFunction activationFunction;
    // private double netInput;
    private double theta;
    private double netValue;
    private boolean inputNeuron = false;

    private double expectedValue;

    /**
     * A default theta (threshold) value
     */
    public final static double DEFAULTTHETA = 0.1;

    /**
     * Explicit constructor that creates a neuron with a particular identifier
     *
     * @param id identifier for the neuron
     *
     * @author ks061
     */
    Neuron(String id, int num) {
        this.id = id;
        this.numberId = num;
        this.theta = DEFAULTTHETA;
        this.inEdges = new ArrayList<>();
        this.outEdges = new ArrayList<>();
        this.activationFunction = new StepActivationFunction();
    }

    /**
     * Explicit constructor that creates a neuron with a particular numerical
     * identifier
     *
     * @param idNum numerical identifier for the neuron
     *
     * @author ks061
     */
    Neuron(int idNum) {
        this(Integer.toString(idNum), idNum);
    }

    /**
     * Fire the neuron (essentially run net input in neuron using an activation
     * function)
     */
    public void fire() {
        double net = 0.0;
        for (Edge inEdge : this.inEdges) {
            net += inEdge.getValue();
        }
        net -= theta;
        this.netValue = activationFunction.calcOutput(net);

    }

    /**
     * Sets the net value for the neuron (used assign input values to neurons in
     * the input layer)
     *
     * @param netValue value to set the neuron to
     *
     * @author ks061
     */
    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    /**
     * Gets the current net value of the neuron
     *
     * @return net value of the neuron
     *
     * @author ks061
     */
    public double getValue() {
        return this.netValue;
    }

    /**
     * Gets the list of edges serving as input channels to the neuron
     *
     * @return list of edges serving as input channels to the neuron
     *
     * @author ks061
     */
    public ArrayList<Edge> getInEdges() {
        return inEdges;
    }

    /**
     * Gets the list of edges serving as output channels to the neuron
     *
     * @return list of edges serving as output channels to the neuron
     *
     * @author ks061
     */
    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }

    /**
     *
     * @param weightAssign
     */
    public void setWeightAssign(WeightAssignment weightAssign) {
        this.weightAssign = weightAssign;
    }

    /**
     *
     * @param activationFunction
     */
    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    /**
     *
     *
     * @return
     *
     * @author lts010, ks061
     */
    public void learn() {
        // check to see if it is an output neuron
        double errorGradient = 0;
        if (outEdges.isEmpty()) {
            errorGradient = this.netValue * (1 - this.netValue) * (this.expectedValue - netValue);
            this.theta = this.theta + NeuralNet.alpha * -1 * errorGradient;
            for (Edge edge : inEdges) {
                edge.learn(errorGradient, this.netValue);
            }
        }
        else {
            double sum = 0;
            for (Edge edge : outEdges) {
                sum += edge.getWeightTimesDelta();
            }
            errorGradient = this.netValue + (1 - this.netValue) * sum;
            this.theta = this.theta + NeuralNet.alpha * -1 * errorGradient;

            // if true, must be a hidden layer
            if (!inEdges.isEmpty()) {
                for (Edge edge : inEdges) {
                    edge.learn(errorGradient, this.netValue);
                }
            }
        }

    }

    /**
     * Sets the expected value for the output neuron
     *
     * @param expectedValue expected value for the output neuron
     */
    public void setExpectedValue(double expectedValue) {
        this.expectedValue = expectedValue;
    }
}
