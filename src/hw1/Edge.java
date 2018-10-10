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
 * @author lts010, ks061
 */
public class Edge {

    private double weight;
    private final double errorGradient = 0;
    private final double weightDelta = 0;
    private double weightTimesDelta = 0;
    private Neuron to;
    private Neuron from;
    private final int edgeNumber;
    private final int layerNumber;
    private final NeuralNet neuralNet;

    /**
     * Explicit constructor for creating an edge
     *
     * @param edgeNum is a ID number for this edge, and is unique within the
     * layer. It is used a an index in the \\weight table.
     * @author ks061, lts010
     */
    Edge(NeuralNet nN, int layerNum, int edgeNum) {
        this.neuralNet = nN;
        this.weight = neuralNet.getWeight(layerNum, edgeNum);
        this.edgeNumber = edgeNum;
        this.layerNumber = layerNum;
    }

    /**
     * Gives the weighted value
     *
     * @return the weighted value
     *
     * @author lts010, ks061
     */
    public double getWeightedValue() {
        return weight * from.getNetValue();
    }

    /**
     * Sets the neuron that this edge gets data from
     *
     * @param neuron neuron that this edge should get data from
     *
     * @author ks061, lts010
     */
    protected void setFrom(Neuron neuron) {
        this.from = neuron;
    }

    /**
     * Sets the neuron that this edge delivers data to
     *
     * @param neuron neuron that this edge should deliver data to
     *
     * @author ks061, lts010
     */
    protected void setTo(Neuron neuron) {
        this.to = neuron;
    }

    /**
     * Gets the neuron that this edge gets data from
     *
     * @return neuron that this edge gets data from
     *
     * @author ks061, lts010
     */
    protected Neuron getFrom() {
        return this.from;
    }

    /**
     * Gets the neuron that this edge delivers data to
     *
     * @return neuron that this edge delivers data to
     *
     * @author ks061, lts010
     */
    protected Neuron getTo() {
        return this.to;
    }

    /**
     * Gets the value that this edge will send to the neuron this edge will
     * deliver data to (after applying the weight to the input received from the
     * neuron that delivers data to the edge)
     *
     * @return value that this edge will send to the neuron this edge will
     * deliver data to
     *
     * @author ks061, lts010
     */
    protected double getValue() {
        return weight * from.getNetValue();
    }

    /**
     * Gets the weight times the delta
     *
     * @return weight times delta
     *
     * @author ks061, lts010
     */
    public double getWeightTimesDelta() {
        return weightTimesDelta;
    }

    // TODO: ASK PROF make everything protected (should they be public or protected)
    /**
     * Updates the weight for the edge
     *
     * @param errorGradient the value of delta, the error gradient
     * @param toOutputLayer true if this edge is connected to the output layer.
     *
     * @author lts010, ks061
     */
    protected void learn(double errorGradient) {
        this.weightTimesDelta = this.weight * errorGradient;
        this.weight = this.weight + NeuralNet.alpha * this.from.getNetValue() * errorGradient;
        neuralNet.storeWeight(layerNumber, edgeNumber, this.weight);
    }

}

//    /**
//     * Changes the weight
//     *
//     * @param alpha - the learning rate
//     * @param error - the difference between the desired output and the actual
//     * output
//     *
//     * @author lts010, ks061
//     */
//    public void changeWeight(double alpha, double error) {
//        this.prevWeightDelta = this.weightDelta;
//        this.weightDelta = alpha * from.getValue() * error;
//        this.weight += weightDelta;
//        neuralNet.storeWeight(layerNumber, edgeNumber, this.weight);
//    }
//    /**
//     * Explicit constructor for creating an edge
//     *
//     * @param weight weight applied to data transferred to neuron
//     *
//     * @author ks061, lts010
//     */
//    Edge(double weight) {
//        this.to = to;
//        this.from = from;
//        this.weight = weight;
//    }
