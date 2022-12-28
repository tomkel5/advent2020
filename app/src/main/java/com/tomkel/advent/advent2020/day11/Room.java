package com.tomkel.advent.advent2020.day11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Room {

    private final Space[][] spaces;
    private final NeighborStrategy strategy;
    private final int neighborTolerance;

    public Room(String[] lines, NeighborStrategy strategy, int neighborTolerance) {
        this.strategy = strategy;
        this.neighborTolerance = neighborTolerance;
        spaces = new Space[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                Type type = Type.fromCharacter(line.charAt(j));
                spaces[i][j] = new Space(type);
            }
        }
    }

    enum NeighborStrategy {
        DIRECT_NEIGHBOR, LINE_OF_SIGHT
    }

    enum Type {
        EMPTY_SPACE('.'), EMPTY_SEAT('L'), OCCUPIED_SEAT('#');

        private final char character;

        Type(char character) {
            this.character = character;
        }

        public static Type fromCharacter(char c) {
            return Arrays.stream(values())
                    .filter(t -> t.character == c)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }
    }

    static class Space {

        private Type current;
        private Type next;

        public Space(Type current) {
            this.current = current;
            // Assume no changes, until we know otherwise.
            this.next = current;
        }

        public void advance() {
            current = next;
        }
    }

    /**
     * Count the total number of occupied seats in the current iteration.
     */
    public int numOccupiedSeats() {
        int count = 0;
        for (Space[] row : spaces) {
            for (Space space : row) {
                if (space.current.equals(Type.OCCUPIED_SEAT)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Go through all spaces, and update their "next" values.
     */
    public void prepareIteration() {
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[0].length; j++) {
                Space space = spaces[i][j];
                // Rule:
                // If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
                if (space.current.equals(Type.EMPTY_SEAT) && getAdjacentOccupants(i, j) == 0) {
                    space.next = Type.OCCUPIED_SEAT;
                    continue;
                }

                // Rule:
                // If a seat is occupied (#) and too many seats adjacent to it are also occupied (based on the
                // tolerance), then the seat becomes empty.
                if (space.current.equals(Type.OCCUPIED_SEAT) && getAdjacentOccupants(i, j) >= neighborTolerance) {
                    space.next = Type.EMPTY_SEAT;
                }
            }
        }
    }

    /**
     * Move the room to the next iteration.
     *
     * @return If anything changed, return true.
     */
    public boolean advance() {
        boolean changed = false;

        // Start out by updating each space's "next" value
        prepareIteration();

        // Go through all the spaces, and see if any have changed.
        loop:
        for (Space[] row : spaces) {
            for (Space space : row) {
                if (space.current != space.next) {
                    changed = true;
                    break loop;
                }
            }
        }

        // If no space has changed, then the room has reached equilibrium.
        if (!changed) {
            return false;
        }

        // Otherwise, advance all the spaces at once.
        for (Space[] row : spaces) {
            for (Space space : row) {
                space.advance();
            }
        }
        return true;
    }

    public int getAdjacentOccupants(int row, int col) {
        if (strategy.equals(NeighborStrategy.DIRECT_NEIGHBOR)) {
            return getAdjacentOccupantsDirectNeighborStrategy(row, col);
        } else if (strategy.equals(NeighborStrategy.LINE_OF_SIGHT)) {
            return getAdjacentOccupantsLineOfSightStrategy(row, col);
        }

        throw new RuntimeException("Not a valid neighbor strategy");
    }

    /**
     * Get the number of directly adjacent occupants (horizontally, vertically, and diagonally).
     */
    private int getAdjacentOccupantsDirectNeighborStrategy(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (i < 0 || i >= spaces.length) {
                    continue;
                }
                if (j < 0 || j >= spaces[0].length) {
                    continue;
                }
                if (spaces[i][j].current.equals(Type.OCCUPIED_SEAT)) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Get the number of seats in the line of sight that are occupied (only the first chair seen counts).
     */
    private int getAdjacentOccupantsLineOfSightStrategy(int row, int col) {

        // Treat these as vectors that go in every direction. To determine adjacent seats, we'll start in our own
        // seat, and then move along these vectors until we find a seat, empty or not.
        List<Point> directions = new ArrayList<Point>() {{
            add(new Point(-1, -1));
            add(new Point(-1, 0));
            add(new Point(-1, 1));
            add(new Point(0, -1));
            add(new Point(0, 1));
            add(new Point(1, -1));
            add(new Point(1, 0));
            add(new Point(1, 1));
        }};

        int count = 0;

        for (Point direction : directions) {

            int x = row;
            int y = col;

            while (true) {
                x += direction.x;
                y += direction.y;

                if (!(x >= 0 && x < spaces.length && y >= 0 && y < spaces[0].length)) {
                    break;
                }

                if (spaces[x][y].current.equals(Type.OCCUPIED_SEAT)) {
                    count++;
                    break;
                } else if (spaces[x][y].current.equals(Type.EMPTY_SEAT)) {
                    break;
                }
            }
        }

        return count;
    }
}
