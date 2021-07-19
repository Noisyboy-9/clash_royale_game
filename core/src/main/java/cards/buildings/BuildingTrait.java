package cards.buildings;

import cards.Card;
import cards.utils.AttackAble;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Building.
 */
public abstract class BuildingTrait extends Card implements AttackAble {
    private final TypeEnum selfType;
    private final TypeEnum attackType;
    private final double radius;
    private final double duration;
    private AttackAble target;
    private int HP;
    private double damage;
    private double hitSpeed;

    /**
     * Instantiates a new Card.
     *
     * @param id         the id
     * @param cost       the cost
     * @param owner      the owner
     * @param position   the position
     * @param HP         the hp
     * @param radius     the radius
     * @param damage     the damage
     * @param hitSpeed   the hit speed
     * @param duration   the duration
     * @param selfType   the self type
     * @param attackType the attack type
     */
    protected BuildingTrait(UUID id,
                            int cost,
                            User owner,
                            Point2D position,
                            int HP,
                            double radius,
                            double damage,
                            double hitSpeed,
                            double duration,
                            TypeEnum selfType,
                            TypeEnum attackType) {
        super(id, cost, owner, position);
        this.HP = HP;
        this.radius = radius;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.duration = duration;
        this.selfType = selfType;
        this.attackType = attackType;
    }

    @Override
    public void reduceHealthBy(double damage) {
        this.HP -= damage;
    }

    @Override
    public TypeEnum getSelfType() {
        return selfType;
    }

    /**
     * Gets attack type.
     *
     * @return the attack type
     */
    public TypeEnum getAttackType() {
        return attackType;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets hit speed.
     *
     * @return the hit speed
     */
    public double getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Sets hit speed.
     *
     * @param hitSpeed the hit speed
     */
    public void setHitSpeed(double hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public AttackAble getTarget() {
        return target;
    }

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(AttackAble target) {
        this.target = target;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(double damage) {
        this.damage = damage;
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
     * Sets hp.
     *
     * @param HP the hp
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Do main action that the building shoot do
     */
    public abstract void attack();

    /**
     * Add target to the list of targets.
     *
     * @param target the target
     */
    public abstract void addTarget(AttackAble target);
}