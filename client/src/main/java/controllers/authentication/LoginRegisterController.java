package controllers.authentication;

import controllers.connector.Connector;
import controllers.Controller;
import commands.authenicationCommands.login.LoginCommand;
import commands.authenicationCommands.login.LoginResponseCommand;
import commands.authenicationCommands.register.RegisterCommand;
import commands.authenicationCommands.register.RegisterResponseCommand;
import globals.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * The type Login register controller.
 */
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

    /**
     * Change button handler.
     */
    @FXML
    void changeButtonHandler() {
        String temp = mainButtonText.getText();
        mainButtonText.setText(changeButtonText.getText());
        changeButtonText.setText(temp);
    }

    /**
     * Exit handler.
     */
    @FXML
    void exitHandler() {
        System.exit(0);
    }

    /**
     * Main button handler.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    @FXML
    void mainButtonHandler() throws IOException, ClassNotFoundException {
        message.setText("");
        if (this.isInputValid()) {
            message.setText("Please enter your username and password.");
        } else {
            if (this.mainButtonText.getText().equalsIgnoreCase("register")) {
                this.handleRegister();
            } else {
                this.handleLogin();
            }
        }
    }

    private void handleRegister() throws IOException, ClassNotFoundException {
        RegisterCommand registerCommand = new RegisterCommand(
                this.usernameField.getText().trim(),
                this.passwordField.getText().trim()
        );

        Connector connector = Connector.getInstance();
        connector.getRequest().writeObject(registerCommand);

        RegisterResponseCommand response = (RegisterResponseCommand) connector.getResponse().readObject();

        if (!response.isSuccessful()) {
            this.message.setText(response.getMessage());
        } else {
            GlobalData.user = response.getUser();

            Controller.SCENE_CONTROLLER.removeScene("Menu/ProfileMenu.fxml");
            Controller.SCENE_CONTROLLER.removeScene("Menu/MainMenu.fxml");

            Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");
        }
    }

    private void handleLogin() throws IOException, ClassNotFoundException {
        LoginCommand loginCommand = new LoginCommand(
                this.usernameField.getText().trim(),
                this.passwordField.getText().trim()
        );

        Connector connector = Connector.getInstance();
        connector.getRequest().writeObject(loginCommand);

        LoginResponseCommand response = (LoginResponseCommand) connector.getResponse().readObject();

        if (!response.isSuccessful()) {
            this.message.setText(response.getMessage());
        } else {
            GlobalData.user = response.getUser();

            Controller.SCENE_CONTROLLER.removeScene("Menu/ProfileMenu.fxml");
            Controller.SCENE_CONTROLLER.removeScene("Menu/MainMenu.fxml");
            Controller.SCENE_CONTROLLER.removeScene("Menu/BattleDeckMenu.fxml");
            Controller.SCENE_CONTROLLER.removeScene("Menu/HistoryMenu.fxml");
            Controller.SCENE_CONTROLLER.showScene("Menu/MainMenu.fxml");
        }
    }

    private boolean isInputValid() {
        return usernameField.getText().equals("") || passwordField.getText().equals("");
    }
}
