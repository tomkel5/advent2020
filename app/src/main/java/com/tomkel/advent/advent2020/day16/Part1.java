package com.tomkel.advent.advent2020.day16;

import com.tomkel.advent.FileHelpers;

import java.util.Collection;
import java.util.List;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/16">Puzzle 16.1</a>
     */
    public static void main(String[] args) throws Exception {
        String content = FileHelpers.getContent("day16/input.txt");

        String[] sections = content.split("\n\n");

        RuleSet rules = new RuleSet(sections[0]);

        List<List<Integer>> tickets = TicketUtils.getTicketSet(sections[2]);

        // Get the sum of all integers in any ticket that match any range in any rule.
        int sum = tickets.stream()
                .flatMap(Collection::stream)
                .filter(i -> rules.getRules(i).size() == 0)
                .mapToInt(i -> i)
                .sum();

        System.out.println(sum);
    }
}
