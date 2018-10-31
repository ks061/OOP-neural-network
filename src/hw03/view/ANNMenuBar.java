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

import hw03.ANNConfig;
import hw03.ProgramMode;
import hw03.utility.ANNUtility;
import hw03.utility.ANNUtilityGUICompatible;
import hw03.utility.ANNViewUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< HEAD
<<<<<<< HEAD
import javafx.collections.ObservableList;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
=======
=======
>>>>>>> 4f0b3402e5668b970df6f0cd15a1f7e8eeae3365
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * ANNMenuBar represents the menu bar at the top of the application view/GUI.
 *
 * @author lts010, ks061
 */
public class ANNMenuBar {

    private Group root;
    private Group menu;
    private Group configGroup;
    private Stage theStage;
    private ANNConfig theConfig;
    double[][] theData;

    public ANNMenuBar(Stage theStage) {
        this.theStage = theStage;

        this.configGroup = new Group();
        configGroup.setLayoutX(10);
        configGroup.setLayoutY(25);
        root.getChildren().add(configGroup);

        Menu menuFile = new Menu("File");
        MenuItem loadConfigFileMI = new MenuItem("Load Config File");
        MenuItem saveConfigFileMI = new MenuItem("Save Config File");
        MenuItem loadNeuralNetMI = new MenuItem("Load Saved Neural Net");
        MenuItem saveNeuralNetMI = new MenuItem("Save Neural Net");
        MenuItem loadTrainingFileMI = new MenuItem("Load Training Data File");
        MenuItem loadTestFileMI = new MenuItem("Load Test Data File");
        menuFile.getItems().addAll(loadConfigFileMI, saveConfigFileMI,
                                   loadNeuralNetMI, saveNeuralNetMI,
                                   loadTrainingFileMI, loadTestFileMI);

        Menu menuConfig = new Menu("Config");
        MenuItem configMI = new MenuItem("Configure a new Neural Net");
        menuConfig.getItems().add(configMI);

        Menu menuExit = new Menu("Exit");
        MenuItem exitMI = new MenuItem("Exit");
        menuExit.getItems().add(exitMI);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuConfig, menuExit);
        root.getChildren().add(menuBar);

        loadConfigFileMI.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open config file");
            fileChooser.getExtensionFilters().add(new ExtensionFilter(
                    "Text Files", "*.txt"));
            File configFile = fileChooser.showOpenDialog(this.theStage);
            if (configFile != null) {
                try {
                    this.theConfig = ANNUtilityGUICompatible.createConfigurationFromFile(
                            configFile);
                } catch (FileNotFoundException ex) {
                    // This should not happen.
                    Logger.getLogger(ANNMenuBar.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
            //TODO we need to restart the Neural Net thread
            System.out.println("loadConfigFileMI was selected.");
        });

        saveConfigFileMI.setOnAction((event) -> {
            ANNViewUtility.showInputAlert("Error",
                                          "Save config file net not implement");
            System.out.println("saveConfigFileMI was selected.");
        });

        loadNeuralNetMI.setOnAction((event) -> {
            ANNViewUtility.showInputAlert("Error",
                                          "Load Neural net not implement");
            System.out.println("loadNeuralNetMI was selected.");
        });

        saveNeuralNetMI.setOnAction((event) -> {
            ANNViewUtility.showInputAlert("Error",
                                          "Save Neural net not implement");
            System.out.println("saveNeuralNetMI was selected.");
        });

        loadTrainingFileMI.setOnAction((event) -> {
            double[][] result;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Training File");
            fileChooser.getExtensionFilters().add(new ExtensionFilter(
                    "Training Data", "*.csv"));
            File trainingFile = fileChooser.showOpenDialog(this.theStage);
            if (trainingFile == null) {
                ANNViewUtility.showInputAlert("Error", "Error openning file");
            }
            else {
                result = ANNUtilityGUICompatible.getData(
                        trainingFile);
                //if empty getData should have already notified the user
                if (result.length > 0) {
                    this.theData = result;
                }
            }
            this.theConfig.setProgramMode(ProgramMode.TRAINING);
        });

        loadTestFileMI.setOnAction((event) -> {
            double[][] result;
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Input/Test Data");
            fileChooser.getExtensionFilters().add(new ExtensionFilter(
                    "Input/Test Data", "*.csv"));
            File testFile = fileChooser.showOpenDialog(this.theStage);
            if (testFile == null) {
                ANNViewUtility.showInputAlert("Error", "Error openning file");
            }
            else {
                result = ANNUtilityGUICompatible.getData(testFile);
                //if empty getData should have already notified the user
                if (result.length > 0) {
                    this.theData = result;
                }
            }
            this.theConfig.setProgramMode(ProgramMode.CLASSIFICATION);
        });

        configMI.setOnAction((event) -> {
            getConfigInfo();
            System.out.println("configMI was selected.");
        });

        exitMI.setOnAction((event) -> {
            System.out.println("exitMI was selected.");
            System.exit(0);
        });
    }

