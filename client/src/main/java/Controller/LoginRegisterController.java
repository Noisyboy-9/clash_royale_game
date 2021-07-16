package Controller;

import commands.authenicationCommands.register.RegisterCommand;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginRegisterController {
    @FXML
    private Text changeButtonText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text mainButtonText;

    @FXML
    private Text message;

    @FXML
    void changeButtonHandler(MouseEvent event) {
        String temp = mainButtonText.getText();
        mainButtonText.setText(changeButtonText.getText());
        changeButtonText.setText(temp);

    }

    @FXML
    void exitHandler(MouseEvent event) {
        System.exit(0);

    }

    @FXML
    void mainButtonHandler(MouseEvent event) {
        message.setText("");
        if (this.isInputValid()) {
            message.setText("Please enter your username and password.");
        } else {
            if (this.mainButtonText.getText().equalsIgnoreCase("register")) {
                this.handleRegister();
            } else {
                this.handleLogin();
            }
            // send command to server... if response was successful change the scene
            usernameField.setText("");
            passwordField.setText("");
            Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");
        }

    }

    private void handleRegister() {

    }

    private void handleLogin() {

    }

    private boolean isInputValid() {
        return usernameField.getText().equals("") || passwordField.getText().equals("");
    }

}
