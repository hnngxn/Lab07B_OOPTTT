import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TTTFrame.java
 * The main Swing GUI window for Tic Tac Toe.
 * Responsibilities: build and display the board grid, handle user interaction,
 *                   update status label, provide New Game button.
 * Collaborators: TTTGame, TTTBoard, TTTTileButton
 * Lab 07B - OOP Tic Tac Toe
 */
public class TTTFrame extends JFrame {

    private TTTGame game;
    private TTTTileButton[][] tiles;
    private JLabel statusLabel;

    /**
     * Constructs and displays the Tic Tac Toe window.
     */
    public TTTFrame() {
        game = new TTTGame("Player 1", "Player 2");
        tiles = new TTTTileButton[TTTBoard.SIZE][TTTBoard.SIZE];

        setTitle("OOP Tic Tac Toe - Lab 07B");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        // --- Status Label (North) ---
        statusLabel = new JLabel(game.getStatusMessage(), SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        add(statusLabel, BorderLayout.NORTH);

        // --- Board Grid (Center) ---
        JPanel boardPanel = new JPanel(new GridLayout(TTTBoard.SIZE, TTTBoard.SIZE, 4, 4));
        boardPanel.setBackground(Color.DARK_GRAY);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        for (int r = 0; r < TTTBoard.SIZE; r++) {
            for (int c = 0; c < TTTBoard.SIZE; c++) {
                TTTTileButton tile = new TTTTileButton(r, c);
                tile.setFont(new Font("SansSerif", Font.BOLD, 48));
                tile.setPreferredSize(new Dimension(120, 120));
                tiles[r][c] = tile;
                tile.addActionListener(new TileClickListener());
                boardPanel.add(tile);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // --- New Game Button (South) ---
        JButton newGameBtn = new JButton("New Game");
        newGameBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        newGameBtn.setFocusPainted(false);
        newGameBtn.addActionListener(e -> resetGame());
        JPanel southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        southPanel.add(newGameBtn);
        add(southPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * ActionListener for TTTTileButton clicks.
     */
    private class TileClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TTTTileButton clicked = (TTTTileButton) e.getSource();
            int row = clicked.getRow();
            int col = clicked.getCol();

            boolean moved = game.makeMove(row, col);
            if (moved) {
                // Update tile display directly (TTTTileButton stores row/col only)
                char symbol = game.getBoard().getCell(row, col);
                clicked.setText(String.valueOf(symbol));
                clicked.setForeground(symbol == 'X' ? new Color(0, 102, 204) : new Color(204, 0, 0));
                clicked.setEnabled(false);

                statusLabel.setText(game.getStatusMessage());

                if (game.isGameOver()) {
                    disableAllTiles();
                    JOptionPane.showMessageDialog(TTTFrame.this, game.getStatusMessage());
                }
            }
        }
    }

    /** Disables all tiles when the game ends. */
    private void disableAllTiles() {
        for (TTTTileButton[] row : tiles)
            for (TTTTileButton tile : row)
                tile.setEnabled(false);
    }

    /** Resets the game and all tile buttons for a new round. */
    private void resetGame() {
        game.reset();
        for (TTTTileButton[] row : tiles) {
            for (TTTTileButton tile : row) {
                tile.setText("");
                tile.setEnabled(true);
                tile.setForeground(Color.BLACK);
            }
        }
        statusLabel.setText(game.getStatusMessage());
    }

    /** Entry point. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TTTFrame::new);
    }
}