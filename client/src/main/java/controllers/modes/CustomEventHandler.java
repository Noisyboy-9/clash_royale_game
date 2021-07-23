package controllers.modes;

import events.CustomEvent;
import events.cards.CardAddedEvent;
import events.cards.CardDeletedEvent;
import events.counts.CrownCountChangeEvent;
import events.towers.TowerActiveEvent;
import events.towers.TowerDestroyedEvent;
import javafx.event.EventHandler;

/**
 * The interface Custom event handler.
 */
public interface CustomEventHandler extends EventHandler<CustomEvent> {
    @Override
    void handle(CustomEvent event);


    /**
     * Tower destroyed event handler.
     *
     * @param event the event
     */
    void towerDestroyedEventHandler(TowerDestroyedEvent event);

    /**
     * Tower active event handler.
     *
     * @param event the event
     */
    void towerActiveEventHandler(TowerActiveEvent event);

    /**
     * Card added event handler.
     *
     * @param event the event
     */
    void cardAddedEventHandler(CardAddedEvent event);

    /**
     * Card deleted event handler.
     *
     * @param event the event
     */
    void cardDeletedEventHandler(CardDeletedEvent event);

    /**
     * Crown count change handler.
     *
     * @param event the event
     */
    void crownCountChangeHandler(CrownCountChangeEvent event);
}
