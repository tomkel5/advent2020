package com.tomkel.advent.advent2020.day01;

import com.tomkel.advent.FileHelpers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/1">Puzzle 1.1</a>
     */
    public static void main(String[] args) throws Exception {

        Part2 part2 = new Part2();
        com.tomkel.advent.advent2020.day04.Part2 a = new com.tomkel.advent.advent2020.day04.Part2();

        List<Integer> numbers = FileHelpers.getIntegers("day01/input.txt");

        // Combine the numbers with their difference from 2020.
        // When we see a duplicate value in the combined list, we'll have our answer.
        Stream<Integer> numbersAndDifferences = Stream.concat(
                numbers.stream(),
                numbers.stream().map(i -> (2020 - i))
        );

        Optional<Integer> found = numbersAndDifferences
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(set -> set.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();

        if (found.isPresent()) {
            int number = found.get();
            int difference = 2020 - number;

            System.out.printf("%s * %s = %s%n", number, difference, number * difference);
            return;
        }

        System.out.println("Whoops, that number doesn't exist");
    }
}
