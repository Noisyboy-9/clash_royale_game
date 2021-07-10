package towers;

import cards.utils.AttackAble;

import user.UserLevelEnum;

import java.util.UUID;

public class KingTower extends Tower {

    /**
     * Instantiates a new Tower.
     *
     * @param id                   the id
     * @param HP                   the hp
     * @param damage               the damage
     * @param ownerLevel           the owner level
     * @param demolitionBonusCount the demolition bonus count
     * @param target               the target
     */
    public KingTower(UUID id,
                     int HP,
                     int damage,
                     UserLevelEnum ownerLevel,
                     int demolitionBonusCount,
                     AttackAble target) {
        super(id, HP, damage, ownerLevel, demolitionBonusCount, false, target);
    }
}
