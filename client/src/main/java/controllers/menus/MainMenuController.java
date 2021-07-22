package controllers.menus;

import cards.Card;
import commands.gameStateCommands.gameTimeCommands.GameStartCommand;
import controllers.Controller;
import controllers.connector.Connector;
import globals.GlobalData;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.OnlineModeModel;
import towers.Tower;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenuController extends MenuController {
    @FXML
    private Text levelField;

    @FXML
    private Text usernameField;

    @FXML
    public void initialize() {
        if (!Objects.isNull(GlobalData.user)) {
            this.usernameField.setText(GlobalData.user.getUsername());
            this.levelField.setText(GlobalData.user.getLevel().toString());
        }
    }

    @FXML
    void twoPlayerMode(MouseEvent event) {
        ArrayList<Tower> enemyTowers = new ArrayList<>();
        ArrayList<Tower> friendlyTowers = new ArrayList<>();

        try {
            GameStartCommand command = (GameStartCommand) Connector.getInstance().getResponse().readObject();
            enemyTowers = command.getEnemyTowers();
            friendlyTowers = command.getFriendlyTowers();
            setPositions(enemyTowers);
            setPositions(friendlyTowers);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Card> playerAllCards = GlobalData.createCards(GlobalData.user);
        ArrayList<Card> playerBattleCards = new ArrayList<>(playerAllCards.subList(0, 4));

        




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


    private void setPositions(ArrayList<Tower> towers) {
        towers.get(0).setPosition(new Point2D(6, 29));
        towers.get(1).setPosition(new Point2D(17, 29));

        if (towers.size() > 3) {
            towers.get(2).setPosition(new Point2D(9, 32));
            towers.get(3).setPosition(new Point2D(14, 32));
        }
        else {
            towers.get(2).setPosition(new Point2D(9, 34));

        }

    }


}
