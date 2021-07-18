package cards.spells.arrows;

import cards.Card;
import cards.spells.Spell;
import cards.troops.Troop;
import exceptions.TargetAlreadyExistException;
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
    private final int damage;

    /**
     * Instantiates a new Arrows.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param damage   the damage
     */
    public Arrows(UUID id,
                  User owner,
                  Point2D position,
                  int damage) {
        super(id, 3, owner, position, 4);
        this.targetTowers = new ArrayList<>();
        this.targetTroops = new ArrayList<>();
        this.damage = damage;

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
    public void addTroopTarget(Troop troop) throws TargetAlreadyExistException {
        if (this.targetTroops.contains(troop)) {
            throw new TargetAlreadyExistException("tower with id: " + troop.getId().toString() + "exist");
        }

        this.targetTroops.add(troop);
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    @Override
    public void chant() {
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }

    @Override
    public Card create(User user, Point2D position) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Arrows(UUID.randomUUID(), user, position, 144);
            case LEVEL_2 -> new Arrows(UUID.randomUUID(), user, position, 156);
            case LEVEL_3 -> new Arrows(UUID.randomUUID(), user, position, 174);
            case LEVEL_4 -> new Arrows(UUID.randomUUID(), user, position, 189);
            case LEVEL_5 -> new Arrows(UUID.randomUUID(), user, position, 210);
        };
    }
}
