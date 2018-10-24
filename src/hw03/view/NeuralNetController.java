/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 23, 2018
* Time: 11:57:01 PM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: NeuralNetController
* Description:
*
* ****************************************
 */
package hw03.view;

import hw03.NeuralNet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author logan
 */
public class NeuralNetController implements EventHandler<ActionEvent> {

    private NeuralNetView theView;
    private NeuralNet theModel;

    public NeuralNetController(NeuralNetView theView) {
        this.theView = theView;
        this.theModel = theView.getMyNet();

    }

    @Override
    public void handle(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
