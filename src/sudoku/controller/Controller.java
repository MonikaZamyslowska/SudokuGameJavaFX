package sudoku.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sudoku.model.SudokuBoard;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private SudokuBoard sudokuBoard;
    private int selectedRow;
    private int selectedCol;
    private int level;
    private boolean isSolveButtonPressed;
    int[][] initialBoard;
    int[][] playerBoard;

    @FXML private RadioButton radioButtonEasy;
    @FXML private RadioButton radioButtonMedium;
    @FXML private RadioButton radioButtonHard;
    @FXML private ToggleGroup levelToggleGroup;
    @FXML private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reloadApp();
    }

    @FXML
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
        reloadApp();
        initialBoard = sudokuBoard.generateBoard(level);
        sudokuBoard.deepCopy(initialBoard);
        playerBoard = sudokuBoard.getPlayerBoard();
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonSolveEvent(ActionEvent event) {
        isSolveButtonPressed = true;
        playerBoard = new int[9][9];
        initialBoard = sudokuBoard.generateSolvingBoard(sudokuBoard.getCopyBoard());
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonOneAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(1, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonTwoAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(2, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonThreeAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(3, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonFourAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(4, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonFiveAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(5, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonSixAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(6, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonSevenAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(7, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonEightAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(8, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void handleButtonNineAction(ActionEvent event) {
        sudokuBoard.modifyUsersArea(9, selectedRow, selectedCol);
        drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
    }

    @FXML
    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int mouse_x = (int) event.getX();
                int mouse_y = (int) event.getY();

                selectedCol = mouse_x/50;
                selectedRow = mouse_y/50;

                drawSudokuGridOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }


    public void drawSudokuGridOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 450, 450);
        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int position_y = row * 50 + 2;
                int position_x = col * 50 + 2;
                int width = 46;
                context.setFill(Color.LAVENDER);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }

        context.setStroke(Color.BLUEVIOLET);
        context.setLineWidth(5);
        context.strokeRoundRect(selectedCol * 50 + 2, selectedRow * 50 + 2, 46, 46, 10, 10);

        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int actualNumber = initialBoard[row][col];
                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if (actualNumber != 0) {
                    context.fillText(actualNumber + "", position_x, position_y);
                }
            }
        }

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                int actualNumber = playerBoard[row][col];
                int position_y = row * 50 + 30;
                int position_x = col * 50 + 20;
                context.setFill(Color.DARKMAGENTA);
                context.setFont(new Font(20));
                if(actualNumber != 0) {
                    context.fillText(actualNumber + "", position_x, position_y);
                }
            }
        }

        if(sudokuBoard.checkSuccsess()) {
            if (!isSolveButtonPressed) {
                context.clearRect(0, 0, 450, 450);
                context.setFill(Color.BLUE);
                context.setFont(new Font(50));
                context.fillText("Gratulation!!! \n Sudoku was solved!!!", 150, 250);
            }
        }
    }

    public void reloadApp() {
        sudokuBoard = new SudokuBoard();
        playerBoard = new int[9][9];
        initialBoard = new int[9][9];
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawSudokuGridOnCanvas(context);
        selectedCol = 0;
        selectedRow = 0;
        isSolveButtonPressed = false;
    }
}
