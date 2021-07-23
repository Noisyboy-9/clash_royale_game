package models;

import cards.Card;
import cards.buildings.Building;
import cards.spells.Spell;
import cards.troops.Troop;
import cards.utils.AttackAble;
import errors.DuplicateCardException;
import errors.InvalidCardException;
import errors.InvalidTowerException;
import globals.GlobalData;
import towers.KingTower;
import towers.Tower;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Bot mode model.
 */
public class BotModeModel extends GameModel {
    private final ArrayList<Tower> botTowers;
    private final ArrayList<Card> botAllCards;
    private final ArrayList<Card> botInMapCards;
    private final ArrayList<Card> botBattleCards;
    private final ArrayList<Card> botComingCards;
    private int botElixirCount;
    private int botCrownCount;

    /**
     * Instantiates a new Bot mode model.
     *
     * @param botAllCards       the bot all cards
     * @param botBattleCards    the bot battle cards
     * @param playerAllCards    the player all cards
     * @param playerBattleCards the player battle cards
     */
    public BotModeModel(ArrayList<Card> botAllCards,
                        ArrayList<Card> botBattleCards,
                        ArrayList<Card> playerAllCards,
                        ArrayList<Card> playerBattleCards) {
        super(playerAllCards, playerBattleCards);
        this.botTowers = this.createTowers(GlobalData.bot);
        this.botAllCards = botAllCards;
        this.botInMapCards = new ArrayList<>();
        this.botBattleCards = botBattleCards;
        this.botElixirCount = 4;

        //        player coming cards is: All player cards that are in playerAllCards but not in botBattleCards

//        bot coming cards is : All cards that are in botAllCards but not in botBattleCards
        this.botComingCards = botAllCards.stream()
                .filter(card -> !botBattleCards.contains(card))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets bot crown count.
     *
     * @return the bot crown count
     */
    public int getBotCrownCount() {
        return botCrownCount;
    }

    /**
     * Sets bot crown count.
     *
     * @param botCrownCount the bot crown count
     */
    public void setBotCrownCount(int botCrownCount) {
        this.botCrownCount = botCrownCount;
    }

    /**
     * Gets bot elixir count.
     *
     * @return the bot elixir count
     */
    public int getBotElixirCount() {
        return botElixirCount;
    }

    /**
     * Sets bot elixir count.
     *
     * @param amount the amount
     */
    public void reduceBotElixirsCountBy(int amount) {
        this.botElixirCount -= amount;
    }

    /**
     * Increase bot elixir count by.
     *
     * @param amount the amount
     */
    public void increaseBotElixirCountBy(int amount) {
        this.botElixirCount += amount;
    }

    /**
     * Gets bot towers.
     *
     * @return the bot towers
     */
    public ArrayList<Tower> getBotTowers() {
        return botTowers;
    }

    /**
     * Gets bot all cards.
     *
     * @return the bot all cards
     */
    public ArrayList<Card> getBotAllCards() {
        return botAllCards;
    }

    /**
     * Gets bot in map cards.
     *
     * @return the bot in map cards
     */
    public ArrayList<Card> getBotInMapCards() {
        return botInMapCards;
    }

    /**
     * Gets bot battle cards.
     *
     * @return the bot battle cards
     */
    public ArrayList<Card> getBotBattleCards() {
        return botBattleCards;
    }

    /**
     * Gets bot coming cards.
     *
     * @return the bot coming cards
     */
    public ArrayList<Card> getBotComingCards() {
        return botComingCards;
    }

    /**
     * Delete bot tower.
     *
     * @param tower the tower
     * @throws InvalidTowerException the invalid tower exception
     */
    public void deleteBotTower(Tower tower) throws InvalidTowerException {
        if (!this.botTowers.contains(tower)) {
            throw new InvalidTowerException("bot doesn't have any such tower.");
        }

        this.botTowers.remove(tower);
    }

    /**
     * Add card to in map bot cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToInMapBotCards(Card card) throws DuplicateCardException {
        if (this.botInMapCards.contains(card)) {
            throw new DuplicateCardException("The bot already have the card in map.");
        }

        this.botInMapCards.add(card);
    }

    /**
     * Remove card from in map bot cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromInMapBotCards(Card card) throws InvalidCardException {
        if (!this.botInMapCards.contains(card)) {
            throw new InvalidCardException("The bot doesn't have any such card in map.");
        }

        this.botInMapCards.remove(card);
    }

    /**
     * Add card to bot in coming cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToBotComingCards(Card card) throws DuplicateCardException {
        if (this.botComingCards.contains(card)) {
            throw new DuplicateCardException("The bot already have the card in coming card list");
        }

        this.botComingCards.add(card);
    }

    /**
     * Remove card from bot in coming cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromBotComingCards(Card card) throws InvalidCardException {
        if (!this.botComingCards.contains(card)) {
            throw new InvalidCardException("The bot doesn't have any such card in coming cards");
        }

        this.botComingCards.remove(card);
    }

    /**
     * Add card to bot battle cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToBotBattleCards(Card card) throws DuplicateCardException {
        if (this.botBattleCards.contains(card)) {
            throw new DuplicateCardException("The bot already have the card in battle card list");
        }

        this.botBattleCards.add(card);
    }

    /**
     * Remove card from bot battle cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromBotBattleCards(Card card) throws InvalidCardException {
        if (!this.botBattleCards.contains(card)) {
            throw new InvalidCardException("The bot doesn't have any such card in battle cards");
        }

        this.botBattleCards.remove(card);
    }

    /**
     * Gets bot in map troops.
     *
     * @return the bot in map troops
     */
    public ArrayList<Troop> getBotInMapTroops() {
        ArrayList<Troop> troops = new ArrayList<>();
        for (Card card : this.botInMapCards) {
            if (card.isTroop()) {
                troops.add((Troop) card);
            }
        }

        return troops;
    }

    /**
     * Gets bot in map spells.
     *
     * @return the bot in map spells
     */
    public ArrayList<Spell> getBotInMapSpells() {
        ArrayList<Spell> troops = new ArrayList<>();
        for (Card card : this.botInMapCards) {
            if (card.isSpell()) {
                troops.add((Spell) card);
            }
        }

        return troops;
    }

    /**
     * Gets bot in map buildings.
     *
     * @return the bot in map buildings
     */
    public ArrayList<Building> getBotInMapBuildings() {
        ArrayList<Building> troops = new ArrayList<>();
        for (Card card : this.botInMapCards) {
            if (card.isBuilding()) {
                troops.add((Building) card);
            }
        }

        return troops;
    }

    /**
     * Gets bot in map attack ables cards.
     *
     * @return the bot in map attack ables cards
     */
    public ArrayList<AttackAble> getBotInMapAttackAblesCards() {
        ArrayList<AttackAble> attackAbles = new ArrayList<>();
        attackAbles.addAll(this.getBotInMapTroops());
        attackAbles.addAll(this.getBotInMapBuildings());
        return attackAbles;
    }

    /**
     * Gets bot in map attack ables.
     *
     * @return the bot in map attack ables
     */
    public ArrayList<AttackAble> getBotInMapAttackAbles() {
        ArrayList<AttackAble> attackAbles = new ArrayList<>();
        attackAbles.addAll(this.getBotInMapTroops());
        attackAbles.addAll(this.getBotInMapBuildings());
        attackAbles.addAll(this.getBotTowers());
        return attackAbles;
    }

    /**
     * Gets bot king tower.
     *
     * @return the bot king tower
     */
    public KingTower getBotKingTower() {
        for (Tower tower : this.botTowers) {
            if (tower.isKingTower()) {
                return (KingTower) tower;
            }
        }
        return null;
    }
}
