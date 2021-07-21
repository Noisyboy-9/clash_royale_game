package cards.utils;


import javafx.geometry.Point2D;

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

    /**
     * Gets position.
     *
     * @return the position
     */
    Point2D getPosition();

    /**
     * Sets position.
     *
     * @param position the position
     */
    void setPosition(Point2D position);

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    boolean isDead();
}
