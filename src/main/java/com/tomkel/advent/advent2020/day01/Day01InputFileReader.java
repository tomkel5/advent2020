package com.tomkel.advent.advent2020.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day01InputFileReader {

    public List<Integer> getIntegers() throws FileNotFoundException {
        ClassLoader classLoader = Part1.class.getClassLoader();
        URL resource = classLoader.getResource("day01/input.txt");
        if (resource == null) {
            throw new AssertionError();
        }

        String path = resource.getPath();
        Scanner scanner = new Scanner(new File(path));
        List<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        return numbers;
    }
}
