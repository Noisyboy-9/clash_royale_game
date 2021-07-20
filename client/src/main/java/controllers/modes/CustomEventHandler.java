package controllers.modes;

import events.CustomEvent;
import events.cards.CardAddedEvent;
import events.cards.CardDeletedEvent;
import events.counts.CrownCountChangeEvent;
import events.towers.TowerActiveEvent;
import events.towers.TowerDestroyedEvent;
import javafx.event.EventHandler;

public interface CustomEventHandler extends EventHandler<CustomEvent> {
    @Override
    void handle(CustomEvent event);


    void towerDestroyedEventHandler(TowerDestroyedEvent event);

    void towerActiveEventHandler(TowerActiveEvent event);

    void cardAddedEventHandler(CardAddedEvent event);

    void cardDeletedEventHandler(CardDeletedEvent event);

    void crownCountChangeHandler(CrownCountChangeEvent event);
}
