package events.spells;

import cards.spells.Spell;
import commands.Command;
import commands.gameStateCommands.spellCommands.SpellAddedCommand;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;

/**
 * The type Spell added event.
 */
public class SpellAddedEvent extends SpellEvent {
    private final Spell spell;
    private final Point2D position;

    /**
     * Instantiates a new Spell added event.
     *
     * @param eventType the event type
     * @param spell     the spell
     * @param position  the position
     */
    public SpellAddedEvent(EventType<? extends Event> eventType, Spell spell, Point2D position) {
        super(eventType);
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
    public SpellAddedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Spell spell, Point2D position) {
        super(source, target, eventType);
        this.spell = spell;
        this.position = position;
    }

    /**
     * Gets spell.
     *
     * @return the spell
     */
    public Spell getSpell() {
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
}
