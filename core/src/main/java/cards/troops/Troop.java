package cards.troops;

import cards.Card;
import cards.utils.AttackAble;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import cards.utils.TroopTypeEnum;
import exceptions.InvalidAttackTargetException;
import user.User;

import java.util.UUID;

/**
 * The abstract type Troop.
 */
public abstract class Troop extends Card implements AttackAble {
    private final int damage;
    private final MovementSpeedEnum movementSpeed;
    private final boolean areaSplash;
    private final int range;
    private final double hitSpeed;
    private final TroopTypeEnum selfType;
    private final TroopTypeEnum attackType;
    private AttackAble attackTarget;
    private int HP;

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
    public Troop(UUID id,
                 int cost,
                 User owner,
                 Position position,
                 int HP,
                 int damage,
                 MovementSpeedEnum speed,
                 boolean areaSplash,
                 int range,
                 double hitSpeed,
                 TroopTypeEnum selfType,
                 TroopTypeEnum attackType) {
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

    @Override
    public void reduceHealthBy(int damage) {
        this.HP -= damage;
    }


    /**
     * Gets attack type.
     *
     * @return the attack type
     */
    public TroopTypeEnum getAttackType() {
        return this.attackType;
    }

    @Override
    public TroopTypeEnum getSelfType() {
        return this.selfType;
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
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
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
