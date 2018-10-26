/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles and Kartikeya Sharma
* Date: Oct 25, 2018
* Time: 8:32:27 AM
*
* Project: csci205_proj_hw
* Package: hw03.view
* File: ConfigScene
* Description:
*
* ****************************************
 */
package hw03.view;

import hw02.ANNUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author ks061, lts010
 */
public class ConfigScene {

    private Group root;
    private Group bottomGroup;
    private Group getConfigFileGroup;
    private Group getANNGroup;
    private Group enterANNGroup;
    private Stage theStage;
    private hw02.ANNConfig theConfig;

    /**
     *
     * @param theStage
     * @author lts010, ks061
     */
    public ConfigScene(Stage theStage) {
        this.theStage = theStage;
        this.root = new Group();
        this.bottomGroup = new Group();
        this.getConfigFileGroup = new Group();
        this.getANNGroup = new Group();
        this.enterANNGroup = new Group();

        bottomGroup.setLayoutX(10);
        bottomGroup.setLayoutY(100);
        root.getChildren().add(bottomGroup);
        //bottomGroup.getChildren().add(getConfigFileGroup);
        initGetConfigFileGroup();
        initGetANNGroup();
        initEnterANNGroup();

        //perhaps these buttons should be a menu?
        Button configButton = new Button("Load Config file");
        configButton.setLayoutX(10);
        configButton.setOnAction((ActionEvent event) -> {
            //updateBottomGroup(getConfigFileGroup);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open config file");
            fileChooser.getExtensionFilters().add(new ExtensionFilter(
                    "Text Files", "*.txt"));
            File configFile = fileChooser.showOpenDialog(this.theStage);
            try {
                initializeConfigurationFromFile(configFile);
            } catch (FileNotFoundException ex) {
                //this shouldn't happen
                Logger.getLogger(ConfigScene.class.getName()).log(Level.SEVERE,
                                                                  null, ex);
            }
        });
        root.getChildren().add(configButton);

        Button savedANNButton = new Button("Load Saved Neural Net");
        savedANNButton.setLayoutX(150);
        savedANNButton.setOnAction((ActionEvent event) -> {
            updateBottomGroup(getANNGroup);
            System.out.println(
                    "Should show input control to get ann file name + submit button");
        });
        root.getChildren().add(savedANNButton);

        Button enterANNButton = new Button("Enter New Neural Net");
        enterANNButton.setLayoutX(330);
        enterANNButton.setOnAction((ActionEvent event) -> {
            updateBottomGroup(enterANNGroup);
            System.out.println(
                    "Should show input controls to enter all parameter + submit button");
        });
        root.getChildren().add(enterANNButton);

    }

    /**
     *
     * @return @author ks061, lts010
     */
    public Group getRootNode() {
        return root;
    }

    /**
     * @author lts010, ks061
     */
    private void initGetConfigFileGroup() {
        Label configFileLabel = new Label("Enter the name of the config file");
        TextField configFileTextField = new TextField();
        // configFileTextField.setPromptText("Enter the name of the config file (.txt file)");
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((ActionEvent event) -> {
            String configFile = configFileTextField.getText();
            System.out.println("Need to load " + configFile);
        });

        HBox configFileHBox = new HBox();
        configFileHBox.getChildren().addAll(configFileLabel, configFileTextField,
                                            submitButton);
        configFileHBox.setSpacing(10);
        getConfigFileGroup.getChildren().add(configFileHBox);

    }

    /**
     * @author ks061, lts010
     */
    private void initGetANNGroup() {
        Label aNNFileLabel = new Label(
                "Enter the name of the saved Neural Net file");
        TextField aNNFileTextField = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((ActionEvent event) -> {
            String configFile = aNNFileTextField.getText();
            System.out.println("Need to load " + configFile);
        });

        HBox aNNFileHBox = new HBox();
        aNNFileHBox.getChildren().addAll(aNNFileLabel, aNNFileTextField,
                                         submitButton);
        aNNFileHBox.setSpacing(10);
        getANNGroup.getChildren().add(aNNFileHBox);
    }

    /**
     * @author lts010, ks061
     */
    private void initEnterANNGroup() {
        System.out.println("Need to implement initEnterANNGroup");
    }

    /**
     *
     * @param displayGroup
     *
     * @author ks061, lts010
     */
    private void updateBottomGroup(Group displayGroup) {
        if (!bottomGroup.getChildren().contains(displayGroup)) {
            bottomGroup.getChildren().clear();
            bottomGroup.getChildren().add(displayGroup);
            System.out.println("changed bottomGroup");
        }
    }

    /**
     * Reads the configuration file and creates ANNConfig object containing the
     * configuration information read in from the file
     *
     * @author ks061, lts010
     */
    //TODO should
    private void initializeConfigurationFromFile(File file) throws FileNotFoundException {
        ArrayList<String> configList = new ArrayList<>();
        //  File f = new File(filename);
        Scanner fReader = new Scanner(file);
        while (fReader.hasNextLine()) {
            configList.add(fReader.nextLine());
        }
        int thetaIndex = configList.indexOf("THETAS"); // indicates which index the thetas start at
        ArrayList<String> configListWeights = new ArrayList<>(
                configList.subList(0,
                                   thetaIndex)); // seperates the weights (and the numbers before the weights) into a list before the string "THETA"
        ArrayList<String> configListThetas = new ArrayList<>( // seperates the thetas into a list after the string "THETA"
                configList.subList(
                        thetaIndex + 1, configList.size()));
        ArrayList<ArrayList<Double>> weightList = ANNUtility.strListToDoubleList( // turns the weight list into a list of list of doubles
                configListWeights);
        ArrayList<ArrayList<Double>> thetas = ANNUtility.strListToDoubleList(
                configListThetas); // turns the theta list into a list of list of doubles
        thetas.add(0, new ArrayList<>()); // adds input layer without any weights
        int numInputs = (int) Math.round(weightList.get(0).get(0));
        int numOutputs = (int) Math.round(weightList.get(0).get(1));
        int numHiddenLayers = (int) Math.round(weightList.get(0).get(2));
        int numNeuronsPerHiddenLayer = (int) Math.round(weightList.get(0).get(3));
        double highestSSE = weightList.get(0).get(4);
        int numMaxEpochs = (int) Math.round(weightList.get(0).get(5));
        ArrayList<ArrayList<Double>> weights = new ArrayList<>(
                weightList.subList(1,
                                   configListWeights.size()));

        //TODO fix so that TRAINING mode isn't hardcoded below
        //TODO add to config file?  if so does comand line need to be fixed?
        this.theConfig = new hw02.ANNConfig(numInputs, numOutputs,
                                            numHiddenLayers,
                                            numNeuronsPerHiddenLayer,
                                            highestSSE, numMaxEpochs,
                                            weights, thetas,
                                            hw02.ProgramMode.TRAINING);
    }
}
