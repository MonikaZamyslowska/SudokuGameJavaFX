package sudoku.model;

public class SudokuBoard {
    private SudokuSolver sudokuSolver;
    private int[][] playerBoard;
    private int[][] board;
    private int[][] copyBoard;

    int[][] easyBoard = {
            {0, 2, 0, 4, 5, 6, 7, 8, 9},
            {4, 5, 7, 0, 8, 0, 2, 3, 6},
            {6, 8, 9, 2, 3, 7, 0, 4, 0},
            {0, 0, 5, 3, 6, 2, 9, 7, 4},
            {2, 7, 4, 0, 9, 0, 6, 5, 9},
            {3, 9, 6, 5, 7, 4, 8, 0, 0},
            {0, 4, 0, 6, 1, 8, 3, 9, 7},
            {7, 6, 1, 0, 4, 0, 5, 2, 8},
            {9, 3, 8, 7, 2, 5, 0, 6, 0}
    };

    int[][] mediumBoard = {
            { 0, 9, 3, 1, 0, 5, 6, 4, 0 },
            { 7, 0, 0, 0, 0, 0, 0, 0, 5 },
            { 5, 0, 1, 2, 0, 9, 3, 0, 7 },
            { 2, 0, 0, 0, 0, 0, 0, 0, 3 },
            { 0, 3, 6, 9, 0, 7, 5, 2, 0 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 3, 0, 2, 4, 0, 8, 1, 0, 9 },
            { 6, 0, 0, 0, 0, 0, 0, 0, 4 },
            { 0, 4, 7, 3, 0, 2, 8, 5, 0 }
    };

    int[][] hardBoard = {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
    };

    public SudokuBoard() {
        playerBoard = new int[9][9];
        board = new int[9][9];
    }

    public int[][] generateBoard(int level) {
        if (level == 0) {
            board = easyBoard;
        }
        if (level == 1) {
            board = mediumBoard;
        }
        if (level == 2) {
            board = hardBoard;
        }
        return board;
    }

    public void deepCopy(int[][] board) {
        copyBoard = new int[board.length][board.length];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }
    }

    public int[][] generateSolvingBoard(int [][] boardToSolve) {
        if (sudokuSolver.solve(boardToSolve)) {
            playerBoard = boardToSolve;
        }
        return playerBoard;
    }

    public void modifyUsersArea(int value, int row, int col) {
        if (board[row][col] == 0) {
            playerBoard[row][col] = value;
        }
    }

    public boolean checkSuccsess() {
        sudokuSolver = new SudokuSolver();

        int[][] combined = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != 0) {
                    combined[row][col] = board[row][col];
                }
                else {
                    combined[row][col] = playerBoard[row][col];
                }
            }
        }

        for(int row = 0; row < 9; row++) {
            int sum = 0;
            for(int col = 0; col<9; col++) {
                sum += combined[row][col];
            }
            if(sum != 45) {
                return false;
            }
        }

        for(int col = 0; col < 9; col++) {
            int sum = 0;
            for(int row = 0; row<9; row++) {
                sum += combined[row][col];
            }
            if(sum != 45) {
                return false;
            }
        }

        for (int row_offset = 0; row_offset < 9; row_offset+=3) {
            for(int col_offset = 0; col_offset < 9; col_offset+=3) {
                int sum = 0;
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        sum += combined[row + row_offset][col + col_offset];
                    }
                }
                if(sum != 45) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public int[][] getCopyBoard() {
        return copyBoard;
    }
}
