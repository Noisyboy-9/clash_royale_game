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

public class SceneController
{
    private Scene scene;
    private Parent root;
    private final HashMap<String, Scene> menus;

    public SceneController()
    {
        this.menus = new HashMap<>();

    }

    public void showScene(String sceneName)
    {
        try
        {
            if (menus.containsKey(sceneName))
            {
                System.out.println("here");
                scene = menus.get(sceneName);
            }
            else
            {
                URL url = new URL("file:/" + Controller.PATH + sceneName);
                root = FXMLLoader.load(url);
                scene = new Scene(root, 528, 946);

            }
            Controller.STAGE.setTitle("Clash Royale");
            Controller.STAGE.setScene(scene);
            Controller.STAGE.show();

        }catch (IOException e)
        {
            System.err.println("there was a problem related to I/O");
        }

    }


    public void loadAllMenuScenes()
    {
        try
        {
            // get path of all files in the directory
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(Controller.PATH + "Menu"));
            for (Path path : directoryStream)
            {
                Parent menuRoot = FXMLLoader.load(new URL("file:/" + path));
                menus.put("Menu/" + path.getFileName(), new Scene(menuRoot, 528, 946));

            }

        }
        catch (IOException e)
        {
            System.err.println("there was a problem related to I/O");
        }

    }

}
