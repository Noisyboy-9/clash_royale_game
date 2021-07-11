package cards.spells.balls;

import cards.spells.Spell;
import cards.troops.Troop;
import cards.utils.Position;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Fire ball.
 */
public class FireBall extends Spell {
    private int damage;
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<Troop> targetTroops;

    /**
     * Instantiates a new Fire ball.
     *
     * @param id           the id
     * @param cost         the cost
     * @param owner        the owner
     * @param position     the position
     * @param radius       the radius
     * @param targetTowers the target towers
     * @param targetTroops the target troops
     */
    public FireBall(UUID id,
                    int cost,
                    User owner,
                    Position position,
                    int radius,
                    ArrayList<Tower> targetTowers,
                    ArrayList<Troop> targetTroops) {
        super(id, 4, owner, position, 2.5);
        this.targetTowers = targetTowers;
        this.targetTroops = targetTroops;

        this.setDamage();
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

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }
}
