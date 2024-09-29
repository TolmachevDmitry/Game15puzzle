package com.tolmic.puzzle15.solver;

import java.util.Stack;

public class DFS extends Solver {

    @Override
    protected State solve(State startState, int[][] goal) {
        Stack<State> open = new Stack<>();
        Stack<Integer> deep = new Stack<>();

        open.push(startState);
        deep.push(1);

        int minDeep = Integer.MAX_VALUE;

        State goalState = null;

        while (!open.isEmpty()) {

            State currState = open.pop();
            int currDeep = deep.pop();

            if (isGoal(currState, goal) && currDeep < minDeep) {
                minDeep = currDeep;

                goalState = currState;
            }

            for (State state : searchNextStates(currState)) {
                if (!alreadyPassed(state)) {
                    open.push(state);
                    deep.push(currDeep + 1);

                    putToClose(state);
                }
            }
        }

        return goalState;
    }

}
