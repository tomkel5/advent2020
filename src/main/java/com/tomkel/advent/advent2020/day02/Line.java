package com.tomkel.advent.advent2020.day02;

public class Line {
    private int lowerNum;
    private int higherNum;
    private char character;
    private String password;

    public static Line fromLine(String text) {
        Line line = new Line();

        String[] parts = text.split(" ");

        // Part 1: "1-4"
        String[] part1Parts = parts[0].split("-");
        line.lowerNum = Integer.parseInt(part1Parts[0]);
        line.higherNum = Integer.parseInt(part1Parts[1]);

        // Part 2: "w:"
        line.character = parts[1].charAt(0);

        // Part 3: "tuvwwwxyz"
        line.password = parts[2];

        return line;
    }

    /**
     * Determine if the password is valid according to the rules:
     *    The character must appear in the password at least lowerNum, but no more than higherNum.
     */
    public boolean isValidSledRental() {
        long numTimes = this.password.chars().filter(c -> c == this.character).count();

        return (numTimes >= lowerNum && numTimes <= higherNum);
    }

    /**
     * Determine if the password is valid according to the rules:
     *     The character must appear in the 1-based position of either lowerNum or higherNum, but not both.
     */
    public boolean isValidTobogganRental() {
        return password.charAt(lowerNum - 1) == character ^ password.charAt(higherNum - 1) == character;
    }
}
