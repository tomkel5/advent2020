package com.tomkel.advent.advent2020.day12;

import com.tomkel.advent.advent2020.FileHelpers;

import java.awt.*;
import java.util.List;

public class Part1 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/12">Puzzle 12.1</a>
     */
    public static void main(String[] args) throws Exception {

        List<String> lines = FileHelpers.getLines("day12/input.txt");

        Point point = new Point(0, 0);
        Compass compass = new Compass();

        lines.forEach(line -> {
            char instruction = line.toUpperCase().charAt(0);
            int argument = Integer.parseInt(line.substring(1));

            switch (instruction) {
                case 'N':
                    point.y += argument;
                    break;
                case 'S':
                    point.y -= argument;
                    break;
                case 'E':
                    point.x += argument;
                    break;
                case 'W':
                    point.x -= argument;
                    break;
                case 'R':
                    compass.turn(argument);
                    break;
                case 'L':
                    compass.turn(360 - argument);
                    break;
                case 'F':
                    point.x += (compass.direction.vector.x * argument);
                    point.y += (compass.direction.vector.y * argument);
                    break;
            }
        });

        int manhattanDistance = Math.abs(point.x) + Math.abs(point.y);
        System.out.printf("Ship is at (%s, %s). The Manhattan distance is %s.%n", point.x, point.y, manhattanDistance);
    }

    static class Compass {

        private Direction direction;

        public Compass() {
            Direction east = new Direction(new Point(1, 0));
            Direction south = new Direction(new Point(0, -1));
            Direction west = new Direction(new Point(-1, 0));
            Direction north = new Direction(new Point(0, 1));
            east.setNextDirection(south);
            south.setNextDirection(west);
            west.setNextDirection(north);
            north.setNextDirection(east);

            // Start facing East
            direction = east;
        }

        public void turn(int degrees) {

            int turns = degrees / 90;

            // Make sure the number of turns is positive
            turns = turns % 4;
            if (turns < 0) {
                turns += 4;
            }

            while (turns > 0) {
                direction = direction.nextDirection;
                turns--;
            }
        }
    }

    static class Direction {
        private final Point vector;
        private Direction nextDirection;

        public Direction(Point vector) {
            this.vector = vector;
        }

        public void setNextDirection(Direction direction) {
            this.nextDirection = direction;
        }
    }
}
