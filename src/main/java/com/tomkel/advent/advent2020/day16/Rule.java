package com.tomkel.advent.advent2020.day16;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private final List<Range> ranges;

    public Rule(String line) {
        String[] keyValueParts = line.split(": ");
        this.ranges = new ArrayList<>();

        String[] rangeParts = keyValueParts[1].split(" or ");
        for (String rangePart : rangeParts) {
            String[] numberParts = rangePart.split("-");
            ranges.add(new Range(
                    Integer.parseInt(numberParts[0]),
                    Integer.parseInt(numberParts[1])
            ));
        }
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public boolean test(int number) {
        return this.ranges.stream().anyMatch(i -> i.test(number));
    }
}
