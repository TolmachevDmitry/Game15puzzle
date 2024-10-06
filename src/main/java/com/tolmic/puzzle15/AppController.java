package com.tolmic.puzzle15;

import com.tolmic.puzzle15.gui.CreatorTableCellsManager;
import com.tolmic.puzzle15.solver.PuzzleSolver;
import com.tolmic.puzzle15.solver.SolverType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;


public class AppController {

    private final int MAX_CELL_NUM = 4;
    private final int MIN_CELL_SIZE = 2;

    private int colNum;
    private int rowNum;

    private PuzzleSolver puzzleSolver = new PuzzleSolver();

    
    @FXML
    private TilePane puzzleField;

    @FXML
    private Button addRowButton;

    @FXML
    private Button removeRowButton;

    @FXML
    private Label notificationLabel;

    @FXML
    private Button addColumnButton;

    @FXML
    private Button removeColumnButton;

    @FXML
    private Button fillRandomButton;
    
    @FXML
    private ComboBox<SolverType> methodList;

    @FXML
    private Button solveButton;

    @FXML
    private ScrollPane solutionField;

    @FXML
    protected void onSolveButtonClick() {
        puzzleSolver.solvePuzzle(puzzleField, solutionField, methodList.getValue(), rowNum, colNum);
    }

    @FXML
    protected void onAddRowButtonClick() {
        changeRowCount(1);
    }

    @FXML 
    protected void onRemoveRowButtonClick() {
        changeRowCount(-1);
    }

    @FXML
    protected void onAddColumnButtonClick() {
        changeColumnCount(1);
    }

    @FXML 
    protected void onRemoveColumnButtonClick() {
        changeColumnCount(-1);
    }

    @FXML
    protected void onRandomlyDistributeValues() {
        CreatorTableCellsManager.createTableCellsWithRandom(puzzleField, rowNum, colNum);
    }

    @FXML
    private void initialize() {

        colNum = rowNum = 2;

        initialPuzzleDimension();

        for (SolverType solverType : SolverType.values()) {
            methodList.getItems().add(solverType);
        }

        methodList.setValue(SolverType.A_STAR);
    }

    private void initialPuzzleDimension() {
        CreatorTableCellsManager.creatorTableCellsWithOrdered(puzzleField, rowNum, colNum);
    }

    private void changeRowCount(int dSize) {
        if (MIN_CELL_SIZE <= rowNum + dSize && rowNum + dSize <= MAX_CELL_NUM) {
            rowNum += dSize;
            initialPuzzleDimension();
        }
    }
    
    private void changeColumnCount(int dSize) {
        if (MIN_CELL_SIZE <= colNum + dSize && colNum + dSize <= MAX_CELL_NUM) {
            colNum += dSize;
            initialPuzzleDimension();
        }
    }

}