package towers;

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
                       int damage) {
        super(id, owner, 1, true, 8, HP, damage, null);
    }

    /**
     * Create tower.
     *
     * @param owners the owners
     * @return the tower
     */
    public static Tower create(User owners) {
        return switch (owners.getLevel()) {
            case LEVEL_1 -> new QueenTower(UUID.randomUUID(), owners, 1400, 50);
            case LEVEL_2 -> new QueenTower(UUID.randomUUID(), owners, 1512, 54);
            case LEVEL_3 -> new QueenTower(UUID.randomUUID(), owners, 1624, 58);
            case LEVEL_4 -> new QueenTower(UUID.randomUUID(), owners, 1750, 62);
            case LEVEL_5 -> new QueenTower(UUID.randomUUID(), owners, 1890, 69);
        };
    }
}
