package sample.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.model.World;


public class GameWindow {
    private Stage primaryStage;
    private GridPane pane;
    private World world;
    private Controller controller;

    public GameWindow(String nameHero, Controller controller) {
        this.controller = controller;
        primaryStage = new Stage();
        primaryStage.setTitle("Bomberman");
        world = new World(nameHero);
        pane = world.getField().getPane();

        Scene scene = new Scene(new Group(), 400, 400);
        ((Group) scene.getRoot()).getChildren().addAll(pane);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.UP)) {
                controller.moveHeroUp();
                controller.setPlayer();
                controller.repaint();
            }
            if (e.getCode().equals(KeyCode.DOWN)) {
                controller.moveHeroDown();
                controller.setPlayer();
                controller.repaint();
            }
            if (e.getCode().equals(KeyCode.RIGHT)) {
                controller.moveHeroRight();
                controller.setPlayer();
                controller.repaint();
            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                controller.moveHeroLeft();
                controller.setPlayer();
                controller.repaint();
            }
            if (e.getCode().equals(KeyCode.ENTER)) {
                controller.putBomb();
                controller.repaint();
            }
        });
    }


    public World getWorld() {
        return world;
    }

    public void show() {
        primaryStage.show();
    }

}
