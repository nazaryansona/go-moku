package org.example;

import java.util.Scanner;

/**
 * Represents a player in the game.
 */
public class Player {
    private final String name;
    private final char symbol;
    private final Scanner scanner = new Scanner(System.in);

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Player(String name, SymbolEnum symbolEnum) {
        this.name = name;
        this.symbol = (symbolEnum == SymbolEnum.X) ? 'X' : 'O';
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int[] getMove(Board board) {
        System.out.println(name + "'s turn (" + symbol + ")");
        System.out.print("Enter row: ");
        int r = scanner.nextInt();
        System.out.print("Enter column: ");
        int c = scanner.nextInt();
        return new int[]{r, c};
    }
}
