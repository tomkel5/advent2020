package com.tomkel.advent.advent2020.day03;

public class Board {

    private final boolean[][] grid;
    private int currentRow;
    private int currentColumn;

    public Board(String[] lines) {
        int numRows = lines.length;
        int numColumns = lines[0].length();

        grid = new boolean[numRows][numColumns];

        for (int row = 0; row < numRows; row++) {
            String line = lines[row];
            for (int column = 0; column < numColumns; column++) {
                char character = line.charAt(column);
                grid[row][column] = (character == '#');
            }
        }

        // Start the marker off at [0, 0]
        currentRow = 0;
        currentColumn = 0;
    }

    public void reset() {
        currentRow = 0;
        currentColumn = 0;
    }

    /**
     * Move the marker left/right and up/down. If it would go above or below the map, return false.
     */
    public boolean move(int horizontal, int vertical) {
        int nextRow = currentRow + vertical;
        int nextColumn = currentColumn + horizontal;

        if ((nextRow >= grid.length) || (nextRow < 0)) {
            return false;
        }

        currentRow = nextRow;
        currentColumn = nextColumn;
        if (currentColumn >= grid[0].length) {
            currentColumn -= grid[0].length;
        }

        return true;
    }

    /**
     * Is the current position on a tree?
     */
    public boolean isTree() {
        return grid[currentRow][currentColumn];
    }

    /**
     * Display the grid with the following indicators:
     *    .  Empty land
     *    #  Tree
     *    O  Current marker, on empty land
     *    X  Current marker, on a tree
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(grid.length * (grid[0].length + 1));

        int numRows = grid.length;
        int numColumns = grid[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                builder.append(
                        (row == currentRow && column == currentColumn)
                                ? (this.grid[row][column] ? "X" : "O")
                                : (this.grid[row][column] ? "#" : ".")
                );
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
