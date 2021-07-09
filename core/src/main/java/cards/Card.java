package cards;

import cards.utils.Position;
import user.User;

import java.util.UUID;

public abstract class Card {
    private UUID id;
    private int cost;
    private User owner;
    private Position position;

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
