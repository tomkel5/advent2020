package com.tomkel.advent.advent2020.day17;

import com.tomkel.advent.advent2020.FileHelpers;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/17">Puzzle 17.1</a>
     */
    public static void main(String[] args) throws Exception {
        String content = FileHelpers.getContent("day17/input.txt");

        Grid grid = new Grid(content);

        int numIterations = 6;
        for (int i = 0; i < numIterations; i++) {
            grid.iterate();
        }

        System.out.printf("After %s iterations, there are %s active cubes.%n",
                numIterations, grid.getActiveCubes().size());
    }
}
