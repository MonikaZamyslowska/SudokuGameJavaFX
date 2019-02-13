package sudoku.model;

public class SudokuBoard {
    private SudokuSolver sudokuSolver;
    private int[][] playerBoard;
    private int[][] board;

    public SudokuBoard() {
        playerBoard = new int[9][9];

        board = new int[][] {
                { 0, 2, 0, 4, 5, 6, 7, 8, 9 },
                { 4, 5, 7, 0, 8, 0, 2, 3, 6 },
                { 6, 8, 9, 2, 3, 7, 0, 4, 0 },
                { 0, 0, 5, 3, 6, 2, 9, 7, 4 },
                { 2, 7, 4, 0, 9, 0, 6, 5, 9 },
                { 3, 9, 6, 5, 7, 4, 8, 0, 0 },
                { 0, 4, 0, 6, 1, 8, 3, 9, 7 },
                { 7, 6, 1, 0, 4, 0, 5, 2, 8 },
                { 9, 3, 8, 7, 2, 5, 0, 6, 0 }
        };
    }

//    public int[][] generateBoard(int level) {
//        if (level == 1) generatedBoard = normalBoard;
//        if (level == 2) generatedBoard = hardBoard;
//        if (level == 3) generatedBoard = easyBoard;
//
//        return generatedBoard;
//    }
    public void modifyUsersArea(int value, int row, int col) {
        if (board[row][col] == 0) {
            playerBoard[row][col] = value;
        }
    }

    public boolean checkForSuccessGeneral() {
        sudokuSolver = new SudokuSolver();

        int[][] combined = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != 0) {
                    combined[row][col] = board[row][col];
                } else {
                    combined[row][col] = playerBoard[row][col];
                }
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int actualNumber = combined[row][col];
                if (!sudokuSolver.isValid(combined, row, col, actualNumber)) return false;
            }
        }
        return true;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public int[][] getBoard() {
        return board;
    }
}
