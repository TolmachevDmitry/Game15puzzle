package com.tolmic.puzzle15.gui;

import javafx.scene.control.Control;
import javafx.scene.layout.TilePane;


public abstract class CreatorCellsTile {

    private final double CELL_CIZE = 30.0;

    protected abstract Control createControl(String text);

    protected void fillCells(TilePane tilePane, int rowNum, int colNum, String[] values) {

        int n = colNum * rowNum;

        tilePane.getChildren().clear();
        tilePane.setPrefHeight(rowNum * CELL_CIZE);
        tilePane.setPrefWidth(colNum * CELL_CIZE);

        formCells(tilePane, values);
    }

    private void formCells(TilePane tile, String[] values) {
        for (String v : values) {
            Control control = createControl(v);

            control.setPrefHeight(CELL_CIZE);
            control.setPrefWidth(CELL_CIZE);

            tile.getChildren().add(control);
        }
        
    }

}
