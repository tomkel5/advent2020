package com.tomkel.advent.advent2020.day15;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private final Long[] startingNumbers;

    public Game(Long[] startingNumbers) {
        this.startingNumbers = startingNumbers;
    }

    public long getNumberAtTurn(long turnNumber) {
        long number;

        // A map of number -> the last turn in which it was spoken.
        Map<Long, Long> numbers = new HashMap<>();

        // Initialize the map with all the numbers (except for the last one), and the turn in which they were spoken.
        // (First turn is turn 1)
        for(int i = 0; i < startingNumbers.length - 1; i ++) {
            number = startingNumbers[i];
            numbers.put(number, i + 1L);
        }


        number = startingNumbers[startingNumbers.length - 1];
        long nextNumber = 0L;
        for (long i = startingNumbers.length; i < turnNumber; i ++) {
            // If the number has been spoken already, calculate the next number as:
            //   (this turn number) - (the last turn in which that number was spoken)
            if (numbers.containsKey(number)) {
                nextNumber = i - numbers.get(number);
            }
            // Otherwise, if the number hasn't been spoken before, then the next number spoken should be zero.
            else {
                nextNumber = 0L;
            }

            // Either way, update the last turn taken for the given number to this turn.
            numbers.put(number, i);

            // Advance to the next round.
            number = nextNumber;
        }

        return nextNumber;
    }
}
