package com.tomkel.advent.advent2020.day07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagPool {

    Map<String, Bag> bags;

    public BagPool(List<String> lines) {
        this.bags = new HashMap<>();
        for (String line : lines) {
            // Example: "faded magenta bags contain 3 wavy yellow bags, 4 clear orange bags."
            //           ^-----------------^       ^---------------------------------------^
            String[] parts = line.split("contain");

            // Example: "vibrant blue bags contain no other bags."
            //                                    ^-------------^
            // This means that we need only create the bag type; no connections necessary.
            if (parts[1].contains("no other bags")) {
                this.getBag(parts[0]); // Ignore the result
                continue;
            }

            // Example: "plaid bronze bags contain 3 vibrant orange bags, 3 dark aqua bags."
            //                                    ^---------------------^-----------------^
            // Bag descriptors here are separated by commas. (ignore whitespace, since we trim the descriptor later)
            String[] enclosedParts = parts[1].split(",");

            for (String enclosedPart : enclosedParts) {
                this.connect(parts[0], enclosedPart);
            }
        }
    }

    /**
     * Find a bag in the pool, or create a new one if no existing bag matches the descriptor.
     */
    public Bag getBag(String descriptor) {

        String name = getBagDescriptorName(descriptor);

        if (bags.containsKey(name)) {
            return bags.get(name);
        }

        Bag bag = new Bag();
        bags.put(name, bag);
        return bag;
    }

    /**
     * Connect a bag with another bag inside it, based on two bag descriptors.
     * The enclosed bag will become aware of the surrounding bag; the surrounding bag will have a number
     * of enclosed bags added, the quantity of which is determined by the descriptor. (Note that there is
     * only one instance of each bag type, so this instance is added multiple times to the list)
     */
    public void connect(String surroundingBagDescriptor, String enclosedBagDescriptor) {
        // Find or create both bag types.
        Bag surroundingBag = getBag(surroundingBagDescriptor);
        Bag enclosedBag = getBag(enclosedBagDescriptor);

        // Add the bags inside this bag.
        int quantity = getBagDescriptorQuantity(enclosedBagDescriptor);
        for (int i = 0; i < quantity; i ++) {
            surroundingBag.getEnclosedBags().add(enclosedBag);
        }

        // Make the bag aware of the bag surrounding it.
        enclosedBag.getSurroundingBags().add(surroundingBag);
    }

    /**
     * Extract the quantity portion of a bag descriptor.
     * Example: "3 plaid green bags" has a quantity of 3
     */
    private int getBagDescriptorQuantity(String bagDescriptor) {
        return Integer.parseInt(bagDescriptor.trim().split(" ")[0]);
    }

    /**
     * Extract the name portion of a bag descriptor.
     * Example: "3 plaid green bags" has a name of "plaid green"
     */
    private String getBagDescriptorName(String bagDescriptor) {
        String[] parts = bagDescriptor.trim().split(" ");

        // If the descriptor is formatted "medium blue bags" or "dark blue", then use the first two words as the name.
        // Otherwise, if it's "8 light blue bags", then use the second two words.
        int nameStartIndex = (parts.length <= 3) ? 0 : 1;
        return String.format("%s %s", parts[nameStartIndex], parts[nameStartIndex +1]);
    }
}
