package cards.traits;

import cards.buildings.Building;
import cards.buildings.cannons.Cannon;
import cards.buildings.towers.InfernoTower;

/**
 * The interface Can know its exact building type trait.
 */
public interface CanKnowItsExactBuildingTypeTrait {
    /**
     * Is building boolean.
     *
     * @return the boolean
     */
    default boolean isBuilding() {
        return this instanceof Building;
    }

    /**
     * Is cannon boolean.
     *
     * @return the boolean
     */
    default boolean isCannon() {
        return this instanceof Cannon;
    }

    /**
     * Is inferno tower boolean.
     *
     * @return the boolean
     */
    default boolean isInfernoTower() {
        return this instanceof InfernoTower;
    }
}
