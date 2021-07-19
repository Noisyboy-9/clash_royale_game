package events.towers;

import commands.Command;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import towers.Tower;

/**
 * The type Tower destroyed event.
 */
public class TowerDestroyedEvent extends TowerEvent {
    private final Tower tower;

    /**
     * Instantiates a new Tower destroyed event.
     *
     * @param eventType the event type
     * @param tower     the tower
     */
    public TowerDestroyedEvent(EventType<? extends Event> eventType, Tower tower) {
        super(eventType);
        this.tower = tower;
    }

    /**
     * Instantiates a new Tower destroyed event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     * @param tower     the tower
     */
    public TowerDestroyedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Tower tower) {
        super(source, target, eventType);
        this.tower = tower;
    }

    @Override
    public Command toCommand() {
        return new TowerDestroyedCommand(this.tower);
    }
}
