package commands.gameStateCommands.spellCommands;

import cards.spells.SpellTrait;
import javafx.geometry.Point2D;

/**
 * The type Spell addition command.
 */
public class SpellAddedCommand extends SpellCommand {
    private final SpellTrait spell;
    private final Point2D position;

    /**
     * Instantiates a new Spell addition command.
     *
     * @param spell    the spell
     * @param position the position
     */
    public SpellAddedCommand(SpellTrait spell, Point2D position) {
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
}
