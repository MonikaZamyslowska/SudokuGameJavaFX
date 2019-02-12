package sudoku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import sudoku.model.SudokuGenerator;

public class Controller {
    public static String EASY = "easy";
    public static String MEDIUM = "medium";
    public static String HARD = "hard";

    private SudokuGenerator sudokuGenerator = new SudokuGenerator();
    private int[][] board;
    private int level = 0;

    @FXML
    private GridPane sudokuGrid;

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
        board = sudokuGenerator.generateBoard(level);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = new TextField();
                int actualNumber = board[row][col];
                if (col % 3 == 0 && row % 3 == 0) {
                    textField.getStyleClass().add("subsection");
                }
                if (actualNumber == 0) {
                    sudokuGrid.add(textField, col, row, 1,1);
                } else {
                    textField.setText(String.valueOf(actualNumber));
                    textField.setEditable(false);
                    sudokuGrid.add(textField, col, row, 1,1);
                }
                textField.setAlignment(Pos.CENTER);
            }
        }
    }

    @FXML
    public void handleButtonRestartAction(ActionEvent event) {

    }

    @FXML
    public void handleButtonSolveEvent(ActionEvent event) {

    }
}
