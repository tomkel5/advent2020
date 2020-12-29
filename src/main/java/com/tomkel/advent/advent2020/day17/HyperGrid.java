package com.tomkel.advent.advent2020.day17;

import java.util.ArrayList;
import java.util.List;

public class HyperGrid extends Grid<HyperCube> {

    public HyperGrid(String grid) {
        super(grid);
    }

    /**
     * Determine the next iteration of active states for each cube, and then change to those values all at once.
     */
    public void iterate() {
        for (int x = getLowest(HyperCube::getX) - 1; x <= getHighest(HyperCube::getX) + 1; x ++) {
            for (int y = getLowest(HyperCube::getY) - 1; y <= getHighest(HyperCube::getY) + 1; y ++) {
                for (int z = getLowest(HyperCube::getZ) - 1; z <= getHighest(HyperCube::getZ) + 1; z ++) {
                    for (int w = getLowest(HyperCube::getW) - 1; w <= getHighest(HyperCube::getW) + 1; w ++) {

                        HyperCube centerCube = findOrCreateCube(x, y, z, w);

                        List<HyperCube> surroundingCubes = getSurroundingCubes(centerCube);
                        int count = (int) surroundingCubes.stream().filter(HyperCube::isActive).count();

                        if (centerCube.isActive()) {
                            // Rule:
                            //   - If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains
                            //     active. Otherwise, the cube becomes inactive.
                            centerCube.setActiveNextGen(count == 2 || count == 3);
                        } else {
                            // Rule:
                            //   - If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active.
                            //     Otherwise, the cube remains inactive.
                            centerCube.setActiveNextGen(count == 3);
                        }
                    }
                }
            }
        }

        // Update the active states of every cube all at once.
        cubes.values().forEach(HyperCube::iterate);
    }


    /**
     * Given the coordinates (x, y, z, w), find a hypercube in the list that has those coordinates.
     * If one does not exist, then create and add it.
     */
    public HyperCube findOrCreateCube(int x, int y, int z, int w) {

        Coordinate coordinate = new Coordinate(x, y, z, w);
        if (cubes.containsKey(coordinate)) {
            return cubes.get(coordinate);
        }

        HyperCube cube = new HyperCube(coordinate);
        cubes.put(coordinate, cube);

        return cube;
    }

    /**
     * Given a cube, find all 26 cubes that surround it.
     */
    public List<HyperCube> getSurroundingCubes(HyperCube cube) {
        List<HyperCube> surroundingCubes = new ArrayList<>();

        for (int x = -1; x <= 1; x ++) {
            for (int y = -1; y <= 1; y ++) {
                for (int z = -1; z <= 1; z ++) {
                    for (int w = -1; w <= 1; w ++) {
                        HyperCube adjacentCube =
                                findOrCreateCube(cube.getX() + x, cube.getY() + y, cube.getZ() + z, cube.getW() + w);

                        // Ignore the center cube.
                        if (adjacentCube != cube) {
                            surroundingCubes.add(adjacentCube);
                        }
                    }
                }
            }
        }

        if (surroundingCubes.size() != 80) {
            throw new RuntimeException(String.format("Shouldn't have %s surrounding cubes.", surroundingCubes.size()));
        }

        return surroundingCubes;
    }

    /**
     * Given the coordinates (x, y, z), find a cube in the list that has those coordinates. If one does not
     * exist, then create and add it.
     */
    public HyperCube findOrCreateCube(int x, int y, int z) {

        Coordinate coordinate = new Coordinate(x, y, z, 0);
        if (cubes.containsKey(coordinate)) {
            return cubes.get(coordinate);
        }

        HyperCube cube = new HyperCube(coordinate);
        cubes.put(coordinate, cube);

        return cube;
    }
}
