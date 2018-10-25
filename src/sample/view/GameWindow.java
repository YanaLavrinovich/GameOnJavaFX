
package sample.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.UserException;
import sample.controller.Controller;
import sample.model.Cell;
import sample.model.Field;
import sample.model.World;
import sample.model.pattern.Observer;


public class GameWindow{
    private Stage primaryStage;
    private GridPane pane;
    private HBox hBox;
    private VBox mainPane;
    private World world;
    private Label timeLabel;
    private Label lifeLabel;
    private Timeline timeline;
    private int fullTime;
    private Controller controller;

    public GameWindow(String nameHero, Controller controller) throws UserException {
        this.controller = controller;
        primaryStage = new Stage();
        primaryStage.setTitle("Bomberman");
        world = new World(nameHero, controller);
        timeLabel = new Label();
        lifeLabel = new Label();
        setLifeInLabel();
        hBox = new HBox();
        mainPane = new VBox();
        pane = new GridPane();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(timeLabel, lifeLabel);
        Scene scene = new Scene(new Group(), 420, 440);
        mainPane.getChildren().addAll(pane,hBox);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.UP)) {
                controller.moveHeroUp();
                controller.setPlayer();
                repaint();
            }
            if (e.getCode().equals(KeyCode.DOWN)) {
                controller.moveHeroDown();
                controller.setPlayer();
                repaint();
            }
            if (e.getCode().equals(KeyCode.RIGHT)) {
                controller.moveHeroRight();
                controller.setPlayer();
                repaint();
            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                controller.moveHeroLeft();
                controller.setPlayer();
                repaint();
            }
            if (e.getCode().equals(KeyCode.ENTER)) {
                controller.putBomb();
                repaint();
            }
        });
        timeline = new Timeline();
        addTimer(nameHero);
        Timeline timeRepaint = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        ae -> {
                            repaint();}
                )
        );
        timeline.play();
        timeRepaint.setCycleCount(Timeline.INDEFINITE);
        timeRepaint.play();
        ((Group) scene.getRoot()).getChildren().addAll(mainPane);

    }


    public World getWorld() {
        return world;
    }

    public void repaint() {
        pane.getChildren().clear();
        for (int i = 0; i < Field.HIGH; i++) {
            for (int j = 0; j < Field.WEIGHT; j++) {
                Cell cell = new Cell(world.getField().getField()[j][i]);
                pane.setGridLinesVisible(true);
                pane.add(cell.getRectangle(), i, j);
            }
        }
    }

    private void addTimer(String namePlayer) {
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        ae -> {
                           setTimeInLabel();
                           setLifeInLabel();
                           addInPlayer(namePlayer);
                           checkStatusPlayer();
                        }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void checkStatusPlayer() {
        if(getWorld().getPlayer().getLive() == 0) {
            Thread.currentThread().stop();
        }
    }

    private void setLifeInLabel() {
        lifeLabel.setText("Life: " + world.getPlayer().getLive());
    }

    public void setTimeInLabel() {
            int newTime = 1;
            fullTime += newTime;
            timeLabel.setText("Time: " + fullTime + " seconds");

    }

    public void addInPlayer(String namePlayer) {
        controller.addInPlayer(fullTime);
        controller.addInBase(world.getPlayer());
    }

    public void show() {
        primaryStage.show();
    }

    public void close() {
        timeline.stop();
        primaryStage.close();
    }
}
