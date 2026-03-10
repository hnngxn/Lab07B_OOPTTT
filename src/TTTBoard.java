/**
 * TTTBoard.java
 * Manages the 3x3 Tic Tac Toe grid state.
 * Responsibilities: store board state, check win/draw conditions, reset board.
 * Lab 07B - OOP Tic Tac Toe
 */
public class TTTBoard {

    public static final int SIZE = 3;
    private char[][] grid;

    /** Constructs an empty TTTBoard. Cells are initialized to ' '. */
    public TTTBoard() {
        grid = new char[SIZE][SIZE];
        reset();
    }

    /**
     * Resets all cells to ' ' (empty).
     */
    public void reset() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                grid[r][c] = ' ';
    }

    /**
     * Places a player's symbol at the given row and column.
     * @param row    0-2
     * @param col    0-2
     * @param symbol 'X' or 'O'
     * @return true if the move was valid and placed; false if the cell was occupied
     */
    public boolean placeSymbol(int row, int col, char symbol) {
        if (grid[row][col] != ' ') return false;
        grid[row][col] = symbol;
        return true;
    }

    /**
     * Returns the symbol at the given cell, or ' ' if empty.
     */
    public char getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Checks whether the given symbol has won the game.
     * @param symbol 'X' or 'O'
     * @return true if that symbol has three in a row
     */
    public boolean checkWin(char symbol) {
        // Rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) return true;
            if (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol) return true;
        }
        // Diagonals
        if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) return true;
        if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) return true;
        return false;
    }

    /**
     * Returns true if all cells are filled (draw condition).
     */
    public boolean isFull() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (grid[r][c] == ' ') return false;
        return true;
    }

    /**
     * Returns true if the game is over (win or draw).
     */
    public boolean isGameOver() {
        return checkWin('X') || checkWin('O') || isFull();
    }
}