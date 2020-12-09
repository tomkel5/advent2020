package com.tomkel.advent.advent2020.day01;

import com.tomkel.advent.advent2020.FileHelpers;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/1#part2">Puzzle 1.2</a>
     */
    public static void main(String[] args) throws Exception {

        int[] numbers = FileHelpers.getIntegers("day01/input.txt")
                .stream().mapToInt(Integer::valueOf).sorted().toArray();

        for (int i = 0; i < numbers.length; i ++) {
            for (int j = i + 1; j < numbers.length; j ++) {
                for (int k = j + 1; k < numbers.length; k ++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        System.out.printf(
                                "%s * %s * %s = %s",
                                numbers[i],
                                numbers[j],
                                numbers[k],
                                numbers[i] * numbers[j] * numbers[k]
                        );
                        return;
                    }

                    // This array is sorted.
                    // If the sum is already more than 2020, it's only going to get worse on this iteration.
                    if (numbers[i] + numbers[j] + numbers[k] > 2020) {
                        break;
                    }
                }
            }
        }

        System.out.println("Whoops, that number doesn't exist");
    }
}
