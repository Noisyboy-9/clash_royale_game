package events.troops;

import cards.troops.Troop;
import commands.Command;
import commands.gameStateCommands.troopCommands.TroopAddedCommand;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;

public class TroopAddedEvent extends TroopEvent {
    private final Troop troop;
    private final Point2D position;

    public TroopAddedEvent(EventType<? extends Event> eventType, Troop troop, Point2D position) {
        super(eventType);
        this.troop = troop;
        this.position = position;
    }

    public TroopAddedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Troop troop, Point2D position) {
        super(source, target, eventType);
        this.troop = troop;
        this.position = position;
    }

    public Troop getTroop() {
        return troop;
    }

    public Point2D getPosition() {
        return position;
    }

    @Override
    public Command toCommand() {
        return new TroopAddedCommand(this.troop, this.position);
    }
}
