package com.tomkel.advent.advent2020.day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grid<T extends Cube> {
    Map<Coordinate, T> cubes = new HashMap<>();

    public Grid(String grid) {
        String[] rows = grid.split("\n");
        for (int x = 0; x < rows.length; x ++) {
            for (int y = 0; y < rows[x].length(); y ++) {
                T cube = findOrCreateCube(x, y, 0);
                if (rows[x].charAt(y) == '#') {
                    cube.setActive(true);
                }
            }
        }
    }

    /**
     * Find the cube with the lowest coordinate, based on the function provided.
     */
    public int getLowest(Function<T, Integer> f) {
        return this.cubes.values().stream().filter(T::isActive)
                .map(f)
                .mapToInt(i -> i)
                .min().orElse(0);
    }

    /**
     * Find the cube with the highest coordinate, based on the function provided.
     */
    public int getHighest(Function<T, Integer> f) {
        return this.cubes.values().stream().filter(T::isActive)
                .map(f)
                .mapToInt(i -> i)
                .max().orElse(0);
    }

    /**
     * Determine the next iteration of active states for each cube, and then change to those values all at once.
     */
    public void iterate() {
        for (int x = getLowest(Cube::getX) - 1; x <= getHighest(Cube::getX) + 1; x ++) {
            for (int y = getLowest(Cube::getY) - 1; y <= getHighest(Cube::getY) + 1; y ++) {
                for (int z = getLowest(Cube::getZ) - 1; z <= getHighest(Cube::getZ) + 1; z ++) {

                    Cube centerCube = findOrCreateCube(x, y, z);

                    List<Cube> surroundingCubes = getSurroundingCubes(centerCube);
                    int count = (int) surroundingCubes.stream().filter(Cube::isActive).count();


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

        // Update the active states of every cube all at once.
        cubes.values().forEach(Cube::iterate);
    }

    /**
     * Given the coordinates (x, y, z), find a cube in the list that has those coordinates. If one does not
     * exist, then create and add it.
     */
    public T findOrCreateCube(int x, int y, int z) {

        Coordinate coordinate = new Coordinate(x, y, z);
        if (cubes.containsKey(coordinate)) {
            return cubes.get(coordinate);
        }

        T cube = (T) new Cube(coordinate);
        cubes.put(coordinate, cube);

        return cube;
    }

    /**
     * Given a cube, find all 26 cubes that surround it.
     */
    public List<Cube> getSurroundingCubes(Cube cube) {
        List<Cube> surroundingCubes = new ArrayList<>();

        for (int x = -1; x <= 1; x ++) {
            for (int y = -1; y <= 1; y ++) {
                for (int z = -1; z <= 1; z ++) {
                    Cube adjacentCube = findOrCreateCube(cube.getX() + x, cube.getY() + y, cube.getZ() + z);

                    // Ignore the center cube.
                    if (adjacentCube != cube) {
                        surroundingCubes.add(adjacentCube);
                    }
                }
            }
        }

        if (surroundingCubes.size() != 26) {
            throw new RuntimeException(String.format("Shouldn't have %s surrounding cubes.", surroundingCubes.size()));
        }

        return surroundingCubes;
    }

    /**
     * Find all cubes that are in the "active" state.
     */
    public List<Cube> getActiveCubes() {
        return cubes.values().stream().filter(Cube::isActive).collect(Collectors.toList());
    }
}
