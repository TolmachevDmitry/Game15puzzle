package com.tolmic.puzzle15.gui;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class CreatorLabelTile extends CreatorCellsTile {

    @Override
    protected Control createControl(String text) {
        return new Label(text);
    }

    private String[] converTwoDemToOne(String[][] array) {
        int n = array.length;
        int m = array[0].length;

        int i = 0;
        String[] oneDemArray = new String[n * m];
        for (String[] row : array) {
            for (String col : row) {
                oneDemArray[i] = col;

                i += 1;
            }
        }

        return oneDemArray;
    }

    public void fillTileByDefined(TilePane tilePane, String[][] matrix) {
        fillCells(tilePane, matrix.length, matrix[0].length, converTwoDemToOne(matrix));
    }

}
