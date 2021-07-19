package controllers.modes.botControllers;

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
import models.BotModeModel;

public class NormalBotModeController extends BotController {
    public NormalBotModeController(BotModeModel model) {
        super(model);
    }

    @Override
    public void handle(CustomEvent event) {

    }

    @Override
    public void troopAddedEventHandler(TroopAddedEvent event) {

    }

    @Override
    public void troopKilledEventHandler(TroopKilledEvent event) {

    }

    @Override
    public void towerDestroyedEventHandler(TowerDestroyedEvent event) {

    }

    @Override
    public void towerActiveEventHandler(TowerActiveEvent event) {

    }

    @Override
    public void spellAddedEventHandler(SpellAddedEvent event) {

    }

    @Override
    public void spellDurationFinishedEventHandler(SpellDurationFinishedEvent event) {

    }

    @Override
    public void crownCountChangeHandler(CrownCountChangeEvent event) {

    }

    @Override
    public void buildingAddedEventHandler(BuildingAddedEvent event) {

    }

    @Override
    public void buildingDurationFinishedHandler(BuildingDurationFinishedEvent event) {

    }
}
