package com.tomkel.advent.advent2020.day06;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/6#part2">Puzzle 6.2</a>
     */
    public static void main(String[] args) throws Exception {

        String content = FileHelpers.getContent("day06/input.txt");

        String[] groups = content.split("\n\n");

        int count = 0;

        for (String group : groups) {
            // Since there are newline characters between each person in each group,
            // we can find the group size by adding 1 to the number of newline characters.
            int numPeople = (int) group.chars().filter(c -> c == '\n').count() + 1;

            // Get a histogram of characters -> frequency answered in this group
            Map<Integer, Long> answerFrequencies = group.chars()
                    .filter(c -> c <= 'z' && c >= 'a')
                    .boxed()
                    .collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );

            // No person can answer the same question twice, so we can tell when "all" members of
            // the group answered a question by comparing the answer frequency to the group size.
            int countAllAnswered = (int) answerFrequencies.entrySet().stream()
                    .filter(s -> s.getValue() == numPeople)
                    .count();

            count += countAllAnswered;
        }

        System.out.printf("Total number of answers from all members in a group: %s%n", count);
    }
}
