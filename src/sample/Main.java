package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controller.Controller;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws UserException {
        Controller controller = new Controller();
        controller.showStartWindow();
    }

/* TODO : добавить лейбл с жизнью на экран игры
   TODO : добавить еще три уровня (или это как развитие)
   TODO : сделать таблицу рейтинга
    */

    public static void main(String[] args) {
        launch(args);
    }
}
