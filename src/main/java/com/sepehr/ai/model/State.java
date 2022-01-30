package com.sepehr.ai.model;

import java.util.Objects;

/**
 * Describe state on puzzle8
 */
public class State implements Comparable {

    private final Puzzle8 puzzle8;

    // Distance from the starting point
    private int distance;

    public State(Puzzle8 puzzle8, int distance) {
        this.puzzle8 = puzzle8;
        this.distance = distance;
    }

    public State(Puzzle8 puzzle8) {
        this.puzzle8 = puzzle8;
        this.distance = 0;
    }

    public State(State state){
        this.puzzle8 = new Puzzle8(state.puzzle8);
        this.distance = 0;
    }

    public Puzzle8 getPuzzle8() {
        return puzzle8;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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

    @Override
    public int compareTo(Object o) {
        State state = (State) o;
        return (this.distance + this.getPuzzle8().heuristic()) - (state.distance + state.puzzle8.heuristic());
    }

    @Override
    public String toString() {
        return puzzle8.toString();
    }
}
