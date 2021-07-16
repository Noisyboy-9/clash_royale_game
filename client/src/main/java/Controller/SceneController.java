package Controller;

import connector.Connector;
import exceptions.AlreadyConnectedToServerException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class SceneController {
    private final HashMap<String, Scene> menus;
    private Scene scene;
    private Parent root;

    public SceneController() {
        this.menus = new HashMap<>();
        try {
            this.connectToServer();
        } catch (IOException | AlreadyConnectedToServerException exception) {
            exception.printStackTrace();
        }
    }

    private void connectToServer() throws IOException, AlreadyConnectedToServerException {
        Socket socket = new Socket("localhost", 8080);

//        create a instance of the connection, and then fetch with the getInstance() method.
        Connector connector = Connector.connect(socket, socket.getInputStream(), socket.getOutputStream());
    }

    public void showScene(String sceneName) {
        try {
            if (menus.containsKey(sceneName)) {
                scene = menus.get(sceneName);
            } else {
                URL url = new URL("file:/" + Controller.PATH + sceneName);
                root = FXMLLoader.load(url);
                scene = new Scene(root, 528, 946);
            }
            Controller.STAGE.setTitle("Clash Royale");
            Controller.STAGE.setScene(scene);
            Controller.STAGE.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }


    public void loadAllMenuScenes() {
        try {
            DirectoryStream<Path> directoryStream = this.getDirectoryStreamBasedOnOs();
            String protocol = this.getFileProtocol();
            for (Path path : directoryStream) {
                URL url = new URL(protocol + path);
                Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(url));
                menus.put("Menu/" + path.getFileName(), new Scene(menuRoot, 528, 946));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private String getFileProtocol() {
        if (!System.getProperty("os.name").contains("Windows")) {
//            for mac and linux file system
            return "file://";
        }

//        for windows file system
        return "file:/";
    }

    private DirectoryStream<Path> getDirectoryStreamBasedOnOs() throws IOException {
        DirectoryStream<Path> directoryStream = null;
        if (!System.getProperty("os.name").contains("Windows")) {
//                create a directory stream on mac or linux
            return Files.newDirectoryStream(Paths.get("/" + Controller.PATH + "Menu/"));
        }

//                create a directory stream for windows
        return Files.newDirectoryStream(Paths.get(Controller.PATH + "Menu"));
    }
}
