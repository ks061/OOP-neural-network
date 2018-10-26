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
* Description:
*
* ****************************************
 */
package hw03.view;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lts010
 */
public class ANNMain extends Application {

    private ANNView theView;
    private ConfigScene theConfigScene;
    private ANNController theCtrl;

    /**
     *
     * @throws Exception
     * @author ks061, lts010
     */
    @Override
    public void init() throws Exception {
        super.init();
        this.theView = new ANNView();
        this.theCtrl = new ANNController(theView);
    }

    /**
     *
     * @param primaryStage
     * @throws FileNotFoundException
     * @author lts010, ks061
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.theConfigScene = new ConfigScene(primaryStage);
        // Scene scene = new Scene(theView.getRootNode());
        Scene scene = new Scene(theConfigScene.getRootNode(), 600, 400);
        primaryStage.setTitle("Neural Net");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
