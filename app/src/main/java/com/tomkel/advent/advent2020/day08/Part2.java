package com.tomkel.advent.advent2020.day08;

import com.tomkel.advent.FileHelpers;

import java.util.List;

public class Part2 {

    /**
     * @see <a href="https://adventofcode.com/2020/day/8#part2">Puzzle 8.2</a>
     */
    public static void main(String[] args) throws Exception {

        List<String> lines = FileHelpers.getLines("day08/input.txt");
        GameConsole console = new GameConsole(lines);

        // Run the startup until it fails
        console.startup();

        // The system is now in a state where all instructions prior to the loop, and including the loop, are now
        // visited. The "current" instruction now points to the last unvisited node, before it begins to loop.

        // Since not every instruction must be visited, we cannot guarantee that "current" is the bad one.
        // We therefore must rewind nodes from "current", and test what happens when we swap node types between JUMP
        // and NOOP. We do this backwards, from the line number of "current", back to the line number of the first
        // revisited instruction.

        // Save the current instruction
        Instruction lastToLoop = console.getCurrent();

        Instruction current = lastToLoop;
        do {
            // Get the instruction before this one.
            Instruction previous = current.getPrevious().stream()
                    // We only care about instructions that we visited on this journey.
                    .filter(Instruction::isVisited)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("There were no previous visited instructions"));

            // Do not bother testing if the instruction type is not swappable between JUMP and NOOP.
            if (previous.getType().equals(Instruction.Type.ACCUMULATE)) {
                current = previous;
                continue;
            }

            // Otherwise, run the test:

            // Do this in a separate console, so we don't change our initial console's state
            GameConsole test = new GameConsole(lines);
            test.getInstructions()[previous.getLineNumber()].swapType();
            test.reconnect();

            test.startup();

            if (test.successfullyCompleted()) {
                System.out.printf("After running the program successfully, the accumulator is: %s%n", test.getAccumulator());
                return;
            }

            // Move backward
            current = previous;

        } while (current != lastToLoop.getNext());

        // If we went backwards through the whole loop, and didn't find the bad instruction, then there must be more
        // than one bad instruction.
        throw new RuntimeException("There was more than one bad instruction.");
    }
}
