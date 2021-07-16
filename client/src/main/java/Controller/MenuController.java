package Controller;

import globals.UserData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class MenuController {
    @FXML
    void changeMenuHandler(MouseEvent event) {
        Node source = (Node) event.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);

        if (colIndex == null)
            colIndex = 0;

        switch (colIndex) {
            case 0 -> Controller.SCENE_CONTROLLER.showScene("Menu/ProfileMenu.fxml");
            case 1 -> Controller.SCENE_CONTROLLER.showScene("Menu/BattleDeckMenu.fxml");
            case 2 -> Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");
            case 3 -> Controller.SCENE_CONTROLLER.showScene("Menu/TrainingCampMenu.fxml");
            case 4 -> Controller.SCENE_CONTROLLER.showScene("Menu/HistoryMenu.fxml");
        }
    }
}
