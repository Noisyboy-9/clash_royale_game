package cards.troops.barbarians;

import cards.Card;
import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Barbarian.
 */
public class Barbarian extends Troop {
    /**
     * Instantiates a new Barbarian.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Barbarian(UUID id, User owner, Point2D position, int HP, int damage) {
        super(id,
                5,
                owner,
                position,
                HP,
                damage,
                MovementSpeedEnum.MEDIUM,
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
     * @param user     the user
     * @param position the position
     * @return the card
     */
    public static Card create(User user, Point2D position) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Barbarian(UUID.randomUUID(), user, position, 300, 75);
            case LEVEL_2 -> new Barbarian(UUID.randomUUID(), user, position, 330, 82);
            case LEVEL_3 -> new Barbarian(UUID.randomUUID(), user, position, 363, 90);
            case LEVEL_4 -> new Barbarian(UUID.randomUUID(), user, position, 438, 99);
            case LEVEL_5 -> new Barbarian(UUID.randomUUID(), user, position, 480, 109);
        };
    }
}
