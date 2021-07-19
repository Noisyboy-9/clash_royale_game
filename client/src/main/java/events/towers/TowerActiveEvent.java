package events.towers;

import cards.utils.AttackAble;
import commands.Command;
import commands.gameStateCommands.towerCommands.TowerActiveCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import towers.Tower;
import user.User;

import java.util.ArrayList;

/**
 * The type Tower active event.
 */
public class TowerActiveEvent extends TowerEvent {
    private final Tower tower;

    /**
     * Gets attack target.
     *
     * @return the attack target
     */
    public AttackAble getAttackTarget() {
        return attackTarget;
    }

    private final AttackAble attackTarget;

    /**
     * Instantiates a new Tower active event.
     *
     * @param eventType     the event type
     * @param tower         the tower
     * @param attackTarget  the target
     * @param targetPlayers the target players
     */
    public TowerActiveEvent(EventType<? extends Event> eventType, Tower tower, AttackAble attackTarget, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
        this.tower = tower;
        this.attackTarget = attackTarget;
    }

    /**
     * Instantiates a new Tower active event.
     *
     * @param source        the source
     * @param attackTarget  the target
     * @param eventType     the event type
     * @param tower         the tower
     * @param target1       the target 1
     * @param targetPlayers the target players
     */
    public TowerActiveEvent(Object source,
                            EventTarget attackTarget,
                            EventType<? extends Event> eventType,
                            Tower tower,
                            AttackAble target1,
                            ArrayList<User> targetPlayers) {
        super(source, attackTarget, eventType, targetPlayers);
        this.tower = tower;
        this.attackTarget = target1;
    }

    @Override
    public Command toCommand() {
        return new TowerActiveCommand(this.tower, this.attackTarget);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.towerActiveEventHandler(this);
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
