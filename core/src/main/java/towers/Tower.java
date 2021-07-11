package towers;


import cards.utils.AttackAble;
import cards.utils.TypeEnum;
import user.UserLevelEnum;

import java.util.UUID;

/**
 * The type Tower.
 */
public abstract class Tower implements AttackAble {
    private final UUID id;
    private final int damage;
    private final UserLevelEnum ownerLevel;
    private final int demolitionBonusCount;
    private final TypeEnum selfType;
    private final TypeEnum attackType;
    private boolean active;
    private AttackAble target;
    private int HP;

    /**
     * Instantiates a new Tower.
     *
     * @param id                   the id
     * @param HP                   the hp
     * @param damage               the damage
     * @param ownerLevel           the owner level
     * @param demolitionBonusCount the demolition bonus count
     * @param active               the active
     * @param target               the target
     */
    public Tower(UUID id,
                 int HP,
                 int damage,
                 UserLevelEnum ownerLevel,
                 int demolitionBonusCount,
                 boolean active,
                 AttackAble target) {
        this.id = id;
        this.HP = HP;
        this.damage = damage;
        this.ownerLevel = ownerLevel;
        this.demolitionBonusCount = demolitionBonusCount;
        this.active = active;
        this.target = target;

        this.selfType = TypeEnum.GROUND;
        this.attackType = TypeEnum.AIR_GROUND;
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
     * Gets owner level.
     *
     * @return the owner level
     */
    public UserLevelEnum getOwnerLevel() {
        return ownerLevel;
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
    public void reduceHealthBy(int damage) {
        this.HP -= damage;
    }

    @Override
    public TypeEnum getSelfType() {
        return this.selfType;
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
