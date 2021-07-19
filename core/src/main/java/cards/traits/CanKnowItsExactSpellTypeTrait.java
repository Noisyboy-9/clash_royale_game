package cards.traits;

import cards.spells.Spell;
import cards.spells.arrows.Arrows;
import cards.spells.balls.FireBall;
import cards.spells.rages.Rage;

public interface CanKnowItsExactSpellTypeTrait {
    default boolean isSpell() {
        return this instanceof Spell;
    }

    default boolean isRage() {
        return this instanceof Rage;
    }

    default boolean isFireBall() {
        return this instanceof FireBall;
    }

    default boolean isArrows() {
        return this instanceof Arrows;
    }
}
