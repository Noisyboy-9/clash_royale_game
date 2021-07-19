package events.troops;

import cards.troops.TroopTraitCard;
import commands.Command;
import commands.gameStateCommands.troopCommands.TroopKilledCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Troop killed event.
 */
public class TroopKilledEvent extends TroopEvent {
    private final TroopTraitCard troop;

    /**
     * Instantiates a new Troop killed event.
     *  @param eventType the event type
     * @param troop     the troop
     * @param targets
     */
    public TroopKilledEvent(EventType<? extends Event> eventType, TroopTraitCard troop, ArrayList<User> targets) {
        super(eventType, targets);
        this.troop = troop;
    }

    /**
     * Instantiates a new Troop killed event.
     *  @param source    the source
     * @param target    the target
     * @param eventType the event type
     * @param troop     the troop
     * @param targets
     */
    public TroopKilledEvent(Object source, EventTarget target, EventType<? extends Event> eventType, TroopTraitCard troop, ArrayList<User> targets) {
        super(source, target, eventType, targets);
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

    /**
     * Gets troop.
     *
     * @return the troop
     */
    public TroopTraitCard getTroop() {
        return troop;
    }
}
