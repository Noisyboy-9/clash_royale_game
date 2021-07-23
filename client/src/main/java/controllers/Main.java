package controllers;

import controllers.connector.Connector;
import database.models.GameResult;
import errors.AlreadyConnectedToServerException;
import javafx.application.Application;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Controller.SCENE_CONTROLLER.showScene("Menu/SplashScreen.fxml");
//        this.seedDatabase();
    }

    private void seedDatabase() throws IOException {
        User user = new User("nejadipour", "password");
        User user2 = new User("sina", "sina");
        User user3 = new User("ashkan", "ashkan");
        User user4 = new User("alireza", "alireza");

        ArrayList<User> winners1 = new ArrayList<>();
        ArrayList<User> winners2 = new ArrayList<>();
        ArrayList<User> winners3 = new ArrayList<>();
        ArrayList<User> winners4 = new ArrayList<>();
        ArrayList<User> winners5 = new ArrayList<>();

        ArrayList<User> losers1 = new ArrayList<>();
        ArrayList<User> losers2 = new ArrayList<>();
        ArrayList<User> losers3 = new ArrayList<>();
        ArrayList<User> losers4 = new ArrayList<>();
        ArrayList<User> losers5 = new ArrayList<>();

        winners1.add(user);
        losers1.add(user2);

        winners2.add(user);
        winners2.add(user2);
        losers2.add(user3);
        losers2.add(user4);

        winners3.add(user2);
        winners3.add(user3);
        losers3.add(user);
        losers3.add(user4);

        winners4.add(user3);
        losers4.add(user);

        winners5.add(user);
        winners5.add(user3);
        losers5.add(user2);
        losers5.add(user4);

        GameResult result1 = new GameResult(winners1, losers1, 3, 2);
        GameResult result2 = new GameResult(winners2, losers2, 2, 2);
        GameResult result3 = new GameResult(winners3, losers3, 3, 1);
        GameResult result4 = new GameResult(winners4, losers4, 1, 3);
        GameResult result5 = new GameResult(winners5, losers5, 2, 0);

        Controller.HISTORY_QUERY_BUILDER.insert(user, result1);
        Controller.HISTORY_QUERY_BUILDER.insert(user, result2);
        Controller.HISTORY_QUERY_BUILDER.insert(user, result3);
        Controller.HISTORY_QUERY_BUILDER.insert(user, result4);
        Controller.HISTORY_QUERY_BUILDER.insert(user, result5);
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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
