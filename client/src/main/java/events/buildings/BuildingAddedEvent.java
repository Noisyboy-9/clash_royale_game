package events.buildings;

import cards.buildings.Building;
import commands.Command;
import commands.gameStateCommands.buildingsCommands.BuildingAddedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;

/**
 * The type Building added event.
 */
public class BuildingAddedEvent extends BuildingEvent {
    private final Building building;
    private final Point2D position;

    /**
     * Instantiates a new Building added event.
     *
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param building      the building
     * @param position      the position
     */
    public BuildingAddedEvent(EventType<? extends Event> eventType,
                              ArrayList<User> targetPlayers,
                              Building building,
                              Point2D position) {
        super(eventType, targetPlayers);
        this.building = building;
        this.position = position;
    }

    /**
     * Instantiates a new Building added event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param building      the building
     * @param position      the position
     */
    public BuildingAddedEvent(Object source,
                              EventTarget target,
                              EventType<? extends Event> eventType,
                              ArrayList<User> targetPlayers,
                              Building building,
                              Point2D position) {
        super(source, target, eventType, targetPlayers);
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

    @Override
    public Command toCommand() {
        return new BuildingAddedCommand(this.building, this.position);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.buildingAddedEventHandler(this);
    }
}
