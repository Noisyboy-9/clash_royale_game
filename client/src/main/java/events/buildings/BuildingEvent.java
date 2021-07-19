package events.buildings;

import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

public abstract class BuildingEvent extends CustomEvent {
    public BuildingEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
    }

    public BuildingEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
    }
}
