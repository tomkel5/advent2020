package com.tomkel.advent.advent2020.day07;

import java.util.HashMap;
import java.util.Map;

public class BagTypePool {

    Map<String, BagType> bagTypes;

    public BagTypePool() {
        this.bagTypes = new HashMap<>();
    }

    /**
     * Find an existing bag type in the pool, or create a new one.
     * @param bagDescriptor Descriptor examples: "8 light blue bags", "medium blue bags", or simply "dark blue"
     */
    public BagType getBagType(String bagDescriptor) {
        String[] parts = bagDescriptor.trim().split(" ");

        // If the descriptor is formatted "medium blue bags" or "dark blue", then use the first two words as the name.
        // Otherwise, if it's "8 light blue bags", then use the second two words.
        int nameStartIndex = (parts.length <= 3) ? 0 : 1;
        String name = String.format("%s %s", parts[nameStartIndex], parts[nameStartIndex +1]);

        // Return the bag type if it already exists in the pool.
        if (bagTypes.containsKey(name)) {
            return bagTypes.get(name);
        }

        // If it doesn't exist in the pool, create a new one and return it.
        bagTypes.put(name, new BagType());
        return bagTypes.get(name);
    }

    public void connect(String surroundingBagDescriptor, String enclosedBagDescriptor) {
        // Find or create both bag types
        BagType surrounding = getBagType(surroundingBagDescriptor);
        BagType enclosed = getBagType(enclosedBagDescriptor);

        // And then connect them
        enclosed.getSurroundingBagTypes().add(surrounding);
    }

}
