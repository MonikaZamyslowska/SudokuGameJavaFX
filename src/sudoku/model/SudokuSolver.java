package sudoku.model;

import java.util.Observable;

public class SudokuSolver extends Observable {
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 9;
    public static int EMPTY = 0;
    public static int SUBSECTION_SIZE = 3;
    public static int BOARD_START_INDEX = 0;
    public static int BOARD_SIZE = 9;



    public boolean solve(int[][] board) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int col = BOARD_START_INDEX; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int num = MIN_VALUE; num <= MAX_VALUE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int[][] board, int row, int col, int num) {
        return !(containsInRow(board, row, num) || containsInColumn(board, col, num) ||
                containsInSubsection(board, row, col, num));
    }

    public boolean containsInRow(int[][] board, int row, int num) {
        for (int r = BOARD_START_INDEX; r < BOARD_SIZE; r++) {
            if (board[row][r] == num) {
                return true;
            }
        }
        return false;
    }

    public boolean containsInColumn(int[][] board, int col, int num) {
        for (int c = BOARD_START_INDEX; c < BOARD_SIZE; c++) {
            if (board[c][col] == num) {
                return true;
            }
        }
        return false;
    }

    public boolean containsInSubsection(int[][] board, int row, int col, int num) {
        int contRowStart = (row / SUBSECTION_SIZE) * 3;
        int contRowEnd = contRowStart + SUBSECTION_SIZE;
        int contColStart = (col / SUBSECTION_SIZE) * 3;
        int contColEnd = contColStart + SUBSECTION_SIZE;

        for (int r = contRowStart; r < contRowEnd; r++) {
            for (int c = contColStart; c < contColEnd; c++) {
                if (board[r][c] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
