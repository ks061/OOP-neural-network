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
* File: ANNMenuBar
* Description: This file contains ANNMenuBar, which represents the menu bar at
*              the top of the application view/GUI.
* ****************************************
 */
package hw03.view;

import hw03.model.neuralnet.ANNConfig;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * ANNMenuBar represents the menu bar at the top of the application view/GUI.
 *
 * @author lts010, ks061
 */
public class ANNMenuBar {

    private Group menu;
    private Group configGroup;
    private ArrayList<Node> workSpaceChildren;
    private Stage theStage;
    private ANNConfig theConfig;
    private MenuBar menuBar;

    private TextField numInputsTextField;
    private TextField numOutTextField;
    private TextField numHiddenTextField;
    private TextField maxSSETextField;
    private TextField maxEpochTextField;
    double[][] theData;

    private MenuItem loadConfigFileMI;
    private MenuItem saveConfigFileMI;
    private MenuItem loadTrainingFileMI;
    private MenuItem loadTestFileMI;
    private MenuItem configMI;
    private MenuItem exitMI;
    private Button submitBtn;
    private Button cancelBtn;

    public ANNMenuBar(Stage theStage, Group workSpaceGroup) {
        this.theStage = theStage;
        this.configGroup = workSpaceGroup;
        this.workSpaceChildren = new ArrayList<Node>();

        Menu menuFile = new Menu("File");
        this.loadConfigFileMI = new MenuItem("Load Config File");
        this.saveConfigFileMI = new MenuItem("Save Config File");
        this.loadTrainingFileMI = new MenuItem("Load Training Data File");
        this.loadTestFileMI = new MenuItem("Load Test Data File");
        menuFile.getItems().addAll(loadConfigFileMI, saveConfigFileMI,
                                   loadTrainingFileMI, loadTestFileMI);

        Menu menuConfig = new Menu("Config");
        this.configMI = new MenuItem("Configure a new Neural Net");
        menuConfig.getItems().add(configMI);

        Menu menuExit = new Menu("Exit");
        exitMI = new MenuItem("Exit");
        menuExit.getItems().add(exitMI);
        cancelBtn = new Button("Cancel");
        submitBtn = new Button("Submit");

        this.menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuConfig, menuExit);

    }

    public MenuItem getLoadConfigFileMI() {
        return loadConfigFileMI;
    }

    public MenuItem getSaveConfigFileMI() {
        return saveConfigFileMI;
    }

    public MenuItem getLoadTrainingFileMI() {
        return loadTrainingFileMI;
    }

    public MenuItem getLoadTestFileMI() {
        return loadTestFileMI;
    }

    public MenuItem getConfigMI() {
        return configMI;
    }

    public MenuItem getExitMI() {
        return exitMI;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public TextField getNumInputsTextField() {
        return numInputsTextField;
    }

    public TextField getNumOutTextField() {
        return numOutTextField;
    }

    public TextField getNumHiddenTextField() {
        return numHiddenTextField;
    }

    public TextField getMaxSSETextField() {
        return maxSSETextField;
    }

    public TextField getMaxEpochTextField() {
        return maxEpochTextField;
    }

    /**
     * Creates a GridPane that has a form for the end user to enter the number
     * of input neurons, the number of output neurons, the number of neurons in
     * the hidden layer, the maximum allowable sum of squared errors (if the
     * program is in training mode), and the maximum number of epochs the
     * program should run (if the program is in training mode).
     *
     * @author ks061, lts010
     */
    public void getConfigInfo() {
        GridPane configGridPane = new GridPane();

        configGridPane.setPadding(new Insets(10));
        configGridPane.setHgap(10);
        configGridPane.setVgap(10);

        ColumnConstraints columnOfConfigLabels = new ColumnConstraints(300);
        ColumnConstraints columnOfConfigInputs = new ColumnConstraints(75);
        configGridPane.getColumnConstraints().add(columnOfConfigLabels);
        configGridPane.getColumnConstraints().add(columnOfConfigInputs);

        final int LABEL_COLUMN_INDEX = 0;
        final int INPUTS_COLUMN_INDEX = 1;

        int rowIndex = 0;
        Label numInputsLabel = new Label(
                "Enter the number of input neurons [default 2]");
        numInputsTextField = new TextField();
        GridPane.setHalignment(numInputsLabel, HPos.RIGHT);
        configGridPane.add(numInputsLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(numInputsTextField, INPUTS_COLUMN_INDEX, rowIndex++);
        //TODO define the default values somewhere else
        Label numOutLabel = new Label(
                "Enter the number of output neurons [default 1]");
        numOutTextField = new TextField();
        GridPane.setHalignment(numOutLabel, HPos.RIGHT);
        configGridPane.add(numOutLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(numOutTextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Label numHiddenLabel = new Label(
                "Enter the number hidden neurons [default 3]");
        numHiddenTextField = new TextField();
        GridPane.setHalignment(numHiddenLabel, HPos.RIGHT);
        configGridPane.add(numHiddenLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(numHiddenTextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Label maxSSELabel = new Label(
                "Enter the maximum SSE desired  [default 0.1]");
        maxSSETextField = new TextField();
        GridPane.setHalignment(maxSSELabel, HPos.RIGHT);
        configGridPane.add(maxSSELabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(maxSSETextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Label maxEpochLabel = new Label(
                "Enter the maximum number of Epochs [default 50000]");
        maxEpochTextField = new TextField();
        GridPane.setHalignment(maxEpochLabel, HPos.RIGHT);
        configGridPane.add(maxEpochLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(maxEpochTextField, INPUTS_COLUMN_INDEX, rowIndex++);

        GridPane.setHalignment(cancelBtn, HPos.CENTER);
        configGridPane.add(cancelBtn, LABEL_COLUMN_INDEX, rowIndex);

        GridPane.setHalignment(submitBtn, HPos.CENTER);
        configGridPane.add(submitBtn, INPUTS_COLUMN_INDEX, rowIndex++);

        this.configGroup.getChildren().add(configGridPane);

    }
}
