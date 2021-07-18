package cards.spells.rages;

import cards.spells.Spell;
import cards.troops.Troop;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Rage.
 */
public class Rage extends Spell {
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<Troop> targetTroops;

    /**
     * Instantiates a new Rage.
     *
     * @param id           the id
     * @param owner        the owner
     * @param position     the position
     * @param targetTowers the target towers
     * @param targetTroops the target troops
     */
    public Rage(UUID id,
                User owner,
                Position position,
                ArrayList<Tower> targetTowers,
                ArrayList<Troop> targetTroops) {
        super(id, 3, owner, position, 5);

        this.targetTowers = targetTowers;
        this.targetTroops = targetTroops;
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
}
