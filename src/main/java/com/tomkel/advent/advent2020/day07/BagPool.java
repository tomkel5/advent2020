package com.tomkel.advent.advent2020.day07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagPool {

    Map<String, BagType> bagTypes;
    Map<BagType, Bag> bags;

    public BagPool() {
        this.bagTypes = new HashMap<>();
        this.bags = new HashMap<>();
    }

    public BagPool(List<String> lines) {
        this();
        for (String line : lines) {
            // Example: "faded magenta bags contain 3 wavy yellow bags, 4 clear orange bags."
            //           ^-----------------^       ^---------------------------------------^
            String[] parts = line.split("contain");

            // Example: "vibrant blue bags contain no other bags."
            //                                    ^-------------^
            // This means that we need only create the bag type; no connections necessary.
            if (parts[1].contains("no other bags")) {
                this.getBagType(parts[0]); // Ignore the result
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
     * Find an existing bag type in the pool, or create a new one.
     * @param bagDescriptor Descriptor examples: "8 light blue bags", "medium blue bags", or simply "dark blue"
     */
    public BagType getBagType(String bagDescriptor) {

        String name = getBagDescriptorName(bagDescriptor);

        // Return the bag type if it already exists in the pool.
        if (bagTypes.containsKey(name)) {
            return bagTypes.get(name);
        }

        // If it doesn't exist in the pool, create a new one and return it.
        bagTypes.put(name, new BagType());
        return bagTypes.get(name);
    }

    public Bag getBag(BagType bagType) {
        if (bags.containsKey(bagType)) {
            return bags.get(bagType);
        }

        Bag bag = new Bag();
        bags.put(bagType, bag);
        return bag;
    }

    public void connect(String surroundingBagDescriptor, String enclosedBagDescriptor) {
        // Find or create both bag types
        BagType surrounding = getBagType(surroundingBagDescriptor);
        BagType enclosed = getBagType(enclosedBagDescriptor);

        // And then connect them
        enclosed.getSurroundingBagTypes().add(surrounding);

        // Find or create a bag
        Bag bag = getBag(surrounding);
        int quantity = getBagDescriptorQuantity(enclosedBagDescriptor);
        for (int i = 0; i < quantity; i ++) {
            bag.getEnclosedBags().add(getBag(enclosed));
        }
    }

    private int getBagDescriptorQuantity(String bagDescriptor) {
        return Integer.parseInt(bagDescriptor.trim().split(" ")[0]);
    }

    private String getBagDescriptorName(String bagDescriptor) {
        String[] parts = bagDescriptor.trim().split(" ");

        // If the descriptor is formatted "medium blue bags" or "dark blue", then use the first two words as the name.
        // Otherwise, if it's "8 light blue bags", then use the second two words.
        int nameStartIndex = (parts.length <= 3) ? 0 : 1;
        return String.format("%s %s", parts[nameStartIndex], parts[nameStartIndex +1]);
    }

}
