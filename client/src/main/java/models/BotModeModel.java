package models;

import cards.Card;
import cards.buildings.Building;
import cards.spells.Spell;
import cards.troops.Troop;
import errors.DuplicateCardException;
import errors.InvalidCardException;
import errors.InvalidTowerException;
import globals.GlobalData;
import towers.KingTower;
import towers.QueenTower;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Bot mode model.
 */
public class BotModeModel {
    private final ArrayList<Tower> botTowers;
    private final ArrayList<Card> botAllCards;
    private final ArrayList<Card> botInMapCards;
    private final ArrayList<Card> botBattleCards;
    private final ArrayList<Card> botComingCards;
    private final ArrayList<Tower> playerTowers;
    private final ArrayList<Card> playerAllCards;
    private final ArrayList<Card> playerInMapCards;
    private final ArrayList<Card> playerBattleCards;
    private final ArrayList<Card> playerComingCards;
    private int botElixirCount;
    private int playerElixirCount;
    private int playerCrownCount;
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
        this.botTowers = this.createTowers(GlobalData.bot);
        this.botAllCards = botAllCards;
        this.botInMapCards = new ArrayList<>();
        this.botBattleCards = botBattleCards;
        this.botElixirCount = 4;

        this.playerTowers = createTowers(GlobalData.user);
        this.playerAllCards = playerAllCards;
        this.playerInMapCards = new ArrayList<>();
        this.playerBattleCards = playerBattleCards;
        this.playerElixirCount = 4;

//        player coming cards is: All player cards that are in playerAllCards but not in botBattleCards
        this.playerComingCards = playerAllCards.stream()
                .filter(card -> !playerBattleCards.contains(card))
                .collect(Collectors.toCollection(ArrayList::new));

//        bot coming cards is : All cards that are in botAllCards but not in botBattleCards
        this.botComingCards = botAllCards.stream()
                .filter(card -> !botBattleCards.contains(card))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets player crown count.
     *
     * @return the player crown count
     */
    public int getPlayerCrownCount() {
        return playerCrownCount;
    }

    /**
     * Sets player crown count.
     *
     * @param playerCrownCount the player crown count
     */
    public void setPlayerCrownCount(int playerCrownCount) {
        this.playerCrownCount = playerCrownCount;
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
     * @param botElixirCount the bot elixir count
     */
    public void setBotElixirCount(int botElixirCount) {
        this.botElixirCount = botElixirCount;
    }

    /**
     * Gets player elixir count.
     *
     * @return the player elixir count
     */
    public int getPlayerElixirCount() {
        return playerElixirCount;
    }

    /**
     * Sets player elixir count.
     *
     * @param playerElixirCount the player elixir count
     */
    public void setPlayerElixirCount(int playerElixirCount) {
        this.playerElixirCount = playerElixirCount;
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
     * Gets player towers.
     *
     * @return the player towers
     */
    public ArrayList<Tower> getPlayerTowers() {
        return playerTowers;
    }

    /**
     * Gets player all cards.
     *
     * @return the player all cards
     */
    public ArrayList<Card> getPlayerAllCards() {
        return playerAllCards;
    }

    /**
     * Gets player in map cards.
     *
     * @return the player in map cards
     */
    public ArrayList<Card> getPlayerInMapCards() {
        return playerInMapCards;
    }

    /**
     * Gets player battle cards.
     *
     * @return the player battle cards
     */
    public ArrayList<Card> getPlayerBattleCards() {
        return playerBattleCards;
    }

    /**
     * Gets player coming cards.
     *
     * @return the player coming cards
     */
    public ArrayList<Card> getPlayerComingCards() {
        return playerComingCards;
    }

    /**
     * Delete player tower.
     *
     * @param tower the tower
     * @throws InvalidTowerException the invalid tower exception
     */
    public void deletePlayerTower(Tower tower) throws InvalidTowerException {
        if (!this.playerTowers.contains(tower)) {
            throw new InvalidTowerException("Player doesn't have any such tower.");
        }

        this.playerTowers.remove(tower);
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
     * Add card to in map player cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToInMapPlayerCards(Card card) throws DuplicateCardException {
        if (this.playerInMapCards.contains(card)) {
            throw new DuplicateCardException("Player already have the specified card in map.");
        }

        this.playerInMapCards.add(card);
    }

    /**
     * Remove card from in map player cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromInMapPlayerCards(Card card) throws InvalidCardException {
        if (!this.playerInMapCards.contains(card)) {
            throw new InvalidCardException("The player doesn't have any such card in map.");
        }

        this.playerInMapCards.remove(card);
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
     * Add card to player in coming cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToPlayerComingCards(Card card) throws DuplicateCardException {
        if (this.playerComingCards.contains(card)) {
            throw new DuplicateCardException("player already have the card in coming cards list.");
        }

        this.playerComingCards.add(card);
    }

    /**
     * Remove card from player coming cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromPlayerComingCards(Card card) throws InvalidCardException {
        if (!this.playerComingCards.contains(card)) {
            throw new InvalidCardException("The player doesn't have any such card in coming cards.");
        }

        this.playerComingCards.remove(card);
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
     * Add card to player battle cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToPlayerBattleCards(Card card) throws DuplicateCardException {
        if (this.playerBattleCards.contains(card)) {
            throw new DuplicateCardException("The player already have the card in battle cards list.");
        }

        this.playerBattleCards.add(card);
    }

    /**
     * Remove card from player battle cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromPlayerBattleCards(Card card) throws InvalidCardException {
        if (!this.playerBattleCards.contains(card)) {
            throw new InvalidCardException("The player doesn't have any such card in battle cards.");
        }

        this.playerBattleCards.remove(card);
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
     * Gets player in map troops.
     *
     * @return the player in map troops
     */
    public ArrayList<Troop> getPlayerInMapTroops() {
        ArrayList<Troop> troops = new ArrayList<>();
        for (Card card : this.playerInMapCards) {
            if (card instanceof Troop) {
                troops.add((Troop) card);
            }
        }

        return troops;
    }

    /**
     * Gets bot in map troops.
     *
     * @return the bot in map troops
     */
    public ArrayList<Troop> getBotInMapTroops() {
        ArrayList<Troop> troops = new ArrayList<>();
        for (Card card : this.botInMapCards) {
            if (card instanceof Troop) {
                troops.add((Troop) card);
            }
        }

        return troops;
    }

    /**
     * Gets player in map spells.
     *
     * @return the player in map spells
     */
    public ArrayList<Spell> getPlayerInMapSpells() {
        ArrayList<Spell> troops = new ArrayList<>();
        for (Card card : this.playerInMapCards) {
            if (card instanceof Spell) {
                troops.add((Spell) card);
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
            if (card instanceof Spell) {
                troops.add((Spell) card);
            }
        }

        return troops;
    }

    /**
     * Gets player in map buildings.
     *
     * @return the player in map buildings
     */
    public ArrayList<Building> getPlayerInMapBuildings() {
        ArrayList<Building> troops = new ArrayList<>();
        for (Card card : this.playerInMapCards) {
            if (card instanceof Building) {
                troops.add((Building) card);
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
            if (card instanceof Building) {
                troops.add((Building) card);
            }
        }

        return troops;
    }

    private ArrayList<Tower> createTowers(User owner) {
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(QueenTower.create(owner));
        towers.add(KingTower.create(owner));
        towers.add(QueenTower.create(owner));
        return towers;
    }
}
