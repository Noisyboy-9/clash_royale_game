package events.counts;

import commands.Command;
import commands.gameStateCommands.gameBonusCommands.CrownCountChangeCommand;
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
    private final ArrayList<User> targetTeam;
    private final int newCrownCount;

    /**
     * Instantiates a new Crown count change event.
     *
     * @param eventType     the event type
     * @param targetTeam    the target team
     * @param newCrownCount the new crown count
     */
    public CrownCountChangeEvent(EventType<? extends Event> eventType,
                                 ArrayList<User> targetTeam,
                                 int newCrownCount) {
        super(eventType);
        this.targetTeam = targetTeam;
        this.newCrownCount = newCrownCount;
    }

    /**
     * Instantiates a new Crown count change event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetTeam    the target team
     * @param newCrownCount the new crown count
     */
    public CrownCountChangeEvent(Object source,
                                 EventTarget target,
                                 EventType<? extends Event> eventType,
                                 ArrayList<User> targetTeam,
                                 int newCrownCount) {
        super(source, target, eventType);
        this.targetTeam = targetTeam;
        this.newCrownCount = newCrownCount;
    }

    /**
     * Gets target team.
     *
     * @return the target team
     */
    public ArrayList<User> getTargetTeam() {
        return targetTeam;
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
        return new CrownCountChangeCommand(this.targetTeam, this.newCrownCount);
    }
}
