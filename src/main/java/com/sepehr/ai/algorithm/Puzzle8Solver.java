package com.sepehr.ai.algorithm;

import com.sepehr.ai.model.Puzzle8;
import com.sepehr.ai.model.State;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class Puzzle8Solver {

    private static final Logger LOGGER = Logger.getLogger(Puzzle8Solver.class.getName());

    public State solveWithAStar(State startingState){
        var close = new HashSet<State>();
        close.add(startingState);
        var open = findNeighbors(startingState, close);
        updateDistanceBaseOnCurrentState(startingState, open);
        State present = startingState;
        State lowest = getLowestState(open);

        while (!present.getPuzzle8().isSolved()){
            present = lowest;
            LOGGER.info(present.toString());
            close.add(present);
            open.remove(present);
            Set<State> neighbors = findNeighbors(present, close);
            updateDistanceBaseOnCurrentState(present, neighbors);
            open.addAll(findNeighbors(present, close));
            lowest = getLowestState(open);
        }

        return present;
    }

    private Set<State> findNeighbors(State state, Set<State> close){
        Set<State> neighbors = new HashSet<>();
        if (state.getPuzzle8().right()){
            if (!close.contains(state))
                neighbors.add(new State(state));
            state.getPuzzle8().left();
        }
        if (state.getPuzzle8().left()){
            if (!close.contains(state))
                neighbors.add(new State(state));
            state.getPuzzle8().right();
        }
        if (state.getPuzzle8().down()){
            if (!close.contains(state))
                neighbors.add(new State(state));
            state.getPuzzle8().up();
        }
        if (state.getPuzzle8().up()){
            if (!close.contains(state))
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
