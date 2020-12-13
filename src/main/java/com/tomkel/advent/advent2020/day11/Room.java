package com.tomkel.advent.advent2020.day11;

import java.util.Arrays;

class Room {

    private final Space[][] spaces;

    public Room(String[] lines) {
        spaces = new Space[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (int j = 0; j < line.length(); j++) {
                Type type = Type.fromCharacter(line.charAt(j));
                spaces[i][j] = new Space(type);
            }
        }
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
                // If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat
                // becomes empty.
                if (space.current.equals(Type.OCCUPIED_SEAT) && getAdjacentOccupants(i, j) >= 4) {
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

    /**
     * Get the number of directly adjacent occupants (horizontally, vertically, and diagonally).
     */
    private int getAdjacentOccupants(int row, int col) {
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
}
