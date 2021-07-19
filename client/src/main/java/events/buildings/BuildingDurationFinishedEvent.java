package events.buildings;

import cards.buildings.Building;
import commands.Command;
import commands.gameStateCommands.buildingsCommands.BuildingDurationFinishedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

/**
 * The type Building duration finished event.
 */
public class BuildingDurationFinishedEvent extends BuildingEvent {
    private final Building building;

    /**
     * Instantiates a new Building duration finished event.
     *
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param building      the building
     */
    public BuildingDurationFinishedEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Building building) {
        super(eventType, targetPlayers);
        this.building = building;
    }

    /**
     * Instantiates a new Building duration finished event.
     *
     * @param source        the source
     * @param target        the target
     * @param eventType     the event type
     * @param targetPlayers the target players
     * @param building      the building
     */
    public BuildingDurationFinishedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Building building) {
        super(source, target, eventType, targetPlayers);
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

    @Override
    public Command toCommand() {
        return new BuildingDurationFinishedCommand(this.building);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.buildingDurationFinishedHandler(this);
    }
}
