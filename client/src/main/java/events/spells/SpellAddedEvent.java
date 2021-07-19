package events.spells;

import cards.spells.SpellTrait;
import commands.Command;
import commands.gameStateCommands.spellCommands.SpellAddedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;

/**
 * The type Spell added event.
 */
public class SpellAddedEvent extends SpellEvent {
    private final SpellTrait spell;
    private final Point2D position;

    /**
     * Instantiates a new Spell added event.
     *
     * @param eventType the event type
     * @param spell     the spell
     * @param position  the position
     */
    public SpellAddedEvent(EventType<? extends Event> eventType, SpellTrait spell, Point2D position, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
        this.spell = spell;
        this.position = position;
    }

    /**
     * Instantiates a new Spell added event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     * @param spell     the spell
     * @param position  the position
     */
    public SpellAddedEvent(Object source,
                           EventTarget target,
                           EventType<? extends Event> eventType,
                           SpellTrait spell,
                           Point2D position,
                           ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
        this.spell = spell;
        this.position = position;
    }

    /**
     * Gets spell.
     *
     * @return the spell
     */
    public SpellTrait getSpell() {
        return spell;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }

    @Override
    public Command toCommand() {
        return new SpellAddedCommand(this.spell, this.position);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.spellAddedEventHandler(this);
    }
}
