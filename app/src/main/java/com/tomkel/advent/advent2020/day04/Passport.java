package com.tomkel.advent.advent2020.day04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Passport extends HashMap<String, String> {

    /**
     * A mapping of field names to validator predicates
     */
    public static final Map<String, Predicate<String>> VALIDATORS = new HashMap<String, Predicate<String>>() {{

        // Birth year validator
        put("byr", val -> {
            try {
                // Must be an integer
                int i = Integer.parseInt(val);
                // Must be four digits and be in [1920, 2002]
                return (i <= 2002) && (i >= 1920);
            } catch (NumberFormatException e) {
                return false;
            }
        });

        // Issue year validator
        put("iyr", val -> {
            try {
                // Must be an integer
                int i = Integer.parseInt(val);
                // Must be four digits, and be in [2010, 2020]
                return (i <= 2020) && (i >= 2010);
            } catch (NumberFormatException e) {
                return false;
            }
        });

        // Expiration year validator
        put("eyr", val -> {
            try {
                // Must be an integer
                int i = Integer.parseInt(val);
                // Must be four digits, and be in [2020, 2030]
                return (i <= 2030) && (i >= 2020);
            } catch (NumberFormatException e) {
                return false;
            }
        });

        // Height validator
        put("hgt", val -> {
            try {
                if (val.endsWith("cm")) {
                    // If centimeters,
                    // Must be an integer in [150, 193]
                    int i = Integer.parseInt(val.replace("cm", ""));
                    return (i >= 150) && (i <= 193);
                }
                if (val.endsWith("in")) {
                    // If inches,
                    // Must be an integer in [59, 76]
                    int i = Integer.parseInt(val.replace("in", ""));
                    return (i >= 59) && (i <= 76);
                }
                return false;
            } catch (NumberFormatException e) {
                return false;
            }
        });

        // Hair color validator
        put("hcl", val -> {
            if (val.length() != 7) {
                return false;
            }
            // First character must be '#'
            if (val.charAt(0) != '#') {
                return false;
            }
            for (int i = 1; i <= 6; i++) {
                // Every other character must be in [0, 9] or [a, f]
                char c = val.charAt(i);
                if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f'))) {
                    return false;
                }
            }
            return true;
        });

        // Eye color validator
        put("ecl", val -> Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(val));

        // Passport ID validator
        put("pid", val -> {
            // Must be 9 characters
            if (val.length() != 9) {
                return false;
            }
            // Each character must be in [0, 9]
            for (int i = 0; i < 9; i++) {
                char c = val.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        });

        // Country ID
        // Always valid
        put("cid", val -> true);
    }};

    public Passport(String line) {
        String[] tokens = line.split(" ");
        for (String token : tokens) {
            String[] keyValue = token.split(":");
            put(keyValue[0], keyValue[1]);
        }
    }

    public boolean isQuickValid() {
        // All of the validated fields must exist, except for cid
        return VALIDATORS.keySet().stream()
                .filter(name -> !"cid".equals(name))
                .allMatch(this::containsKey);
    }

    public boolean isExtendedValid() {
        return isQuickValid() && VALIDATORS.entrySet().stream()
                .allMatch(es -> es.getValue().test(get(es.getKey())));
    }
}
