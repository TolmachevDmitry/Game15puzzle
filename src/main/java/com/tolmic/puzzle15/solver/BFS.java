package com.tolmic.puzzle15.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BFS extends Solver {

    @Override
    protected State solve(State startState, int[][] goal) {
        Stack<State> open = new Stack<>();

        open.push(startState);

        while (!open.isEmpty()) {
            
            List<State> states = new ArrayList<>();
            while (!open.isEmpty()) {

                State currState = open.pop();
    
                if (isGoal(currState, goal)) {
                    return currState;
                }

                states.add(currState); 
            }

            for (State currState : states) {
                for (State state : searchNextStates(currState)) {
                    if (!alreadyPassed(state)) {
                        open.push(state);
                        putToClose(currState);
                    }
                }
            }

        }

        return null;
    }

}
