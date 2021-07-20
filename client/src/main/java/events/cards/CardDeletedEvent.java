package events.cards;

import cards.Card;
import commands.Command;
import commands.gameStateCommands.cardCommands.*;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Card deleted event.
 */
public class CardDeletedEvent extends CardEvent {
    private final Card card;

    /**
     * Instantiates a new Card deleted event.
     *
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param card          the card
     */
    public CardDeletedEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card) {
        super(eventType, targetPlayers);
        this.card = card;
    }

    /**
     * Instantiates a new Card deleted event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param card          the card
     */
    public CardDeletedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card) {
        super(source, target, eventType, targetPlayers);
        this.card = card;
    }

    @Override
    public Command toCommand() {
        return new CardDeletedCommand(this.card);
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.cardDeletedEventHandler(this);
    }
}
