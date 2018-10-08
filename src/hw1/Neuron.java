/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 7, 2016
 * Time: 6:41:58 AM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: Neuron
 * Description:
 *
 * ****************************************
 */
package hw1;

import java.util.ArrayList;

/**
 * Class for individual neuron elements
 *
 * @author cld028
 *
 */
public class Neuron {

    private ArrayList<Edge> inEdges;
    private ArrayList<Edge> outEdges;
    private String id = "Neuron";
    private WeightAssignment weightAssign;
    private double alpha;
    private double netInput;
    private double theta;
    private double value;
    private boolean inputNeuron = false;

    /**
     * A default learning rate if you decide to use this within the neurons
     */
    public final static double DEFAULTALPHA = 0.2;

    /**
     * A default theta (threshold) value
     */
    public final static double DEFAULTTHETA = 0.1;

    Neuron(String id) {
        this.id = id;
        this.alpha = DEFAULTALPHA;
        this.theta = DEFAULTTHETA;
    }

    Neuron(int idNum) {
        this(Integer.toString(idNum));
    }

    Neuron(int idNum, int numOutEdges) {
        this(idNum);
        for (int i = 0; i < numOutEdges; i++) {
            outEdges.add(new Edge());
        }
    }

    public void setInput() {

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
        this.value = net;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public ArrayList<Edge> getInEdges() {
        return inEdges;
    }

    public ArrayList<Edge> getOutEdges() {
        return outEdges;
    }
}
