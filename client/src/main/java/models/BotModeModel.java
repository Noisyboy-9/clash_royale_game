package models;

import cards.Card;
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
                .filter(card -> !playerAllCards.contains(card))
                .collect(Collectors.toCollection(ArrayList::new));

//        bot coming cards is : All cards that are in botAllCards but not in botBattleCards
        this.botComingCards = botAllCards.stream()
                .filter(card -> !botBattleCards.contains(card))
                .collect(Collectors.toCollection(ArrayList::new));
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

    private ArrayList<Tower> createTowers(User owner) {
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(QueenTower.create(owner));
        towers.add(KingTower.create(owner));
        towers.add(QueenTower.create(owner));
        return towers;
    }


    public void deletePlayerTower(Tower tower) throws InvalidTowerException {
        if (!this.playerTowers.contains(tower)) {
            throw new InvalidTowerException("Player doesn't have any such tower.");
        }

        this.playerTowers.remove(tower);
    }


    public void deleteBotTower(Tower tower) throws InvalidTowerException {
        if (!this.botTowers.contains(tower)) {
            throw new InvalidTowerException("bot doesn't have any such tower.");
        }

        this.botTowers.remove(tower);
    }
}
