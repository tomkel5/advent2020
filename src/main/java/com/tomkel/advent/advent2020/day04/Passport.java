package com.tomkel.advent.advent2020.day04;

import java.util.HashMap;
import java.util.stream.Stream;

public class Passport extends HashMap<String, String> {

    public Passport(String line) {
        String[] tokens = line.split(" ");
        for (String token : tokens) {
            String[] keyValue = token.split(":");
            put(keyValue[0], keyValue[1]);
        }
    }

    public boolean isValid() {
        // All of these fields must exist as keys
        return Stream.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
                .filter(this::containsKey)
                .count() == 7;
    }
}
