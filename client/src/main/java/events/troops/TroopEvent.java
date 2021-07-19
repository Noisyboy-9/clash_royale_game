package events.troops;

import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Troop event.
 */
public abstract class TroopEvent extends CustomEvent {
    /**
     * Instantiates a new Troop event.
     *
     * @param eventType    the event type
     * @param eventTargets the owners
     */
    public TroopEvent(EventType<? extends Event> eventType, ArrayList<User> eventTargets) {
        super(eventType, eventTargets);
    }

    /**
     * Instantiates a new Troop event.
     *
     * @param source       the source
     * @param target       the target
     * @param eventType    the event type
     * @param eventTargets the event targets
     */
    public TroopEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> eventTargets) {
        super(source, target, eventType, eventTargets);
    }
}
