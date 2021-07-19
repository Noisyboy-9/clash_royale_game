package cards.traits;

import cards.buildings.cannons.Cannon;
import cards.buildings.towers.InfernoTower;

public interface CanKnowItsExactBuildingTypeTrait {
    default boolean isCannon() {
        return this instanceof Cannon;
    }

    default boolean isInfernoTower() {
        return this instanceof InfernoTower;
    }
}
