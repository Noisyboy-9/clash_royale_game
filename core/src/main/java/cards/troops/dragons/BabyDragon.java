package cards.troops.dragons;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import cards.utils.TypeEnum;
import user.User;

import java.util.UUID;

public class BabyDragon extends Troop {

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public BabyDragon(UUID id, User owner, Position position, int HP, int damage) {
        super(id,
                4,
                owner,
                position,
                HP,
                damage,
                MovementSpeedEnum.FAST,
                true,
                3,
                1.8,
                TypeEnum.AIR,
                TypeEnum.AIR_GROUND
        );
    }
}
