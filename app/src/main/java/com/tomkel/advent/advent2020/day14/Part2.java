package com.tomkel.advent.advent2020.day14;

import com.tomkel.advent.FileHelpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/14#part2">Puzzle 14.2</a>
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

            else if (line.startsWith("mem")) {
                String[] parts = line.split(" ");
                long argument = Long.parseLong(parts[2]);
                long index = Long.parseLong(parts[0].replace("mem[", "").replace("]", ""));

                // Create an extended mask that masks the existing mask over the number.
                String extendedMask = MaskUtils.extendMask(index, mask);

                List<String> permutations = MaskUtils.getMaskPermutations(extendedMask);
                permutations.forEach(p -> {
                    // Each permutation of the mask at this stage will be strictly 0s and 1s.
                    // As a matter of convenience, we can use applyMask() to mask over any long value.
                    long key = MaskUtils.applyMask(index, p);
                    memory.put(key, argument);
                });
            }
        }

        long sum = memory.values().stream().mapToLong(l -> l).sum();

        System.out.printf("The sum is: %s%n", sum);
    }
}
