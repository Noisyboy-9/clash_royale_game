package towers;


import cards.utils.AttackAble;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import towers.traits.CanKnowItsExactTowerTypeTrait;
import user.User;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Tower.
 */
public abstract class Tower implements AttackAble, CanKnowItsExactTowerTypeTrait, Serializable {
    private final UUID id;
    private final User owner;
    private final int demolitionBonusCount;
    private final TypeEnum selfType;
    private final TypeEnum attackType;
    private final int range;
    private int damage;
    private boolean active;
    private AttackAble target;
    private double HP;
    private double hitSpeed;
    private Point2D position;

    /**
     * Instantiates a new Tower.
     *
     * @param id                   the id
     * @param owner                the owner level
     * @param demolitionBonusCount the demolition bonus count
     * @param active               the active
     * @param range                the range
     * @param HP                   the hp
     * @param damage               the damage
     * @param position             the position
     */
    protected Tower(UUID id, User owner, int demolitionBonusCount, boolean active, int range, int HP, int damage, Point2D position) {
        this.id = id;
        this.HP = HP;
        this.damage = damage;
        this.owner = owner;
        this.demolitionBonusCount = demolitionBonusCount;
        this.active = active;
        this.range = range;
        this.position = position;

        this.target = null;
        this.selfType = TypeEnum.GROUND;
        this.attackType = TypeEnum.AIR_GROUND;
        this.hitSpeed = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tower tower = (Tower) o;
        return id.equals(tower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Gets tower range.
     *
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * Gets attack speed.
     *
     * @return the attack speed
     */
    public double getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Sets attack speed.
     *
     * @param attackSpeed the attack speed
     */
    public void setHitSpeed(double attackSpeed) {
        this.hitSpeed = attackSpeed;
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
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets owner level.
     *
     * @return the owner level
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Gets the number of crowns that will be awarded to
     * the player for destroying the tower.
     *
     * @return the demolition bonus count
     */
    public int getDemolitionBonusCount() {
        return demolitionBonusCount;
    }


    /**
     * Gets target that the tower is trying to shoot at.
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
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Make the tower active.
     */
    public void activate() {
        this.active = true;
    }

    /**
     * De active.
     */
    public void deActive() {
        this.active = false;
    }

    /**
     * Shoot the target and reduce it's health
     */
    public void shoot() {
        this.target.reduceHealthBy(this.damage);
    }

    @Override
    public void reduceHealthBy(double damage) {
        this.HP -= damage;
    }

    @Override
    public TypeEnum getSelfType() {
        return this.selfType;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public boolean isDead() {
        return this.HP <= 0;
    }

    /**
     * Gets attack type.
     *
     * @return the attack type
     */
    public TypeEnum getAttackType() {
        return attackType;
    }
}
