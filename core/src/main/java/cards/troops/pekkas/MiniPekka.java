package cards.troops.pekkas;

import cards.Card;
import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Mini pekka.
 */
public class MiniPekka extends Troop {
    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public MiniPekka(UUID id, User owner, Point2D position, int HP, int damage) {
        super(id,
                4,
                owner,
                position,
                HP,
                damage,
                MovementSpeedEnum.FAST,
                false,
                1,
                1.8,
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
            case LEVEL_1 -> new MiniPekka(UUID.randomUUID(), user, position, 600, 325);
            case LEVEL_2 -> new MiniPekka(UUID.randomUUID(), user, position, 660, 357);
            case LEVEL_3 -> new MiniPekka(UUID.randomUUID(), user, position, 726, 393);
            case LEVEL_4 -> new MiniPekka(UUID.randomUUID(), user, position, 798, 432);
            case LEVEL_5 -> new MiniPekka(UUID.randomUUID(), user, position, 876, 474);
        };
    }
}
