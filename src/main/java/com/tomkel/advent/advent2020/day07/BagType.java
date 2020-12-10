package com.tomkel.advent.advent2020.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BagType {

    private final List<BagType> surroundingBagTypes;

    public BagType() {
        this.surroundingBagTypes = new ArrayList<>();
    }

    public List<BagType> getSurroundingBagTypes() {
        return surroundingBagTypes;
    }

    /**
     * Recursively look through the surrounding bag types to get a total set of bag types that might contain this one.
     */
    public Set<BagType> getAllSurroundingBagTypes(Set<BagType> runningList) {
        for (BagType bagType : getSurroundingBagTypes()) {
            // If this bag type is new to the set, then add it, and then recursively add its surrounding types.
            if (!runningList.contains(bagType)) {
                runningList.add(bagType);
                runningList.addAll(bagType.getAllSurroundingBagTypes(runningList));
            }
        }

        return runningList;
    }
}
