package com.tolmic.puzzle15.solver;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch extends Solver {

    private int[][] startIndexes;

    Comparator<State> statePriorityComporator = (a, b) -> {
        if (a.priority > b.priority) {
            return 1;
        } else if (a.priority < b.priority) {
            return -1;
        }

        return 0;
    };

    // g(x)
    private int g(State state) {
        int sumG = 0;
        int[][] currentState = state.directlyState;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int indexOfValue = (currentState[i][j] == -1) ? (0) : (currentState[i][j]);

                int dI = startIndexes[indexOfValue][0];
                int dJ = startIndexes[indexOfValue][1];

                sumG += Math.abs(i - n - 1 - dI) + Math.abs(j - m - 1 - dJ);
            }
        }

        return sumG;
    }

    // h(x)
    private int h(State state) {
        int sumH = 0;
        int[][] currentState = state.directlyState;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = currentState[i][j];

                if (value == -1 && i != n - 1 && j != m - 1) {
                    sumH += Math.abs(i - n - 1) + Math.abs(j - m - 1);
                } else if (value != i * m + j + 1) {
                    sumH += Math.abs(i - ((value - 1) / m)) + Math.abs(j - (value - ((value - 1) / m) * m - 1));
                }

            }
        }

        return sumH;
    }

    private void defineStartIndexes(int[][] startStateMatrix) {
        int n = startStateMatrix.length;
        int m = startStateMatrix[0].length;

        startIndexes = new int[n * m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = startStateMatrix[i][j] == -1 ? 0 : startStateMatrix[i][j];

                startIndexes[value][0] = i;
                startIndexes[value][1] = j;
            }
        }
    }

    @Override
    protected State solve(State startState, int[][] goal) {
        PriorityQueue<State> open = new PriorityQueue<>(statePriorityComporator);

        open.add(startState);
        int iterCount = 0;

        defineStartIndexes(startState.directlyState);

        while (!open.isEmpty() && iterCount < MAX_ITERATION) {
            State currState = open.poll();

            if (isGoal(currState, goal)) {
                return currState;
            }

            for (State state : searchNextStates(currState)) {
                if (!alreadyPassed(state)) {
                    int f = g(state) + h(state);
                    state.priority = f;

                    open.add(state);

                    putToClose(state);
                }
            };

            iterCount += 1;
        }

        if (iterCount == MAX_ITERATION) {
            System.out.println("Превышено максимальное число итераций, возможно, задача не разрешима!");
        }

        return null;
    }

}
