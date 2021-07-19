package commands.gameStateCommands.buildingsCommands;

import cards.buildings.Building;

/**
 * The type Building duration finished command.
 */
public class BuildingDurationFinishedCommand extends BuildingCommand {
    private final Building building;

    /**
     * Instantiates a new Building duration finished command.
     *
     * @param building the building
     */
    public BuildingDurationFinishedCommand(Building building) {
        this.building = building;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    public Building getBuilding() {
        return building;
    }
}
