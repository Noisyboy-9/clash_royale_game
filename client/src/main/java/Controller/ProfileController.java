package Controller;

import Globals.UserData;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Objects;

public class ProfileController extends MenuController {
    // TODO: ۱۴/۰۷/۲۰۲۱ some methods needed to update username, level and battle deck of player (be careful that level is placed under cards too)

    @FXML
    private GridPane battleCards;

    @FXML
    private Text levelFiled;

    @FXML
    private Text usernameField;

    @FXML
    public void initialize() {
        if (!Objects.isNull(UserData.user)) {
            this.usernameField.setText(UserData.user.getUsername());
            this.levelFiled.setText(UserData.user.getLevel().toString());
        }
    }
}
