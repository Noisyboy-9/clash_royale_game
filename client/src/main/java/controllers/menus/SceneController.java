package controllers.menus;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The type Scene controller.
 */
public class SceneController {
    private final HashMap<String, Scene> menus;
    private final ArrayList<String> cardsUrls;
    private Scene scene;
    private Parent root;

    /**
     * Instantiates a new Scene controller.
     */
    public SceneController() {
        this.menus = new HashMap<>();
        this.cardsUrls = new ArrayList<>();

    }

    /**
     * Remove scene.
     *
     * @param sceneName the scene name
     */
    public void removeScene(String sceneName) {
        this.menus.remove(sceneName);
    }

    /**
     * Show scene.
     *
     * @param sceneName the scene name
     */
    public void showScene(String sceneName) {
        try {
            if (menus.containsKey(sceneName)) {
                scene = menus.get(sceneName);
            } else {
                URL url = new URL("file:/" + Controller.VIEW_PATH + sceneName);
                root = FXMLLoader.load(url);
                scene = new Scene(root, 528, 946);
                this.menus.put(sceneName, scene);
            }

            Controller.STAGE.setTitle("Clash Royale");
            Controller.STAGE.setScene(scene);
            Controller.STAGE.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Load all menu scenes.
     */
    public void loadAllMenuScenes() {
        try {
            DirectoryStream<Path> directoryStream = this.getDirectoryStreamBasedOnOs(Controller.VIEW_PATH + "Menu");
            String protocol = this.getFileProtocol();
            for (Path path : directoryStream) {
                URL url = new URL(protocol + path);
                Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(url));
                menus.put("Menu/" + path.getFileName(), new Scene(menuRoot, 528, 946));
            }
        } catch (IOException e) {
            System.err.println("there was a problem related to IO");
        }
    }


    public void loadAllCardsUrls()
    {
        try {
            DirectoryStream<Path> directoryStream = this.getDirectoryStreamBasedOnOs(Controller.RESOURCE_PATH + "photos");
            String protocol = this.getFileProtocol();
            for (Path path : directoryStream)
            {
                if (path.toString().contains("Card"))
                {
                    URL url = new URL(protocol + path);
                    this.cardsUrls.add(url.toString());

                }

            }
        } catch (IOException e) {
            System.err.println("there was a problem related to IO");
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

    private DirectoryStream<Path> getDirectoryStreamBasedOnOs(String exclusivePath) throws IOException {
        DirectoryStream<Path> directoryStream = null;
        if (!System.getProperty("os.name").contains("Windows")) {
//                create a directory stream on mac or linux
            return Files.newDirectoryStream(Paths.get("/" + exclusivePath + "/"));
        }

//                create a directory stream for windows
        return Files.newDirectoryStream(Paths.get(exclusivePath));
    }


    public ArrayList<String> getCardsUrls()
    {
        return cardsUrls;
    }

}
