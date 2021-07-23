package controllers.menus.runnables;

import commands.gameStateCommands.GameStateCommand;
import commands.gameStateCommands.cardCommands.CardAddedCommand;
import commands.gameStateCommands.cardCommands.CardDeletedCommand;
import commands.gameStateCommands.gameBonusCommands.CrownCountChangeCommand;
import commands.gameStateCommands.towerCommands.TowerActiveCommand;
import commands.gameStateCommands.towerCommands.TowerDestroyedCommand;
import controllers.connector.Connector;
import controllers.modes.onlineControllers.OnlineController;
import events.cards.CardAddedEvent;
import events.cards.CardDeletedEvent;
import events.counts.CrownCountChangeEvent;
import events.towers.TowerActiveEvent;
import events.towers.TowerDestroyedEvent;
import globals.GlobalData;
import javafx.event.Event;

import java.io.IOException;

/**
 * The type Game command receiver runnable.
 */
public record GameCommandReceiverRunnable(OnlineController controller) implements Runnable {
    @Override
    public void run() {
        try {
            GameStateCommand gameStateCommand = (GameStateCommand) Connector.getInstance().getResponse().readObject();

            if (gameStateCommand.isTowerDestroyedCommand()) {
                TowerDestroyedCommand command = (TowerDestroyedCommand) gameStateCommand;
                TowerDestroyedEvent event = new TowerDestroyedEvent(Event.ANY, command.getTower(), GlobalData.playerTeam);
                event.invokeHandler(this.controller);
            }

            if (gameStateCommand.isTowerActiveCommand()) {
                TowerActiveCommand command = (TowerActiveCommand) gameStateCommand;
                TowerActiveEvent event = new TowerActiveEvent(Event.ANY, command.getTower(), command.getTarget(), GlobalData.playerTeam);
                event.invokeHandler(this.controller);
            }

            if (gameStateCommand.isCardAddedCommand()) {
                CardAddedCommand command = (CardAddedCommand) gameStateCommand;
                CardAddedEvent event = new CardAddedEvent(Event.ANY, GlobalData.playerTeam, command.getCard(), command.getPosition());
                event.invokeHandler(this.controller);
            }


            if (gameStateCommand.isCardDeletedCommand()) {
                CardDeletedCommand command = (CardDeletedCommand) gameStateCommand;
                CardDeletedEvent event = new CardDeletedEvent(Event.ANY, GlobalData.playerTeam, command.getCard());
                event.invokeHandler(this.controller);
            }

            if (gameStateCommand.isGameBonusCommand()) {
                CrownCountChangeCommand command = (CrownCountChangeCommand) gameStateCommand;
                CrownCountChangeEvent event = new CrownCountChangeEvent(Event.ANY, GlobalData.playerTeam, command.getCrownCount());
                event.invokeHandler(this.controller);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
