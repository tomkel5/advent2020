package com.tomkel.advent.advent2020.day05;

public class BoardingPass {

    private final int row;
    private final int column;

    public BoardingPass(String line) {

        this.row = resolve(line.substring(0, 7), 'B');
        this.column = resolve(line.substring(7, 10), 'R');
    }

    private int resolve(String s, char topChar) {

        // String has depleted; no further recursion needed.
        if (s.length() == 0) {
            return 0;
        }

        int totalSpots = (int) Math.pow(2, s.length());
        String remaining = s.substring(1);

        if (s.charAt(0) == topChar) {
            // If this is the top half, then add half of the spots remaining to lift the range to the top half.
            return (totalSpots / 2) + resolve(remaining, topChar);
        } else {
            // Otherwise, adding zero spots will keep it in the bottom half.
            return resolve(remaining, topChar);
        }
    }

    public int getSeatId() {
        return (row * 8) + column;
    }
}
