package cards.troops.giants;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import cards.utils.TroopTypeEnum;
import user.User;

import java.util.UUID;

/**
 * The type Giant.
 */
public class Giant extends Troop {

    /**
     * Instantiates a new Giant.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Giant(UUID id, User owner, Position position, int HP, int damage) {
        super(id,
                5,
                owner,
                position,
                HP,
                damage,
                MovementSpeedEnum.SLOW,
                false,
                1,
                1.5,
                TroopTypeEnum.GROUND,
                TroopTypeEnum.GROUND
        );
    }
}
