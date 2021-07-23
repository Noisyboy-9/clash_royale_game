package controllers.menus;

import cards.Card;
import controllers.Controller;
import globals.GlobalData;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import models.BotModeModel;
import towers.Tower;

import java.util.ArrayList;

/**
 * The type Training camp controller.
 */
public class TrainingCampController extends MenuController {
    /**
     * Crazy mode.
     *
     * @param event the event
     */
    @FXML
    void crazyMode(MouseEvent event) {
        setData();
        Controller.SCENE_CONTROLLER.showScene("Map/CrazyBotMap.fxml");
    }

    /**
     * Normal mode.
     *
     * @param event the event
     */
    @FXML
    void normalMode(MouseEvent event) {
        setData();
        Controller.SCENE_CONTROLLER.showScene("Map/NormalBotMap.fxml");
    }


    private void setData() {
        ArrayList<Card> playerAllCards = GlobalData.createCards(GlobalData.user);
        ArrayList<Card> playerBattleCards = new ArrayList<>(playerAllCards.subList(0, 4));

        ArrayList<Card> botAllCards = copyCards(playerAllCards);
        ArrayList<Card> botBattleCards = new ArrayList<>(botAllCards.subList(0, 4));

        GlobalData.opponentTeam.add(GlobalData.bot);
        GlobalData.playerTeam.add(GlobalData.user);
        GlobalData.gameModel = new BotModeModel(botAllCards, botBattleCards, playerAllCards, playerBattleCards);
        setPositions(GlobalData.gameModel.getPlayerTowers());
        setPositions(((BotModeModel)GlobalData.gameModel).getBotTowers());
        GlobalData.bot.setLevel(GlobalData.user.getLevel());
    }


    private ArrayList<Card> copyCards(ArrayList<Card> playerCards) {
        ArrayList<Card> botCards = new ArrayList<>();

        for (Card card : playerCards) {
            String cardClass = card.getClass().getSimpleName();
            Card botCard = GlobalData.getCardBasedOnName(cardClass, GlobalData.bot);
            botCards.add(botCard);
        }

        return botCards;
    }


    private void setPositions(ArrayList<Tower> towers) {
        towers.get(0).setPosition(new Point2D(6, 29));
        towers.get(1).setPosition(new Point2D(17, 29));
        towers.get(2).setPosition(new Point2D(9, 34));
    }
}
