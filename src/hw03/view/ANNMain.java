/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private ANNController theCtrl;

    @Override
    public void init() throws Exception {
        super.init();
        theView = new ANNView();
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        theCtrl = new ANNController(theView);

        Scene scene = new Scene(theView.getRootNode());
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
