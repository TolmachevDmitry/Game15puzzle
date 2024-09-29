package com.tolmic.puzzle15.gui;

import javafx.scene.layout.TilePane;

public class CreatorTableCellsManager {

    public static void creatorTableCellsWithOrdered(TilePane tilePane, int rowNum, int colNum) {
        new CreatorTextFieldTile().fillTileByOrdered(tilePane, rowNum, colNum);
    }

    public static void createTableCellsWithRandom(TilePane tilePane, int rowNum, int colNum) {
        new CreatorTextFieldTile().fillTileByRandom(tilePane, rowNum, colNum);
    }

    public static void createTabeCellWithDefined(TilePane tilePane, String[][] matrix) {
        new CreatorLabelTile().fillTileByDefined(tilePane, matrix);
    }

}
