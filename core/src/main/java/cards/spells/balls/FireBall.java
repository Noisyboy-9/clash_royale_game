package cards.spells.balls;

import cards.spells.Spell;
import cards.troops.Troop;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Fire ball.
 */
public class FireBall extends Spell {
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<Troop> targetTroops;
    private int damage;

    /**
     * Instantiates a new Fire ball.
     *
     * @param id           the id
     * @param owner        the owner
     * @param position     the position
     * @param targetTowers the target towers
     * @param targetTroops the target troops
     */
    public FireBall(UUID id,
                    User owner,
                    Position position,
                    ArrayList<Tower> targetTowers,
                    ArrayList<Troop> targetTroops) {
        super(id, 4, owner, position, 2.5);
        this.targetTowers = targetTowers;
        this.targetTroops = targetTroops;

        this.setDamage();
    }

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }

    private void setDamage() {
        this.damage = switch (this.getOwner().getLevel()) {
            case LEVEL_1 -> 325;
            case LEVEL_2 -> 357;
            case LEVEL_3 -> 393;
            case LEVEL_4 -> 432;
            case LEVEL_5 -> 474;
        };
    }
}
