/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 11, 2018
* Time: 10:50:17 AM
*
* Project: csci205_proj_hw
* Package: hw1
* File: HiddenNeuron
* Description: The file contains HiddenNeuron, which represents a neuron in a
*              hidden layer of a neural network.
*
* ****************************************
 */
package hw1;

/**
 * HiddenNeuron represents a neuron in a hidden layer of a neural network.
 *
 * @author ks061
 */
public class HiddenNeuron extends Neuron {

    private double theta;

    /**
     * Constructor for a neuron in a hidden layer of a neural network.
     *
     * @param neuronNum index of the neuron within a hidden layer
     * @param layerNum index of the hidden layer this neuron lies within
     * @param nN neural network this neuron lies within
     */
    public HiddenNeuron(int neuronNum, int layerNum, NeuralNet nN) {
        super(neuronNum, layerNum, nN);
        this.theta = super.getNeuralNet().getTheta(layerNum, neuronNum);
    }

    /**
     * Fire the neuron (essentially calculate the net input of neuron based on
     * weighted inputs from incoming edges and using an activation function)
     *
     *
     * @author lts010, ks061
     */
    public void fire() {
        double net = 0.0;
        for (Edge inEdge : super.getInEdges()) {
            net += inEdge.getWeightedValue();
        }
        net -= this.theta;
        super.setNetValue(super.getActivationFunction().calcOutput(net));
    }

    /**
     * Calculates error gradient by weighting the error gradients back
     * propagated by its output edges, adjusts the neuron's theta, back
     * propagates the error gradient to edges connecting to it, and stores its
     * own theta within the neural network.
     *
     * @author lts010, ks061
     */
    public void learn() {
        double errorGradient = 0;
        double weightedErrorGradients = 0;
        for (Edge edge : super.getOutEdges()) {
            weightedErrorGradients += edge.getWeightTimesDelta();
        }

        //String strNetVal = Double.toString(super.getNetValue());
        // System.out.println("netValue: " + strNetVal);
        // System.out.println("weightedErrorGradient: " + weightedErrorGradients);
        errorGradient = super.getNetValue() * (1 - super.getNetValue()) * weightedErrorGradients;
        // System.out.println("errorGradient: " + errorGradient);
        // TODO: remove
        //String strNeuronNum = Integer.toString((super.getNeuronNum() + 1));
        // TODO: remove
        //String strErrorGradient = Double.toString(errorGradient);
        // TODO: remove
        // System.out.println(
        //       "delta_h" + strNeuronNum + "(0) = " + strErrorGradient);
        setTheta(getTheta() + NeuralNet.alpha * -1 * errorGradient);
        // System.out.println(
        //       "theta_h" + strNeuronNum + "(1) = " + getTheta());

        for (Edge edge : super.getInEdges()) {
            edge.learn(errorGradient);
        }

        super.getNeuralNet().storeTheta(super.getLayerNum(),
                                        super.getNeuronNum(), getTheta());
    }

    /**
     * Gets the theta value for this neuron
     *
     * @return theta value for this neuron
     */
    public double getTheta() {
        return this.theta;
    }

    /**
     * Sets the theta value for this neuron
     *
     * @param theta theta value to set for this neuron
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }
}
