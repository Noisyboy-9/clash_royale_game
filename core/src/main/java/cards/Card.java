package cards;

import cards.traits.CanKnowItsExactCardTypeTraitTraitTraitTrait;
import javafx.geometry.Point2D;
import user.User;

import java.util.Objects;
import java.util.UUID;

/**
 * The type Card.
 */
public abstract class Card implements CanKnowItsExactCardTypeTraitTraitTraitTrait {
    private final UUID id;
    private final int cost;
    private final User owner;
    private Point2D position;
    private CardStatusEnum status;

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     */
    protected Card(UUID id, int cost, User owner, Point2D position) {
        this.id = id;
        this.cost = cost;
        this.owner = owner;
        this.position = position;
        this.status = CardStatusEnum.WALK;
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
    public Point2D getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }


    /**
     * Gets status.
     *
     * @return the status
     */
    public CardStatusEnum getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(CardStatusEnum status) {
        this.status = status;
    }
}

