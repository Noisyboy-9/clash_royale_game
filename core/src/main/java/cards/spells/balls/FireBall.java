package cards.spells.balls;

import cards.Card;
import cards.spells.Spell;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Fire ball.
 */
public class FireBall extends Spell {
    private final int damage;

    /**
     * Instantiates a new Fire ball.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param damage   the damage
     */
    private FireBall(UUID id,
                     User owner,
                     Point2D position,
                     int damage) {
        super(id, 4, owner, position, 2.5);

        this.damage = damage;
    }

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }

    /**
     * Create card.
     *
     * @param user the user
     * @return the card
     */
    public static Card create(User user) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new FireBall(UUID.randomUUID(), user, null, 325);
            case LEVEL_2 -> new FireBall(UUID.randomUUID(), user, null, 357);
            case LEVEL_3 -> new FireBall(UUID.randomUUID(), user, null, 393);
            case LEVEL_4 -> new FireBall(UUID.randomUUID(), user, null, 432);
            case LEVEL_5 -> new FireBall(UUID.randomUUID(), user, null, 474);
        };
    }
}
