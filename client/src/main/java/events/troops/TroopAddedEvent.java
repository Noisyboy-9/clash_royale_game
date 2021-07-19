package events.troops;

import cards.troops.Troop;
import commands.Command;
import commands.gameStateCommands.troopCommands.TroopAddedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;

public class TroopAddedEvent extends TroopEvent {
    private final Troop troop;
    private final Point2D position;

    public ArrayList<User> getTargets() {
        return targets;
    }

    private ArrayList<User> targets;

    public TroopAddedEvent(EventType<? extends Event> eventType, Troop troop, Point2D position, ArrayList<User> targets) {
        super(eventType);
        this.troop = troop;
        this.position = position;
        this.targets = targets;
    }

    public TroopAddedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Troop troop, Point2D position, ArrayList<User> targets) {
        super(source, target, eventType);
        this.troop = troop;
        this.position = position;
        this.targets = targets;
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

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.troopAddedEventHandler(this);
    }
}
