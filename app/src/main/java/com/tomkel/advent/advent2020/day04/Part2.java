package com.tomkel.advent.advent2020.day04;

import com.tomkel.advent.FileHelpers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/4#part2">Puzzle 4.2</a>
     */
    public static void main(String[] args) throws Exception {

        // Join the lines by a space, and replace all newlines with spaces.
        // Then, the tokens will be separated by spaces, and the passports by double-spaces.
        String[] passportParts = FileHelpers.getLines("day04/input.txt")
                .stream().collect(Collectors.joining(" "))
                .replace("\n", " ")
                .split("  ");

        long numValidPassports = Stream.of(passportParts)
                .map(Passport::new)
                .filter(Passport::isExtendedValid)
                .count();

        System.out.printf("There are %s valid passports.%n", numValidPassports);
    }
}
