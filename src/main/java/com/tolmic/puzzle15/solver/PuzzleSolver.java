package com.tolmic.puzzle15.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tolmic.puzzle15.gui.CreatorTableCellsManager;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;


public class PuzzleSolver {

    private int[][] getTaskMatrix(TilePane puzzleTile, int rowNum, int colNum) {

        int[][] puzzleMatrix = new int[rowNum][colNum];

        List<TextField> fields = puzzleTile.getChildren().stream().map(n -> (TextField) n).collect(Collectors.toList());
        int n = fields.size();

        for (int i = 0; i < n; i++) {
            String value = fields.get(i).getText();

            puzzleMatrix[i / colNum][i % colNum] = !value.equals("") ? Integer.valueOf(value) : -1;
        }

        return puzzleMatrix;
    }

    private String[][] convertIntegerToString(int[][] array) {
        int n = array.length;
        int m = array[0].length;

        String[][] stringArray = new String[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = array[i][j];
                if (value != -1) {
                    stringArray[i][j] = String.valueOf(value);
                }
            }
        }

        return stringArray;
    }

    private void formSolutionTable(ScrollPane scrollPane, List<int[][]> solutionSequence) {

        if (solutionSequence.isEmpty()) {
            scrollPane.setContent(new Label("Нет решения у конфигурации"));
            return;
        }

        TilePane solutionTable = new TilePane();
        int n = solutionSequence.size();

        int number = 1;
        for (int[][] iPuzzle : solutionSequence) {
            
            Label label = creaetLabel(number, n);

            AnchorPane.setTopAnchor(label, 10.0);

            TilePane tp = new TilePane();
            CreatorTableCellsManager.createTabeCellWithDefined(tp, convertIntegerToString(iPuzzle));

            AnchorPane.setTopAnchor(tp, 30.0);

            solutionTable.getChildren().add(new AnchorPane(label, tp));
            solutionTable.getChildren().add(new AnchorPane());

            number += 1;
        }

        scrollPane.setContent(solutionTable);
    }

    private Label creaetLabel(int numberPuzzle, int n) {
        String labelText = String.valueOf(numberPuzzle);

        if (numberPuzzle == 1) {
            labelText += " - Начало";
        }

        if (numberPuzzle == n) {
            labelText += " - Конец";
        }

        return new Label(labelText);
    }


    public void solvePuzzle(TilePane puzzleTile, ScrollPane scrollPane, SolverType method, int rowNum, int colNum) {

        int[][] puzzleMatrix = getTaskMatrix(puzzleTile, rowNum, colNum);

        List<int[][]> solutionSequense = new ArrayList<>();

        switch (method) {
            case A_STAR -> {
                solutionSequense = new AStarSearch().calculateSolution(puzzleMatrix);
            }
            case BFS -> {
                solutionSequense = new BFS().calculateSolution(puzzleMatrix);
            }
            case DFS -> {
                solutionSequense = new DFS().calculateSolution(puzzleMatrix);
            }
        }
        
        formSolutionTable(scrollPane, solutionSequense);          
    }

}
