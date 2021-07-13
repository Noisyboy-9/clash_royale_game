package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class SceneController
{
    private Scene scene;
    private Parent root;

    public void showScene(String sceneName)
    {
        try
        {
            URL url = new URL("file:/" + Controller.PATH + sceneName);
            root = FXMLLoader.load(url);
            scene = new Scene(root, 528, 946);
            Controller.STAGE.setTitle("Clash Royale");
            Controller.STAGE.setScene(scene);
            Controller.STAGE.show();

        }catch (IOException e)
        {
            System.err.println("there was a problem related to I/O");
        }

    }

}
