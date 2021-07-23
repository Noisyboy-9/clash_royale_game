package towers.traits;

import towers.KingTower;
import towers.QueenTower;
import towers.Tower;

/**
 * The interface Can know its exact tower type trait.
 */
public interface CanKnowItsExactTowerTypeTrait {
    /**
     * Is tower boolean.
     *
     * @return the boolean
     */
    default boolean isTower() {
        return this instanceof Tower;
    }

    /**
     * Is king tower boolean.
     *
     * @return the boolean
     */
    default boolean isKingTower() {
        return this instanceof KingTower;
    }

    /**
     * Is queen tower boolean.
     *
     * @return the boolean
     */
    default boolean isQueenTower() {
        return this instanceof QueenTower;
    }
}
