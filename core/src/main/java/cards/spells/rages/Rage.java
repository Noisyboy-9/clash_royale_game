package cards.spells.rages;

import cards.Card;
import cards.spells.Spell;
import javafx.geometry.Point2D;
import user.User;

import java.util.UUID;

/**
 * The type Rage.
 */
public class Rage extends Spell {
    private double remainingFrameCount;

    /**
     * Instantiates a new Rage.
     *
     * @param id                  the id
     * @param owner               the owner
     * @param position            the position
     * @param remainingFrameCount the remainingFrameCount
     */
    protected Rage(UUID id,
                   User owner,
                   Point2D position,
                   double remainingFrameCount) {
        super(id, 3, owner, position, 5);
        this.remainingFrameCount = remainingFrameCount;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public double getRemainingFrameCount() {
        return remainingFrameCount;
    }

    /**
     * Decrease remaining frame count by.
     *
     * @param amount the amount
     */
    public void decreaseRemainingFrameCountBy(int amount) {
        this.remainingFrameCount -= amount;
    }

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.setHitSpeed(
                (1.4) * tower.getHitSpeed() // boost the attack speed of tower by 40%
        ));

        this.targetTroops.forEach(troop -> troop.setHitSpeed(
                (1.4) * troop.getHitSpeed() // boost the hit speed of troop by 40%
        ));

        this.targetTroops.forEach(troop -> troop.setDamage(
                (1.4) * troop.getDamage() // boost damage by damage of troop by 40%
        ));
    }

    /**
     * remove the effect of the spell
     */
    public void unChant() {
        this.targetTowers.forEach(tower -> tower.setHitSpeed(
                (5 * tower.getHitSpeed()) / 7 // change hit tower speed to normal value
        ));

        this.targetTroops.forEach(troop -> troop.setHitSpeed(
                (5 * troop.getHitSpeed()) / 7 // change hit troop speed to normal value
        ));

        this.targetTroops.forEach(troop -> troop.setDamage(
                (5 * troop.getDamage()) / 7// change damage of the troop to normal value
        ));
    }

    /**
     * Create card.
     *
     * @param user             the user
     * @param FRAME_PER_SECOND the frame per second
     * @return the card
     */
    public static Card create(User user, int FRAME_PER_SECOND) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Rage(UUID.randomUUID(), user, null, 6 * FRAME_PER_SECOND);
            case LEVEL_2 -> new Rage(UUID.randomUUID(), user, null, 6.5 * FRAME_PER_SECOND);
            case LEVEL_3 -> new Rage(UUID.randomUUID(), user, null, 7 * FRAME_PER_SECOND);
            case LEVEL_4 -> new Rage(UUID.randomUUID(), user, null, 7.5 * FRAME_PER_SECOND);
            case LEVEL_5 -> new Rage(UUID.randomUUID(), user, null, 8 * FRAME_PER_SECOND);
        };
    }
}
