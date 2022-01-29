package com.sepehr.ai.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Simple implementation of puzzle 8 game
 */
public class Puzzle8 {

    // Final state
    private static final int[][] ANSWER = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
    };

    // Present state
    private final int[][] arr;

    // Position of empty cell call it zero
    private int zeroX;
    private int zeroY;

    // Generate puzzle with final state
    public Puzzle8(){
        // Make a copy from final state
        arr = copy(ANSWER);
        zeroX = 0;
        zeroY = 0;
    }

    // Generate puzzle with custom state
    public Puzzle8(int[][] arr){
        this.arr = arr;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (arr[i][j] == 0){
                    this.zeroX = j;
                    this.zeroY = i;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Zero value not found");
    }

    public Puzzle8(Puzzle8 state){
        this(copy(state.arr));
    }

    /**
     * Make a copy from 2D array
     * @param arr The array we want to copy
     * @return copy of input array
     */
    private static int[][] copy(int[][] arr){
        return Arrays.stream(arr).map(int[]::clone).toArray(int[][]::new);
    }

    // Move the empty cell to the right
    public boolean right(){
        if (zeroX < 2){
            arr[zeroY][zeroX] = arr[zeroY][zeroX+1];
            arr[zeroY][zeroX+1] = 0;
            this.zeroX++;
            return true;
        }
        return false;
    }

    public boolean left(){
        if (zeroX > 0){
            arr[zeroY][zeroX] = arr[zeroY][zeroX-1];
            arr[zeroY][zeroX-1] = 0;
            this.zeroX--;
            return true;
        }
        return false;
    }

    public boolean down(){
        if (zeroY < 2){
            arr[zeroY][zeroX] = arr[zeroY+1][zeroX];
            arr[zeroY+1][zeroX] = 0;
            this.zeroY++;
            return true;
        }
        return false;
    }

    public boolean up(){
        if (zeroY > 0){
            arr[zeroY][zeroX] = arr[zeroY-1][zeroX];
            arr[zeroY-1][zeroX] = 0;
            this.zeroY--;
            return true;
        }
        return false;
    }

    public int heuristic(){
        int counter = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (this.arr[i][j] - ANSWER[i][j] != 0)
                    counter++;
            }
        }
        return counter;
    }

    // Check if puzzle is solved
    public boolean isSolved(){
        return Arrays.deepEquals(arr, ANSWER);
    }


    // Getters
    public int[][] getArr() {
        return arr;
    }

    public int getZeroX() {
        return zeroX;
    }

    public int getZeroY() {
        return zeroY;
    }


    // Equals and hashcode method to describe difference between states
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle8 puzzle8 = (Puzzle8) o;
        return zeroX == puzzle8.zeroX && zeroY == puzzle8.zeroY && Arrays.deepEquals(arr, puzzle8.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(zeroX, zeroY);
        result = 31 * result + Arrays.deepHashCode(arr);
        return result;
    }

    // To string method to print result on the console
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n");
        for (int i = 0; i < 3; i++){
            stringBuilder.append("|");
            for (int j = 0; j < 3; j++){
                stringBuilder.append(" ").append(arr[i][j]).append(" ");
            }
            stringBuilder.append("|\n");
        }
        return stringBuilder.toString();
    }
}
