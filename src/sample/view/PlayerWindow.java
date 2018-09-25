package sample.view;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.controller.Controller;


public class PlayerWindow {
    private Controller controller;
    private Label textLabel;
    private VBox pane;
    private TextField playerNameField;
    private Stage primaryStage;
    private Button okButton;

    private final static String text = "Please, enter your name"; //Yana, what is happen with name of variable??!!
    private final static String nameWindow = "Player's name";
    private final static String okName = "OK";
    private final static String regexName = "[A-z]+";

    public PlayerWindow(Controller controller) {
        primaryStage = new Stage();
        this.controller = controller;
        pane = new VBox();
        okButton = new Button(okName);
        textLabel = new Label(text);
        playerNameField = new TextField();
    }

    public void createWindow() { //May be it is needed to create an abstract class. You should think about it!
        textLabel.setFont(Font.font(20));
        playerNameField.setMinSize(40, 20);
        okButton.setMinSize(20, 20);
        pane.getChildren().addAll(textLabel, playerNameField, okButton);
        pane.setSpacing(20);
        pane.setPadding(new Insets(10, 10, 10, 55));
        Scene scene = new Scene(new Group(), 320, 200);

        ((Group) scene.getRoot()).getChildren().addAll(pane);

        primaryStage.setScene(scene);
        primaryStage.setTitle(nameWindow);


        okButton.setOnAction(e -> {
            if (playerNameField.getText().matches(regexName)) {
                //add to base with players
                controller.showGameWindow();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You entered a wrong name!");
                alert.showAndWait();
                playerNameField.clear();
            }
        });
    }

    public void show() {
        primaryStage.show();
    }
}
