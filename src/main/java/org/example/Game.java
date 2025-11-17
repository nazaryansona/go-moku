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
    private final Player human;
    private final AIPlayer ai;
    private final Logger logger;

    public Game(int rows, int cols, Player human, AIPlayer ai) throws Exception {
        this.board = new Board(rows, cols);
        this.human = human;
        this.ai = ai;

        logger = Logger.getLogger("GameLogger");
        FileHandler fh = new FileHandler("gomoku.log", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        Player current = human;

        while (true) {
            board.printBoard();

            int r, c;
            if (current instanceof AIPlayer) {
                int[] move = ((AIPlayer) current).getMove(board);
                r = move[0];
                c = move[1];
                System.out.println(current.getName() + " plays at (" + r + ", " + c + ")");
            } else {
                System.out.println(current.getName() + "'s turn (" + current.getSymbol() + ")");
                System.out.print("Enter row: ");
                r = sc.nextInt();
                System.out.print("Enter column: ");
                c = sc.nextInt();
            }

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

            current = (current == human) ? ai : human;
        }

        sc.close();
    }
}
