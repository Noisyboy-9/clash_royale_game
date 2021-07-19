package commands.gameStateCommands.spellCommands;

import cards.spells.SpellTraitCard;

/**
 * The type Spell duration finished command.
 */
public class SpellDurationFinishedCommand extends SpellCommand {
    private final SpellTraitCard spell;

    /**
     * Instantiates a new Spell duration finished command.
     *
     * @param spell the spell
     */
    public SpellDurationFinishedCommand(SpellTraitCard spell) {
        this.spell = spell;
    }

    /**
     * Gets spell.
     *
     * @return the spell
     */
    public SpellTraitCard getSpell() {
        return spell;
    }
}
