package com.sepehr.ai.model;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class StateTest {

    private static final Logger LOGGER = Logger.getLogger(StateTest.class.getName());

    @Test
    public void testChangeState(){
        var state = new Puzzle8();
        assertTrue(state.isSolved());
        assertEquals(0 ,state.heuristic());
        LOGGER.info(state.toString());

        assertTrue(state.right());
        LOGGER.info(state.toString());
        assertEquals(2 ,state.heuristic());
        assertArrayEquals(new int[]{1, 0, 2}, state.getArr()[0]);

        assertTrue(state.down());
        LOGGER.info(state.toString());
        assertEquals(3 ,state.heuristic());
        assertArrayEquals(new int[]{3, 0, 5}, state.getArr()[1]);

        assertTrue(state.down());
        LOGGER.info(state.toString());
        assertEquals(4 ,state.heuristic());

        assertTrue(state.left());
        LOGGER.info(state.toString());
        assertEquals(5 ,state.heuristic());

        assertFalse(state.left());
        LOGGER.info(state.toString());
        assertEquals(5 ,state.heuristic());
        assertArrayEquals(new int[]{0, 6, 8}, state.getArr()[2]);
    }

    @Test
    public void testCopyState(){
        var state1 = new Puzzle8();
        state1.right();

        var state2 = new Puzzle8(state1);
        state2.right();

        var state3 = new Puzzle8(state2);
        state3.down();

        assertArrayEquals(new int[]{1, 0, 2}, state1.getArr()[0]);
        LOGGER.info(state1.toString());

        assertArrayEquals(new int[]{1, 2, 0}, state2.getArr()[0]);
        assertArrayEquals(new int[]{3, 4, 5}, state2.getArr()[1]);
        LOGGER.info(state2.toString());

        assertArrayEquals(new int[]{1, 2, 5}, state3.getArr()[0]);
        assertArrayEquals(new int[]{3, 4, 0}, state3.getArr()[1]);
        LOGGER.info(state3.toString());
    }

    @Test
    public void testEquationStates(){
        var state1 = new Puzzle8();
        var state2 = new Puzzle8();

        state1.down();
        state2.down();

        assertEquals(state1, state2);
    }

}
