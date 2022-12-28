package com.tomkel.advent.advent2020.day16;

import com.tomkel.advent.FileHelpers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/16#part2">Puzzle 16.2</a>
     */
    public static void main(String[] args) throws Exception {

        String content = FileHelpers.getContent("day16/input.txt");
        String[] sections = content.split("\n\n");

        RuleSet rules = new RuleSet(sections[0]);

        List<Integer> myTicket = TicketUtils.getTicketSet(sections[1]).get(0);
        List<List<Integer>> tickets = TicketUtils.getTicketSet(sections[2]);

        // Find all integers in any ticket that match any range in any rule.
        List<Integer> badNumbers = tickets.stream()
                .flatMap(Collection::stream)
                .filter(i -> rules.getRules(i).size() == 0)
                .collect(Collectors.toList());

        List<List<Integer>> goodTickets = tickets.stream()
                .filter(t -> t.stream().noneMatch(badNumbers::contains))
                .collect(Collectors.toList());

        goodTickets.add(myTicket);

        Map<Rule, List<Integer>> matches = TicketUtils.getAllMatchingRules(goodTickets, rules);
        Map<Rule, Integer> actualMatches = TicketUtils.getDistinctRules(matches);

        long product = actualMatches.entrySet().stream()
                // Find all the ticket field names that start with "departure".
                .filter(es -> es.getKey().getName().startsWith("departure"))
                .map(Map.Entry::getValue)
                // For each of those fields, get the value on my own ticket.
                .map(myTicket::get)
                .mapToLong(i -> i)
                // Multiply them together.
                .reduce(1L, (x, y) -> x * y);

        System.out.printf("The product is %s.%n", product);
    }
}
