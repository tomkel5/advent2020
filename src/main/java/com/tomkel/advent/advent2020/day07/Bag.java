package com.tomkel.advent.advent2020.day07;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private final List<Bag> enclosedBags;

    public Bag() {
        this.enclosedBags = new ArrayList<>();
    }

    public List<Bag> getEnclosedBags() {
        return enclosedBags;
    }

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
}
