package cards.buildings.towers;

import cards.Card;
import cards.buildings.Building;
import cards.utils.AttackAble;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Inferno tower.
 */
public class InfernoTower extends Building {
    private final ArrayList<AttackAble> targets;
    /**
     * Instantiates a new Inferno tower.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    private InfernoTower(UUID id,
                         User owner,
                         Point2D position,
                         int HP,
                         double damage) {
        super(id,
                3,
                owner,
                position,
                HP,
                6,
                damage,
                0.4,
                40 * 30,
                TypeEnum.GROUND,
                TypeEnum.AIR_GROUND);
        this.targets = new ArrayList<>();
    }

    @Override
    public void attack() {
        this.targets.forEach(target -> target.reduceHealthBy(this.getDamage()));
    }

    /**
     * Add target.
     *
     * @param target the target
     */
    public void addTarget(AttackAble target) {
        if (!this.targets.contains(target)) {
            this.targets.add(target);
        }
    }

    /**
     * Create card.
     *
     * @param user the user
     * @return the card
     */
    public static Card create(User user) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new InfernoTower(UUID.randomUUID(), user, null, 800, 210);
            case LEVEL_2 -> new InfernoTower(UUID.randomUUID(), user, null, 880, 231);
            case LEVEL_3 -> new InfernoTower(UUID.randomUUID(), user, null, 968, 254);
            case LEVEL_4 -> new InfernoTower(UUID.randomUUID(), user, null, 1064, 279);
            case LEVEL_5 -> new InfernoTower(UUID.randomUUID(), user, null, 1168, 307);
        };
    }
}
