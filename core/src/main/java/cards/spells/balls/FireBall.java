package cards.spells.balls;

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
 * The type Fire ball.
 */
public class FireBall extends SpellTrait {
    private final ArrayList<Tower> targetTowers;
    private final ArrayList<TroopTrait> targetTroops;
    private final int damage;

    /**
     * Instantiates a new Fire ball.
     *
     * @param id       the id
     * @param owner    the owner
     * @param position the position
     * @param damage   the damage
     */
    private FireBall(UUID id,
                     User owner,
                     Point2D position,
                     int damage) {
        super(id, 4, owner, position, 2.5);

        this.damage = damage;
        this.targetTroops = new ArrayList<>();
        this.targetTowers = new ArrayList<>();
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
        this.targetTowers.forEach(tower -> tower.reduceHealthBy(this.damage));
        this.targetTroops.forEach(troop -> troop.reduceHealthBy(this.damage));
    }

    public static Card create(User user) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new FireBall(UUID.randomUUID(), user, null, 325);
            case LEVEL_2 -> new FireBall(UUID.randomUUID(), user, null, 357);
            case LEVEL_3 -> new FireBall(UUID.randomUUID(), user, null, 393);
            case LEVEL_4 -> new FireBall(UUID.randomUUID(), user, null, 432);
            case LEVEL_5 -> new FireBall(UUID.randomUUID(), user, null, 474);
        };
    }
}
