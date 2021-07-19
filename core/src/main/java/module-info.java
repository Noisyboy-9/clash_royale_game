module core {
    requires javafx.graphics;
    exports cards;
    exports cards.troops.archers;
    exports cards.troops.barbarians;
    exports cards.troops.dragons;
    exports cards.troops.giants;
    exports cards.troops.pekkas;
    exports cards.troops.valkyries;
    exports cards.troops.wizards;

    exports cards.utils;

    exports commands;
    exports commands.authenicationCommands.login;
    exports commands.authenicationCommands.register;
    exports commands.gameStateCommands.gameBonusCommands;
    exports commands.gameStateCommands.gameTimeCommands;
    exports commands.gameStateCommands.enums;
    exports commands.gameStateCommands.spellCommands;
    exports commands.gameStateCommands.towerCommands;
    exports commands.matchRequestCommands;
    exports commands.playerStateCommands;


    exports exceptions;
    exports towers;
    exports user;
    exports cards.spells.arrows;
    exports cards.buildings.cannons;
    exports cards.spells.balls;
    exports cards.buildings.towers;
    exports cards.spells.rages;
}