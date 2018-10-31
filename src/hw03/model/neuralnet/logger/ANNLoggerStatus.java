/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 18, 2018
* Time: 1:31:07 PM
*
* Project: csci205_proj_hw
* Package: hw02.Logger
* File: ANNLoggerStatus
* Description: This file contains ANNLoggerStatus, which represents whether the
*              logger is on or off (printing out to a .txt file or not).
* ****************************************
 */
package hw03.model.neuralnet.logger;

/**
 * ANNLoggerStatus represents whether the logger is on or off (printing out to a
 * .txt file or not).
 *
 * @author ks061, lts010
 */
public enum ANNLoggerStatus {
    /**
     * State assigned to a logger to indicate that it will write log messages
     * from the program to the console.
     */
    ON,
    /**
     * State assigned to a logger to indicate that it will not write log
     * messages from the program to the console.
     */
    OFF;
}
