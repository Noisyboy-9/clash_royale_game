package events.spells;

import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * The type Spell event.
 */
public abstract class SpellEvent extends CustomEvent {
    /**
     * Instantiates a new Spell event.
     *
     * @param eventType the event type
     */
    public SpellEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    /**
     * Instantiates a new Spell event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     */
    public SpellEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }
}
