package cards.troops.giants;

import cards.Card;
import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
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
    private Giant(UUID id, User owner, Point2D position, int HP, int damage) {
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
                TypeEnum.GROUND,
                TypeEnum.GROUND
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
            case LEVEL_1 -> new Giant(UUID.randomUUID(), user, null, 2000, 126);
            case LEVEL_2 -> new Giant(UUID.randomUUID(), user, null, 2200, 138);
            case LEVEL_3 -> new Giant(UUID.randomUUID(), user, null, 2420, 152);
            case LEVEL_4 -> new Giant(UUID.randomUUID(), user, null, 2660, 167);
            case LEVEL_5 -> new Giant(UUID.randomUUID(), user, null, 2920, 183);
        };
    }
}
