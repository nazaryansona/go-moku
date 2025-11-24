package org.example;

/**
 * Factory pattern for creating player objects.
 */
public class PlayerFactory {
    //TODO create separate methods for player creation based on type
    public static Player createPlayer(String type, String name, char symbol) {
        if (type.equalsIgnoreCase("AI")) {
            return new AIPlayer(name, symbol);
        } else {
            return new Player(name, symbol);
        }
    }
}
