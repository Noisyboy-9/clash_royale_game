package events.troops;

import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * The type Troop event.
 */
public abstract class TroopEvent extends CustomEvent {
    /**
     * Instantiates a new Troop event.
     *
     * @param eventType the event type
     */
    public TroopEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    /**
     * Instantiates a new Troop event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     */
    public TroopEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }
}
