package controllers.modes.onlineControllers;

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
import controllers.connector.Connector;
import controllers.modes.BaseController;
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
import models.OnlineModeModel;
import towers.Tower;
import user.User;

import java.io.IOException;

/**
 * The type Online controller.
 */
public abstract class OnlineController extends BaseController {
    /**
     * The Model.
     */
    protected final OnlineModeModel model;

    /**
     * Instantiates a new Bot controller.
     */
    public OnlineController() {
        super();
        this.model = (OnlineModeModel) GlobalData.gameModel;
    }

    /**
     * Gets frame remaining count.
     *
     * @return the frame remaining count
     */
    public long getFrameRemainingCount() {
        return frameRemainingCount;
    }

    @Override
    public void towerDestroyedEventHandler(TowerDestroyedEvent event) {
        Tower destroyedTower = event.getTower();
        User owner = event.getTargetPlayers().get(0);
        try {
            this.removeItem(destroyedTower, owner);
        } catch (InvalidTowerException e) {
            e.printStackTrace();
        }

        this.broadcastEvent(event);
        event.consume();
    }

    @Override
    public void towerActiveEventHandler(TowerActiveEvent event) {
        Tower activatedTower = event.getTower();
        AttackAble towerTarget = event.getAttackTarget();
        activatedTower.setTarget(towerTarget);
        this.broadcastEvent(event);
        event.consume();
    }

    @Override
    public void cardAddedEventHandler(CardAddedEvent event) {
        Card addedCard = event.getCard();
        Point2D position = event.getPosition();
        addedCard.setPosition(position);

        User cardOwner = event.getTargetPlayers().get(0);

        try {
            if (this.isFriendlyTeam(cardOwner)) {
                this.chargeElixir(addedCard, cardOwner);
                int newCardPlacementIndex = this.deployFriendlyCard(addedCard);
                this.updateCardList(addedCard, cardOwner, newCardPlacementIndex);
            } else {
                this.deployOpponentCard(addedCard);
            }
        } catch (DuplicateCardException | InvalidCardException exception) {
            exception.printStackTrace();
        }

        this.broadcastEvent(event);
        event.consume();
    }

    @Override
    public void cardDeletedEventHandler(CardDeletedEvent event) {
        Card deletedCard = event.getCard();
        User owner = event.getTargetPlayers().get(0);

        try {
            this.removeItem(deletedCard, owner);
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
        this.broadcastEvent(event);
        event.consume();
    }

    @Override
    public void crownCountChangeHandler(CrownCountChangeEvent event) {
        int crownCount = event.getNewCrownCount();
        User target = event.getTargetPlayers().get(0);

        if (this.isFriendlyTeam(target)) {
//            crown count of player changed
            this.model.setPlayerCrownCount(crownCount);
        } else {
//            crown count of bot changed
            this.model.setOpponentCrownCount(crownCount);
        }

        this.broadcastEvent(event);
        event.consume();
    }

    /**
     * Reduce frame remaining count by one.
     */
    public void reduceFrameRemainingCount() {
        this.frameRemainingCount -= 1;
    }

    @Override
    public void handle(CustomEvent event) {
        event.invokeHandler(this);
    }

    /**
     * Broadcast event.
     *
     * @param event the event
     */
    public void broadcastEvent(CustomEvent event) {
        try {
            Connector.getInstance().getRequest().writeObject(event.toCommand());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets timer.
     */
    protected abstract void setTimer();

    /**
     * Is friendly team boolean.
     *
     * @param player the player
     * @return the boolean
     */
    protected boolean isFriendlyTeam(User player) {
        return GlobalData.playerTeam.contains(player);
    }

    private void deployOpponentCard(Card addedCard) throws DuplicateCardException {
        this.model.addCardToOpponentInMapCards(addedCard);
    }

    private void removeItem(Tower destroyedTower, User owner) throws InvalidTowerException {
        if (this.isFriendlyTeam(owner)) {
//            one the friendly team towers has been destroyed.
            this.model.deletePlayerTower(destroyedTower);
        } else {
//            one of the enemy team towers has been destroyed.
            this.model.deleteOpponentTower(destroyedTower);
        }
    }

    private void removeItem(Card deletedCard, User owner) throws InvalidCardException {
        if (this.isFriendlyTeam(owner)) {
//            one of the in map cards of the friendly team has been killed.
            this.model.removeCardFromInMapPlayerCards(deletedCard);
        } else {
//            one of the in map cards of the opponent team has been killed.
            this.model.removeCardFromOpponentInMapCards(deletedCard);
        }
    }

    private void chargeElixir(Card addedCard, User cardOwner) {
        if (this.isFriendlyTeam(cardOwner)) {
            this.model.reducePlayerElixirsCountBy(addedCard.getCost());
        }
    }

    private void updateCardList(Card addedCard, User cardOwner, int newCardPlacementIndex) throws DuplicateCardException, InvalidCardException {
        if (cardOwner.equals(GlobalData.user)) {
            this.model.addCardToPlayerComingCards(this.createNewCardWithSameType(addedCard, cardOwner));

            Card nextCard = this.model.getPlayerComingCards().get(0);
            this.model.getPlayerBattleCards().set(newCardPlacementIndex, nextCard);

            this.model.removeCardFromPlayerComingCards(nextCard);
        }
    }

    private int deployFriendlyCard(Card addedCard) throws DuplicateCardException, InvalidCardException {
        this.model.addCardToInMapPlayerCards(addedCard);
        this.model.removeCardFromPlayerBattleCards(addedCard);
        return this.model.getPlayerBattleCards().indexOf(addedCard);
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
}
