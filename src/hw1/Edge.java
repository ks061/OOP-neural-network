/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2016
 *
 * Name: Chris Dancy
 * Date: Oct 7, 2016
 * Time: 8:29:57 AM
 *
 * Project: 205-FA16Class
 * Package: hw01
 * File: Edge
 * Description:
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
    private double input = 0;

    /**
     *
     */
    private Neuron to;
    private Neuron from;

    Edge(Neuron to, Neuron from) {
        this.weight = Math.random(-0.5, 0.5);
        this.to = to;
        this.from = from;

    }

    Edge(double weight, Neuron to, Neuron from) {
        this.weight = weight;
        this.to = to;
        this.from = from;
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
        this.weightDelta = alpha * this.input * error;
        this.weight += weightDelta;
    }

    /**
     * Changes the value of the input
     *
     * @param newInput - the new input
     * @author - lts010
     */
    public void setInputValue(double newInput) {
        this.input = newInput;
    }

    /**
     * Gives the weighted value
     *
     * @return the weighted value
     * @author - lts010
     */
    public double getWeightedValue() {
        return weight * input;
    }
}
