package com.tomkel.advent.advent2020.day10;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/10#part2">Puzzle 10.2</a>
     */
    public static void main(String[] args) throws Exception {

        Map<Integer, Adapter> adapters = FileHelpers.getLines("day10/input.txt").stream()
                .map(Integer::parseInt)
                .collect(Collectors.toMap(Function.identity(), x -> new Adapter()));

        Adapter outlet = new Adapter();
        adapters.put(0, outlet);

        int max = adapters.keySet().stream().max(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        Adapter lastAdapter = new Adapter();
        lastAdapter.setLast();
        adapters.put(max + 3, lastAdapter);

        // Connect the adapters
        adapters.forEach((index, adapter) -> {
            // An adapter can be connected to another adapter that is 1, 2, or 3 jolts away
            for (int i = 1; i <= 3; i++) {
                if (adapters.containsKey(index + i)) {
                    adapter.getForwardAdapters().add(adapters.get(index + i));
                }
            }
        });

        adapters.entrySet().stream()
                .sorted((a, b) -> b.getKey().compareTo(a.getKey())) // Sorted in reverse key order
                .map(Map.Entry::getValue)
                .forEachOrdered(Adapter::getNumCombinations);

        // The outlet will now have the correct number of combinations
        System.out.printf("There are %s total combinations.%n", outlet.getNumCombinations());
    }

    static class Adapter {

        private final List<Adapter> forwardAdapters;
        private long numCombinations;

        public Adapter() {
            this.numCombinations = 0;
            this.forwardAdapters = new ArrayList<>();
        }

        /**
         * The number of combinations between this adapter and the phone. If the number has not yet been calculated,
         * then add the number of calculations from each forward adapter, and cache that value. This will give correct
         * values only if the adapters are calculated in reverse order.
         */
        public long getNumCombinations() {
            if (numCombinations > 0) {
                return numCombinations;
            }

            // Add the number of combinations from forward adapters
            numCombinations = forwardAdapters.stream()
                    .mapToLong(Adapter::getNumCombinations)
                    .sum();

            return numCombinations;
        }

        public List<Adapter> getForwardAdapters() {
            return forwardAdapters;
        }

        public void setLast() {
            this.numCombinations = 1;
        }
    }
}
