package org.example;

/**
 * Represents the Go-Moku board and handles moves and win checking.
 */
public class Board {
    private final int rows;
    private final int cols;
    private final char[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new char[rows][cols];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = '.';
    }

    public void printBoard() {
        System.out.print("   ");
        for (int c = 0; c < cols; c++)
            System.out.printf("%2d ", c);
        System.out.println();
        for (int r = 0; r < rows; r++) {
            System.out.printf("%2d ", r);
            for (int c = 0; c < cols; c++)
                System.out.print(" " + grid[r][c] + " ");
            System.out.println();
        }
    }

    /**
     * Places a move on the board.
     * @return true if placed successfully, false otherwise.
     */
    public boolean placeMove(int row, int col, char symbol) {
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != '.')
            return false;
        grid[row][col] = symbol;
        return true;
    }

    public boolean isEmptyCell(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) return false;
        return grid[row][col] == '.';
    }

    public boolean checkWin(int row, int col, char symbol) {
        return checkDirection(row, col, symbol, 1, 0) ||
                checkDirection(row, col, symbol, 0, 1) ||
                checkDirection(row, col, symbol, 1, 1) ||
                checkDirection(row, col, symbol, 1, -1);
    }

    private boolean checkDirection(int row, int col, char symbol, int dr, int dc) {
        int count = 1;
        count += countInDirection(row, col, symbol, dr, dc);
        count += countInDirection(row, col, symbol, -dr, -dc);
        return count >= 5;
    }

    private int countInDirection(int row, int col, char symbol, int dr, int dc) {
        int count = 0;
        int r = row + dr;
        int c = col + dc;
        while (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == symbol) {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public char getCell(int r, int c) { return grid[r][c]; }

    /**
     * Simulate whether placing 'symbol' at (row,col) would produce a win.
     * Does not modify the board.
     */
    public boolean wouldWin(int row, int col, char symbol) {
        if (!isEmptyCell(row, col)) return false;
        if (wouldWinDirection(row, col, symbol, 1, 0)) return true;
        if (wouldWinDirection(row, col, symbol, 0, 1)) return true;
        if (wouldWinDirection(row, col, symbol, 1, 1)) return true;
        if (wouldWinDirection(row, col, symbol, 1, -1)) return true;
        return false;
    }

    private boolean wouldWinDirection(int row, int col, char symbol, int dr, int dc) {
        int count = 1; // the simulated piece at (row,col)
        count += countInDirection(row, col, symbol, dr, dc);
        count += countInDirection(row, col, symbol, -dr, -dc);
        return count >= 5;
    }
}
