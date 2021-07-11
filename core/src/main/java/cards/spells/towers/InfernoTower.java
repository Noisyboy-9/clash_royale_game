package cards.spells.towers;

import cards.spells.Spell;
import cards.utils.AttackAble;
import cards.utils.Position;
import cards.utils.TypeEnum;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Inferno tower.
 */
public class InfernoTower extends Spell implements AttackAble {
    private final ArrayList<AttackAble> targets;
    private int damage;
    private int HP;
    private TypeEnum selfType;
    private TypeEnum targetType;

    /**
     * Instantiates a new Inferno tower.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     * @param radius   the radius
     */
    public InfernoTower(UUID id, int cost, User owner, Position position, double radius) {
        super(id, 5, owner, position, 6);
        targets = new ArrayList<>();
        this.setDamageLimits();
        this.setHP();
    }

    @Override
    public void reduceHealthBy(double damage) {
        this.HP -= damage;
    }

    @Override
    public TypeEnum getSelfType() {
        return this.selfType;
    }

    /**
     * Gets target type.
     *
     * @return the target type
     */
    public TypeEnum getTargetType() {
        return this.targetType;
    }

    /**
     * Add target.
     *
     * @param target the target
     */
    public void addTarget(AttackAble target) {
        this.targets.add(target);
    }

    @Override
    public void chant() {
        this.targets.forEach(target -> target.reduceHealthBy(this.damage));
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHP() {
        return HP;
    }

    private void setHP() {
        this.HP = switch (this.getOwner().getLevel()) {
            case LEVEL_1 -> 800;
            case LEVEL_2 -> 880;
            case LEVEL_3 -> 968;
            case LEVEL_4 -> 1064;
            case LEVEL_5 -> 1168;
        };
    }

    private void setDamageLimits() {
        this.damage = switch (this.getOwner().getLevel()) {
            case LEVEL_1 -> 210;
            case LEVEL_2 -> 231;
            case LEVEL_3 -> 254;
            case LEVEL_4 -> 279;
            case LEVEL_5 -> 307;
        };
    }
}
