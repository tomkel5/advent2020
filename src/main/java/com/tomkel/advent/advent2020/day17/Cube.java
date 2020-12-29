package com.tomkel.advent.advent2020.day17;

public class Cube {
    private final Coordinate coordinate;
    public boolean active;
    public boolean activeNextGen;

    public Cube(int x, int y, int z) {
        this.coordinate = new Coordinate(x, y, z);
        active = false;
        activeNextGen = false;
    }

    public int getX() {
        return coordinate.getX();
    }

    public int getY() {
        return coordinate.getY();
    }

    public int getZ() {
        return coordinate.getZ();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setActiveNextGen(boolean active) {
        this.activeNextGen = active;
    }

    public void iterate() {
        this.active = this.activeNextGen;
    }
}
