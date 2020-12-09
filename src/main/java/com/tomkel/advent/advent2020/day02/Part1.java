package com.tomkel.advent.advent2020.day02;

import com.tomkel.advent.advent2020.FileHelpers;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/2">Puzzle 2.1</a>
     */
    public static void main(String[] args) throws Exception {

        long validLines = FileHelpers.getLines("day02/input.txt")
                .stream().map(Line::fromLine)
                .filter(Line::isValid)
                .count();

        System.out.printf("There are %s valid passwords.", validLines);
    }
}
