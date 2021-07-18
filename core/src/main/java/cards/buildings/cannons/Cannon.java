package cards.buildings.cannons;

import cards.buildings.Building;
import cards.utils.AttackAble;
import cards.utils.TypeEnum;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Canon.
 */
public class Cannon extends Building {
    private final ArrayList<AttackAble> targets;

    /**
     * Instantiates a new Card.
     *
     * @param id       the id
     * @param cost     the cost
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Cannon(UUID id, int cost, User owner, Point2D position, int HP, double damage) {
        super(id,
                cost,
                owner,
                position,
                HP,
                5.5,
                damage,
                0.8,
                30,
                TypeEnum.GROUND,
                TypeEnum.GROUND
        );

        this.targets = new ArrayList<>();
    }


    @Override
    public void addTarget(AttackAble target) {
        if (!this.targets.contains(target)) {
            this.targets.add(target);
        }
    }


    @Override
    public void attack() {
        this.targets.forEach(target -> target.reduceHealthBy(this.getDamage()));
    }
}