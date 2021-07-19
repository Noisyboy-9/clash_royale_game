package cards.troops.wizards;

import cards.Card;
import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Wizard.
 */
public class Wizard extends Troop {

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    private Wizard(UUID id, User owner, Point2D position, int HP, int damage) {
        super(id,
                5,
                owner,
                position,
                HP,
                damage,
                MovementSpeedEnum.MEDIUM,
                true,
                5,
                1.7,
                TypeEnum.GROUND,
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
            case LEVEL_1 -> new Wizard(UUID.randomUUID(), user, null, 340, 130);
            case LEVEL_2 -> new Wizard(UUID.randomUUID(), user, null, 374, 143);
            case LEVEL_3 -> new Wizard(UUID.randomUUID(), user, null, 411, 157);
            case LEVEL_4 -> new Wizard(UUID.randomUUID(), user, null, 452, 172);
            case LEVEL_5 -> new Wizard(UUID.randomUUID(), user, null, 496, 189);
        };

    }
}
