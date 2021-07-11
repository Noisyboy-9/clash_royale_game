package cards.spells;

import cards.Card;
import cards.utils.Position;
import user.User;

import java.util.UUID;

/**
 * The type Spell.
 */
public abstract class Spell extends Card {
    private final int radius;

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     * @param radius   the radius
     */
    public Spell(UUID id, int cost, User owner, Position position, int radius) {
        super(id, cost, owner, position);
        this.radius = radius;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * let the spell do the thing that it must do.
     */
    public abstract void chant();

    /**
     * cancel the process that the spell has done.
     */
    public abstract void unChant();
}
