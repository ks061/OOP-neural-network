/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 24, 2018
* Time: 5:25:27 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: Point
* Description: This file contains Point, which holds the x and y coordinates
*              for a point.
* ****************************************
 */
package hw03.view;

/**
 * Point holds the x and y coordinates for a point.
 *
 * @author lts010, ks061
 */
public class Point {

    /**
     * X coordinate for the point
     */
    private final double x;
    /**
     * Y coordinate for the point
     */
    private final double y;

    /**
     * Gets the x coordinate for the point
     *
     * @return x coordinate for the point
     *
     * @author ks061, lts010
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coordinate for the point
     *
     * @return y coordinate for the point
     *
     * @author lts010, ks061
     */
    public double getY() {
        return y;
    }

    /**
     * Constructor for a point containing its x and y coordinates
     *
     * @param x x coordinate for the point
     * @param y y coordinate for the point
     *
     * @author ks061, lts010
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets a string representation of the point containing its x and y
     * coordinates
     *
     * @return string representation of the point containing its x and y
     * coordinates
     *
     * @author lts010, ks061
     */
    @Override
    public String toString() {
        return ("(" + x + ", " + y + ")");
    }
}
