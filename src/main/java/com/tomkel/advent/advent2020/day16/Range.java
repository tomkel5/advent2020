package com.tomkel.advent.advent2020.day16;

public class Range {

    private final int lower;
    private final int upper;

    public Range(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public boolean test(int number) {
        return ((number >= lower) && (number <= upper));
    }
}
