/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 24, 2018
* Time: 9:21:10 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: NodeCircle
* Description: This file contains NodeCircle, which is used by the view
*              to display the neurons in the neural network
*
* ****************************************
 */
package hw03.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * NodeCircle is used by the view to display the neurons in the neural network
 *
 * @author lts010, ks061
 */
public class NodeCircle extends Circle {

    /**
     * Text displayed within the circle in the GUI
     */
    private Text text;

    /**
     * Constructor that specifies its location (its x and y coordinates within
     * the GUI) and radius
     *
     * @param x x coordinate of the circle in the GUI
     * @param y y coordinate of the circle in the GUI
     * @param radius radius of the circle
     *
     * @author ks061, lts010
     */
    public NodeCircle(double x, double y, double radius) {
        super(x, y, radius);
        this.text = new Text("");
        this.text.setLayoutX(this.getCenterX() - this.getRadius() * 2 / 3);
        this.text.setLayoutY(y - this.getRadius() / 2);

        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
        this.text.setStroke(Color.BLACK);

    }

    /**
     * Gets the text displayed within the circle in the GUI
     *
     * @return text displayed within the circle in the GUI
     *
     * @author ks061, lts010
     */
    public Text getText() {
        return this.text;
    }

    /**
     * Sets the text displayed within the circle in the GUI
     *
     * @param text text displayed within the circle in the GUI
     *
     * @author ks061, lts010
     */
    public void setText(Text text) {
        this.text = text;
        this.text.setLayoutX(this.getCenterX() - this.getRadius() * 2 / 3);
        this.text.setLayoutY(this.getCenterY() - this.getRadius() / 2);
    }

}
