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
import models.BotModeModel;
import towers.Tower;
import user.User;

/**
 * The type Bot controller.
 */
public abstract class BotController extends BaseController {
    /**
     * The Model.
     */
    protected final BotModeModel model;

    /**
     * Sets timer.
     */
    protected abstract void setTimer();

    /**
     * Instantiates a new Bot controller.
     */
    public BotController() {
        super();
        this.model = (BotModeModel) GlobalData.gameModel;
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
        destroyedTower.deActive();
        User owner = event.getTargetPlayers().get(0);
        try {
            this.removeItem(destroyedTower, owner);
        } catch (InvalidTowerException e) {
            e.printStackTrace();
        }

        event.consume();
    }

    @Override
    public void towerActiveEventHandler(TowerActiveEvent event) {
        Tower activatedTower = event.getTower();
        activatedTower.activate();
        AttackAble towerTarget = event.getAttackTarget();
        activatedTower.setTarget(towerTarget);
        event.consume();
    }

    @Override
    public void cardAddedEventHandler(CardAddedEvent event) {
        Card addedCard = event.getCard();
        Point2D position = event.getPosition();
        addedCard.setPosition(position);

        User cardOwner = event.getTargetPlayers().get(0);

        try {
            this.chargeElixir(addedCard, cardOwner);
            int newCardPlacementIndex = this.deployCard(addedCard, cardOwner);
            this.updateCardList(addedCard, cardOwner, newCardPlacementIndex);
        } catch (DuplicateCardException | InvalidCardException exception) {
            exception.printStackTrace();
        }

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
        event.consume();
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

    @Override
    public void handle(CustomEvent event) {
        event.invokeHandler(this);
    }

    private void removeItem(Tower destroyedTower, User owner) throws InvalidTowerException {
        if (owner.equals(GlobalData.bot)) {
//                a tower of the bot has been destroyed
            this.model.deleteBotTower(destroyedTower);
        } else {
//                a tower of the player has been destroyed
            this.model.deletePlayerTower(destroyedTower);
        }
    }

    private void removeItem(Card deletedCard, User owner) throws InvalidCardException {
        if (owner.equals(GlobalData.bot)) {
//                one of bot cards is deleted
            this.model.removeCardFromInMapBotCards(deletedCard);
        } else {
//                one of player cards is deleted
            this.model.removeCardFromInMapPlayerCards(deletedCard);
        }
    }

    private void chargeElixir(Card addedCard, User cardOwner) {
        if (cardOwner.equals(GlobalData.user)) {
            this.model.reducePlayerElixirsCountBy(addedCard.getCost());
        }

        if (cardOwner.equals(GlobalData.bot)) {
            this.model.reduceBotElixirsCountBy(addedCard.getCost());
        }
    }

    private void updateCardList(Card addedCard, User cardOwner, int newCardPlacementIndex) throws DuplicateCardException, InvalidCardException {
        if (cardOwner.equals(GlobalData.bot)) {
            this.model.addCardToBotComingCards(this.createNewCardWithSameType(addedCard, cardOwner));

            Card nextCard = this.model.getBotComingCards().get(0);
            this.model.getBotBattleCards().set(newCardPlacementIndex, nextCard);

            this.model.removeCardFromBotComingCards(nextCard);
        }

        if (cardOwner.equals(GlobalData.user)) {
            this.model.addCardToPlayerComingCards(this.createNewCardWithSameType(addedCard, cardOwner));

            Card nextCard = this.model.getPlayerComingCards().get(0);
            this.model.getPlayerBattleCards().set(newCardPlacementIndex, nextCard);

            this.model.removeCardFromPlayerComingCards(nextCard);
        }
    }

    private int deployCard(Card addedCard, User cardOwner) throws DuplicateCardException, InvalidCardException {
        if (cardOwner.equals(GlobalData.bot)) {
            this.model.addCardToInMapBotCards(addedCard);
            int indexOfAddedCard = this.model.getBotBattleCards().indexOf(addedCard);
//            this.model.removeCardFromBotBattleCards(addedCard);
            return indexOfAddedCard;
        }

//        the player has deployed the card
        this.model.addCardToInMapPlayerCards(addedCard);
//        this.model.removeCardFromPlayerBattleCards(addedCard);
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
