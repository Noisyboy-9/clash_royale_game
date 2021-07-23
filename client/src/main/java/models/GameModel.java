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
import towers.QueenTower;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The type Game model.
 */
public abstract class GameModel {
    /**
     * The Player towers.
     */
    protected final ArrayList<Tower> playerTowers;
    /**
     * The Player all cards.
     */
    protected final ArrayList<Card> playerAllCards;
    /**
     * The Player in map cards.
     */
    protected final ArrayList<Card> playerInMapCards;
    /**
     * The Player battle cards.
     */
    protected final ArrayList<Card> playerBattleCards;
    /**
     * The Player coming cards.
     */
    protected final ArrayList<Card> playerComingCards;
    /**
     * The Player elixir count.
     */
    protected int playerElixirCount;
    private int playerCrownCount;

    /**
     * Instantiates a new Game model.
     *
     * @param playerAllCards    the player all cards
     * @param playerBattleCards the player battle cards
     */
    public GameModel(ArrayList<Card> playerAllCards, ArrayList<Card> playerBattleCards) {
        this.playerTowers = createTowers(GlobalData.user);
        this.playerAllCards = playerAllCards;
        this.playerInMapCards = new ArrayList<>();
        this.playerBattleCards = playerBattleCards;
        this.playerComingCards = playerAllCards.stream()
                .filter(card -> !playerBattleCards.contains(card))
                .collect(Collectors.toCollection(ArrayList::new));
        this.playerElixirCount = 4;
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
     * @param amount the amount
     */
    public void reducePlayerElixirsCountBy(int amount) {
        this.playerElixirCount -= amount;
    }

    /**
     * Increase player elixirs count by.
     *
     * @param amount the amount
     */
    public void increasePlayerElixirsCountBy(int amount) {
        if (this.playerElixirCount == 10) {
            return;
        }

        if (this.playerElixirCount + amount >= 10) {
            this.playerElixirCount = 10;
            return;
        }

        this.playerElixirCount += amount;
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
     * Gets player in map troops.
     *
     * @return the player in map troops
     */
    public ArrayList<Troop> getPlayerInMapTroops() {
        ArrayList<Troop> troops = new ArrayList<>();
        for (Card card : this.playerInMapCards) {
            if (card.isTroop()) {
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
        ArrayList<Spell> spells = new ArrayList<>();

        for (Card card : this.playerInMapCards) {
            if (card.isSpell()) {
                spells.add((Spell) card);
            }
        }

        return spells;
    }

    /**
     * Gets player in map buildings.
     *
     * @return the player in map buildings
     */
    public ArrayList<Building> getPlayerInMapBuildings() {
        ArrayList<Building> troops = new ArrayList<>();
        for (Card card : this.playerInMapCards) {
            if (card.isBuilding()) {
                troops.add((Building) card);
            }
        }

        return troops;
    }

    /**
     * Gets player attack ables.
     *
     * @return the player attack ables
     */
    public ArrayList<AttackAble> getPlayerInMapAttackAblesCards() {
        ArrayList<AttackAble> attackAbles = new ArrayList<>();
        attackAbles.addAll(this.getPlayerInMapTroops());
        attackAbles.addAll(this.getPlayerInMapBuildings());
        return attackAbles;
    }

    /**
     * Gets player attack ables.
     *
     * @return the player attack ables
     */
    public ArrayList<AttackAble> getPlayerInMapAttackAbles() {
        ArrayList<AttackAble> attackAbles = new ArrayList<>();
        attackAbles.addAll(this.getPlayerInMapTroops());
        attackAbles.addAll(this.getPlayerInMapBuildings());
        attackAbles.addAll(this.getPlayerTowers());
        return attackAbles;
    }

    /**
     * Gets player king tower.
     *
     * @return the player king tower
     */
    public KingTower getPlayerKingTower() {
        for (Tower tower : this.playerTowers) {
            if (tower.isKingTower()) {
                return (KingTower) tower;
            }
        }
        return null;
    }

    /**
     * Create towers array list.
     *
     * @param owner the owner
     * @return the array list
     */
    protected ArrayList<Tower> createTowers(User owner) {
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(QueenTower.create(owner));
        towers.add(QueenTower.create(owner));
        towers.add(KingTower.create(owner));
        return towers;
    }


    /**
     * Remove card from map.
     *
     * @param card the card
     */
    protected void removeCardFromMap(Card card) {

    }



}
