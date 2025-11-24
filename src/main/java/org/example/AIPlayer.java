package org.example;

import java.util.Random;

/**
 * AIPlayer level 2: tries to win, then block, then chooses a heuristic move (near existing stones).
 */
public class AIPlayer extends Player {
    private final Random random = new Random();

    public AIPlayer(String name, SymbolEnum symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getMove(Board board) {
        int rows = board.getRows();
        int cols = board.getCols();
        char my = getSymbol();
        char opp = (my == 'X') ? 'O' : 'X'; //TODO

        // 1) Try to win
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board.isEmptyCell(r, c) && board.wouldWin(r, c, my)) {
                    return new int[]{r, c};
                }
            }
        }

        // 2) Try to block opponent
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board.isEmptyCell(r, c) && board.wouldWin(r, c, opp)) {
                    return new int[]{r, c};
                }
            }
        }

        int bestR = -1, bestC = -1;
        int bestScore = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!board.isEmptyCell(r, c)) continue;
                int score = adjacencyScore(board, r, c, 2);
                if (score > bestScore) {
                    bestScore = score;
                    bestR = r; bestC = c;
                }
            }
        }

        if (bestR != -1) return new int[]{bestR, bestC};

        // fallback random
        int rr, cc;
        do {
            rr = random.nextInt(rows);
            cc = random.nextInt(cols);
        } while (!board.isEmptyCell(rr, cc));
        return new int[]{rr, cc};
    }

    // Simple heuristic: count non-empty neighbors in a radius; weighting closer cells more
    private int adjacencyScore(Board board, int row, int col, int radius) {
        int score = 0;
        for (int dr = -radius; dr <= radius; dr++) {
            for (int dc = -radius; dc <= radius; dc++) {
                if (dr == 0 && dc == 0) continue;
                int r = row + dr;
                int c = col + dc;
                if (r < 0 || c < 0 || r >= board.getRows() || c >= board.getCols()) continue;
                char cell = board.getCell(r, c);
                if (cell != '.') {
                    // nearer neighbors count more
                    int dist = Math.max(Math.abs(dr), Math.abs(dc));
                    score += (radius - dist + 1);
                }
            }
        }
        return score;
    }
}
