package com.tolmic.puzzle15.solver;

public class State {
    public int[][] directlyState;
    public State prev;

    public int iEmpty;
    public int jEmpty;
    
    public int priority;

    public State(int[][] state, int iEmpty, int jEmpty, State prev) {
        this.directlyState = state;
        this.prev = prev;
        this.iEmpty = iEmpty;
        this.jEmpty = jEmpty;
        this.priority = Integer.MAX_VALUE;
    }
}
