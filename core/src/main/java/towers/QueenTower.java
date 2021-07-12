package towers;

import cards.utils.AttackAble;
import user.UserLevelEnum;

import java.util.UUID;

/**
 * The type Queen tower.
 */
public class QueenTower extends Tower {

    /**
     * Instantiates a new Tower.
     *
     * @param id         the id
     * @param HP         the hp
     * @param damage     the damage
     * @param ownerLevel the owner level
     * @param target     the target
     */
    public QueenTower(UUID id,
                      int HP,
                      int damage,
                      UserLevelEnum ownerLevel,
                      AttackAble target) {
        super(id, HP, damage, ownerLevel, 1, true, target, 8);
    }
}
