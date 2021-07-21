package models;

import cards.Card;
import cards.buildings.Building;
import cards.spells.Spell;
import cards.troops.Troop;
import cards.utils.AttackAble;
import errors.DuplicateCardException;
import errors.InvalidCardException;
import errors.InvalidTowerException;
import towers.Tower;

import java.util.ArrayList;

/**
 * The type Online mode model.
 */
public class OnlineModeModel extends GameModel {
    private final ArrayList<Tower> opponentTowers;
    private final ArrayList<Card> opponentInMapCards;
    private int opponentCrownCount;

    /**
     * Instantiates a new Online mode model.
     *
     * @param playerAllCards    the player all cards
     * @param playerBattleCards the player battle cards
     * @param opponentTowers    the opponent towers
     */
    public OnlineModeModel(ArrayList<Card> playerAllCards, ArrayList<Card> playerBattleCards, ArrayList<Tower> opponentTowers) {
        super(playerAllCards, playerBattleCards);
        this.opponentTowers = opponentTowers;
        this.opponentInMapCards = new ArrayList<>();

    }

    /**
     * Gets opponent towers.
     *
     * @return the opponent towers
     */
    public ArrayList<Tower> getOpponentTowers() {
        return opponentTowers;
    }

    /**
     * Gets opponent in map cards.
     *
     * @return the opponent in map cards
     */
    public ArrayList<Card> getOpponentInMapCards() {
        return opponentInMapCards;
    }

    /**
     * Delete opponent tower.
     *
     * @param tower the tower
     * @throws InvalidTowerException the invalid tower exception
     */
    public void deleteOpponentTower(Tower tower) throws InvalidTowerException {
        if (!this.opponentTowers.contains(tower)) {
            throw new InvalidTowerException("The opponent doesn't have any such tower");
        }

        this.opponentTowers.remove(tower);
    }


    /**
     * Add card to opponent in map cards.
     *
     * @param card the card
     * @throws DuplicateCardException the duplicate card exception
     */
    public void addCardToOpponentInMapCards(Card card) throws DuplicateCardException {
        if (this.opponentInMapCards.contains(card)) {
            throw new DuplicateCardException("Opponents already have the same card.");
        }

        this.opponentInMapCards.add(card);
    }

    /**
     * Remove card from opponent in map cards.
     *
     * @param card the card
     * @throws InvalidCardException the invalid card exception
     */
    public void removeCardFromOpponentInMapCards(Card card) throws InvalidCardException {
        if (!this.opponentInMapCards.contains(card)) {
            throw new InvalidCardException("The opponents doesn't have the specified card");
        }

        this.opponentInMapCards.remove(card);
    }

    /**
     * Gets opponent in map troops.
     *
     * @return the opponent in map troops
     */
    public ArrayList<Troop> getOpponentInMapTroops() {
        ArrayList<Troop> troops = new ArrayList<>();

        for (Card card : this.opponentInMapCards) {
            if (card.isTroop()) {
                troops.add((Troop) card);
            }
        }

        return troops;
    }

    /**
     * Gets opponent in map spells.
     *
     * @return the opponent in map spells
     */
    public ArrayList<Spell> getOpponentInMapSpells() {
        ArrayList<Spell> spells = new ArrayList<>();

        for (Card card : this.opponentInMapCards) {
            if (card.isSpell()) {
                spells.add((Spell) card);
            }
        }

        return spells;
    }

    /**
     * Gets opponent buildings.
     *
     * @return the opponent buildings
     */
    public ArrayList<Building> getOpponentInMapBuildings() {
        ArrayList<Building> buildings = new ArrayList<>();

        for (Card card : this.opponentInMapCards) {
            if (card.isBuilding()) {
                buildings.add((Building) card);
            }
        }

        return buildings;
    }

    /**
     * Gets opponent crown count.
     *
     * @return the opponent crown count
     */
    public int getOpponentCrownCount() {
        return opponentCrownCount;
    }

    /**
     * Sets opponent crown count.
     *
     * @param opponentCrownCount the opponent crown count
     */
    public void setOpponentCrownCount(int opponentCrownCount) {
        this.opponentCrownCount = opponentCrownCount;
    }

    /**
     * Gets bot in map attack ables.
     *
     * @return the bot in map attack ables
     */
    public ArrayList<AttackAble> getOpponentInMapAttackAblesCards() {
        ArrayList<AttackAble> attackAbles = new ArrayList<>();
        attackAbles.addAll(this.getOpponentInMapBuildings());
        attackAbles.addAll(this.getOpponentInMapTroops());
        return attackAbles;
    }

    /**
     * Gets bot in map attack ables.
     *
     * @return the bot in map attack ables
     */
    public ArrayList<AttackAble> getOpponentInMapAttackAbles() {
        ArrayList<AttackAble> attackAbles = new ArrayList<>();
        attackAbles.addAll(this.getOpponentInMapBuildings());
        attackAbles.addAll(this.getOpponentInMapTroops());
        attackAbles.addAll(this.getOpponentTowers());
        return attackAbles;
    }
}