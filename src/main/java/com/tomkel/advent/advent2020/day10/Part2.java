package com.tomkel.advent.advent2020.day10;

import com.tomkel.advent.advent2020.FileHelpers;

import java.util.*;
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
            if (adapters.containsKey(index + 1)) {
                Adapter nextLowAdapter = adapters.get(index + 1);
                adapter.setNextLowAdapter(nextLowAdapter);
            }
            if (adapters.containsKey(index + 2)) {
                Adapter nextMediumAdapter = adapters.get(index + 2);
                adapter.setNextMediumAdapter(nextMediumAdapter);
            }
            if (adapters.containsKey(index + 3)) {
                Adapter nextHighAdapter = adapters.get(index + 3);
                adapter.setNextHighAdapter(nextHighAdapter);
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
        private Adapter nextLowAdapter;
        private Adapter nextMediumAdapter;
        private Adapter nextHighAdapter;
        private long numCombinations;

        public Adapter() {
            this.numCombinations = 0;
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
            numCombinations =
                    Optional.ofNullable(nextLowAdapter).map(Adapter::getNumCombinations).orElse(0L)
                    + Optional.ofNullable(nextMediumAdapter).map(Adapter::getNumCombinations).orElse(0L)
                    + Optional.ofNullable(nextHighAdapter).map(Adapter::getNumCombinations).orElse(0L);

            return numCombinations;
        }

        public void setNextLowAdapter(Adapter nextLowAdapter) {
            this.nextLowAdapter = nextLowAdapter;
        }

        public void setNextMediumAdapter(Adapter nextMediumAdapter) {
            this.nextMediumAdapter = nextMediumAdapter;
        }

        public void setNextHighAdapter(Adapter nextHighAdapter) {
            this.nextHighAdapter = nextHighAdapter;
        }

        public void setLast() {
            this.numCombinations = 1;
        }
    }
}
