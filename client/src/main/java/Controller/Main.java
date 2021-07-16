package Controller;

import Connector.Connector;
import Errors.AlreadyConnectedToServerException;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller.SCENE_CONTROLLER.showScene("Menu/SplashScreen.fxml");
    }

    public static void main(String[] args) {
        try {
            connectToServer();
        } catch (IOException | AlreadyConnectedToServerException exception) {
            exception.printStackTrace();
        }

        setUserAgentStylesheet(STYLESHEET_MODENA);
        launch(args);
    }

    private static void connectToServer() throws IOException, AlreadyConnectedToServerException {
        Socket socket = new Socket("localhost", 8080);

//        create a instance of the connection, and then fetch with the getInstance() method.
        Connector connector = Connector.connect(socket, socket.getInputStream(), socket.getOutputStream());
    }
}
