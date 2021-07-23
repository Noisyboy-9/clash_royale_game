package controllers.menus;

import cards.Card;
import commands.gameStateCommands.gameTimeCommands.GameStartCommand;
import commands.matchRequestCommands.FourPlayerMatchRequesterCommand;
import commands.matchRequestCommands.TwoPlayerMatchRequestCommand;
import controllers.Controller;
import controllers.connector.Connector;
import controllers.menus.runnables.GameCommandReceiverRunnable;
import controllers.modes.onlineControllers.OnlineController;
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

/**
 * The type Main menu controller.
 */
public class MainMenuController extends MenuController {
    @FXML
    private Text levelField;

    @FXML
    private Text usernameField;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        if (!Objects.isNull(GlobalData.user)) {
            this.usernameField.setText(GlobalData.user.getUsername());
            this.levelField.setText(GlobalData.user.getLevel().toString());
        }
    }

    /**
     * Two player mode.
     *
     * @param event the event
     */
    @FXML
    void twoPlayerMode(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/WaitingPage.fxml");
        try {
            Connector.getInstance().getRequest().writeObject(new TwoPlayerMatchRequestCommand(GlobalData.user));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setData();
        Controller.SCENE_CONTROLLER.showScene("Map/TwoPlayerMap.fxml");
        new Thread(new GameCommandReceiverRunnable((OnlineController) GlobalData.gameController));
    }

    /**
     * Four player mode.
     *
     * @param event the event
     */
    @FXML
    void fourPlayerMode(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/WaitingPage.fxml");
        try {
            Connector.getInstance().getRequest().writeObject(new FourPlayerMatchRequesterCommand(GlobalData.user));
            setData();
            Controller.SCENE_CONTROLLER.showScene("Map/FourPlayerMap.fxml");
            new Thread(new GameCommandReceiverRunnable((OnlineController) GlobalData.gameController));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log out handler.
     *
     * @param event the event
     */
    @FXML
    void logOutHandler(MouseEvent event) {
        Controller.SCENE_CONTROLLER.showScene("Menu/LoginRegisterPage.fxml");

    }


    private void setData() {
        ArrayList<Tower> enemyTowers = new ArrayList<>();
        ArrayList<Tower> friendlyTowers = new ArrayList<>();

        try {
            GameStartCommand command = (GameStartCommand) Connector.getInstance().getResponse().readObject();
            enemyTowers = command.getEnemyTowers();
            friendlyTowers = command.getFriendlyTowers();
            setPositions(enemyTowers);
            setPositions(friendlyTowers);
            GlobalData.playerTeam = command.getFriendlyTeam();
            GlobalData.opponentTeam = command.getEnemyTeam();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Card> playerAllCards = GlobalData.createCards(GlobalData.user);
        ArrayList<Card> playerBattleCards = new ArrayList<>(playerAllCards.subList(0, 4));

        GlobalData.gameModel = new OnlineModeModel(playerAllCards, playerBattleCards, enemyTowers);
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
