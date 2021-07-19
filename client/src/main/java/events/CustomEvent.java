package events;

import commands.Command;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public abstract class CustomEvent extends Event {
    public CustomEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

    public CustomEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }

    public abstract Command toCommand();
}
