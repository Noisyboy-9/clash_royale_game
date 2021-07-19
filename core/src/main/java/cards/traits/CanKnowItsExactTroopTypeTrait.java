package cards.traits;

import cards.troops.Troop;
import cards.troops.archers.Archer;
import cards.troops.barbarians.Barbarian;
import cards.troops.dragons.BabyDragon;
import cards.troops.giants.Giant;
import cards.troops.pekkas.MiniPekka;
import cards.troops.valkyries.Valkyrie;
import cards.troops.wizards.Wizard;

public interface CanKnowItsExactTroopTypeTrait {
    default boolean isTroop() {
        return this instanceof Troop;
    }

    default boolean isBarbarian() {
        return this instanceof Barbarian;
    }

    default boolean isArcher() {
        return this instanceof Archer;
    }

    default boolean isBabyDragon() {
        return this instanceof BabyDragon;
    }

    default boolean isWizard() {
        return this instanceof Wizard;
    }

    default boolean isMiniPeka() {
        return this instanceof MiniPekka;
    }

    default boolean isGiant() {
        return this instanceof Giant;
    }

    default boolean isValkyrie() {
        return this instanceof Valkyrie;
    }
}
