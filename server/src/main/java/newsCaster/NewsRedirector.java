package newsCaster;

import commands.Command;
import commands.gameStateCommands.enums.GameTypeEnum;
import commands.gameStateCommands.gameTimeCommands.GameStartCommand;
import newsCaster.runnables.PlayerCommandReceiverRunnable;
import towers.KingTower;
import towers.QueenTower;
import towers.Tower;
import user.User;
import workers.PlayerWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type News redirector.
 */
public class NewsRedirector {
    private ArrayList<PlayerWorker> team1 = new ArrayList<>();
    private ArrayList<PlayerWorker> team2 = new ArrayList<>();

    /**
     * Instantiates a new News redirector.
     *
     * @param player1 the player 1
     * @param player2 the player 2
     */
    public NewsRedirector(PlayerWorker player1, PlayerWorker player2) {
        team1.add(player1);
        team2.add(player2);
    }

    /**
     * Instantiates a new News redirector.
     *
     * @param team1 the team 1
     * @param team2 the team 2
     */
    public NewsRedirector(List<PlayerWorker> team1, List<PlayerWorker> team2) {
        this.team1 = (ArrayList<PlayerWorker>) team1;
        this.team2 = (ArrayList<PlayerWorker>) team2;
    }

    /**
     * Start.
     */
    public void start() {
        ArrayList<User> team1Users = this.team1
                .stream()
                .map(PlayerWorker::getUserData)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<User> team2Users = this.team2
                .stream()
                .map(PlayerWorker::getUserData)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Tower> team1Towers = this.createTowers(this.team1);
        ArrayList<Tower> team2Towers = this.createTowers(this.team2);

        if (this.team1.size() == 2) {
            this.broadcastCommand(
                    new GameStartCommand(GameTypeEnum.TWO_PLAYER_MODE, team1Users, team2Users, team1Towers, team2Towers),
                    team1
            );

            this.broadcastCommand(
                    new GameStartCommand(GameTypeEnum.TWO_PLAYER_MODE, team2Users, team1Users, team2Towers, team1Towers),
                    team2
            );
        } else {
            this.broadcastCommand(
                    new GameStartCommand(GameTypeEnum.FOUR_PLAYER_MODE, team1Users, team2Users, team1Towers, team2Towers),
                    team1
            );

            this.broadcastCommand(
                    new GameStartCommand(GameTypeEnum.FOUR_PLAYER_MODE, team2Users, team1Users, team2Towers, team1Towers),
                    team2
            );
        }

        this.startGameLoop();
    }

    private ArrayList<Tower> createTowers(ArrayList<PlayerWorker> owners) {
        ArrayList<Tower> towers = new ArrayList<>();
        if (owners.size() == 1) {
            towers.add(QueenTower.create(owners.get(0).getUserData()));
            towers.add(QueenTower.create(owners.get(0).getUserData()));
            towers.add(KingTower.create(owners.get(0).getUserData()));
            return towers;
        }

//        the game is four player mode
//        in four player mode the owner of the towers is the user with highest level
        User towersOwner = this.findPlayerWithHighestLevel(owners).getUserData();

        towers.add(QueenTower.create(towersOwner));
        towers.add(QueenTower.create(towersOwner));
        towers.add(KingTower.create(towersOwner));
        towers.add(KingTower.create(towersOwner));

        return towers;
    }

    /**
     * Broadcast command.
     *
     * @param command   the command
     * @param receivers the receivers
     */
    public void broadcastCommand(Command command, ArrayList<PlayerWorker> receivers) {
        for (PlayerWorker receiver : receivers) {
            try {
                receiver.getResponse().writeObject(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Broadcast command.
     *
     * @param command the command
     * @param sender  the sender
     */
    public void broadcastCommand(Command command, PlayerWorker sender) {
//        create a list of all players.
        ArrayList<PlayerWorker> allPlayers = new ArrayList<>();
        allPlayers.addAll(team1);
        allPlayers.addAll(team2);

//        send the command to all players except the sender itself.
        for (PlayerWorker receiver : allPlayers) {
            if (!receiver.equals(sender)) {
                try {
                    receiver.getResponse().writeObject(command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private PlayerWorker findPlayerWithHighestLevel(ArrayList<PlayerWorker> players) {
        PlayerWorker player1 = players.get(0);
        PlayerWorker player2 = players.get(1);

        int player1Level = Integer.parseInt(player1.getUserData().getLevel().toString());
        int player2Level = Integer.parseInt(player2.getUserData().getLevel().toString());

        if (player1Level > player2Level) {
            return player1;
        }

        return player2;
    }

    private void startGameLoop() {
        ArrayList<PlayerWorker> players = new ArrayList<>();
        players.addAll(team2);
        players.addAll(team1);

        for (PlayerWorker worker : players) {
            new Thread(new PlayerCommandReceiverRunnable(worker, this)).start();
        }
    }
}
