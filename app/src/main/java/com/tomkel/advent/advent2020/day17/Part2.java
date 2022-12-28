package com.tomkel.advent.advent2020.day17;

import com.tomkel.advent.FileHelpers;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/17#part2">Puzzle 17.2</a>
     */
    public static void main(String[] args) throws Exception {
        String content = FileHelpers.getContent("day17/input.txt");

        HyperGrid grid = new HyperGrid(content);

        int numIterations = 6;
        for (int i = 0; i < numIterations; i++) {
            grid.iterate();
        }

        System.out.printf("After %s iterations, there are %s active cubes.%n",
                numIterations, grid.getActiveCubes().size());
    }
}
