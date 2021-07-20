package events.cards;

import cards.Card;
import commands.Command;
import commands.gameStateCommands.cardCommands.CardDeletedCommand;
import controllers.modes.CustomEventHandler;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import user.User;

import java.util.ArrayList;

public class CardDeletedEvent extends CardEvent {
    private final Card card;

    public CardDeletedEvent(EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card) {
        super(eventType, targetPlayers);
        this.card = card;
    }

    public CardDeletedEvent(Object source, EventTarget target, EventType<? extends Event> eventType, ArrayList<User> targetPlayers, Card card) {
        super(source, target, eventType, targetPlayers);
        this.card = card;
    }

    @Override
    public Command toCommand() {
        return new CardDeletedCommand(this.card);
    }

    @Override
    public void invokeHandler(CustomEventHandler handler) {
        handler.cardDeletedEventHandler(this);
    }
}
