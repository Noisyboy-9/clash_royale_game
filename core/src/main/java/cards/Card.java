package cards;

import cards.utils.Position;
import user.User;

import java.util.UUID;

public abstract class Card {
    private final UUID id;
    private final int cost;
    private final User owner;
    private final Position position;

    public Card(UUID id, int cost, User owner, Position position) {
        this.id = id;
        this.cost = cost;
        this.owner = owner;
        this.position = position;
    }

    public UUID getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public User getOwner() {
        return owner;
    }

    public Position getPosition() {
        return position;
    }
}
