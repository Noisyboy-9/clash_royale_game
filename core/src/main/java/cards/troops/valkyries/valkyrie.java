package cards.troops.valkyries;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import cards.utils.TroopTypeEnum;
import user.User;

import java.util.UUID;

public class valkyrie extends Troop {

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public valkyrie(UUID id, User owner, Position position, int HP, int damage) {
        super(id,
                4,
                owner,
                position,
                HP,
                damage,
                MovementSpeedEnum.MEDIUM,
                true,
                1,
                1.5,
                TroopTypeEnum.GROUND,
                TroopTypeEnum.GROUND
        );
    }
}
