package towers;

import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type King tower.
 */
public class KingTower extends Tower {

    /**
     * Instantiates a new Tower.
     *
     * @param id     the id
     * @param owner  the owner level
     * @param HP     the hp
     * @param damage the damage
     */
    private KingTower(UUID id,
                      User owner,
                      int HP,
                      int damage,
                      Point2D position) {


        super(id, owner, 3, false, 7, HP, damage, position);
    }

    /**
     * Create tower.
     *
     * @param owner the owner
     * @return the tower
     */
    public static Tower create(User owner, Point2D position) {
        return switch (owner.getLevel()) {
            case LEVEL_1 -> new KingTower(UUID.randomUUID(), owner, 2400, 50, position);
            case LEVEL_2 -> new KingTower(UUID.randomUUID(), owner, 2568, 53, position);
            case LEVEL_3 -> new KingTower(UUID.randomUUID(), owner, 2736, 57, position);
            case LEVEL_4 -> new KingTower(UUID.randomUUID(), owner, 2904, 60, position);
            case LEVEL_5 -> new KingTower(UUID.randomUUID(), owner, 3096, 64, position);
        };
    }
}
