package cards.spells.rages;

import cards.Card;
import cards.spells.SpellTrait;
import cards.troops.TroopTrait;
import exceptions.TargetAlreadyExistException;
import javafx.geometry.Point2D;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Rage.
 */
public class Rage extends SpellTrait {
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<TroopTrait> targetTroops;
    private final double duration;

    /**
     * Instantiates a new Rage.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param duration the duration
     */
    protected Rage(UUID id,
                   User owner,
                   Point2D position,
                   double duration) {
        super(id, 3, owner, position, 5);
        this.duration = duration;
        this.targetTowers = new ArrayList<>();
        this.targetTroops = new ArrayList<>();
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
     * Add tower target.
     *
     * @param tower the tower
     * @throws TargetAlreadyExistException the target already exist exception
     */
    public void addTowerTarget(Tower tower) throws TargetAlreadyExistException {
        if (this.targetTowers.contains(tower)) {
            throw new TargetAlreadyExistException("tower with id: " + tower.getId().toString() + "exist");
        }

        this.targetTowers.add(tower);
    }


    /**
     * Add troop target.
     *
     * @param troop the troop
     * @throws TargetAlreadyExistException the target already exist exception
     */
    public void addTroopTarget(TroopTrait troop) throws TargetAlreadyExistException {
        if (this.targetTroops.contains(troop)) {
            throw new TargetAlreadyExistException("tower with id: " + troop.getId().toString() + "exist");
        }

        this.targetTroops.add(troop);
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
     * @param user the user
     * @return the card
     */
    public static Card create(User user) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Rage(UUID.randomUUID(), user, null, 6);
            case LEVEL_2 -> new Rage(UUID.randomUUID(), user, null, 6.5);
            case LEVEL_3 -> new Rage(UUID.randomUUID(), user, null, 7);
            case LEVEL_4 -> new Rage(UUID.randomUUID(), user, null, 7.5);
            case LEVEL_5 -> new Rage(UUID.randomUUID(), user, null, 8);
        };
    }
}
