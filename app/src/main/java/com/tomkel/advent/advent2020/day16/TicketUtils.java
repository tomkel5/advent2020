package com.tomkel.advent.advent2020.day16;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TicketUtils {

    // Turns a line of comma-separated integers, like "7,3,47", into a list.
    private static final Function<String, List<Integer>> createTicket = line ->
            Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

    public static List<List<Integer>> getTicketSet(String body) {
        return Arrays.stream(body.split("\n"))
                .skip(1) // Each section has a header
                .map(createTicket)
                .collect(Collectors.toList());
    }

    public static Map<Rule, List<Integer>> getAllMatchingRules(List<List<Integer>> tickets, RuleSet rules) {

        // Assume at least one ticket in the list.
        int numColumns = tickets.get(0).size();

        Map<Rule, List<Integer>> matches = new HashMap<>();
        rules.getRules().forEach(rule -> matches.put(rule, new ArrayList<>()));

        for (int i = 0; i < numColumns; i++) {

            int j = i;
            List<Integer> columnValues = tickets.stream()
                    .map(li -> li.get(j))
                    .collect(Collectors.toList());

            for (Rule rule : rules.getRules()) {
                if (columnValues.stream().allMatch(rule::test)) {
                    // This rule is a match.
                    matches.get(rule).add(i);
                }
            }
        }

        return matches;
    }


    public static Map<Rule, Integer> getDistinctRules(Map<Rule, List<Integer>> matches) {

        int numColumns = matches.values().stream()
                .findFirst()
                .map(List::size)
                .orElseThrow(RuntimeException::new);

        Map<Rule, Integer> actualMatches = new HashMap<>();

        while (actualMatches.size() < numColumns) {

            // If any only match a single rule, then remove that rule from rotation.
            for (Rule rule : matches.keySet()) {
                if (matches.get(rule).size() == 1) {
                    actualMatches.put(rule, matches.get(rule).get(0));
                }
            }

            for (Rule rule : matches.keySet()) {
                Collection<Integer> matchesToRemove = actualMatches.values();
                for (Integer matchToRemove : matchesToRemove) {
                    matches.get(rule).remove(matchToRemove);
                }
            }
        }

        return actualMatches;
    }
}
