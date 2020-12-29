package com.tomkel.advent.advent2020.day16;

import java.util.Arrays;
import java.util.List;
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
}
