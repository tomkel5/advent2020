package com.tomkel.advent.advent2020.day09;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/9">Puzzle 9.1</a>
     */
    public static void main(String[] args) throws Exception {

        List<Long> numbers = FileHelpers.getLines("day09/input.txt").stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        int preambleSize = 25;

        // For each number starting after the preamble...
        for (int i = preambleSize; i < numbers.size(); i++) {

            long testNumber = numbers.get(i);

            // Take the set of numbers directly preceding the test number, filtering out those numbers that would add
            // to itself to equal the test number. Example: if the test number is 10, and the preceding numbers include
            // [2, 4, 5, 6, 9], then 5 should be filtered out, because the two numbers must be different.
            List<Long> previousNumbers = numbers.stream()
                    .skip(i - preambleSize)
                    .limit(preambleSize)
                    .filter(n -> n + n != testNumber) // Numbers must be different
                    .collect(Collectors.toList());

            // Add the inverse of each number to the list.
            // Example: If the test number is 5, and the list contains [1, 3, 4], then the new list would contain
            // [1, 3, 4, (5 - 1), (5 - 3), (5 - 4)], or [1, 3, 4, 4, 3, 1]
            previousNumbers.addAll(
                    previousNumbers.stream().map(n -> testNumber - n).collect(Collectors.toList())
            );

            // If there are any duplicate values, then two numbers *do* exist that add up to the test number.
            // However, if the list sizes are the same, then those numbers do not exist.
            if (previousNumbers.size() == previousNumbers.stream().distinct().count()) {
                System.out.printf("Found the problem at %s%n", testNumber);
                return;
            }
        }
    }
}
