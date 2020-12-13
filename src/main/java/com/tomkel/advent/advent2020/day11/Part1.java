package com.tomkel.advent.advent2020.day11;

import com.tomkel.advent.advent2020.FileHelpers;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/11">Puzzle 11.1</a>
     */
    public static void main(String[] args) throws Exception {

        String[] lines = FileHelpers.getLines("day11/input.txt").toArray(new String[0]);
        Room room = new Room(lines);

        boolean changed = true;
        int iterations = 0;
        while (changed) {
            iterations++;
            changed = room.advance();
        }

        System.out.printf("System settled after %s iterations. There are now %s occupied seats.%n", iterations, room.numOccupiedSeats());
    }

}
