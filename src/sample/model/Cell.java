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
            rectangle = new Rectangle(20,20,Color.ROSYBROWN);
        }
        if(code == Entity.BOMB.getCode()) {
            rectangle = new Rectangle(20,20,Color.BLACK);
        }
        if(code == Entity.KEY_CLOSE.getCode()) {
            rectangle = new Rectangle(20,20,Color.ROSYBROWN);
        }
        if(code == Entity.FIRE.getCode()) {
            rectangle = new Rectangle(20,20,Color.RED);
        }
        if(code == Entity.KEY_OPEN.getCode()) {
            rectangle = new Rectangle(20, 20, Color.GOLD);
        }
        if(code == Entity.BOT.getCode()) {
            rectangle = new Rectangle(20, 20, Color.PINK);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
