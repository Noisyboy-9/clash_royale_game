package commands.gameStateCommands.spellCommands;

import cards.spells.SpellTrait;

/**
 * The type Spell duration finished command.
 */
public class SpellDurationFinishedCommand extends SpellCommand {
    private final SpellTrait spell;

    /**
     * Instantiates a new Spell duration finished command.
     *
     * @param spell the spell
     */
    public SpellDurationFinishedCommand(SpellTrait spell) {
        this.spell = spell;
    }

    /**
     * Gets spell.
     *
     * @return the spell
     */
    public SpellTrait getSpell() {
        return spell;
    }
}
