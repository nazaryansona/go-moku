package org.example;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main gameplay logic between the human and AI.
 */
public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Logger logger;

    public Game(int rows, int cols, Player player1, Player player2) throws Exception {
        this.board = new Board(rows, cols);
        this.player1 = player1;
        this.player2 = player2;

        logger = Logger.getLogger("GameLogger");
        FileHandler fh = new FileHandler("gomoku.log", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        Player current = player1;

        while (true) {
            board.printBoard();

            int r, c;
            //TODO remove Player getMove logic and just call the method
            int[] move = current.getMove(board);
            r = move[0];
            c = move[1];
            System.out.println(current.getName() + " plays at (" + r + ", " + c + ")");


            if (!board.placeMove(r, c, current.getSymbol())) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            logger.info(current.getName() + " placed at (" + r + ", " + c + ")");

            if (board.checkWin(r, c, current.getSymbol())) {
                board.printBoard();
                System.out.println("🎉 " + current.getName() + " wins!");
                logger.info(current.getName() + " wins!");
                DatabaseManager.getInstance().saveResult(current.getName(), 15, 15);
                break;
            }

            current = (current == player1) ? player2 : player1;
        }

        sc.close();
    }
}
