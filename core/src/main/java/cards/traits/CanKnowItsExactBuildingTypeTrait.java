package cards.traits;

import cards.buildings.Building;
import cards.buildings.cannons.Cannon;
import cards.buildings.towers.InfernoTower;

public interface CanKnowItsExactBuildingTypeTrait {
    default boolean isBuilding() {
        return this instanceof Building;
    }
    default boolean isCannon() {
        return this instanceof Cannon;
    }

    default boolean isInfernoTower() {
        return this instanceof InfernoTower;
    }
}
