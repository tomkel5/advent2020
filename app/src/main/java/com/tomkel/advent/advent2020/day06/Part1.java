package com.tomkel.advent.advent2020.day06;

import com.tomkel.advent.FileHelpers;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/6">Puzzle 6.1</a>
     */
    public static void main(String[] args) throws Exception {

        String content = FileHelpers.getContent("day06/input.txt");

        String[] groups = content.split("\n\n");

        int count = 0;
        for (String group : groups) {
            // Find the count of distinct characters in [a, z]
            count += group
                    .chars()
                    .distinct()
                    .filter(c -> c <= 'z' && c >= 'a') // (filters out whitespace)
                    .count();
        }

        System.out.printf("There were %s positive answers.%n", count);
    }
}
