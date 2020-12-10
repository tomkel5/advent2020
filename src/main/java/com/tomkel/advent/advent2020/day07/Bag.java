package com.tomkel.advent.advent2020.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bag {

    private final List<Bag> enclosedBags;
    private final Set<Bag> surroundingBags;

    public Bag() {
        this.enclosedBags = new ArrayList<>();
        this.surroundingBags = new HashSet<>();
    }

    public List<Bag> getEnclosedBags() {
        return enclosedBags;
    }

    public Set<Bag> getSurroundingBags() {
        return surroundingBags;
    }

    /**
     * Recursively count the bags inside this one.
     */
    public int getAllEnclosedBagCount() {
        return getAllEnclosedBagCount(0);
    }

    /**
     * Recursively count the bags inside this one.
     */
    private int getAllEnclosedBagCount(int runningTotal) {
        for (Bag bag : getEnclosedBags()) {
            runningTotal = 1 + bag.getAllEnclosedBagCount(runningTotal);
        }
        return runningTotal;
    }

    /**
     * Recursively look through the surrounding bag types to get a total set of bag types that might contain this one.
     */
    public Set<Bag> getAllSurroundingBags(Set<Bag> runningList) {
        for (Bag bag : surroundingBags) {
            // If this bag type is new to the set, then add it, and then recursively add its surrounding types.
            if (!runningList.contains(bag)) {
                runningList.add(bag);
                runningList.addAll(bag.getAllSurroundingBags(runningList));
            }
        }

        return runningList;
    }
}
