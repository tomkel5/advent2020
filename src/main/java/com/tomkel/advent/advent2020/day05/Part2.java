package com.tomkel.advent.advent2020.day05;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/5#part2">Puzzle 5.2</a>
     */
    public static void main(String[] args) throws Exception {

        // Generate a list of boarding passes, in order of increasing seat ID
        List<BoardingPass> passes = FileHelpers.getLines("day05/input.txt")
                .stream()
                .map(BoardingPass::new)
                .sorted(Comparator.comparingInt(BoardingPass::getSeatId))
                .collect(Collectors.toList());

        int currentSeat = 0;
        for (BoardingPass pass : passes) {
            // Since the two adjacent seats are there, any difference of 2 will surround the seat we're looking for.
            if (pass.getSeatId() - 2 == currentSeat) {
                System.out.printf("Found your seat: %s%n", pass.getSeatId() - 1);
                return;
            } else {
                // If it wasn't found, advance the current seat to the one we just checked.
                currentSeat = pass.getSeatId();
            }
        }

        System.out.println("Could not find your seat.");
    }
}
