package commands.gameStateCommands.buildingsCommands;

import cards.buildings.Building;
import javafx.geometry.Point2D;

/**
 * The type Building added command.
 */
public class BuildingAddedCommand extends BuildingCommand {
    private final Building building;
    private final Point2D position;

    /**
     * Instantiates a new Building added command.
     *
     * @param building the building
     * @param position the position
     */
    public BuildingAddedCommand(Building building, Point2D position) {
        this.building = building;
        this.position = position;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }
}
