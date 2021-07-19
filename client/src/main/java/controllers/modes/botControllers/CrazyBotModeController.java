package controllers.modes.botControllers;

import cards.Card;
import cards.buildings.Building;
import cards.buildings.cannons.Cannon;
import cards.buildings.towers.InfernoTower;
import cards.spells.Spell;
import cards.spells.arrows.Arrows;
import cards.spells.balls.FireBall;
import cards.spells.rages.Rage;
import cards.troops.Troop;
import cards.troops.archers.Archer;
import cards.troops.barbarians.Barbarian;
import cards.troops.dragons.BabyDragon;
import cards.troops.giants.Giant;
import cards.troops.pekkas.MiniPekka;
import cards.troops.valkyries.Valkyrie;
import cards.troops.wizards.Wizard;
import cards.utils.AttackAble;
import controllers.modes.CustomEventHandler;
import errors.DuplicateCardException;
import errors.InvalidCardException;
import errors.InvalidTowerException;
import events.CustomEvent;
import events.buildings.BuildingAddedEvent;
import events.buildings.BuildingDurationFinishedEvent;
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
                this.model.addCardToBotComingCards(this.createNewCardWithSameType(addedTroop, GlobalData.bot));

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
                this.model.addCardToPlayerComingCards(this.createNewCardWithSameType(addedTroop, GlobalData.user));

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
                this.model.removeCardFromInMapPlayerCards(killedTroop);
            }
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }

        event.consume();
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
    public void spellAddedEventHandler(SpellAddedEvent event) {
        Spell addedSpell = event.getSpell();
        Point2D position = event.getPosition();
        addedSpell.setPosition(position);

        User owner = event.getTargetPlayers().get(0);


        try {
            if (owner.equals(GlobalData.bot)) {
//                the bot has placed a spell
//                remove the card from list of the battle cards and add it to map
                this.model.addCardToInMapBotCards(addedSpell);

                this.model.removeCardFromBotBattleCards(addedSpell);

//                add a new spell to list of coming cards
                this.model.addCardToBotComingCards(this.createNewCardWithSameType(addedSpell, GlobalData.bot));

//               add the next card to the battle cards
                Card nextCard = this.model.getBotComingCards().get(0);
                this.model.removeCardFromBotComingCards(nextCard);
                this.model.addCardToBotBattleCards(nextCard);
            } else {
//               the player has placed a spell
//                remove the card from list of the battle cards and add it to map
                this.model.addCardToInMapPlayerCards(addedSpell);

                int index = this.model.getPlayerBattleCards().indexOf(addedSpell);
                this.model.removeCardFromPlayerBattleCards(addedSpell);

//                add a new spell to list of coming cards
                this.model.addCardToPlayerComingCards(this.createNewCardWithSameType(addedSpell, GlobalData.user));

//               add the next card to the battle cards
                Card nextCard = this.model.getBotComingCards().get(0);
                this.model.removeCardFromBotComingCards(nextCard);
                this.model.getPlayerBattleCards().add(index, nextCard);
            }
        } catch (DuplicateCardException | InvalidCardException exception) {
            exception.printStackTrace();
        }

        event.consume();
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
    public void spellDurationFinishedEventHandler(SpellDurationFinishedEvent event) {
        Spell expiredSpell = event.getSpell();
        User owner = event.getTargetPlayers().get(0);

        try {
            if (owner.equals(GlobalData.bot)) {
//                One of bot spells has been expired
                this.model.removeCardFromInMapBotCards(expiredSpell);
            } else {
//                One of player spells has been expired
                this.model.removeCardFromInMapPlayerCards(expiredSpell);
            }
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
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

    @Override
    public void buildingAddedEventHandler(BuildingAddedEvent event) {
        Building addedBuilding = event.getBuilding();
        Point2D position = event.getPosition();
        addedBuilding.setPosition(position);

        User owner = event.getTargetPlayers().get(0);

        try {
            if (owner.equals(GlobalData.bot)) {
//                the bot has dropped a spell
                this.model.addCardToInMapBotCards(addedBuilding);

                this.model.removeCardFromBotBattleCards(addedBuilding);
                this.model.addCardToBotComingCards(this.createNewCardWithSameType(addedBuilding, GlobalData.bot));

                Card nextCard = this.model.getBotComingCards().get(0);
                this.model.addCardToBotBattleCards(nextCard);
                this.model.removeCardFromBotComingCards(nextCard);
            } else {
//                the player has dropped a spell.
                this.model.addCardToInMapPlayerCards(addedBuilding);

//                get the index of the added card and remove it from battle cards
                int index = this.model.getPlayerBattleCards().indexOf(addedBuilding);
                this.model.removeCardFromPlayerBattleCards(addedBuilding);

//                add the card to coming cards list
                this.model.addCardToPlayerComingCards(this.createNewCardWithSameType(addedBuilding, GlobalData.user));

//                get the first element of the coming cards and put it in added card place.
                Card nextCard = this.model.getPlayerInMapTroops().get(0);
                this.model.getPlayerBattleCards().add(index, nextCard);

//                remove the next card from coming cards list.
                this.model.removeCardFromPlayerComingCards(nextCard);
            }
        } catch (DuplicateCardException | InvalidCardException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void buildingDurationFinishedHandler(BuildingDurationFinishedEvent event) {
        Building expiredBuilding = event.getBuilding();
        User owner = event.getTargetPlayers().get(0);

        try {
            if (owner.equals(GlobalData.bot)) {
                this.model.removeCardFromInMapBotCards(expiredBuilding);
            } else {
                this.model.removeCardFromInMapPlayerCards(expiredBuilding);
            }
        } catch (InvalidCardException e) {
            e.printStackTrace();
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
