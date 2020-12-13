package com.tomkel.advent.advent2020.day13;

import com.tomkel.advent.advent2020.FileHelpers;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/13">Puzzle 13.1</a>
     */
    public static void main(String[] args) throws Exception {
        List<String> lines = FileHelpers.getLines("day13/input.txt");

        int currentTime = Integer.parseInt(lines.get(0));

        Map.Entry<Integer, Integer> entry = Arrays.stream(lines.get(1).split(",")).filter(x -> !x.equals("x"))
                .map(Integer::parseInt)
                // Map of Bus ID -> # of minutes you need to wait
                .collect(Collectors.toMap(Function.identity(), i -> i - (currentTime % i)))
                .entrySet().stream()
                // Get the lowest number of minutes you have to wait
                .min(Map.Entry.comparingByValue())
                .orElseThrow(RuntimeException::new);

        System.out.printf("The Bus ID is #%s, and you'll need to wait %s minutes. The answer is %s.%n",
                entry.getKey(), entry.getValue(), entry.getKey() * entry.getValue());
    }
}
