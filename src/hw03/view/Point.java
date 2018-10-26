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
* Description:
*
* ****************************************
 */
package hw03.view;

/**
 * This class holds the x and y coordinates for a point.
 *
 * @author lts010, ks061
 */
public class Point {

    private final double x;
    private final double y;

    /**
     *
     * @return @author ks061, lts010
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return @author lts010, ks061
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @param x
     * @param y
     * @author ks061, lts010
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return @author lts010, ks061
     */
    @Override
    public String toString() {
        return ("(" + x + ", " + y + ")");
    }
}
