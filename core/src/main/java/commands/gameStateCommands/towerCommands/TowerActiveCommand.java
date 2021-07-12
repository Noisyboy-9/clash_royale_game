package commands.gameStateCommands.towerCommands;

import cards.utils.AttackAble;
import towers.Tower;

/**
 * The type Tower active command.
 */
public class TowerActiveCommand extends TowerCommand {
    private final AttackAble target;
    private final Tower tower;

    /**
     * Instantiates a new Tower active command.
     *
     * @param target the target
     * @param tower  the tower
     */
    public TowerActiveCommand(AttackAble target, Tower tower) {
        this.target = target;
        this.tower = tower;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public AttackAble getTarget() {
        return target;
    }

    /**
     * Gets tower.
     *
     * @return the tower
     */
    public Tower getTower() {
        return tower;
    }
}
