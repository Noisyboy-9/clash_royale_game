package events.towers;

import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Tower event.
 */
public abstract class TowerEvent extends CustomEvent {
    /**
     * Instantiates a new Tower event.
     *
     * @param eventType     the event type
     * @param targetPlayers the event targets
     */
    public TowerEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
    }

    /**
     * Instantiates a new Tower event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the event targets
     */
    public TowerEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
    }
}
