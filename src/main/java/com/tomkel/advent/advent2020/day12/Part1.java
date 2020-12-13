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

        Point ship = new Point(0, 0);
        // Use the waypoint method from part 2 of this puzzle. The waypoint begins facing East, one space away.
        // In the first half of the puzzle, the ship does not move independently with the waypoint; however, the
        // forward movement of the ship, and the rotation of the waypoint are done just like the second part.
        Point waypoint = new Point(1, 0);

        lines.forEach(line -> {
            char instruction = line.toUpperCase().charAt(0);
            int argument = Integer.parseInt(line.substring(1));

            switch (instruction) {
                case 'N':
                    ship.y += argument;
                    waypoint.y += argument;
                    break;
                case 'S':
                    ship.y -= argument;
                    waypoint.y -= argument;
                    break;
                case 'E':
                    ship.x += argument;
                    waypoint.x += argument;
                    break;
                case 'W':
                    ship.x -= argument;
                    waypoint.x -= argument;
                    break;
                case 'R':
                    while(argument > 0) {
                        int xDiff = waypoint.x - ship.x;
                        int yDiff = waypoint.y - ship.y;
                        waypoint.x = ship.x + yDiff;
                        waypoint.y = ship.y - xDiff;
                        argument -= 90;
                    }
                    break;
                case 'L':
                    while (argument > 0) {
                        int xDiff = waypoint.x - ship.x;
                        int yDiff = waypoint.y - ship.y;
                        waypoint.x = ship.x - yDiff;
                        waypoint.y = ship.y + xDiff;
                        argument -= 90;
                    }
                    break;
                case 'F':
                    int xDiff = waypoint.x - ship.x;
                    int yDiff = waypoint.y - ship.y;

                    ship.x = ship.x + ((waypoint.x - ship.x) * argument);
                    ship.y = ship.y + ((waypoint.y - ship.y) * argument);
                    waypoint.x = ship.x + xDiff;
                    waypoint.y = ship.y + yDiff;
                    break;
            }
        });

        int manhattanDistance = Math.abs(ship.x) + Math.abs(ship.y);
        System.out.printf("Ship is at (%s, %s). The Manhattan distance is %s.%n", ship.x, ship.y, manhattanDistance);
    }
}
