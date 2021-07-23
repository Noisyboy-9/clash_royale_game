package events.counts;

import commands.Command;
import commands.gameStateCommands.gameBonusCommands.CrownCountChangeCommand;
import controllers.modes.CustomEventHandler;
import events.CustomEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Crown count change event.
 */
public class CrownCountChangeEvent extends CustomEvent {
    private final int newCrownCount;

    /**
     * Instantiates a new Crown count change event.
     *
     * @param eventType     the event type
     * @param targetPlayers the target team
     * @param newCrownCount the new crown count
     */
    public CrownCountChangeEvent(EventType<? extends Event> eventType,
                                 ArrayList<User> targetPlayers,
                                 int newCrownCount) {
        super(eventType, targetPlayers);
        this.newCrownCount = newCrownCount;
    }

    /**
     * Instantiates a new Crown count change event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param eventTargets  the target team
     * @param newCrownCount the new crown count
     */
    public CrownCountChangeEvent(Object source,
                                 EventTarget target,
                                 EventType<? extends Event> eventType,
                                 ArrayList<User> eventTargets,
                                 int newCrownCount) {
        super(source, target, eventType, eventTargets);
        this.newCrownCount = newCrownCount;
    }

    /**
     * Gets new crown count.
     *
     * @return the new crown count
     */
    public int getNewCrownCount() {
        return newCrownCount;
    }

    @Override
    public Command toCommand() {
        return new CrownCountChangeCommand(this.getTargetPlayers(), this.newCrownCount);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.crownCountChangeHandler(this);
    }
}
