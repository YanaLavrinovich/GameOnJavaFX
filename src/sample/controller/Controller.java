package sample.controller;

import javafx.scene.control.Alert;
import sample.UserException;
import sample.model.DataBase;
import sample.model.Player;
import sample.view.GameWindow;
import sample.view.PlayerWindow;
import sample.view.StartWindow;
import sample.view.TopWindow;

public class Controller {
    private StartWindow startWindow;
    private PlayerWindow playerWindow;
    private GameWindow gameWindow;
    private DataBase dataBase;
    private TopWindow topWindow;


    public Controller() throws UserException {
        dataBase = new DataBase();
        startWindow = new StartWindow(this);
        playerWindow = new PlayerWindow(this);
        playerWindow.createWindow();
        startWindow.createWindow();

    }

    public void startTopWindow() {
        topWindow = new TopWindow(this);
        topWindow.createWindow();
        topWindow.show();
    }

    public void startGameWorld(String playerName) throws UserException {
        gameWindow = new GameWindow(playerName, this);
    }

    public void addInBase(Player player) {
        dataBase.addInBase(player);
    }

    public void addInPlayer(int time) {
        gameWindow.getWorld().getPlayer().setTime(time);
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

    public DataBase getDataBase() {
        return dataBase;
    }

    public void checkStatusPlayer() {
        gameWindow.checkStatusPlayer();
    }

    public void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Game over!");
        alert.setContentText("You dead!");
        alert.show();
        playerWindow.close();
        gameWindow.close();
        dataBase.writeToFile();
    }

    public void gameOverWithWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Game over!");
        alert.setContentText("You win!");
        alert.show();
        playerWindow.close();
        gameWindow.close();
        dataBase.writeToFile();
    }

    public void writeToFile() {
        dataBase.writeToFile();
    }
}
