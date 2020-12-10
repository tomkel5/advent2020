package com.tomkel.advent.advent2020.day08;

import java.util.ArrayList;
import java.util.List;

public class GameConsole {

    private int accumulator;
    private final List<Instruction> instructions;

    public GameConsole(List<String> lines) {
        this.instructions = new ArrayList<>();
        this.accumulator = 0;

        for (String line : lines) {
            this.instructions.add(new Instruction(line));
        }
    }

    public void startup() {

        int i = 0;

        // Get first instruction
        Instruction instruction = this.instructions.get(i);

        do {
            instruction.setVisited(true);
            switch (instruction.getType()) {
                case NOOP:
                    i++;
                    break;
                case ACCUMULATE:
                    this.accumulator += instruction.getArgument();
                    i++;
                    break;
                case JUMP:
                    i += instruction.getArgument();
                    break;
            }

            instruction = this.instructions.get(i);
        } while (!instruction.isVisited());
    }

    public int getAccumulator() {
        return accumulator;
    }
}
