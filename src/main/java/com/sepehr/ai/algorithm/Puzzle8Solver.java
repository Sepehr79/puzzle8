package com.sepehr.ai.algorithm;

import com.sepehr.ai.model.State;

import java.util.HashSet;
import java.util.Set;

public class Puzzle8Solver {

    public State solveWithAStar(State startingState){
        var close = new HashSet<State>();
        close.add(startingState);
        var open = findNeighbors(startingState);
        updateDistanceBaseOnCurrentState(startingState, open);
        State present = startingState;
        State lowest = getLowestState(open);

        while (!present.getPuzzle8().isSolved()){
            present = lowest;
            close.add(present);
            open.remove(present);
            Set<State> neighbors = findNeighbors(present);
            updateDistanceBaseOnCurrentState(present, neighbors);
            open.addAll(findNeighbors(present));
            lowest = getLowestState(open);
        }

        return present;
    }

    private Set<State> findNeighbors(State state){
        Set<State> neighbors = new HashSet<>();
        if (state.getPuzzle8().right()){
            neighbors.add(new State(state));
            state.getPuzzle8().left();
        }
        if (state.getPuzzle8().left()){
            neighbors.add(new State(state));
            state.getPuzzle8().right();
        }
        if (state.getPuzzle8().down()){
            neighbors.add(new State(state));
            state.getPuzzle8().up();
        }
        if (state.getPuzzle8().up()){
            neighbors.add(new State(state));
            state.getPuzzle8().down();
        }
        return neighbors;
    }

    private void updateDistanceBaseOnCurrentState(State state, Set<State> neighbors){
        neighbors.forEach(s -> s.setDistance(state.getDistance() + 1));
    }

    private State getLowestState(Set<State> open){
        return open.stream().min(State::compareTo).stream().findFirst().get();
    }

}
