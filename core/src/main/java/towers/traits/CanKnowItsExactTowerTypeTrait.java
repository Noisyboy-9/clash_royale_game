package towers.traits;

import towers.KingTower;
import towers.QueenTower;
import towers.Tower;

public interface CanKnowItsExactTowerTypeTrait {
    default boolean isTower() {
        return this instanceof Tower;
    }

    default boolean isKingTower() {
        return this instanceof KingTower;
    }

    default boolean isQueenTower() {
        return this instanceof QueenTower;
    }
}
