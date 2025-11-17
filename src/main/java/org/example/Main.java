package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            Player human = PlayerFactory.createPlayer("human", "You", 'X');
            Player ai = PlayerFactory.createPlayer("AI", "Computer", 'O');

            Game game = new Game(15, 15, human, (AIPlayer) ai);
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
