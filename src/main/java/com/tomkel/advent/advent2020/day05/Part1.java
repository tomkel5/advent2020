package com.tomkel.advent.advent2020.day05;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.Comparator;
import java.util.Optional;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/5">Puzzle 5.1</a>
     */
    public static void main(String[] args) throws Exception {

        // Find the boarding pass with the highest ID
        Optional<BoardingPass> pass = FileHelpers.getLines("day05/input.txt")
                .stream()
                .map(BoardingPass::new)
                .max(Comparator.comparingInt(BoardingPass::getSeatId));

        if (pass.isPresent()) {
            System.out.printf("Highest ID is %s%n", pass.get().getSeatId());
        } else {
            System.out.println("Couldn't find the highest ID");
        }
    }
}
