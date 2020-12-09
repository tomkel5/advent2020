package com.tomkel.advent.advent2020.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/1">Puzzle 1.1</a>
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        List<Integer> numbers = getIntegers();

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

    private static List<Integer> getIntegers() throws FileNotFoundException {
        ClassLoader classLoader = Part1.class.getClassLoader();
        URL resource = classLoader.getResource("day01/input.txt");
        if (resource == null) {
            throw new AssertionError();
        }

        String path = resource.getPath();
        Scanner scanner = new Scanner(new File(path));
        List<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        return numbers;
    }
}
