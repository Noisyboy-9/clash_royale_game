package events.towers;

import cards.utils.AttackAble;
import commands.Command;
import commands.gameStateCommands.towerCommands.TowerActiveCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import towers.Tower;

/**
 * The type Tower active event.
 */
public class TowerActiveEvent extends TowerEvent {
    private final Tower tower;
    private final AttackAble target;

    /**
     * Instantiates a new Tower active event.
     *
     * @param eventType the event type
     * @param tower     the tower
     * @param target    the target
     */
    public TowerActiveEvent(EventType<? extends Event> eventType, Tower tower, AttackAble target) {
        super(eventType);
        this.tower = tower;
        this.target = target;
    }

    /**
     * Instantiates a new Tower active event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     * @param tower     the tower
     * @param target1   the target 1
     */
    public TowerActiveEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Tower tower, AttackAble target1) {
        super(source, target, eventType);
        this.tower = tower;
        this.target = target1;
    }

    @Override
    public Command toCommand() {
        return new TowerActiveCommand(this.tower, this.target);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.towerActiveEventHandler(this);
    }
}
