package com.tomkel.advent.advent2020.day10;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/10">Puzzle 10.1</a>
     */
    public static void main(String[] args) throws Exception {

        int[] numbers = FileHelpers.getLines("day10/input.txt").stream()
                .map(Integer::parseInt)
                .sorted()
                .mapToInt(Integer::valueOf)
                .toArray();

        int[] differences = new int[numbers.length];

        differences[0] = numbers[0]; // The first one is the same, because we're comparing it to zero

        for (int i = 1; i <= numbers.length - 1; i++) {
            differences[i] = numbers[i] - numbers[i - 1];
        }

        // Get a map of the counts of ones and threes to their frequencies.
        Map<Integer, Long> groups = Arrays.stream(differences)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long numOnes = groups.get(1);
        // Add one for the connection to your phone, which is always 3 more than your highest adapter.
        long numThrees = groups.get(3) + 1;

        System.out.printf("%s ones * %s threes = %s%n", numOnes, numThrees, numOnes * numThrees);
    }
}
