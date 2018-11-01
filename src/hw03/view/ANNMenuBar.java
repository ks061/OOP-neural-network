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

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
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

    /**
     * Menu of the GUI
     */
    private Group menu;
    /**
     * Components of the configuration view
     */
    private Group configGroup;
    /**
     * Stage of the GUI
     */
    private Stage theStage;
    /**
     * Menu bar of the GUI
     */
    private MenuBar menuBar;

    /**
     * Text field for user to specify number of inputs for the neural network
     */
    private TextField numInputsTextField;
    /**
     * Text field for user to specify number of outputs for the neural network
     */
    private TextField numOutTextField;
    /**
     * Text field for user to specify number of hidden neurons in the neural
     * network
     */
    private TextField numHiddenTextField;
    /**
     * Text field for user to specify the maximum sum of squared errors for the
     * neural network
     */
    private TextField maxSSETextField;
    /**
     * Text field for user to specify the maximum number of epochs for the
     * neural network
     */
    private TextField maxEpochTextField;

    /**
     * Menu item for loading a configuration for the neural network
     */
    private MenuItem loadConfigFileMI;
    /**
     * Menu item for loading a configuration for saving the configuration of the
     * current neural network to a file
     */
    private MenuItem saveConfigFileMI;
    /**
     * Menu item for loading data to train the neural net
     */
    private MenuItem loadTrainingFileMI;
    /**
     * Menu item for loading data that the neural network will make predictions
     * in its output values based off
     */
    private MenuItem loadTestFileMI;
    /**
     * Menu item for exiting out of the application
     */
    private MenuItem exitMI;

    /**
     * Menu item for the user to open a view that allows them to input
     * configuration options for creating neural network
     */
    private MenuItem configMI;
    /**
     * Menu item for submitting the data within the configuration view
     */
    private Button submitBtn;
    /**
     * Menu item for quitting out of the configuration view without submitting
     * any of the data in said view
     */
    private Button cancelBtn;

    /**
     * Constructs with pointers to the stage and
     *
     * @param theStage stage of the GUI
     * @param workSpaceGroup
     *
     * @author ks061, lts010
     */
    public ANNMenuBar(Stage theStage, Group workSpaceGroup) {
        this.theStage = theStage;
        this.configGroup = workSpaceGroup;

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

    /**
     * Gets the menu item in the GUI to load a configuration file for the neural
     * network
     *
     * @return menu item in the GUI to load a configuration file for the neural
     * network
     *
     * @author ks061, lts010
     */
    public MenuItem getLoadConfigFileMI() {
        return loadConfigFileMI;
    }

    /**
     * Gets the menu item in the GUI to save the configuration of the current
     * neural network to a file
     *
     * @return menu item in the GUI to save the configuration of the current
     * neural network to a file
     *
     * @author ks061, lts010
     */
    public MenuItem getSaveConfigFileMI() {
        return saveConfigFileMI;
    }

    /**
     * Gets the menu item in the GUI to load the data for training the neural
     * network
     *
     * @return menu item in the GUI to load the data for training the neural
     * network
     *
     * @author ks061, lts010
     */
    public MenuItem getLoadTrainingFileMI() {
        return loadTrainingFileMI;
    }

    /**
     * Gets the menu item in the GUI to load the test file for classifying the
     * neural network
     *
     * @return menu item in the GUI to load the test file for classifying the
     * neural network
     *
     * @author ks061, lts010
     */
    public MenuItem getLoadTestFileMI() {
        return loadTestFileMI;
    }

    /**
     * Gets the menu item in the GUI to specify a configuration for the neural
     * network of the application
     *
     * @return menu item in the GUI to specify a configuration for the neural
     * network of the applications
     *
     * @author ks061, lts010
     */
    public MenuItem getConfigMI() {
        return configMI;
    }

    /**
     * Gets the menu item in the GUI to exit the program
     *
     * @return menu item in the GUI to exit the program
     *
     * @author ks061, lts010
     */
    public MenuItem getExitMI() {
        return exitMI;
    }

    /**
     * Gets the menu bar of the GUI
     *
     * @return menu bar of the GUI
     *
     * @author ks061, lts010
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Gets the submit button in the GUI of the application when the program
     * asks the user to specify a configuration for the neural network
     *
     * @return submit button in the GUI of the application
     *
     * @author ks061, lts010
     */
    public Button getSubmitBtn() {
        return submitBtn;
    }

    /**
     * Gets the cancel button in the GUI of the application when the program
     * asks the user to specify a configuration for the neural network
     *
     * @return cancel button in the GUI of the application
     *
     * @author ks061, lts010
     */
    public Button getCancelBtn() {
        return cancelBtn;
    }

    /**
     * Gets the text field for the user to enter a number of inputs for the
     * neural network
     *
     * @return text field for the user to enter a number of inputs for the
     * neural network
     *
     * @author ks061, lts010
     */
    public TextField getNumInputsTextField() {
        return numInputsTextField;
    }

    /**
     * Gets the text field for the user to enter a number of outputs for the
     * neural network
     *
     * @return text field for the user to enter the number of outputs for the
     * neural network
     *
     * @author ks061, lts010
     */
    public TextField getNumOutTextField() {
        return numOutTextField;
    }

    /**
     * Gets the text field for the user to enter a the number of hidden neurons
     * to create in the hidden layer within the neural network
     *
     * @return text field for the user to enter a the number of hidden neurons
     * to create in the hidden layer within the neural network
     *
     * @author ks061, lts010
     */
    public TextField getNumHiddenTextField() {
        return numHiddenTextField;
    }

    /**
     * Gets the text field for the user to specify the maximum sum of squared
     * errors value to use whilst training the neural network
     *
     * @return text field for the user to specify the maximum sum of squared
     * errors value to use whilst training the neural network
     *
     * @author ks061, lts010
     */
    public TextField getMaxSSETextField() {
        return maxSSETextField;
    }

    /**
     * Gets the text field for the user to specify the maximum allowed epochs
     * for the neural network to run through during training
     *
     * @return text field for the user to specify the maximum allowed epochs for
     * the neural network to run through during training
     *
     * @author ks061, lts010
     */
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
