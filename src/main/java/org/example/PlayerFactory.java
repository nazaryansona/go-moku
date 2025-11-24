package org.example;

/**
 * Factory pattern for creating player objects.
 */
public class PlayerFactory {
    public static Player createPlayer(String name, SymbolEnum symbol) {
            return new Player(name, symbol);
    }
    public static AIPlayer createAIPlayer(String name, SymbolEnum symbol) {
        return new AIPlayer(name, symbol);
    }
}
