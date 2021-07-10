package cards.troops;

import cards.Card;
import cards.utils.AttackAble;
import cards.utils.MovementSpeedEnum;

/**
 * The abstract type Troop.
 */
public abstract class Troop extends Card implements AttackAble {
    private final int damage;
    private final MovementSpeedEnum movementSpeed;
    private final int areaSplash;
    private final int range;
    private final double hitSpeed;
    private AttackAble attackTarget;
    private int HP;

    /**
     * Instantiates a new Troop.
     *
     * @param HP            the hp
     * @param damage        the damage
     * @param movementSpeed the movement speed
     * @param areaSplash    the area splash
     * @param range         the range
     */
    public Troop(int HP,
                 int damage,
                 MovementSpeedEnum movementSpeed,
                 int areaSplash,
                 int range,
                 double hitSpeed) {
        this.HP = HP;
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.areaSplash = areaSplash;
        this.range = range;
        this.hitSpeed = hitSpeed;
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
    public int getAreaSplash() {
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
    public void setAttackTarget(AttackAble attackTarget) {
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
