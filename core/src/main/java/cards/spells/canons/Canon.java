package cards.spells.canons;

import cards.spells.Spell;
import cards.utils.AttackAble;
import cards.utils.Position;
import cards.utils.TypeEnum;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Canon.
 */
public class Canon extends Spell implements AttackAble {
    private final ArrayList<AttackAble> targets;
    private final TypeEnum selfType;
    private final TypeEnum targetType;
    private int damage;
    private int HP;

    /**
     * Instantiates a new Canon.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     */
    public Canon(UUID id,
                 User owner,
                 Position position) {
        super(id, 6, owner, position, 5.5);
        targets = new ArrayList<>();
        this.setDamage();
        this.setHP();

        this.selfType = TypeEnum.GROUND;
        this.targetType = TypeEnum.GROUND;
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
     * Gets hp.
     *
     * @return the hp
     */
    public int getHP() {
        return HP;
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

    private void setHP() {
        this.HP = switch (this.getOwner().getLevel()) {
            case LEVEL_1 -> 380;
            case LEVEL_2 -> 418;
            case LEVEL_3 -> 459;
            case LEVEL_4 -> 505;
            case LEVEL_5 -> 554;
        };
    }

    private void setDamage() {
        this.damage = switch (this.getOwner().getLevel()) {
            case LEVEL_1 -> 60;
            case LEVEL_2 -> 66;
            case LEVEL_3 -> 72;
            case LEVEL_4 -> 79;
            case LEVEL_5 -> 87;
        };
    }
}
