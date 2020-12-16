package com.tomkel.advent.advent2020.day15;

import com.tomkel.advent.advent2020.FileHelpers;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/15#part2">Puzzle 15.2</a>
     */
    public static void main(String[] args) throws FileNotFoundException {

        Long[] numbers = Stream.of(FileHelpers.getLines("day15/input.txt").get(0).split(","))
                .mapToLong(Long::parseLong)
                .boxed().collect(Collectors.toList()).toArray(new Long[] {});

        Game game = new Game(numbers);

        System.out.printf("The final number is %s%n", game.getNumberAtTurn(30_000_000));
    }
}
