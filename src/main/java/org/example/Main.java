package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Player human = PlayerFactory.createPlayer("You", SymbolEnum.X);
            Player ai = PlayerFactory.createAIPlayer("Computer", SymbolEnum.O);

            Game game = new Game(15, 15, human, ai);
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
