package sample.controller;

import sample.view.GameWindow;
import sample.view.PlayerWindow;
import sample.view.StartWindow;

public class Controller {
    private StartWindow startWindow;
    private PlayerWindow playerWindow;
    private GameWindow gameWindow;


    public Controller() {
        startWindow = new StartWindow(this);
        playerWindow = new PlayerWindow(this);
        gameWindow = new GameWindow("dd", this);
        playerWindow.createWindow();
        startWindow.createWindow();


    }

    public  void showGameWindow() {
        gameWindow.show();
    }

    public void showPlayerWindow() {
        playerWindow.show();
    }

    public void showStartWindow() {
        startWindow.show();
    }

    public void moveHeroUp() {
        gameWindow.getWorld().moveHeroUp();
    }

    public void moveHeroDown() {
        gameWindow.getWorld().moveHeroDown();
    }

    public void moveHeroRight() {
        gameWindow.getWorld().moveHeroRight();
    }

    public void moveHeroLeft() {
        gameWindow.getWorld().moveHeroLeft();
    }

    public void setPlayer() {
        gameWindow.getWorld().setPlayer();
    }

    public void putBomb() {
        gameWindow.getWorld().putBomb();
    }

    public void addFirstLevel() {
        gameWindow.getWorld().getField().addFirstLevel();
    }

    public void repaint() { gameWindow.getWorld().getField().repaint();}
}
