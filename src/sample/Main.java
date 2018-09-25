package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.view.StartWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Controller controller = new Controller();
        controller.showStartWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
