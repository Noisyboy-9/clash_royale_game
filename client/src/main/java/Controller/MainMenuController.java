package Controller;

import globals.UserData;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Objects;

public class MainMenuController extends MenuController {
    @FXML
    private Text levelField;

    @FXML
    private Text usernameField;

    @FXML
    public void initialize() {
        if (!Objects.isNull(UserData.user)) {
            this.usernameField.setText(UserData.user.getUsername());
            this.levelField.setText(UserData.user.getLevel().toString());
        }
    }

    @FXML
    void twoPlayerMode(MouseEvent event) {
        // TODO: ۱۴/۰۷/۲۰۲۱ show wait scene and send command to server
        Controller.SCENE_CONTROLLER.showScene("Menu/WaitingPage.fxml");
    }

    @FXML
    void fourPlayerMode(MouseEvent event) {
        // TODO: ۱۴/۰۷/۲۰۲۱ show wait scene and send command to server
        Controller.SCENE_CONTROLLER.showScene("Menu/WaitingPage.fxml");

    }

    @FXML
    void logOutHandler(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");

    }
}
