package events.spells;

import cards.spells.Spell;
import commands.Command;
import commands.gameStateCommands.spellCommands.SpellDurationFinishedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Spell duration finished event.
 */
public class SpellDurationFinishedEvent extends SpellEvent {
    private final Spell spell;

    /**
     * Instantiates a new Spell duration finished event.
     *
     * @param eventType the event type
     * @param spell     the spell
     */
    public SpellDurationFinishedEvent(EventType<? extends Event> eventType, Spell spell, ArrayList<User> targetPlayers) {
        super(eventType, targetPlayers);
        this.spell = spell;
    }

    /**
     * Instantiates a new Spell duration finished event.
     *
     * @param source    the source
     * @param target    the target
     * @param eventType the event type
     * @param spell     the spell
     */
    public SpellDurationFinishedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, Spell spell, ArrayList<User> targetPlayers) {
        super(source, target, eventType, targetPlayers);
        this.spell = spell;
    }

    /**
     * Gets spell.
     *
     * @return the spell
     */
    public Spell getSpell() {
        return spell;
    }

    @Override
    public Command toCommand() {
        return new SpellDurationFinishedCommand(this.spell);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.spellDurationFinishedEventHandler(this);
    }
}
