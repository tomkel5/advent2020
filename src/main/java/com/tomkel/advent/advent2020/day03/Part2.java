package com.tomkel.advent.advent2020.day03;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.function.Function;
import java.util.stream.Stream;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/3#part2">Puzzle 3.2</a>
     */
    public static void main(String[] args) throws Exception {

        String[] lines = FileHelpers.getLines("day03/input.txt").toArray(new String[0]);
        Board board = new Board(lines);

        class Slope {
            public final int horizontal;
            public final int vertical;

            public Slope(int horizontal, int vertical) {
                this.horizontal = horizontal;
                this.vertical = vertical;
            }
        }

        Function<Slope, Integer> treeCounter = slope -> {
                board.reset();
                int numTrees = 0;
                while (board.move(slope.horizontal, slope.vertical)) {
                    if (board.isTree()) {
                        numTrees++;
                    }
                }
                return numTrees;
        };

        int numTreesMultiplied = Stream.of(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2))
                .map(treeCounter)
                .reduce(1, Math::multiplyExact);

        System.out.printf("Trees * Trees * Trees = %s%n", numTreesMultiplied);
    }
}
