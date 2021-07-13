package cards.utils;

/**
 * The interface Attack able.
 */
public interface AttackAble {
    /**
     * Reduce health by.
     *
     * @param damage the damage
     */
    void reduceHealthBy(double damage);

    /**
     * get the type of the attacker itself.
     *
     * @return the self type
     */
    TypeEnum getSelfType();
}
