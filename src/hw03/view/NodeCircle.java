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
* Description: NodeCircle is what ANNView uses to represent the neurons of the ANN visually
*
* ****************************************
 */
package hw03.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * NodeCircle is what ANNView uses to represent the neurons of the ANN visually
 *
 * @author lts010
 */
public class NodeCircle extends Circle {

    /**
     * The text that will go over the circle
     */
    private Text text;

    public NodeCircle(double x, double y, double radius) {
        super(x, y, radius);
        this.text = new Text("");
        this.text.setLayoutX(this.getCenterX() - (this.getRadius() / 2));
        this.text.setLayoutY(this.getCenterY() - 10);

        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
        this.text.setStroke(Color.BLACK);
        this.text.setLayoutX(x - (radius / 3));
        this.text.setLayoutY(y);
    }

    public Text getText() {
        return this.text;
    }

    public void setText(Text text) {
        this.text = text;
        this.text.setLayoutX(this.getCenterX() - (this.getRadius() / 3));
        this.text.setLayoutY(this.getCenterY());
    }

    public void setText(String text) {
        this.text.setText(text);
    }

}
