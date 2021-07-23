package cards.traits;

import cards.spells.Spell;
import cards.spells.arrows.Arrows;
import cards.spells.balls.FireBall;
import cards.spells.rages.Rage;

/**
 * The interface Can know its exact spell type trait.
 */
public interface CanKnowItsExactSpellTypeTrait {
    /**
     * Is spell boolean.
     *
     * @return the boolean
     */
    default boolean isSpell() {
        return this instanceof Spell;
    }

    /**
     * Is rage boolean.
     *
     * @return the boolean
     */
    default boolean isRage() {
        return this instanceof Rage;
    }

    /**
     * Is fire ball boolean.
     *
     * @return the boolean
     */
    default boolean isFireBall() {
        return this instanceof FireBall;
    }

    /**
     * Is arrows boolean.
     *
     * @return the boolean
     */
    default boolean isArrows() {
        return this instanceof Arrows;
    }
}
