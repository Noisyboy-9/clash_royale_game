package controllers.menus;

import cards.Card;
import controllers.Controller;
import globals.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import models.BotModeModel;

import java.util.ArrayList;

public class TrainingCampController extends MenuController {
    @FXML
    void crazyMode(MouseEvent event) {
        setData();
        Controller.SCENE_CONTROLLER.showScene("Map/CrazyBotMap.fxml");

    }

    @FXML
    void normalMode(MouseEvent event) {
        setData();
        Controller.SCENE_CONTROLLER.showScene("Map/NormalBotMap.fxml");

    }


    private void setData() {
        ArrayList<Card> playerAllCards = GlobalData.createCards(GlobalData.user);
        ArrayList<Card> playerBattleCards = (ArrayList<Card>) playerAllCards.subList(0, 4);

        ArrayList<Card> botAllCards = copyCards(playerAllCards);
        ArrayList<Card> botBattleCards = (ArrayList<Card>) botAllCards.subList(0, 4);

        GlobalData.opponentTeam.add(GlobalData.bot);
        GlobalData.gameModel = new BotModeModel(botAllCards, botBattleCards, playerAllCards, playerBattleCards);

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

}
