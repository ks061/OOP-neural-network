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
    private boolean inputNeuron = false;

    /**
     * A default learning rate if you decide to use this within the neurons
     */
    public final static double DEFAULTALPHA = 0.2;

    /**
     * A default theta (threshold) value
     */
    public final static double DEFAULTTHETA = 0.1;

    Neuron(int idNum) {

    }

    Neuron(String id) {

    }

    Neuron(int numOutEdges, int idNum) {

    }

    /**
     * Fire the neuron (essentially run net input in neuron using an activation
     * function)
     */
    public void fire() {
    }
}
