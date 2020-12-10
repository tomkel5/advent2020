package com.tomkel.advent.advent2020.day07;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/7">Puzzle 7.1</a>
     */
    public static void main(String[] args) throws Exception {

        List<String> lines = FileHelpers.getLines("day07/input.txt");

        BagTypePool pool = new BagTypePool();

        for (String line : lines) {
            // Example: "faded magenta bags contain 3 wavy yellow bags, 4 clear orange bags."
            //           ^-----------------^       ^---------------------------------------^
            String[] parts = line.split("contain");

            // Example: "vibrant blue bags contain no other bags."
            //                                    ^-------------^
            // This means that we need only create the bag type; no connections necessary.
            if (parts[1].contains("no other bags")) {
                pool.getBagType(parts[0]); // Ignore the result
                continue;
            }

            // Example: "plaid bronze bags contain 3 vibrant orange bags, 3 dark aqua bags."
            //                                    ^---------------------^-----------------^
            // Bag descriptors here are separated by commas. (ignore whitespace, since we trim the descriptor later)
            String[] enclosedParts = parts[1].split(",");

            for (String enclosedPart : enclosedParts) {
                pool.connect(parts[0], enclosedPart);
            }
        }

        BagType bag = pool.getBagType("shiny gold");
        Set<BagType> bagTypes = bag.getAllSurroundingBagTypes(new HashSet<>());

        System.out.printf("A shiny gold bag can be held by %s other bags.%n", bagTypes.size());
    }
}
