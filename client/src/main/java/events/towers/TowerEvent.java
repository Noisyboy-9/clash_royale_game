package events.towers;

import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public abstract class TowerEvent extends CustomEvent {
    public TowerEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public TowerEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }
}
