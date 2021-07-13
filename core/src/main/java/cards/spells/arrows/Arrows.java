package cards.spells.arrows;

import cards.spells.Spell;
import cards.troops.Troop;
import cards.utils.Position;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Arrows.
 */
public class Arrows extends Spell {
    private int damage;
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<Troop> targetTroops;

    /**
     * Instantiates a new Arrows.
     *
     * @param id           the id
     * @param owner        the owner
     * @param position     the position
     * @param targetTowers the target towers
     * @param targetTroops the target troops
     */
    public Arrows(UUID id,
                  User owner,
                  Position position,
                  ArrayList<Tower> targetTowers,
                  ArrayList<Troop> targetTroops) {
        super(id, 3, owner, position, 4);
        this.targetTowers = targetTowers;
        this.targetTroops = targetTroops;

        this.setDamage();
    }

    private void setDamage() {
        this.damage = switch (this.getOwner().getLevel()) {
            case LEVEL_1 -> 144;
            case LEVEL_2 -> 156;
            case LEVEL_3 -> 174;
            case LEVEL_4 -> 189;
            case LEVEL_5 -> 210;
        };
    }

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }
}
