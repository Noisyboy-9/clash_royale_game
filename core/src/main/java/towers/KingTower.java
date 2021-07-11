package towers;

import cards.utils.AttackAble;
import user.UserLevelEnum;

import java.util.UUID;

/**
 * The type King tower.
 */
public class KingTower extends Tower {

    /**
     * Instantiates a new Tower.
     *
     * @param id         the id
     * @param HP         the hp
     * @param damage     the damage
     * @param ownerLevel the owner level
     * @param target     the target
     */
    public KingTower(UUID id,
                     int HP,
                     int damage,
                     UserLevelEnum ownerLevel,
                     AttackAble target) {
        super(id, HP, damage, ownerLevel, 3, false, target);
    }
}
