package controllers.modes;

import events.CustomEvent;
import events.buildings.BuildingAddedEvent;
import events.buildings.BuildingDurationFinishedEvent;
import events.counts.CrownCountChangeEvent;
import events.spells.SpellAddedEvent;
import events.spells.SpellDurationFinishedEvent;
import events.towers.TowerActiveEvent;
import events.towers.TowerDestroyedEvent;
import events.troops.TroopAddedEvent;
import events.troops.TroopKilledEvent;
import javafx.event.EventHandler;

public interface CustomEventHandler extends EventHandler<CustomEvent> {
    @Override
    void handle(CustomEvent event);

    void troopAddedEventHandler(TroopAddedEvent event);

    void troopKilledEventHandler(TroopKilledEvent event);

    void towerDestroyedEventHandler(TowerDestroyedEvent event);

    void towerActiveEventHandler(TowerActiveEvent event);

    void spellAddedEventHandler(SpellAddedEvent event);

    void spellDurationFinishedEventHandler(SpellDurationFinishedEvent event);

    void crownCountChangeHandler(CrownCountChangeEvent event);

    void buildingAddedEventHandler(BuildingAddedEvent event);

    void buildingDurationFinishedHandler(BuildingDurationFinishedEvent event);
}
