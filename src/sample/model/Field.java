package sample.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Field {
    private int[][] field;
    private GridPane pane;

    final static int HIGH = 20;
    final static int WEIGHT = 20;



    public Field() {
        pane = new GridPane();
        field = new int[HIGH][WEIGHT];
    }

    public GridPane getPane() {
        return pane;
    }

    public int[][] getField() {
        return field;
    }

    public void repaint() {
        pane.getChildren().clear();
        for (int i = 0; i < HIGH; i++) {
            for (int j = 0; j < WEIGHT; j++) {
                Cell cell = new Cell(field[j][i]);
                pane.setGridLinesVisible(true);
                pane.add(cell.getRectangle(), i, j);
            }
        }
    }

    public void addFirstLevel() {
        for (int i = 0; i < HIGH; i++) {
            for (int j = 0; j < WEIGHT; j++) {
                if (i == 0 || i == HIGH - 1 || j == 0 || j == WEIGHT - 1) {
                    field[j][i] = Entity.INDESTRUCTIBLE_BLOCK.getCode();
                }
                if (j == Entity.HERO.getCode() && i == Entity.HERO.getCode()) {
                    field[j][i] = Entity.HERO.getCode();
                }
                if (i % 2 == 0 && j % 2 == 0) {
                    field[j][i] = Entity.INDESTRUCTIBLE_BLOCK.getCode();
                }
            }
        }
    }
}
