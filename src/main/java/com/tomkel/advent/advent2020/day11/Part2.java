package com.tomkel.advent.advent2020.day11;

import com.tomkel.advent.advent2020.FileHelpers;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/11#part2">Puzzle 11.2</a>
     */
    public static void main(String[] args) throws Exception {

        String[] lines = FileHelpers.getLines("day11/input.txt").toArray(new String[0]);
        Room room = new Room(lines, Room.NeighborStrategy.LINE_OF_SIGHT, 5);

        boolean changed = true;
        int iterations = 0;
        while (changed) {

            iterations++;
            changed = room.advance();
        }

        System.out.printf("System settled after %s iterations. There are now %s occupied seats.%n", iterations, room.numOccupiedSeats());
    }

}
