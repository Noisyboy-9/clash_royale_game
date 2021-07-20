package towers;

import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Queen tower.
 */
public class QueenTower extends Tower {

    /**
     * Instantiates a new Tower.
     *
     * @param id     the id
     * @param owner  the owner level
     * @param HP     the hp
     * @param damage the damage
     */
    private QueenTower(UUID id,
                       User owner,
                       int HP,
                       int damage,
                       Point2D position) {
        super(id, owner, 1, true, 8, HP, damage, position);
    }

    public static Tower create(User owner, Point2D position) {
        return switch (owner.getLevel()) {
            case LEVEL_1 -> new QueenTower(UUID.randomUUID(), owner, 1400, 50, position);
            case LEVEL_2 -> new QueenTower(UUID.randomUUID(), owner, 1512, 54, position);
            case LEVEL_3 -> new QueenTower(UUID.randomUUID(), owner, 1624, 58, position);
            case LEVEL_4 -> new QueenTower(UUID.randomUUID(), owner, 1750, 62, position);
            case LEVEL_5 -> new QueenTower(UUID.randomUUID(), owner, 1890, 69, position);
        };
    }
}
