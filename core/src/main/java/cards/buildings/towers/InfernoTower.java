package cards.buildings.towers;

import cards.buildings.Building;
import cards.utils.AttackAble;
import cards.utils.Position;
import cards.utils.TypeEnum;
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
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public InfernoTower(UUID id,
                        int cost,
                        User owner,
                        Position position,
                        int HP,
                        double damage) {
        super(id,
                cost,
                owner,
                position,
                HP,
                6,
                damage,
                0.4,
                40,
                TypeEnum.GROUND,
                TypeEnum.AIR_GROUND);
        this.targets = new ArrayList<>();
    }

    @Override
    public void attack() {
        this.targets.forEach(target -> target.reduceHealthBy(this.getDamage()));
    }

    @Override
    public void addTarget(AttackAble target) {
        if (!this.targets.contains(target)) {
            this.targets.add(target);
        }
    }
}
