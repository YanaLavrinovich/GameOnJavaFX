package sample.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.Controller;


public class TopWindow {
    private Stage primaryStage;
    private TableWithPlayers tableWithPlayers;
    private BorderPane pane;
    private Button button;

    private static final String nameWindow = "Players' top";

    public TopWindow(Controller controller) {
        primaryStage = new Stage();
        tableWithPlayers = new TableWithPlayers(controller);
        pane = new BorderPane();
        button = new Button("OK");

        button.setOnAction(e -> {
            primaryStage.close();
        });

        pane.setTop(tableWithPlayers.getPaneBox());
        pane.setCenter(button);
    }

    public void createWindow() {
        Scene scene = new Scene(new Group(), 330, 500);
        ((Group) scene.getRoot()).getChildren().addAll(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle(nameWindow);
    }

    public void show() {
        primaryStage.show();
    }
}
