package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginRegisterController
{
    @FXML
    private ImageView exitButton;

    @FXML
    private ImageView changeButton;

    @FXML
    private Text changeButtonText;

    @FXML
    private Text exitButtonText;

    @FXML
    private ImageView mainButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text mainButtonText;

    @FXML
    private Text message;

    @FXML
    void changeButtonHandler(MouseEvent event)
    {
        String temp = mainButtonText.getText();
        mainButtonText.setText(changeButtonText.getText());
        changeButtonText.setText(temp);

    }

    @FXML
    void exitHandler(MouseEvent event)
    {
        System.exit(0);

    }

    @FXML
    void mainButtonHandler(MouseEvent event)
    {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");

    }


}
