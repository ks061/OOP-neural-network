/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 24, 2018
* Time: 12:06:05 AM
*
* Project: csci205_proj_hw
* Package: hw03
* File: NeuralNetMain
* Description:
*
* ****************************************
 */
package hw03.view;

import hw03.NeuralNet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author logan
 */
public class NeuralNetMain extends Application {

    private NeuralNetView theView;
    private NeuralNetController theCtrl;
    private NeuralNet theModel;

    @Override
    public void init() throws Exception {
        super.init();
        theView = new NeuralNetView();
        theModel = theView.getMyNet();
    }

    @Override
    public void start(Stage primaryStage) {
        theCtrl = new NeuralNetController(theView);
        Group root = this.theView.getRootNode();
        Scene scene = new Scene(root);

        primaryStage.setTitle("NeuralNet");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
