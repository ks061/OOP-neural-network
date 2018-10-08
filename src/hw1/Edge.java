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
 * @author lts010, ks061
 */
public class Edge {

    private double weight;
    private double errorGradient = 0;
    private double weightDelta = 0;
    private double prevWeightDelta = 0;
    private Neuron to;
    private Neuron from;

    /**
     * Explicit constructor for creating an edge
     *
     * @param to neuron edge delivers data to
     * @param from neuron edge gets data from
     * @param weight weight applied to data transferred to neuron
     *
     * @author ks061
     */
    Edge(Neuron to, Neuron from, double weight) {
        this.to = to;
        this.from = from;
        this.weight = weight;
    }

    /**
     * Explicit constructor for creating an edge
     *
     * @param to neuron edge delivers data to
     * @param from neuron edge gets data from
     *
     * @author ks061
     */
    Edge(Neuron to, Neuron from) {
        this(to, from, RandomWeightAssignment.assignWeight());
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

    /**
     * Sets the neuron that this edge gets data from
     *
     * @param neuron neuron that this edge should get data from
     *
     * @author ks061
     */
    protected void setFrom(Neuron neuron) {
        this.from = neuron;
    }

    /**
     * Sets the neuron that this edge delivers data to
     *
     * @param neuron neuron that this edge should deliver data to
     *
     * @author ks061
     */
    protected void setTo(Neuron neuron) {
        this.to = neuron;
    }

    /**
     * Gets the value that this edge will send to the neuron this edge will
     * deliver data to (after applying the weight to the input received from the
     * neuron that delivers data to the edge)
     *
     * @return value that this edge will send to the neuron this edge will
     * deliver data to
     */
    protected double getValue() {
        return weight * from.getValue();
    }

}
