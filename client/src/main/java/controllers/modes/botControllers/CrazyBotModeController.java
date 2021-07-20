package controllers.modes.botControllers;

import cards.Card;
import cards.buildings.cannons.Cannon;
import cards.buildings.towers.InfernoTower;
import cards.spells.arrows.Arrows;
import cards.spells.balls.FireBall;
import cards.spells.rages.Rage;
import cards.troops.archers.Archer;
import cards.troops.barbarians.Barbarian;
import cards.troops.dragons.BabyDragon;
import cards.troops.giants.Giant;
import cards.troops.pekkas.MiniPekka;
import cards.troops.valkyries.Valkyrie;
import cards.troops.wizards.Wizard;
import cards.utils.AttackAble;
import errors.DuplicateCardException;
import errors.InvalidCardException;
import errors.InvalidTowerException;
import events.CustomEvent;
import events.cards.CardAddedEvent;
import events.cards.CardDeletedEvent;
import events.counts.CrownCountChangeEvent;
import events.towers.TowerActiveEvent;
import events.towers.TowerDestroyedEvent;
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
public class CrazyBotModeController extends BotController  {
    private final long eachFrameDuration;
    private long frameRemainingCount;

    /**
     * Instantiates a new Crazy bot mode controller.
     *
     * @param model the model
     */
    public CrazyBotModeController(BotModeModel model) {
        super(model);

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
    public void towerDestroyedEventHandler(TowerDestroyedEvent event) {
        Tower destroyedTower = event.getTower();
        User owner = event.getTargetPlayers().get(0);
        try {
            if (owner.equals(GlobalData.bot)) {
//                a tower of the bot has been destroyed
                this.model.deleteBotTower(destroyedTower);
            } else {
//                a tower of the player has been destroyed
                this.model.deletePlayerTower(destroyedTower);
            }
        } catch (InvalidTowerException e) {
            e.printStackTrace();
        }

        event.consume();
    }

    @Override
    public void towerActiveEventHandler(TowerActiveEvent event) {
        Tower activatedTower = event.getTower();
        AttackAble towerTarget = event.getAttackTarget();
        activatedTower.setTarget(towerTarget);
        event.consume();
    }

    @Override
    public void cardAddedEventHandler(CardAddedEvent event) {
        Card addedCard = event.getCard();
        Point2D position = event.getPosition();
        addedCard.setPosition(position);

        User owner = event.getTargetPlayers().get(0);

        try {
            if (owner.equals(GlobalData.bot)) {
//                a bot has added a card
                this.model.reduceBotElixirsCountBy(addedCard.getCost());
                this.model.addCardToBotBattleCards(addedCard);

                int index = this.model.getBotBattleCards().indexOf(addedCard);
                this.model.removeCardFromBotBattleCards(addedCard);

                this.model.addCardToBotComingCards(this.createNewCardWithSameType(addedCard, owner));

                Card nextCard = this.model.getBotComingCards().get(0);
                this.model.getBotBattleCards().add(index, nextCard);

                this.model.removeCardFromBotComingCards(nextCard);
            } else {
//                a player has added a card
                this.model.reducePlayerElixirsCountBy(addedCard.getCost());
                this.model.addCardToPlayerBattleCards(addedCard);

                int index = this.model.getPlayerBattleCards().indexOf(addedCard);
                this.model.removeCardFromPlayerBattleCards(addedCard);

                this.model.addCardToPlayerComingCards(this.createNewCardWithSameType(addedCard, owner));

                Card nextCard = this.model.getPlayerComingCards().get(0);
                this.model.getPlayerBattleCards().add(index, nextCard);

                this.model.removeCardFromPlayerComingCards(nextCard);
            }
        } catch (DuplicateCardException | InvalidCardException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void cardDeletedEventHandler(CardDeletedEvent event) {
        Card deletedCard = event.getCard();
        User owner = event.getTargetPlayers().get(0);

        try {
            if (owner.equals(GlobalData.bot)) {
//                one of bot cards is deleted
                this.model.removeCardFromInMapBotCards(deletedCard);
            } else {
//                one of player cards is deleted
                this.model.removeCardFromInMapPlayerCards(deletedCard);
            }
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
    }

    private Card createNewCardWithSameType(Card card, User owner) {
        if (card.isBarbarian()) {
            return Barbarian.create(owner);
        }

        if (card.isArcher()) {
            return Archer.create(owner);
        }

        if (card.isBabyDragon()) {
            return BabyDragon.create(owner);
        }

        if (card.isWizard()) {
            return Wizard.create(owner);
        }

        if (card.isMiniPeka()) {
            return MiniPekka.create(owner);
        }

        if (card.isGiant()) {
            return Giant.create(owner);
        }

        if (card.isValkyrie()) {
            return Valkyrie.create(owner);
        }

        if (card.isRage()) {
            return Rage.create(owner);
        }

        if (card.isFireBall()) {
            return FireBall.create(owner);
        }

        if (card.isArrows()) {
            return Arrows.create(owner);
        }

        if (card.isCannon()) {
            return Cannon.create(owner);
        }

        return InfernoTower.create(owner);
    }

    @Override
    public void crownCountChangeHandler(CrownCountChangeEvent event) {
        int crownCount = event.getNewCrownCount();
        User target = event.getTargetPlayers().get(0);

        if (target.equals(GlobalData.bot)) {
//            crown count of bot changed
            this.model.setBotCrownCount(crownCount);
        } else {
//            crown count of player changed
            this.model.setPlayerCrownCount(crownCount);
        }
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
//                handle exir count
//                handle bot move.
//                handle time thread.
//                after all these threads are finished: render screen again.
            }
        };

        timer.schedule(timerTask, 0, this.eachFrameDuration);
    }
}
