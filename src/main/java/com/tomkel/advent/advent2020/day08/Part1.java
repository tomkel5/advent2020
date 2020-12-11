package com.tomkel.advent.advent2020.day08;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.List;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/8">Puzzle 8.1</a>
     */
    public static void main(String[] args) throws Exception {

        List<String> lines = FileHelpers.getLines("day08/input.txt");

        GameConsole console = new GameConsole(lines);
        console.startup();

        System.out.printf("The last value of the accumulator was: %s%n", console.getAccumulator());
    }
}
