package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Controller {
    public static String EASY = "easy";
    public static String MEDIUM = "medium";
    public static String HARD = "hard";

    private int level;

    @FXML
    private RadioButton radioButtonEasy;

    @FXML
    private RadioButton radioButtonMedium;

    @FXML
    private RadioButton radioButtonHard;

    @FXML
    private ToggleGroup levelToggleGroup;

    @FXML
    private Label label;

    public void radioButtonChanged() {
        if (this.levelToggleGroup.getSelectedToggle().equals(radioButtonEasy)) {
            level = 0;
            label.setText(EASY);
        }

        if (this.levelToggleGroup.getSelectedToggle().equals(radioButtonMedium)) {
            level = 1;
            label.setText(MEDIUM);
        }

        if (this.levelToggleGroup.getSelectedToggle().equals(radioButtonHard)) {
            level = 2;
            label.setText(HARD);
        }
    }

    @FXML
    public void handleButtonStartAction(ActionEvent event) {
        System.out.println("Klik");
    }

    @FXML
    public void handleButtonRestartAction(ActionEvent event) {

    }

    @FXML
    public void handleButtonSolveEvent(ActionEvent event) {

    }
}
