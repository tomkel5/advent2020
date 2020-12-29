package com.tomkel.advent.advent2020.day17;

import java.util.Objects;

public class Coordinate {

    private int x;
    private int y;
    private int z;
    private int w;

    public Coordinate(int x, int y, int z) {
        this(x, y, z, 0);
    }

    public Coordinate(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getW() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y &&
                z == that.z &&
                w == that.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }
}
