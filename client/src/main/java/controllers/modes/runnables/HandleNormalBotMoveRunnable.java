package controllers.modes.runnables;

import cards.Card;
import controllers.modes.botControllers.NormalBotModeController;
import events.cards.CardAddedEvent;
import globals.GlobalData;
import javafx.event.Event;
import javafx.geometry.Point2D;
import models.BotModeModel;

import java.util.Objects;

public record HandleNormalBotMoveRunnable(BotModeModel model, NormalBotModeController controller) implements Runnable {
    @Override
    public void run() {
        Card lastPlayerAddedCard = this.model
                .getPlayerInMapCards()
                .get(this.model.getPlayerInMapCards().size() - 1);

        Card cardToAdd = null;
        for (Card card : this.model.getBotBattleCards()) {
            if (card.getClass().getSimpleName().equals(lastPlayerAddedCard.getClass().getSimpleName())) {
                cardToAdd = GlobalData.getCardBasedOnName(card.getClass().getSimpleName(), GlobalData.bot);
            }
        }

        if (Objects.isNull(cardToAdd)) return;

        Point2D position = controller.transferPosition(lastPlayerAddedCard.getPosition());

        CardAddedEvent cardAddedEvent = new CardAddedEvent(Event.ANY, GlobalData.opponentTeam, cardToAdd, position);

        controller.cardAddedEventHandler(cardAddedEvent);
    }
}
