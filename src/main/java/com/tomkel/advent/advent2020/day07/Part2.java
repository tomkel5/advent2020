package com.tomkel.advent.advent2020.day07;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.List;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/7#part2">Puzzle 7.2</a>
     */
    public static void main(String[] args) throws Exception {

        List<String> lines = FileHelpers.getLines("day07/input.txt");

        BagPool pool = new BagPool(lines);

        int count = pool.getBag("shiny gold").getAllEnclosedBagCount();

        System.out.printf("A shiny gold bag holds %s other bags.%n", count);
    }
}
