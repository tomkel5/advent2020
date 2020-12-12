package com.tomkel.advent.advent2020.day09;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/9#part2">Puzzle 9.2</a>
     */
    public static void main(String[] args) throws Exception {

        List<Long> numbers = FileHelpers.getLines("day09/input.txt").stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        long requiredSum = 26796446;

        for (int i = 0; i < numbers.size(); i++) {

            long runningTotal = 0;

            for (int j = 0; j < numbers.size(); j++) {

                runningTotal += numbers.get(i + j);

                if (runningTotal == requiredSum) {

                    List<Long> summedNumbers = numbers.subList(i, i + j);
                    Collections.sort(summedNumbers);

                    Long lowest = summedNumbers.get(0);
                    Long highest = summedNumbers.get(summedNumbers.size() - 1);

                    System.out.printf("Found the sum: %s + %s = %s%n", lowest, highest, lowest + highest);
                    return;
                }

                // Already exceeded the required sum
                if (runningTotal > requiredSum) {
                    break;
                }
            }
        }
    }
}
