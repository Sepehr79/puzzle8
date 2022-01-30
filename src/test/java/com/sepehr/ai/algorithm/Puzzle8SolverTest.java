package com.sepehr.ai.algorithm;

import com.sepehr.ai.model.Puzzle8;
import com.sepehr.ai.model.State;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class Puzzle8SolverTest {

    private static final Logger LOGGER = Logger.getLogger(Puzzle8SolverTest.class.getName());

    private static final Puzzle8 PUZZLE_8 = new Puzzle8();

    private static final Puzzle8Solver PUZZLE_8_SOLVER = new Puzzle8Solver();

    @Test
    public void testSolvingFromStartPosition(){
        State state = new State(new Puzzle8());

        State ans =  PUZZLE_8_SOLVER.solveWithAStar(state);
        assertEquals(PUZZLE_8, ans.getPuzzle8());
    }

    @Test
    public void testSolvingFromPosition1(){
        Puzzle8 puzzle8 = new Puzzle8();
        puzzle8.right();
        puzzle8.right();

        State state = new State(puzzle8);
        State ans = PUZZLE_8_SOLVER.solveWithAStar(state);

        assertEquals(PUZZLE_8, ans.getPuzzle8());
    }

    @Test
    public void testSolvingFromPosition2(){
        Puzzle8 puzzle8 = new Puzzle8();
        puzzle8.right();
        puzzle8.right();
        puzzle8.down();
        puzzle8.down();
        LOGGER.info(puzzle8.toString());

        State state = new State(puzzle8);
        State ans = PUZZLE_8_SOLVER.solveWithAStar(state);

        assertEquals(PUZZLE_8, ans.getPuzzle8());
    }

}
