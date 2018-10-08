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
 * File: Edge
 * Description: Represents an edge in a neural net
 *
 * ****************************************
 */
package hw1;

/**
 * Class to hold properties associated with edges between nodes
 *
 * @author lts010
 */
public class Edge {

    private double weight;
    private double errorGradient = 0;
    private double weightDelta = 0;
    private double prevWeightDelta = 0;
    private Neuron to;
    private Neuron from;

    Edge(double weight) {
        this.weight = weight;
    }

    Edge() {
        this.weight = 0;
    }

    /**
     * Changes the weight
     *
     * @param alpha - the learning rate
     * @param error - the difference between the desired output and the actual
     * output
     * @author - lts010
     */
    public void changeWeight(double alpha, double error) {
        this.prevWeightDelta = this.weightDelta;
        this.weightDelta = alpha * from.getValue() * error;
        this.weight += weightDelta;
    }

    /**
     * Gives the weighted value
     *
     * @return the weighted value
     * @author - lts010
     */
    public double getWeightedValue() {
        return weight * from.getValue();
    }

    protected void setFrom(Neuron neuron) {
        this.from = neuron;
    }

    protected void setTo(Neuron neuron) {
        this.to = neuron;
    }

    protected double getValue() {
        return weight * from.getValue();
    }

}
