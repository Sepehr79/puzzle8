package com.sepehr.ai.algorithm;

import com.sepehr.ai.model.Puzzle8;
import com.sepehr.ai.model.State;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Puzzle8Solver {

    private static final Logger LOGGER = Logger.getLogger(Puzzle8Solver.class.getName());

    public State solveWithAStar(State startingState){
        var close = new HashSet<State>();
        close.add(startingState);
        var open = findNeighborsStates(startingState, close);
        updateDistanceBaseOnCurrentState(startingState, open);
        State present = startingState;
        State lowest = getLowestState(open);

        while (!present.getPuzzle8().isSolved()){
            present = lowest;
            LOGGER.info(present.toString());
            close.add(present);
            open.remove(present);
            Set<State> neighbors = findNeighborsStates(present, close);
            updateDistanceBaseOnCurrentState(present, neighbors);
            open.addAll(findNeighborsStates(present, close));
            lowest = getLowestState(open);
        }

        return present;
    }

    private Set<State> findNeighborsStates(State presentState, Set<State> close){
        Set<State> states = new HashSet<>();
        List<Puzzle8> neighborsSet = presentState.getPuzzle8().getNeighbors();
        for (Puzzle8 puzzle8: neighborsSet){
            State newState = new State(puzzle8);
            if (!close.contains(newState)){
                states.add(newState);
            }
        }
        return states;
    }

    private void updateDistanceBaseOnCurrentState(State state, Set<State> neighbors){
        neighbors.forEach(s -> s.setDistance(state.getDistance() + 1));
    }

    private State getLowestState(Set<State> open){
        return open.stream().min(State::compareTo).stream().findFirst().get();
    }

}
