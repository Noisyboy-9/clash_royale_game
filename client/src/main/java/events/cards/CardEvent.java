package events.cards;

import commands.Command;
import controllers.modes.CustomEventHandler;
import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

public abstract class CardEvent extends CustomEvent {
    public CardEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
    }

    public CardEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
    }
}
