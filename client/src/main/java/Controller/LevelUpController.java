package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LevelUpController
{
    @FXML
    void backToMainMenu(MouseEvent event)
    {
        Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");

    }

}
