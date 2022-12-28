package com.tomkel.advent.advent2020.day09;

import com.tomkel.advent.FileHelpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static final int REQUIRED_SUM = 26796446;

    /**
     * @see <a href="https://adventofcode.com/2020/day/9#part2">Puzzle 9.2</a>
     */
    public static void main(String[] args) throws Exception {

        List<Long> numbers = FileHelpers.getLines("day09/input.txt").stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Strategy> strategies = new ArrayList<Strategy>() {{
            add(new SlidingScaleStrategy());
            add(new BruteForceStrategy());
        }};

        boolean profiling = (args.length > 0) && "profile".equals(args[0]);

        for (Strategy strategy : strategies) {

            long start = System.currentTimeMillis();

            List<Long> summedNumbers = strategy.getSummedNumbers(numbers);

            Collections.sort(summedNumbers);
            Long lowest = summedNumbers.get(0);
            Long highest = summedNumbers.get(summedNumbers.size() - 1);

            if (profiling) {
                System.out.printf("%+8d", System.currentTimeMillis() - start);
            }
            System.out.printf("Found the sum: %s + %s = %s%n", lowest, highest, lowest + highest);
            if (!profiling) {
                // Just do the first strategy.
                return;
            }
        }
    }

    interface Strategy {
        List<Long> getSummedNumbers(List<Long> masterList);
    }

    static class BruteForceStrategy implements Strategy {

        @Override
        public List<Long> getSummedNumbers(List<Long> masterList) {

            for (int i = 0; i < masterList.size(); i++) {

                long runningTotal = 0;

                for (int j = 0; j < masterList.size(); j++) {

                    runningTotal += masterList.get(i + j);

                    if (runningTotal == REQUIRED_SUM) {

                        return masterList.subList(i, i + j);
                    }

                    // Already exceeded the required sum
                    if (runningTotal > REQUIRED_SUM) {
                        break;
                    }
                }
            }

            throw new RuntimeException("Couldn't find the set of numbers.");
        }
    }

    static class SlidingScaleStrategy implements Strategy {
        @Override
        public List<Long> getSummedNumbers(List<Long> masterList) {
            try {
                int left = 0;
                int right = 0;

                long runningTotal = masterList.get(0);

                do {
                    if (runningTotal < REQUIRED_SUM) {
                        right++;
                        runningTotal += masterList.get(right);
                    } else if (runningTotal > REQUIRED_SUM) {
                        runningTotal -= masterList.get(left);
                        left++;
                    }
                } while (runningTotal != REQUIRED_SUM);

                return masterList.subList(left, right);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Couldn't find the set of numbers.");
            }
        }
    }
}
