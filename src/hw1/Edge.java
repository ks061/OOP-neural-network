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
 * @author cld028
 */
public class Edge {
    private double weight;
    private double errorGradient = 0;
    private double weightDelta = 0;
    private double prevWeightDelta = 0;

    /**
     *
     */
    private Neuron to;
    private Neuron from;

    Edge() {
        this.weight = 0;
    }

    Edge(double weight) {
        this.weight = weight;
    }
}
