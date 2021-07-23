package events.cards;

import commands.Command;
import controllers.modes.CustomEventHandler;
import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Card event.
 */
public abstract class CardEvent extends CustomEvent {
    /**
     * Instantiates a new Card event.
     *
     * @param eventType     the event type
     * @param targetPlayers the target players
     */
    public CardEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
    }

    /**
     * Instantiates a new Card event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the target players
     */
    public CardEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
    }
}
