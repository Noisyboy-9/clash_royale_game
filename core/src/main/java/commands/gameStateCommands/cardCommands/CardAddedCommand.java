package commands.gameStateCommands.cardCommands;

import cards.Card;
import javafx.geometry.Point2D;

/**
 * The type Card added command.
 */
public class CardAddedCommand extends CardCommand {
    private final Card card;
    private final Point2D position;

    /**
     * Instantiates a new Card added command.
     *
     * @param card     the card
     * @param position the position
     */
    public CardAddedCommand(Card card, Point2D position) {
        this.card = card;
        this.position = position;
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }
}
