package com.tomkel.advent.advent2020.day08;

import java.util.ArrayList;
import java.util.List;

public class Instruction {

    public enum Type {
        ACCUMULATE, JUMP, NOOP
    }

    private Instruction next;
    private List<Instruction> previous;
    private Type type;
    private final int lineNumber;
    private final int argument;
    private int orderVisited;

    public Instruction(int lineNumber, Type type, int argument) {
        this.orderVisited = -1;
        this.type = type;
        this.lineNumber = lineNumber;
        this.argument = argument;
        this.previous = new ArrayList<>();
    }

    public Type getType() {
        return type;
    }

    public int getArgument() {
        return argument;
    }

    public int getDestinationLineNumber() {
        switch(getType()) {
            case NOOP:
            case ACCUMULATE:
                return lineNumber + 1;
            case JUMP:
                return lineNumber + getArgument();
            default:
                throw new RuntimeException("Not a valid instruction type");
        }
    }

    public void visit(int orderVisited) {
        this.orderVisited = orderVisited;
    }

    public void swapType() {
        // The only swappable types are JUMP and NOOP
        if (this.type.equals(Type.NOOP)) {
            this.type = Type.JUMP;
        } else if (this.type.equals(Type.JUMP)) {
            this.type = Type.NOOP;
        }
    }

    public boolean isVisited() {
        return orderVisited >= 0;
    }

    public void reset() {
        this.orderVisited = -1;
    }

    public void setNext(Instruction next) {
        this.next = next;
    }

    public Instruction getNext() {
        return next;
    }

    public List<Instruction> getPrevious() {
        return previous;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
