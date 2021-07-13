package commands.gameStateCommands.spellCommands;

import cards.spells.Spell;
import cards.utils.Position;

/**
 * The type Spell addition command.
 */
public class SpellAdditionCommand extends SpellCommand {
    private final Spell spell;
    private final Position position;

    /**
     * Instantiates a new Spell addition command.
     *
     * @param spell    the spell
     * @param position the position
     */
    public SpellAdditionCommand(Spell spell, Position position) {
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
    public Position getPosition() {
        return position;
    }
}
