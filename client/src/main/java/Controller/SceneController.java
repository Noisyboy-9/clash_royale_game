package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
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
            // get path of all files in the directory
            DirectoryStream<Path> directoryStream = null;
            if (System.getProperty("os.name").contains("Mac")) {
//                create a directory stream on mac
                directoryStream = Files.newDirectoryStream(Paths.get("/" + Controller.PATH + "Menu/"));
            }

            if (System.getProperty("os.name").contains("Windows")) {
//                create a directory stream
                directoryStream = Files.newDirectoryStream(Paths.get(Controller.PATH + "Menu"));
            }

            for (Path path : directoryStream) {
//                the url created from the path has to be different for mac and windows users
                URL url = null;
                if (System.getProperty("os.name").contains("Mac")) {
//                    create a url for mac users
                    url = new URL("file://" + path);
                }

                if (System.getProperty("os.name").contains("Windows")) {
//                    create a url for windows users
                    url = new URL("file:/" + path);
                }

                Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(url));
                menus.put("Menu/" + path.getFileName(), new Scene(menuRoot, 528, 946));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
