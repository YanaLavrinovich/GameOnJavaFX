package sample.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
    private Rectangle rectangle;

    public Cell(int code) {
        if(code == Entity.GRASS.getCode()) {
           rectangle = new Rectangle(20,20,Color.GREEN);
        }
        if(code == Entity.HERO.getCode()) {
            rectangle = new Rectangle(20,20,Color.CYAN);
        }
        if(code == Entity.INDESTRUCTIBLE_BLOCK.getCode()) {
            rectangle = new Rectangle(20,20,Color.GREY);
        }
        if(code == Entity.DESTRUCTIBLE_BLOCK.getCode()) {
            rectangle = new Rectangle(20,20,Color.BROWN);
        }
        if(code == Entity.BOMB.getCode()) {
            rectangle = new Rectangle(20,20,Color.BLACK);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
