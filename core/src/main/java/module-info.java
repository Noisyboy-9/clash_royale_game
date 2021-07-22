module core {
    requires javafx.graphics;

    exports cards;
    exports cards.troops;
    exports cards.troops.archers;
    exports cards.troops.barbarians;
    exports cards.troops.dragons;
    exports cards.troops.giants;
    exports cards.troops.pekkas;
    exports cards.troops.valkyries;
    exports cards.troops.wizards;

    exports cards.spells;
    exports cards.spells.arrows;
    exports cards.spells.balls;
    exports cards.spells.rages;

    exports cards.buildings;
    exports cards.buildings.towers;
    exports cards.buildings.cannons;

    exports cards.utils;


    exports commands;
    exports commands.gameStateCommands;
    exports commands.authenicationCommands.login;
    exports commands.authenicationCommands.register;
    exports commands.gameStateCommands.gameBonusCommands;
    exports commands.gameStateCommands.gameTimeCommands;
    exports commands.gameStateCommands.cardCommands;
    exports commands.gameStateCommands.enums;
    exports commands.gameStateCommands.towerCommands;
    exports commands.matchRequestCommands;
    exports commands.playerStateCommands;


    exports exceptions;
    exports towers;
    exports user;
}