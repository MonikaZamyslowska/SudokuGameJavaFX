package sudoku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sudoku.model.SudokuBoard;

public class Controller {
    public static String EASY = "easy";
    public static String MEDIUM = "medium";
    public static String HARD = "hard";

    private SudokuBoard sudokuBoard = new SudokuBoard();
    private int selectedRow;
    private int selectedCol;
    private int[][] board;
    private int level = 0;

    @FXML private RadioButton radioButtonEasy;
    @FXML private RadioButton radioButtonMedium;
    @FXML private RadioButton radioButtonHard;
    @FXML private ToggleGroup levelToggleGroup;
    @FXML private Button buttonOne;
    @FXML private Button buttonTwo;
    @FXML private Button buttonThree;
    @FXML private Button buttonFour;
    @FXML private Button buttonFive;
    @FXML private Button buttonSix;
    @FXML private Button buttonSeven;
    @FXML private Button buttonEight;
    @FXML private Button buttonNine;
    @FXML private Canvas canvas;

    public void radioButtonChanged() {
        if (this.levelToggleGroup.getSelectedToggle().equals(radioButtonEasy)) {
            level = 0;
        }

        if (this.levelToggleGroup.getSelectedToggle().equals(radioButtonMedium)) {
            level = 1;
        }

        if (this.levelToggleGroup.getSelectedToggle().equals(radioButtonHard)) {
            level = 2;
        }
    }

    @FXML
    public void handleButtonStartAction(ActionEvent event) {
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonRestartAction(ActionEvent event) {

    }

    @FXML
    public void handleButtonSolveEvent(ActionEvent event) {

    }

    public void drawSudokuGridOnCanvas(GraphicsContext context) {

        context.clearRect(0, 0, 450, 450);
        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;
                context.setFill(Color.WHITE);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }

        context.setStroke(Color.BLUEVIOLET);
        context.setLineWidth(5);
        context.strokeRoundRect(selectedCol * 50 + 2, selectedRow * 50 + 2, 46, 46, 10, 10);

        int[][] initial = sudokuBoard.getBoard();
        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int actualNumber = initial[row][col];
                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if (actualNumber != 0) {
                    context.fillText(actualNumber + "", position_x, position_y);
                }
            }
        }

        int[][] player = sudokuBoard.getPlayerBoard();
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                int actualNumber = player[row][col];
                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;
                context.setFill(Color.BLUE);
                context.setFont(new Font(22));
                if(actualNumber != 0) {
                    context.fillText(actualNumber + "", position_x, position_y);
                }
            }
        }

        if(sudokuBoard.checkForSuccessGeneral() == true) {
            context.clearRect(0, 0, 450, 450);
            context.setFill(Color.GREEN);
            context.setFont(new Font(36));
            context.fillText("SUCCESS!", 150, 250);
        }
    }
}
