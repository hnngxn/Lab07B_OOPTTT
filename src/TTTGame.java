/**
 * TTTGame.java
 * Controls the flow of a Tic Tac Toe game.
 * Responsibilities: manage turns, process moves, determine game outcome.
 * Collaborators: TTTBoard, Player
 * Lab 07B - OOP Tic Tac Toe
 */
public class TTTGame {

    private TTTBoard board;
    private Player[] players;      // players[0] = X, players[1] = O
    private int currentPlayerIndex;
    private boolean gameOver;
    private String statusMessage;

    /**
     * Constructs a TTTGame with two named players.
     * @param player1Name name for X player
     * @param player2Name name for O player
     */
    public TTTGame(String player1Name, String player2Name) {
        board = new TTTBoard();
        players = new Player[]{
                new Player(player1Name, 'X'),
                new Player(player2Name, 'O')
        };
        currentPlayerIndex = 0;
        gameOver = false;
        statusMessage = players[0].getName() + "'s turn (X)";
    }

    /**
     * Processes a move at the given board position.
     * @param row 0-2
     * @param col 0-2
     * @return true if the move was accepted; false if invalid or game is over
     */
    public boolean makeMove(int row, int col) {
        if (gameOver) return false;

        Player current = getCurrentPlayer();
        boolean placed = board.placeSymbol(row, col, current.getSymbol());
        if (!placed) return false;

        if (board.checkWin(current.getSymbol())) {
            gameOver = true;
            statusMessage = current.getName() + " wins!";
        } else if (board.isFull()) {
            gameOver = true;
            statusMessage = "It's a draw!";
        } else {
            // Switch player
            currentPlayerIndex = 1 - currentPlayerIndex;
            Player next = getCurrentPlayer();
            statusMessage = next.getName() + "'s turn (" + next.getSymbol() + ")";
        }
        return true;
    }

    /** Resets the game for a new round. */
    public void reset() {
        board.reset();
        currentPlayerIndex = 0;
        gameOver = false;
        statusMessage = players[0].getName() + "'s turn (X)";
    }

    /** @return the Player whose turn it currently is */
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /** @return the TTTBoard for this game */
    public TTTBoard getBoard() { return board; }

    /** @return true if the game has ended (win or draw) */
    public boolean isGameOver() { return gameOver; }

    /** @return a human-readable status string describing the game state */
    public String getStatusMessage() { return statusMessage; }
}