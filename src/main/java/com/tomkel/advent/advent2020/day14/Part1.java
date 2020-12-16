package com.tomkel.advent.advent2020.day14;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.*;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/14">Puzzle 14.1</a>
     */
    public static void main(String[] args) throws Exception {
        List<String> lines = FileHelpers.getLines("day14/input.txt");

        Map<Long, Long> memory = new HashMap<>();
        // Start with a no-op a mask (it should get overwritten by the first line anyway).
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

        for (String line : lines) {
            // Example format: "mask = 1X101010X01101110000001110X11XXX0100"
            if (line.startsWith("mask =")) {

                String[] parts = line.split(" ");

                // Change the mask:
                mask = parts[2];
            }

            // Example format: "mem[43106] = 841367"
            else if (line.startsWith("mem")) {
                String[] parts = line.split(" ");
                long argument = Long.parseLong(parts[2]);
                long index = Integer.parseInt(parts[0].replace("mem[", "").replace("]", ""));

                // Apply the mask to the number, and store it in memory:
                long value = MaskUtils.applyMask(argument, mask);
                memory.put(index, value);
            }
        }

        long sum = memory.values().stream().mapToLong(l -> l).sum();

        System.out.printf("The sum is: %s%n", sum);
    }
}
