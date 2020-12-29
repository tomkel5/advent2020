package com.tomkel.advent.advent2020.day15;

import com.tomkel.advent.advent2020.FileHelpers;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/15">Puzzle 15.1</a>
     */
    public static void main(String[] args) throws FileNotFoundException {

        long number;

        // A map of number -> the last turn in which it was spoken.
        Map<Long, Long> numbers = new HashMap<>();

        Long[] startingNumbers = Stream.of(FileHelpers.getLines("day15/input.txt").get(0).split(","))
                .mapToLong(Long::parseLong)
                .boxed().collect(Collectors.toList()).toArray(new Long[] {});

        // Initialize the map with all the numbers (except for the last one), and the turn in which they were spoken.
        // (First turn is turn 1)
        for(int i = 0; i < startingNumbers.length - 1; i ++) {
            number = startingNumbers[i];
            numbers.put(number, i + 1L);
        }


        number = startingNumbers[startingNumbers.length - 1];
        long nextNumber = 0L;
        for (long i = startingNumbers.length; i < 2020; i ++) {
            // If the number has been spoken already, calculate the next number as:
            //   (this turn number) - (the last turn in which that number was spoken)
            if (numbers.containsKey(number)) {
                nextNumber = i - numbers.get(number);
            }
            // Otherwise, if the number hasn't been spoken before, then the next number spoken should be zero.
            else {
                nextNumber = 0L;
            }

            // Either way, update the last turn taken for the given number to this turn.
            numbers.put(number, i);

            // Advance to the next round.
            number = nextNumber;
        }

        System.out.printf("The final number is %s%n", nextNumber);
    }
}
