package com.tolmic.puzzle15.gui;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class CreatorTextFieldTile extends CreatorCellsTile {

    @Override
    protected Control createControl(String text) {
        return new TextField(text);
    }

    private String[] generateOrdered(int n) {
        String[] strings = new String[n];

        for (int i = 1; i < n; i++) {
            strings[i - 1] = (i + "");
        }

        strings[n - 1] = "";

        return strings;
    }

    private String[] generateRandom(int n) {
        String[] strings = new String[n];

        Set<Integer> intSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int value = 0;

            new ScrollPane();

            while (true) {
                value = (int)(Math.random() * n + 1);

                if (!intSet.contains(value)) {
                    break;
                }
            }

            intSet.add(value);

            strings[i] = value != n ? (value + "") : "";
        }

        return strings;
    }

    public void fillTileByRandom(TilePane tilePane, int colNum, int rowNum) {
        fillCells(tilePane, colNum, rowNum, generateRandom(colNum * rowNum));
    }

    public void fillTileByOrdered(TilePane tilePane, int colNum, int rowNum) {
        fillCells(tilePane, colNum, rowNum, generateOrdered(colNum * rowNum));
    }

}
