package controllers.modes;

import cards.Card;
import cards.troops.Troop;
import errors.DuplicateCardException;
import errors.InvalidCardException;
import events.CustomEvent;
import events.counts.CrownCountChangeEvent;
import events.spells.SpellAddedEvent;
import events.spells.SpellDurationFinishedEvent;
import events.towers.TowerActiveEvent;
import events.towers.TowerDestroyedEvent;
import events.troops.TroopAddedEvent;
import events.troops.TroopKilledEvent;
import globals.GlobalData;
import javafx.geometry.Point2D;
import models.BotModeModel;
import towers.Tower;
import user.User;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Crazy bot mode controller.
 */
public class CrazyBotModeController extends MapController implements CustomEventHandler {
    private final BotModeModel model;
    private final long eachFrameDuration;
    private long frameRemainingCount;

    /**
     * Instantiates a new Crazy bot mode controller.
     *
     * @param model the model
     */
    public CrazyBotModeController(BotModeModel model) {
        this.model = model;

        int FRAME_PER_SECOND = 30;
        this.eachFrameDuration = Math.round((double) 1000 / FRAME_PER_SECOND);
        this.frameRemainingCount = 3 * 60 * FRAME_PER_SECOND;

        this.setTimer();
    }

    @Override
    public void handle(CustomEvent event) {
        event.invokeHandler(this);
    }

    /**
     * Gets frame remaining count.
     *
     * @return the frame remaining count
     */
    public long getFrameRemainingCount() {
        return frameRemainingCount;
    }

    /**
     * Reduce frame remaining count by the specified amount.
     *
     * @param amount the amount
     */
    public void reduceFrameRemainingCount(int amount) {
        this.frameRemainingCount -= amount;
    }

    @Override
    public void troopAddedEventHandler(TroopAddedEvent event) {
        Troop addedTroop = event.getTroop();
        Point2D position = event.getPosition();
        addedTroop.setPosition(position);

        User target = event.getTargetPlayers().get(0);

        try {
            if (target.equals(GlobalData.bot)) {
//                the card has been placed by bot
                this.model.addCardToInMapBotCards(addedTroop);

                this.model.removeCardFromBotBattleCards(addedTroop);
                this.model.addCardToBotComingCards(addedTroop);

                Card nextCard = this.model.getBotComingCards().get(0);
                this.model.addCardToBotBattleCards(nextCard);
                this.model.removeCardFromBotComingCards(nextCard);
            } else {
//                the card has been placed by player
//                add the card to map
                this.model.addCardToInMapPlayerCards(addedTroop);

//                get the index of the added card and remove it from battle cards
                int index = this.model.getPlayerBattleCards().indexOf(addedTroop);
                this.model.removeCardFromPlayerBattleCards(addedTroop);

//                add the card to coming cards list
                this.model.addCardToPlayerComingCards(addedTroop);

//                get the first element of the coming cards and put it in added card place.
                Card nextCard = this.model.getPlayerInMapTroops().get(0);
                this.model.getPlayerBattleCards().add(index, nextCard);

//                remove the next card from coming cards list.
                this.model.removeCardFromPlayerComingCards(nextCard);
            }
        } catch (DuplicateCardException | InvalidCardException exception) {
            exception.printStackTrace();
        }


        event.consume();
    }

    @Override
    public void troopKilledEventHandler(TroopKilledEvent event) {
        Troop killedTroop = event.getTroop();
        User owner = event.getTargetPlayers().get(0);

        try {
            if (owner.equals(GlobalData.bot)) {
//                one of bot troops is killed
                this.model.removeCardFromInMapBotCards(killedTroop);
            } else {
//                one of player troops is killed
                this.model.removeCardFromInMapBotCards(killedTroop);
            }
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void towerDestroyedEventHandler(TowerDestroyedEvent event) {
        Tower destroyedTower = event.getTower();
    }

    @Override
    public void towerActiveEventHandler(TowerActiveEvent event) {

    }

    @Override
    public void spellAddedEventHandler(SpellAddedEvent event) {

    }

    @Override
    public void spellDurationFinishedEventHandler(SpellDurationFinishedEvent event) {

    }

    @Override
    public void crownCountChangeHandler(CrownCountChangeEvent event) {

    }

    /**
     * Reduce frame remaining count by one.
     */
    public void reduceFrameRemainingCount() {
        this.frameRemainingCount -= 1;
    }

    private void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                handle towers thread
//                handle troops thread.
//                handle spells thread.
//                handle buildings thread.
//                handle bot move.
//                handle time thread.
//                after all these threads are finished: render screen again.
            }
        };

        timer.schedule(timerTask, 0, this.eachFrameDuration);
    }
}
