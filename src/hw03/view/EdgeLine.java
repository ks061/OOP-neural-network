/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 25, 2018
* Time: 1:23:07 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: EdgeLine
* Description: This file contains EdgeLine, which is a graphical component of
*              the GUI representing an edge in the artificial neural network.
* ****************************************
 */
package hw03.view;

import javafx.scene.shape.Line;

/**
 * EdgeLine is a graphical component of the GUI representing an edge in the
 * artificial neural network
 *
 * @author lts010, ks061
 */
public class EdgeLine extends Line {

    /**
     * Weight of the the edge line representing an edge in the neural network
     */
    private Double weight;

    /**
     * Constructs an edge line with starting and ending coordinates for the line
     * as well as the weight associated with the edge represented by the line
     *
     * @param startX starting x coordinate for the line
     * @param startY starting y coordinate for the line
     * @param endX ending x coordinate for the line
     * @param endY ending y coordinate for the line
     * @param weight weight associated with the edge represented by the line
     *
     * @author ks061, lts010
     */
    public EdgeLine(double startX, double startY, double endX, double endY,
                    Double weight) {
        super(startX, startY, endX, endY);
        this.weight = weight;
    }

}
