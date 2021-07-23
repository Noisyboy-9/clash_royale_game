package cards.spells.arrows;

import cards.Card;
import cards.spells.Spell;
import cards.troops.Troop;
import exceptions.TargetAlreadyExistException;
import javafx.geometry.Point2D;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Arrows.
 */
public class Arrows extends Spell {
    private final int damage;

    /**
     * Instantiates a new Arrows.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param damage   the damage
     */
    private Arrows(UUID id,
                   User owner,
                   Point2D position,
                   int damage) {
        super(id, 3, owner, position, 4);
        this.damage = damage;

    }


    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
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
            case LEVEL_1 -> new Arrows(UUID.randomUUID(), user, null, 144);
            case LEVEL_2 -> new Arrows(UUID.randomUUID(), user, null, 156);
            case LEVEL_3 -> new Arrows(UUID.randomUUID(), user, null, 174);
            case LEVEL_4 -> new Arrows(UUID.randomUUID(), user, null, 189);
            case LEVEL_5 -> new Arrows(UUID.randomUUID(), user, null, 210);
        };
    }
}
