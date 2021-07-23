package controllers.menus;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 * The type Waiting page controller.
 */
public class WaitingPageController {
    /**
     * Cancel handler.
     *
     * @param event the event
     */
    @FXML
    void cancelHandler(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");

    }

}
