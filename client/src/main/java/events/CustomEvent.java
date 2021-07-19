package events;

import commands.Command;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Custom event.
 */
public abstract class CustomEvent extends Event {
    private final ArrayList<User> targetPlayers;

    /**
     * Instantiates a new Custom event.
     *
     * @param eventType     the event type
     * @param targetPlayers the event targets
     */
    public CustomEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(eventType);
        this.targetPlayers = targetPlayers;
    }

    /**
     * Instantiates a new Custom event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the event targets
     */
    public CustomEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers) {
        super(source, target, eventType);
        this.targetPlayers = targetPlayers;
    }

    /**
     * Gets event targets.
     *
     * @return the event targets
     */
    public ArrayList<User> getTargetPlayers() {
        return targetPlayers;
    }

    /**
     * To command command.
     *
     * @return the command
     */
    public abstract Command toCommand();

    /**
     * Invoke handler.
     *
     * @param handler the handler
     */
    public abstract void invokeHandler(CustomEventHandler handler);
}
