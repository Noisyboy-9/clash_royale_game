package towers;

import cards.utils.AttackAble;
import user.User;

import java.util.UUID;

/**
 * The type King tower.
 */
public class KingTower extends Tower {
    private boolean shooting;
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
                      int damage) {


        super(id, owner, 3, false, 7, HP, damage, null);
        this.shooting = false;
    }

    /**
     * Is shooting boolean.
     *
     * @return the boolean
     */
    public boolean isShooting() {
        return shooting;
    }

    @Override
    public void setTarget(AttackAble target) {
        super.setTarget(target);
        this.shooting = true;
    }

    /**
     * Create tower.
     *
     * @param owner the owner
     * @return the tower
     */
    public static Tower create(User owner) {
        return switch (owner.getLevel()) {
            case LEVEL_1 -> new KingTower(UUID.randomUUID(), owner, 2400, 50);
            case LEVEL_2 -> new KingTower(UUID.randomUUID(), owner, 2568, 53);
            case LEVEL_3 -> new KingTower(UUID.randomUUID(), owner, 2736, 57);
            case LEVEL_4 -> new KingTower(UUID.randomUUID(), owner, 2904, 60);
            case LEVEL_5 -> new KingTower(UUID.randomUUID(), owner, 3096, 64);
        };
    }
}
