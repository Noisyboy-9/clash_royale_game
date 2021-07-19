package cards.troops.dragons;

import cards.Card;
import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Baby dragon.
 */
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
    private BabyDragon(UUID id, User owner, Point2D position, int HP, int damage) {
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

    /**
     * Create card.
     *
     * @param user the user
     * @return the card
     */
    public static Card create(User user) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new BabyDragon(UUID.randomUUID(), user, null, 800, 100);
            case LEVEL_2 -> new BabyDragon(UUID.randomUUID(), user, null, 880, 110);
            case LEVEL_3 -> new BabyDragon(UUID.randomUUID(), user, null, 968, 121);
            case LEVEL_4 -> new BabyDragon(UUID.randomUUID(), user, null, 1064, 133);
            case LEVEL_5 -> new BabyDragon(UUID.randomUUID(), user, null, 1168, 146);
        };
    }
}
