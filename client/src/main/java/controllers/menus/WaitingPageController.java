package controllers.menus;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class WaitingPageController {
    @FXML
    void cancelHandler(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");

    }

}
