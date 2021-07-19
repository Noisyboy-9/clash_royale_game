package cards.spells;

import cards.Card;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Spell.
 */
public abstract class Spell extends Card {
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
     * let the spell do the thing that it must do.
     */
    public abstract void chant();
}
