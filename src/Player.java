/**
 * Player.java
 * Represents a Tic Tac Toe player with a name and symbol (X or O).
 * Lab 07B - OOP Tic Tac Toe
 */
public class Player {
    private String name;
    private char symbol;

    /**
     * Constructs a Player with the given name and symbol.
     * @param name   the player's display name
     * @param symbol 'X' or 'O'
     */
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /** @return the player's name */
    public String getName() {
        return name;
    }

    /** @return the player's symbol ('X' or 'O') */
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}