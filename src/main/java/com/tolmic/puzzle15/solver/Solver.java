package com.tolmic.puzzle15.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.tolmic.puzzle15.solver.exception.IncorrectInitialMatrixException;


public abstract class Solver {

    private HashSet<String> close = new HashSet<>();

    protected int n, m;
    protected final int MAX_ITERATION = 100000;

    // Способы перемещения квадратиков (имеется ввиду изменения положения пустой ячейки)
    protected int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public ArrayList<int[][]> calculateSolution(int[][] startMatrix) {
        this.n = startMatrix.length;
        this.m = startMatrix[0].length;

        int[][] goal = calculateGoalTable(n, m);

        State startState;
        try {
            startState = createInitialState(startMatrix);
        } catch(IncorrectInitialMatrixException ex) {
            ex.toString();
            return new ArrayList<>();
        }

        State finish = solve(startState, goal);

        return toList(finish);
    }

    protected abstract State solve(State startState, int[][] goal);

    protected boolean isGoal(State candidate, int[][] goal) {
        int n = goal.length;

        for (int i = 0; i < n; i++) {
            if (!Arrays.equals(candidate.directlyState[i], goal[i])) {
                return false;
            }
        }
        return true;
    }

    private String stringView(int[][] matrix) {
        StringBuilder output = new StringBuilder("");
        int[][] arr = matrix;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                output.append(arr[i][j]);
                if (i != n - 1 || j != m - 1) {
                    output.append(".");
                }
            }
        }

        return output.toString();
    }

    protected boolean itCanBeDone(int i, int j) {
        return ((0 <= i && i < n) && (0 <= j && j < m));
    }

    protected State goToNexState(State state, int iNew, int jNew) {
        int[][] iState = copyIntArray(state.directlyState);
        int iEmpty = state.iEmpty;
        int jEmpty = state.jEmpty;

        iState[iEmpty][jEmpty] = iState[iNew][jNew];
        iState[iNew][jNew] = -1;

        return new State(iState, iNew, jNew, state);
    }

    protected int[][] calculateGoalTable(int n, int m) {
        int[][] table = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Вычисляем параллельно искомое состояние, на основе размерности сетки
                table[i][j] = (i != n - 1 || j != m - 1) ? (i * m + j + 1) : -1;
            }
        }

        return table;
    }

    protected List<State> searchNextStates(State curr) {

        List<State> states = new ArrayList<>();

        for (int i = 0; i < steps.length; i++) {
            int iNew = curr.iEmpty + steps[i][0];
            int jNew = curr.jEmpty + steps[i][1];

            if (itCanBeDone(iNew, jNew)) {
                states.add(goToNexState(curr, iNew, jNew));
            }
        }

        return states;
    }

    protected int[][] copyIntArray(int[][] arr) {
        int n = arr.length;
        int[][] arr1 = new int[n][arr[0].length];

        for (int i = 0; i < n; i++) {
            arr1[i] = arr[i].clone();
        }

        return arr1;
    }

    protected ArrayList<int[][]> toList(State finalState) {
        ArrayList<int[][]> list = new ArrayList<>();
        State curr = finalState;

        while(curr != null) {
            list.add(0, curr.directlyState);
            curr = curr.prev;
        }

        return list;
    }

    protected boolean alreadyPassed(State curr) {
        return close.contains(stringView(curr.directlyState));
    }

    protected void putToClose(State curr) {
        close.add(stringView(curr.directlyState));
    }

    protected State createInitialState(int[][] matrix) throws IncorrectInitialMatrixException {
        int iEmpty = -1, jEmpty = -1;
        boolean emptyFound = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == -1) {
                    if (emptyFound) {
                        new IncorrectInitialMatrixException("Foun second empty cell in row " 
                                + i + ", column " + j + ". Initial matrix must contain only one empty cell");
                    }

                    iEmpty = i;
                    jEmpty = j;

                    emptyFound = true;
                }
            }
        }

        if (!emptyFound) {
            new IncorrectInitialMatrixException("Initial matrix must contain empty cell");
        }

        return new State(matrix, iEmpty, jEmpty, null);
    }


}
