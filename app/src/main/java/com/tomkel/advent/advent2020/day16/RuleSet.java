package com.tomkel.advent.advent2020.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleSet {
    private final List<Rule> rules = new ArrayList<>();

    public RuleSet(String body) {

        for (String line : body.split("\n")) {
            rules.add(new Rule(line));
        }
    }

    /**
     * Get the list of rules in this rule set that match the given number.
     */
    public List<Rule> getRules(int number) {
        return rules.stream()
                .filter(rule -> rule.test(number))
                .collect(Collectors.toList());
    }

    /**
     * Get all rules in this rule set.
     */
    public List<Rule> getRules() {
        return rules;
    }
}
