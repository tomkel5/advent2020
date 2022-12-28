package com.tomkel.advent.advent2020.day14;

import java.util.ArrayList;
import java.util.List;

public class MaskUtils {

    public static long fromString(String bits) {
        long number = 0;

        bits = new StringBuffer(bits).reverse().toString();

        for (int i = 0; i < 36; i ++) {
            if (bits.charAt(i) == '1') {
                number += Math.pow(2, i);
            }
        }
        return number;
    }

    public static String fromLong(long number) {
        StringBuilder builder = new StringBuilder(36);

        for (int i = 0; i < 36; i ++) {

            builder.append((number % 2 == 1) ? '1' : '0');
            number /= 2;
        }

        return builder.reverse().toString();
    }

    /**
     * Given a mask, represented as a string, mask the number to produce a new number.
     */
    public static long applyMask(long number, String mask) {
        String n = fromLong(number);
        StringBuilder result = new StringBuilder(36);

        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) == 'X') {
                // If the mask bit is 'X', then take the bit value from the original number.
                result.append(n.charAt(i));
            } else {
                // Otherwise, use the bit value from the mask.
                result.append(mask.charAt(i));
            }
        }

        return fromString(result.toString());
    }


    /**
     * Create an extended mask by applying the original mask to the number, keeping the floating bits intact.
     * Example:
     *   Number:        101010
     *   Mask:          X1001X
     *   Extended Mask: X1101X
     * Finding all 1/0 combinations of floating bits will give us all possible permutations of this mask.
     */
    public static String extendMask(long number, String mask) {

        String n = fromLong(number);
        StringBuilder result = new StringBuilder(36);

        for(int i = 0; i < 36; i ++) {
            if (mask.charAt(i) == 'X') {
                result.append('X');
            } else if (mask.charAt(i) == '1' || n.charAt(i) == '1') {
                result.append('1');
            } else {
                result.append('0');
            }
        }

        return result.toString();
    }

    /**
     * Find all permutations of this extended mask by recursively change floating bits to 1s and 0s.
     */
    private static List<String> getMaskPermutations(String mask, List<String> running) {
        int i = mask.indexOf('X');

        if (i >= 0)  {
            running.addAll(getMaskPermutations(mask.replaceFirst("X", "1")));
            running.addAll(getMaskPermutations(mask.replaceFirst("X", "0")));
        } else {
            running.add(mask);
        }

        return running;
    }

    public static List<String> getMaskPermutations(String mask) {
        return getMaskPermutations(mask, new ArrayList<>());
    }
}
