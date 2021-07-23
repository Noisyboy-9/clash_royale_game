package commands.gameStateCommands.gameTimeCommands;

import commands.gameStateCommands.enums.GameTypeEnum;
import towers.Tower;
import user.User;

import java.util.ArrayList;

/**
 * The type Game start command.
 */
public class GameStartCommand extends GameTimeCommand {
    private final ArrayList<User> friendlyTeam;
    private final ArrayList<User> enemyTeam;
    private final ArrayList<Tower> friendlyTowers;
    private final ArrayList<Tower> enemyTowers;

    /**
     * Instantiates a new Game time command.
     *
     * @param gameType       the game type
     * @param friendlyTeam   the users
     * @param enemyTeam      the enemy team
     * @param friendlyTowers the friendly towers
     * @param enemyTowers    the enemy towers
     */
    public GameStartCommand(GameTypeEnum gameType, ArrayList<User> friendlyTeam, ArrayList<User> enemyTeam, ArrayList<Tower> friendlyTowers, ArrayList<Tower> enemyTowers) {
        super(gameType);
        this.friendlyTeam = friendlyTeam;
        this.enemyTeam = enemyTeam;
        this.friendlyTowers = friendlyTowers;
        this.enemyTowers = enemyTowers;
    }

    /**
     * Gets friendly towers.
     *
     * @return the friendly towers
     */
    public ArrayList<Tower> getFriendlyTowers() {
        return friendlyTowers;
    }

    /**
     * Gets enemy towers.
     *
     * @return the enemy towers
     */
    public ArrayList<Tower> getEnemyTowers() {
        return enemyTowers;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public ArrayList<User> getFriendlyTeam() {
        return friendlyTeam;
    }


    /**
     * Gets enemy team.
     *
     * @return the enemy team
     */
    public ArrayList<User> getEnemyTeam() {
        return enemyTeam;
    }
}
