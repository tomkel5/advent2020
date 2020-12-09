package com.tomkel.advent.advent2020.day03;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.Arrays;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/3">Puzzle 3.1</a>
     */
    public static void main(String[] args) throws Exception {

        String[] lines = FileHelpers.getLines("day03/input.txt").toArray(new String[0]);
        Board board = new Board(lines);

        int numTrees = 0;

        while (board.move(3, 1)) {
            if (board.isTree()) {
                numTrees++;
            }
        }

        System.out.printf("Encountered %s trees.%n", numTrees);
    }
}
