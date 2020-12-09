package com.tomkel.advent.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileHelpers {

    /**
     * Get the contents of a file that only includes a list of integers.
     */
    public static List<Integer> getIntegers(String fileName) throws FileNotFoundException {
        return getLines(fileName).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * Get the contents of a file as a list of strings, one per line.
     */
    public static List<String> getLines(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = FileHelpers.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new AssertionError();
        }

        String path = resource.getPath();
        Scanner scanner = new Scanner(new File(path));
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

}
