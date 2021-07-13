package commands.gameStateCommands.towerCommands;

import towers.Tower;

/**
 * The type Tower destroyed command.
 */
public class TowerDestroyedCommand extends TowerCommand {
    private final Tower tower;
    private final int destructionCrownBonusCount;

    /**
     * Instantiates a new Tower destroyed command.
     *
     * @param tower                      the tower
     * @param towerDestructionCrownBonus the tower destruction crown bonus
     */
    public TowerDestroyedCommand(Tower tower, int towerDestructionCrownBonus) {
        this.tower = tower;
        this.destructionCrownBonusCount = towerDestructionCrownBonus;
    }

    /**
     * Gets tower.
     *
     * @return the tower
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Gets tower destruction crown bonus.
     *
     * @return the tower destruction crown bonus
     */
    public int getDestructionCrownBonusCount() {
        return destructionCrownBonusCount;
    }
}
