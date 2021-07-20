package events.cards;

import cards.Card;
import commands.Command;
import commands.gameStateCommands.cardCommands.CardAddedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import user.User;

import java.util.ArrayList;

public class CardAddedEvent extends CardEvent {
    private final Card card;
    private final Point2D position;

    public CardAddedEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card, Point2D position) {
        super(eventType, targetPlayers);
        this.card = card;
        this.position = position;
    }

    public CardAddedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card, Point2D position) {
        super(source, target, eventType, targetPlayers);
        this.card = card;
        this.position = position;
    }

    @Override
    public Command toCommand() {
        return new CardAddedCommand(this.card, this.position);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.cardAddedEventHandler(this);
    }
}
