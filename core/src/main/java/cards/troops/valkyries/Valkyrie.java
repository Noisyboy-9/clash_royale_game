package cards.troops.valkyries;

import cards.Card;
import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Valkyrie.
 */
public class Valkyrie extends Troop {

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Valkyrie(UUID id, User owner, Point2D position, int HP, int damage) {
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
                TypeEnum.GROUND,
                TypeEnum.GROUND
        );
    }

    @Override
    public Card create(User user, Point2D position) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Valkyrie(UUID.randomUUID(), user, position, 2000, 126);
            case LEVEL_2 -> new Valkyrie(UUID.randomUUID(), user, position, 2200, 138);
            case LEVEL_3 -> new Valkyrie(UUID.randomUUID(), user, position, 2420, 152);
            case LEVEL_4 -> new Valkyrie(UUID.randomUUID(), user, position, 2660, 167);
            case LEVEL_5 -> new Valkyrie(UUID.randomUUID(), user, position, 2920, 183);
        };
    }
}
