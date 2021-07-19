package events.troops;

import cards.troops.Troop;
import commands.Command;
import commands.gameStateCommands.troopCommands.TroopKilledCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * The type Troop killed event.
 */
public class TroopKilledEvent extends TroopEvent {
    private final Troop troop;

    /**
     * Instantiates a new Troop killed event.
     *
     * @param eventType the event type
     * @param troop     the troop
     */
    public TroopKilledEvent(EventType<? extends Event> eventType, Troop troop) {
        super(eventType);
        this.troop = troop;
    }

    /**
     * Instantiates a new Troop killed event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     * @param troop     the troop
     */
    public TroopKilledEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Troop troop) {
        super(source, target, eventType);
        this.troop = troop;
    }

    @Override
    public Command toCommand() {
        return new TroopKilledCommand(this.troop);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.troopKilledEventHandler(this);
    }
}
