package cards.traits;

import cards.troops.Troop;
import cards.troops.archers.Archer;
import cards.troops.barbarians.Barbarian;
import cards.troops.dragons.BabyDragon;
import cards.troops.giants.Giant;
import cards.troops.pekkas.MiniPekka;
import cards.troops.valkyries.Valkyrie;
import cards.troops.wizards.Wizard;

/**
 * The interface Can know its exact troop type trait.
 */
public interface CanKnowItsExactTroopTypeTrait {
    /**
     * Is troop boolean.
     *
     * @return the boolean
     */
    default boolean isTroop() {
        return this instanceof Troop;
    }

    /**
     * Is barbarian boolean.
     *
     * @return the boolean
     */
    default boolean isBarbarian() {
        return this instanceof Barbarian;
    }

    /**
     * Is archer boolean.
     *
     * @return the boolean
     */
    default boolean isArcher() {
        return this instanceof Archer;
    }

    /**
     * Is baby dragon boolean.
     *
     * @return the boolean
     */
    default boolean isBabyDragon() {
        return this instanceof BabyDragon;
    }

    /**
     * Is wizard boolean.
     *
     * @return the boolean
     */
    default boolean isWizard() {
        return this instanceof Wizard;
    }

    /**
     * Is mini peka boolean.
     *
     * @return the boolean
     */
    default boolean isMiniPeka() {
        return this instanceof MiniPekka;
    }

    /**
     * Is giant boolean.
     *
     * @return the boolean
     */
    default boolean isGiant() {
        return this instanceof Giant;
    }

    /**
     * Is valkyrie boolean.
     *
     * @return the boolean
     */
    default boolean isValkyrie() {
        return this instanceof Valkyrie;
    }
}
