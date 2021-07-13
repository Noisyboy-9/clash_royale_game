package commands.gameStateCommands.spellCommands;

import cards.spells.Spell;

/**
 * The type Spell duration finished command.
 */
public class SpellDurationFinishedCommand extends SpellCommand {
    private final Spell spell;

    /**
     * Instantiates a new Spell duration finished command.
     *
     * @param spell the spell
     */
    public SpellDurationFinishedCommand(Spell spell) {
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
}
