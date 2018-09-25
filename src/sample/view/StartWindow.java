package sample.view;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.controller.Controller;

public class StartWindow {
    private Controller controller;

    private VBox pane;
    private Button playButton;
    private Button topButton;
    private Button exitButton;
    private Stage primaryStage;

    private final static String PLAY = "Play";
    private final static String TOP = "Top";
    private final static String EXIT = "Exit";
    private final static String NAME_GAME = "name"; //don't forget to add NORMAL NAME!

    public StartWindow(Controller controller) {
        this.controller = controller;
        pane = new VBox();
        primaryStage = new Stage();
        playButton = new Button(PLAY);
        topButton = new Button(TOP);
        exitButton = new Button(EXIT);
    }

    public void createWindow() {
        Label nameGame = new Label(NAME_GAME);
        nameGame.setFont(Font.font(25));
        playButton.setMinSize(100,50);
        topButton.setMinSize(100,50);
        exitButton.setMinSize(100,50);

        pane.getChildren().addAll(nameGame, playButton, topButton, exitButton);
        pane.setSpacing(20);
        pane.setPadding(new Insets(10,10,10,55));
        Scene scene = new Scene(new Group(), 220, 300);
        ((Group) scene.getRoot()).getChildren().addAll(pane);

        playButton.setOnAction(e -> {
            controller.showPlayerWindow();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle(NAME_GAME);
    }

    public void show() {
        primaryStage.show();
    }

}
