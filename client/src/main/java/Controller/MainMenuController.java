package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends MenuController
{
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
