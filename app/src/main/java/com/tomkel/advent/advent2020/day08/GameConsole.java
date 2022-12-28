package com.tomkel.advent.advent2020.day08;

import java.util.Arrays;
import java.util.List;

public class GameConsole {

    private final Instruction[] instructions;
    private final Instruction start; // A special instruction placed before the first normal instruction.
    private final Instruction finish; // A special instruction placed after the last normal instruction.
    private Instruction current;
    private int accumulator;

    public GameConsole(List<String> lines) {
        this.instructions = new Instruction[lines.size()];
        this.accumulator = 0;
        this.start = new Instruction(-1, Instruction.Type.NOOP, 0);
        this.finish = new Instruction(-1, Instruction.Type.NOOP, 0);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Instruction instruction = create(i, line);
            instructions[i] = instruction;
        }

        // Now that the nodes are all set up, connect them:
        reconnect();
    }

    /**
     * Erase all existing connections between instructions, and create new ones.
     */
    public void reconnect() {

        // Clear out existing connections
        Arrays.asList(instructions)
                .forEach(i -> {
                    i.setNext(null);
                    i.getPrevious().clear();
                });

        // Now that all the nodes are created, connect them.
        connect(this.start, instructions[0]);
        connect(instructions[instructions.length - 1], this.finish);
        for (Instruction source : instructions) {
            int destinationLineNumber = source.getDestinationLineNumber();
            if (destinationLineNumber >= 0 && destinationLineNumber < instructions.length) {
                connect(source, instructions[destinationLineNumber]);
            }
        }
    }

    /**
     * Connect two instructions.
     */
    private void connect(Instruction source, Instruction destination)
    {
        source.setNext(destination);
        destination.getPrevious().add(source);
    }

    /**
     * Create an instruction.
     */
    private Instruction create(int lineNumber, String line) {

        String[] parts = line.split(" ");

        Instruction.Type type;
        String operationName = parts[0];
        if ("acc".equals(operationName)) {
            type = Instruction.Type.ACCUMULATE;
        } else if ("jmp".equals(operationName)) {
            type = Instruction.Type.JUMP;
        } else if ("nop".equals(operationName)) {
            type = Instruction.Type.NOOP;
        } else {
            throw new RuntimeException("Not a valid instruction type");
        }

        int argument = Integer.parseInt(parts[1].trim());

        return new Instruction(lineNumber, type, argument);
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    /**
     * The main process of this class. This method places a pointer on the starting instruction, and then runs through
     * the remaining instructions sequentially, adjusting the accumulator as necessary. The process ends when either
     * the next instruction is one that has already been visited, or when the next instruction is the special "finish"
     * instruction.
     */
    public void startup() {

        // Start from first instruction
        current = this.start;

        // Keep track of the order in which the lines were executed.
        int lineExecutionOrder = 0;

        do {
            if (current.getType().equals(Instruction.Type.ACCUMULATE)) {
                this.accumulator += current.getArgument();
            }

            Instruction next = current.getNext();

            // If the next instruction has already been visited, then there's a bug, and we should exit.
            if (!next.isVisited()) {
                next.visit(lineExecutionOrder++);
            } else {
                break;
            }

            // Continue forward.
            current = next;

        } while (!successfullyCompleted());
    }


    public Instruction getCurrent() {
        return current;
    }

    public int getAccumulator() {
        return accumulator;
    }

    /**
     * If the current instruction is the last normal instruction before the special "finish" instruction, then this
     * process has completed successfully.
     */
    public boolean successfullyCompleted() {
        return this.current.getNext() == this.finish;
    }
}
