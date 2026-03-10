import javax.swing.JButton;

/**
 * TTTTileButton.java
 * Subclass of JButton that holds the row/col position of a Tic Tac Toe tile.
 * Based on TicTacToeTile.java from Assignment 01.
 * Lab 07B - OOP Tic Tac Toe
 */
public class TTTTileButton extends JButton
{
    private int row;
    private int col;

    public TTTTileButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}