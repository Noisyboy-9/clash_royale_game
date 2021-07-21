package events.cards;

import cards.Card;
import commands.Command;
import commands.gameStateCommands.cardCommands.*;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;

/**
 * The type Card added event.
 */
public class CardAddedEvent extends CardEvent {
    private final Card card;
    private final Point2D position;

    /**
     * Instantiates a new Card added event.
     *
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param card          the card
     * @param position      the position
     */
    public CardAddedEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card, Point2D position) {
        super(eventType, targetPlayers);
        this.card = card;
        this.position = position;
    }

    /**
     * Instantiates a new Card added event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param card          the card
     * @param position      the position
     */
    public CardAddedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card, Point2D position) {
        super(source, target, eventType, targetPlayers);
        this.card = card;
        this.position = position;
    }

    @Override
    public Command toCommand() {
        return new CardAddedCommand(this.card, this.position);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.cardAddedEventHandler(this);
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
