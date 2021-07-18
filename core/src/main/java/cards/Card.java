package cards;

import cards.utils.Position;
import user.User;
import user.UserLevelEnum;

import java.util.Objects;
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
    private Card(UUID id, int cost, User owner, Position position) {
        this.id = id;
        this.cost = cost;
        this.owner = owner;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    /**
     * create an instance of the card.
     *
     * @param level the level
     * @return the card
     */
    public abstract Card create(UserLevelEnum level);
}

