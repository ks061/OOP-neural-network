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
* File: ANNMain
* Description: This file contains ANNMain, which represents the main runner of
*              the neural network MVC application.
* ****************************************
 */
package hw03.mvcmain;

import hw03.controller.ANNController;
import hw03.model.ANNModel;
import hw03.view.ANNMenuBar;
import hw03.view.ANNView;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ANNMain represents the main runner of the neural network MVC application.
 *
 * @author lts010, ks061
 */
public class ANNMain extends Application {

    /**
     * The model of this neural network MVC application.
     */
    private ANNModel theModel;

    /**
     * The view of this neural network MVC application.
     */
    private ANNView theView;

    /**
     * The controller of this neural network MVC application.
     */
    private ANNController theCtrl;

    // TODO move to view
    private ANNMenuBar theConfigScene;

    /**
     * Initializes the application
     *
     * @throws Exception if an unexpected issue arises
     *
     * @author ks061, lts010
     */
    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new ANNModel();
        this.theView = new ANNView(theModel);
        this.theCtrl = new ANNController(theModel, theView);
    }

    /**
     * Starts the application
     *
     * @param primaryStage stage of the GUI
     *
     * @author ks061, lts010
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Scene scene = new Scene(theView.getRootNode());
        // TODO move to view
        // this.theConfigScene = new ANNMenuBar(primaryStage);
        // Scene scene = new Scene(theConfigScene.getRootNode(), 600, 400);
        primaryStage.setTitle("Neural Net");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the application
     *
     * @param args the command line arguments
     *
     * @author ks061, lts010
     */
    public static void main(String[] args) {
        launch(args);
    }

}
