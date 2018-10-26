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
* Description:
*
* ****************************************
 */
package hw03.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author lts010, ks061
 */
public class EdgeLine extends Line {

    private double weight;

    /**
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param weight
     * @author ks061, lts010
     */
    public EdgeLine(double startX, double startY, double endX, double endY,
                    double weight) {
        super(startX, startY, endX, endY);
        this.weight = weight;
    }

    /**
     * Updates the color of a line based on the edge weight associated with the
     * provided edge and layer number (red for negative weights, green for
     * positive, and blue for zero)
     *
     * @author lts010, ks061
     */
    public void updateColor() {
        if (this.weight > 0) {
            this.setStroke(Color.GREEN);
        }
        else if (this.weight == 0) {
            this.setStroke(Color.BLUE);
        }
        else {
            this.setStroke(Color.RED);
        }
    }

}
