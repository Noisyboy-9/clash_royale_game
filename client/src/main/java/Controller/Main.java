package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage)
    {
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");

    }


    public static void main(String[] args)
    {
        setUserAgentStylesheet(STYLESHEET_MODENA);
        launch(args);
    }

}
