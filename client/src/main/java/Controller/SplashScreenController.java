package Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SplashScreenController {
    @FXML
    void moveToLoginPage(MouseEvent event) {
        Controller.SCENE_CONTROLLER.loadAllMenuScenes();
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");

    }

}
