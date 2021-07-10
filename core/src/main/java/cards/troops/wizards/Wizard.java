package cards.troops.wizards;

import cards.troops.Troop;
import cards.utils.MovementSpeedEnum;
import cards.utils.Position;
import user.User;

import java.util.UUID;

public class Wizard extends Troop {

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
    public Wizard(UUID id, int cost, User owner, Position position, int HP, int damage) {
        super(id, cost, owner, position, HP, damage, MovementSpeedEnum.MEDIUM, true, 5, 1.7);
    }
}
