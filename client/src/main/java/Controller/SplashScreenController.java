package Controller;

import javafx.fxml.FXML;

public class SplashScreenController {
    @FXML
    void moveToLoginPage() {
        Controller.SCENE_CONTROLLER.loadAllMenuScenes();
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");
    }
}
