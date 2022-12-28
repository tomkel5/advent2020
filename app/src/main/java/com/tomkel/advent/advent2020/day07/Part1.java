package com.tomkel.advent.advent2020.day07;

import com.tomkel.advent.FileHelpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/7">Puzzle 7.1</a>
     */
    public static void main(String[] args) throws Exception {

        List<String> lines = FileHelpers.getLines("day07/input.txt");

        BagPool pool = new BagPool(lines);

        Set<Bag> bags = pool.getBag("shiny gold")
                .getAllSurroundingBags(new HashSet<>());

        System.out.printf("A shiny gold bag can be held by %s other bags.%n", bags.size());
    }
}
