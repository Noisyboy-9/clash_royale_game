package cards.troops;

import cards.Card;
import cards.utils.AttackAble;
import cards.utils.MovementSpeedEnum;
import cards.utils.TypeEnum;
import exceptions.InvalidAttackTargetException;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The abstract type Troop.
 */
public abstract class TroopTrait extends Card implements AttackAble {
    private final MovementSpeedEnum movementSpeed;
    private final boolean areaSplash;
    private final int range;
    private final TypeEnum selfType;
    private final TypeEnum attackType;
    private double damage;
    private double hitSpeed;
    private AttackAble attackTarget;
    private double HP;

    /**
     * Instantiates a new Card.
     *
     * @param id         the id
     * @param cost       the cost
     * @param owner      the owner
     * @param position   the position
     * @param HP         the hp
     * @param damage     the damage
     * @param speed      the speed
     * @param areaSplash the area splash
     * @param range      the range
     * @param hitSpeed   the hit speed
     * @param selfType   the self type
     * @param attackType the attack type
     */
    protected TroopTrait(UUID id,
                         int cost,
                         User owner,
                         Point2D position,
                         int HP,
                         double damage,
                         MovementSpeedEnum speed,
                         boolean areaSplash,
                         int range,
                         double hitSpeed,
                         TypeEnum selfType,
                         TypeEnum attackType) {
        super(id, cost, owner, position);
        this.HP = HP;
        this.damage = damage;
        this.movementSpeed = speed;
        this.areaSplash = areaSplash;
        this.range = range;
        this.hitSpeed = hitSpeed;
        this.selfType = selfType;
        this.attackType = attackType;
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

    @Override
    public void reduceHealthBy(double damage) {
        this.HP -= damage;
    }

    /**
     * Gets attack type.
     *
     * @return the attack type
     */
    public TypeEnum getAttackType() {
        return this.attackType;
    }

    @Override
    public TypeEnum getSelfType() {
        return this.selfType;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public double getHP() {
        return HP;
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
     * Gets movement speed.
     *
     * @return the movement speed
     */
    public MovementSpeedEnum getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Gets area splash.
     *
     * @return the area splash
     */
    public boolean isAreaSplash() {
        return areaSplash;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * Gets attack target.
     *
     * @return the attack target
     */
    public AttackAble getAttackTarget() {
        return attackTarget;
    }

    /**
     * Sets attack target.
     *
     * @param attackTarget the attack target
     * @throws InvalidAttackTargetException the invalid attack target exception
     */
    public void setAttackTarget(AttackAble attackTarget) throws InvalidAttackTargetException {
        if (attackTarget.getSelfType() != this.getAttackType()) {
            throw new InvalidAttackTargetException("Troop with attack type set to: " + this.getSelfType().toString() + "can't attack target with type: " + this.getAttackType().toString());
        }

        this.attackTarget = attackTarget;
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return this.getHP() < 0;
    }

    /**
     * Attack the selected attack target.
     */
    public void attack() {
        this.attackTarget.reduceHealthBy(this.damage);
    }
}