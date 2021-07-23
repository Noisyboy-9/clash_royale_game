package controllers.menus;

import controllers.Controller;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * The type Level up controller.
 */
public class LevelUpController {
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        this.levelText.setText("2");
        this.kingHitpoint.setText("2568");
        this.kingDamage.setText("53");
        this.arenaHitpoint.setText("1512");
        this.arenaDamage.setText("54");
    }

    @FXML
    private Text levelText;

    @FXML
    private Text kingHitpoint;

    @FXML
    private Text kingDamage;

    @FXML
    private Text arenaHitpoint;

    @FXML
    private Text arenaDamage;

    /**
     * Back to main menu.
     *
     * @param event the event
     */
    @FXML
    void backToMainMenu(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");
    }
}
