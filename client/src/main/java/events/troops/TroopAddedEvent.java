package events.troops;

import cards.troops.TroopTrait;
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
    private final TroopTrait troop;
    private final Point2D position;

    public TroopAddedEvent(EventType<? extends Event> eventType, TroopTrait troop, Point2D position, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
        this.troop = troop;
        this.position = position;
    }

    public TroopAddedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, TroopTrait troop, Point2D position, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
        this.troop = troop;
        this.position = position;
    }

    public TroopTrait getTroop() {
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
