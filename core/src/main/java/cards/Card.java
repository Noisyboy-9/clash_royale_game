package cards;

import cards.utils.Position;
import user.User;

import java.util.UUID;

/**
 * The type Card.
 */
public abstract class Card {
    private final UUID id;
    private final int cost;
    private final User owner;
    private final Position position;

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     */
    public Card(UUID id, int cost, User owner, Position position) {
        this.id = id;
        this.cost = cost;
        this.owner = owner;
        this.position = position;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }
}
