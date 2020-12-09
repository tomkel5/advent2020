package com.tomkel.advent.advent2020;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Runner {

    public static void main(String[] args) throws ClassNotFoundException {

        RuleBasedNumberFormat numberFormat =
                new RuleBasedNumberFormat(Locale.US, RuleBasedNumberFormat.SPELLOUT);

        IntStream.range(1, 25)
                .peek(i -> {
                    System.out.println();
                    System.out.printf(
                            "On the %s day of Christmas, my true love gave to me:%n",
                            numberFormat.format(i, "%spellout-ordinal"));
                })
                .mapToObj(i -> String.format("%02d", i))
                .flatMap(s -> Stream.of(
                        String.format("%s.day%s.Part1", Runner.class.getPackage().getName(), s),
                        String.format("%s.day%s.Part2", Runner.class.getPackage().getName(), s)
                ))
                .forEach(n -> {
                    try {
                        Class<?> type = Class.forName(n);
                        Method method = type.getMethod("main", String[].class);
                        method.invoke(null, (Object) args);

                    } catch (Exception e) {
                        System.out.println("Christmas is coming...");
                    }
                });
    }
}
