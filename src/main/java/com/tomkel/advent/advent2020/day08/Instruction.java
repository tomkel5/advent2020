package com.tomkel.advent.advent2020.day08;

public class Instruction {

    enum Type {
        ACCUMULATE, JUMP, NOOP
    }

    private Type type;
    private final int argument;
    private boolean visited;

    public Instruction(String line) {
        this.visited = false;
        String[] parts = line.split(" ");

        String operationName = parts[0];
        if ("acc".equals(operationName)) {
            this.type = Type.ACCUMULATE;
        } else if ("jmp".equals(operationName)) {
            this.type = Type.JUMP;
        } else if ("nop".equals(operationName)) {
            this.type = Type.NOOP;
        }

        this.argument = Integer.parseInt(parts[1]);
    }

    public Type getType() {
        return type;
    }

    public int getArgument() {
        return argument;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
