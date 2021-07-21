package controllers.menus;

import controllers.Controller;
import javafx.fxml.FXML;

public class SplashScreenController {
    @FXML
    void moveToLoginPage() {
        Controller.SCENE_CONTROLLER.loadAllMenuScenes();
        Controller.SCENE_CONTROLLER.loadAllCardsUrls();
        Controller.SCENE_CONTROLLER.loadGifs();
        Controller.SCENE_CONTROLLER.loadCardBoxImages();
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");
    }
}
