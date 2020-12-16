package com.tomkel.advent.advent2020.day14;

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
}
