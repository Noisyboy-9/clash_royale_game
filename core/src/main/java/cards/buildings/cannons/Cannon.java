package cards.buildings.cannons;

import cards.Card;
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
     * @param owner    the owner
     * @param position the position
     * @param HP       the hp
     * @param damage   the damage
     */
    public Cannon(UUID id, User owner, Point2D position, int HP, double damage) {
        super(id,
                6,
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

    @Override
    public Card create(User user, Point2D position) {
        return switch (user.getLevel()) {
            case LEVEL_1 -> new Cannon(UUID.randomUUID(), user, position, 380, 60);
            case LEVEL_2 -> new Cannon(UUID.randomUUID(), user, position, 418, 66);
            case LEVEL_3 -> new Cannon(UUID.randomUUID(), user, position, 459, 72);
            case LEVEL_4 -> new Cannon(UUID.randomUUID(), user, position, 505, 79);
            case LEVEL_5 -> new Cannon(UUID.randomUUID(), user, position, 554, 87);
        };
    }
}