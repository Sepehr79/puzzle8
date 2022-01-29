package com.sepehr.ai.model;

import java.util.Objects;

/**
 * Describe state on puzzle8
 */
public class State {

    private final Puzzle8 puzzle8;

    // Distance from the starting point
    private final int distance;

    public State(Puzzle8 puzzle8, int distance) {
        this.puzzle8 = puzzle8;
        this.distance = distance;
    }

    public State(Puzzle8 puzzle8) {
        this.puzzle8 = puzzle8;
        this.distance = 0;
    }

    public Puzzle8 getPuzzle8() {
        return puzzle8;
    }

    public int getDistance() {
        return distance;
    }

    // States are different based on their puzzle state
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(puzzle8, state.puzzle8);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puzzle8);
    }
}
