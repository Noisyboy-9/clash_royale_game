package events.towers;

import commands.Command;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import towers.Tower;
import user.User;

import java.util.ArrayList;

/**
 * The type Tower destroyed event.
 */
public class TowerDestroyedEvent extends TowerEvent {
    private final Tower tower;

    /**
     * Instantiates a new Tower destroyed event.
     *
     * @param eventType     the event type
     * @param tower         the tower
     * @param targetPlayers the target players
     */
    public TowerDestroyedEvent(EventType<? extends Event> eventType, Tower tower, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
        this.tower = tower;
    }

    /**
     * Instantiates a new Tower destroyed event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param tower         the tower
     * @param targetPlayers the target players
     */
    public TowerDestroyedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Tower tower, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
        this.tower = tower;
    }

    @Override
    public Command toCommand() {
        return new TowerDestroyedCommand(this.tower);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.towerDestroyedEventHandler(this);
    }

    /**
     * Gets tower.
     *
     * @return the tower
     */
    public Tower getTower() {
        return tower;
    }
}
