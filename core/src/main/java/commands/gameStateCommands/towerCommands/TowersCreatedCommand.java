package commands.gameStateCommands.towerCommands;

import towers.Tower;

import java.util.ArrayList;

public class TowersCreatedCommand extends TowerCommand{
    private ArrayList<Tower> towers;

    public TowersCreatedCommand(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }
}
