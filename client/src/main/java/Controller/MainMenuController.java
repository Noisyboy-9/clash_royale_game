package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainMenuController extends MenuController
{
    @FXML
    private Text levelField;

    @FXML
    private Text usernameField;

    // TODO: ۱۴/۰۷/۲۰۲۱ a method is needed to update level and username fields


    @FXML
    void twoPlayerMode(MouseEvent event)
    {
        // TODO: ۱۴/۰۷/۲۰۲۱ show wait scene and send command to server

    }

    @FXML
    void fourPlayerMode(MouseEvent event)
    {
        // TODO: ۱۴/۰۷/۲۰۲۱ show wait scene and send command to server

    }

    @FXML
    void logOutHandler(MouseEvent event)
    {
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");

    }

}
