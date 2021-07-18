package cards.spells.arrows;

import cards.Card;
import cards.spells.Spell;
import cards.troops.Troop;
import cards.troops.valkyries.Valkyrie;
import javafx.geometry.Point2D;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Arrows.
 */
public class Arrows extends Spell {
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<Troop> targetTroops;
    private int damage;

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
                  Point2D position,
                  ArrayList<Tower> targetTowers,
                  ArrayList<Troop> targetTroops) {
        super(id, 3, owner, position, 4);
        this.targetTowers = targetTowers;
        this.targetTroops = targetTroops;

        this.setDamage();
    }

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }

    @Override
    public Card create(User user, Point2D position) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Arrows(UUID.randomUUID(), user, position, 2000, 126);
            case LEVEL_2 -> new Arrows(UUID.randomUUID(), user, position, 2200, 138);
            case LEVEL_3 -> new Arrows(UUID.randomUUID(), user, position, 2420, 152);
            case LEVEL_4 -> new Arrows(UUID.randomUUID(), user, position, 2660, 167);
            case LEVEL_5 -> new Arrows(UUID.randomUUID(), user, position, 2920, 183);
        };
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
}
