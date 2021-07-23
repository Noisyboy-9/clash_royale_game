package cards.spells;

import cards.Card;
import cards.troops.Troop;
import javafx.geometry.Point2D;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Spell.
 */
public abstract class Spell extends Card {
    /**
     * The Target towers.
     */
    protected final ArrayList<Tower> targetTowers;
    /**
     * The Target troops.
     */
    protected final ArrayList<Troop> targetTroops;
    private final double radius;

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     * @param radius   the radius
     */
    protected Spell(UUID id, int cost, User owner, Point2D position, double radius) {
        super(id, cost, owner, position);
        this.radius = radius;
        this.targetTowers = new ArrayList<>();
        this.targetTroops = new ArrayList<>();
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Add tower target.
     *
     * @param tower the tower
     */
    public void addTowerTarget(Tower tower) {
        if (!this.targetTowers.contains(tower)) {
            this.targetTowers.add(tower);
        }
    }

    /**
     * Add troop target.
     *
     * @param troop the troop
     */
    public void addTroopTarget(Troop troop) {
        if (!this.targetTroops.contains(troop)) {
            this.targetTroops.add(troop);
        }
    }

    /**
     * let the spell do the thing that it must do.
     */
    public abstract void chant();
}
