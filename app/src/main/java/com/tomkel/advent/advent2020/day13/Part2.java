package com.tomkel.advent.advent2020.day13;

import com.tomkel.advent.FileHelpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/13#part2">Puzzle 13.2</a>
     */
    public static void main(String[] args) throws Exception {
        List<String> lines = FileHelpers.getLines("day13/input.txt");

        String[] schedules = lines.get(1).split(",");

        // Create a mapping of Bus ID -> schedule interval.
        // Typically, the Bus ID and the schedule are the same value. However, if we treat all "x" values as having a
        // schedule of "1", then we won't need any special handling in the logic below.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < schedules.length; i++) {
            int value = 1;
            if (!"x".equals(schedules[i])) {
                value = Integer.parseInt(schedules[i]);
            }
            map.put(i, value);
        }

        long start = map.get(0);
        long multiplier = 1; // Multiplicative identity

        for (int i = 1; i < map.entrySet().size(); i++) {

            // Using the current multiplier, increase the start time by that amount, until the puzzle criteria is met
            // for this iteration.
            while (test(map, start, i)) {
                start += multiplier;
            }

            // Increase the multiplier by multiplying it by the last schedule
            multiplier *= map.get(i - 1);
        }

        // Run this one final time to get the final value with the last multiplier
        while (test(map, start, map.entrySet().size())) {
            start += multiplier;
        }

        System.out.printf("The next start time is %s%n", start);
    }

    /**
     * Check to see if the current start time meets the puzzle criteria, up to the specified number of iterations.
     */
    static boolean test(Map<Integer, Integer> map, long start, int iterations) {

        for (int i = 0; i < iterations; i ++) {
            if ((start + i) % map.get(i) != 0) {
                return true;
            }
        }

        return false;
    }
}
