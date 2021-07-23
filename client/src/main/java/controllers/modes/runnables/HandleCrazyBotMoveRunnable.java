package controllers.modes.runnables;

import cards.Card;
import cards.utils.AttackAble;
import controllers.modes.botControllers.CrazyBotModeController;
import events.cards.CardAddedEvent;
import globals.GlobalData;
import javafx.event.Event;
import javafx.geometry.Point2D;
import models.BotModeModel;

import java.util.ArrayList;
import java.util.Random;


/**
 * The type Handle crazy bot move runnable.
 */
public record HandleCrazyBotMoveRunnable(BotModeModel model, CrazyBotModeController controller) implements Runnable {
    @Override
    public void run() {
        if (model.getBotElixirCount() == 8) {
            Card card = this.selectARandomCardFrom(this.model.getBotBattleCards());
            Point2D location = this.selectRandomLocation();

            CardAddedEvent event = new CardAddedEvent(
                    Event.ANY,
                    GlobalData.opponentTeam,
                    card,
                    location
            );

            controller.cardAddedEventHandler(event);
        }
    }

    private Card selectARandomCardFrom(ArrayList<Card> botCards) {
        Random random = new Random();
        return botCards.get(random.nextInt(botCards.size()));
    }

    private Point2D selectRandomLocation() {
        Random random = new Random();
        int placementX = 6;

        if (random.nextBoolean()) {
            placementX = 17; // generate a number between 3,17
        }

        int placementY = random.nextInt(8) + 20; // generate a number between 20, 28

        while (!this.isValidLocation(placementX, placementY)) {
            placementX = random.nextInt();
            placementY = random.nextInt();
        }

        return new Point2D(placementX, placementY);
    }

    private boolean isValidLocation(int placementX, int placementY) {
//            the input position can't be a position of friendly or enemy troop, buildings or tower.
        Point2D inputPosition = new Point2D(placementX, placementY);


        for (AttackAble target : this.model.getPlayerInMapAttackAbles()) {
            if (controller.transferPosition(target.getPosition()).equals(inputPosition)) {
                return false;
            }
        }


        for (AttackAble target : this.model.getBotInMapAttackAbles()) {
            if (target.getPosition().equals(inputPosition)) {
                return false;
            }
        }

        return true;
    }
}