    // TODO remove commented code "updateBottomGroup" below?
    /**
     * private void updateBottomGroup(Group displayGroup) { if
     * (!bottomGroup.getChildren().contains(displayGroup)) {
     * bottomGroup.getChildren().clear();
     * bottomGroup.getChildren().add(displayGroup); System.out.println("changed
     * bottomGroup"); } }
     */
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
        TextField numInputsTextField = new TextField();
        GridPane.setHalignment(numInputsLabel, HPos.RIGHT);
        configGridPane.add(numInputsLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(numInputsTextField, INPUTS_COLUMN_INDEX, rowIndex++);
        //TODO define the default values somewhere else
        Label numOutLabel = new Label(
                "Enter the number of output neurons [default 1]");
        TextField numOutTextField = new TextField();
        GridPane.setHalignment(numOutLabel, HPos.RIGHT);
        configGridPane.add(numOutLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(numOutTextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Label numHiddenLabel = new Label(
                "Enter the number hidden neurons [default 3]");
        TextField numHiddenTextField = new TextField();
        GridPane.setHalignment(numHiddenLabel, HPos.RIGHT);
        configGridPane.add(numHiddenLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(numHiddenTextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Label maxSSELabel = new Label(
                "Enter the maximum SSE desired  [default 0.1]");
        TextField maxSSETextField = new TextField();
        GridPane.setHalignment(maxSSELabel, HPos.RIGHT);
        configGridPane.add(maxSSELabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(maxSSETextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Label maxEpochLabel = new Label(
                "Enter the maximum number of Epochs [default 50000]");
        TextField maxEpochTextField = new TextField();
        GridPane.setHalignment(maxEpochLabel, HPos.RIGHT);
        configGridPane.add(maxEpochLabel, LABEL_COLUMN_INDEX, rowIndex);
        configGridPane.add(maxEpochTextField, INPUTS_COLUMN_INDEX, rowIndex++);

        Button cancelButton = new Button("Cancel");
        GridPane.setHalignment(cancelButton, HPos.CENTER);
        configGridPane.add(cancelButton, LABEL_COLUMN_INDEX, rowIndex);

        Button submitButton = new Button("Submit");
        GridPane.setHalignment(submitButton, HPos.CENTER);
        configGridPane.add(submitButton, INPUTS_COLUMN_INDEX, rowIndex++);
        this.configGroup.getChildren().add(configGridPane);

        submitButton.setOnAction((event) -> {
            int numInputs = ANNViewUtility.extractPositiveIntFromText(
                    numInputsTextField.getText(),
                    2,
                    "the number of inputs must be a positive integer.");
            if (numInputs == -1) {
                return;
            }
            int numHiddenNodes = ANNViewUtility.extractPositiveIntFromText(
                    numHiddenTextField.getText(),
                    3, "the number of hidden nodes must be a positive integer.");
            if (numHiddenNodes == -1) {
                return;
            }
            int numOutputs = ANNViewUtility.extractPositiveIntFromText(
                    numOutTextField.getText(),
                    1,
                    "the number of output nodes must be a positive integer.");
            if (numOutputs == -1) {
                return;
            }
            int maxEpochs = ANNViewUtility.extractPositiveIntFromText(
                    maxEpochTextField.getText(),
                    50000,
                    "the maximun number of epochs be a positive integer.");
            if (maxEpochs == -1) {
                return;
            }

            // TODO extract this chunk of code to a new method
            double maxSSE = -1;
            boolean formatOK = true;
            String sSEString = maxSSETextField.getText();
            if (sSEString == null || sSEString.isEmpty()) {
                maxSSE = 0.1;
            }
            else {
                try {
                    maxSSE = Double.parseDouble(sSEString);
                } catch (NumberFormatException e) {
                    formatOK = false;
                }
            }
            if (!formatOK || maxSSE <= 0) {
                ANNViewUtility.showInputAlert("SSE must be a positive number",
                                              (sSEString + " cannot be converted to a positive number."));
                return;
            }

            ArrayList<ArrayList<Double>> weights = ANNUtility.getRandomWeights(
                    numInputs,
                    numOutputs,
                    1,
                    numHiddenNodes);
            ArrayList<ArrayList<Double>> thetas = ANNUtilityGUICompatible.getListOfThetas(
                    numOutputs, 1,
                    numHiddenNodes);

            //TODO fix so that TRAINING mode isn't hardcoded below
            //TODO add to config file?  if so does comand line need to be fixed?
            this.theConfig = new ANNConfig(numInputs, numOutputs, 1,
                                           numHiddenNodes,
                                           maxSSE, maxEpochs,
                                           weights, thetas,
                                           ProgramMode.TRAINING);

            System.out.println("Submit was selected.");
            this.configGroup.getChildren().clear();
        }
        );

        cancelButton.setOnAction((event) -> {
            System.out.println("Cancel was selected.");
            this.configGroup.getChildren().clear();
        }
        );

    }

<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> 4f0b3402e5668b970df6f0cd15a1f7e8eeae3365
}
