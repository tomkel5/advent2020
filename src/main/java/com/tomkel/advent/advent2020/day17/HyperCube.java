package com.tomkel.advent.advent2020.day17;

public class HyperCube extends Cube {

    public HyperCube(Coordinate coordinate) {
        super(coordinate);
    }

    public int getW() {
        return coordinate.getW();
    }
}
