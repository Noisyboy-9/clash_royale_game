package newsCaster;

import commands.gameStateCommands.enums.GameTypeEnum;
import javafx.geometry.Point2D;
import newsCaster.runnables.PlayerCommandReceiverRunnable;
import towers.KingTower;
import towers.QueenTower;
import towers.Tower;
import user.User;
import workers.PlayerWorker;

import java.util.ArrayList;
import java.util.List;

/**
 * The type News redirector.
 */
public class NewsRedirector {
    private ArrayList<PlayerWorker> team1 = new ArrayList<>();
    private ArrayList<PlayerWorker> team2 = new ArrayList<>();

    public NewsRedirector(PlayerWorker player1, PlayerWorker player2) {
        team1.add(player1);
        team2.add(player2);
    }

    public NewsRedirector(List<PlayerWorker> team1, List<PlayerWorker> team2) {
        this.team1 = (ArrayList<PlayerWorker>) team1;
        this.team2 = (ArrayList<PlayerWorker>) team2;
    }

    /**
     * Start.
     */
    public void start() {
//        ArrayList<User> team1Users = this.team1
//                .stream()
//                .map(PlayerWorker::getUserData)
//                .collect(Collectors.toCollection(ArrayList::new));
//
//        ArrayList<User> team2Users = this.team2
//                .stream()
//                .map(PlayerWorker::getUserData)
//                .collect(Collectors.toCollection(ArrayList::new));

//        try {
//            ArrayList<Tower> team1Towers = this.createTowers(this.team1);
//            ArrayList<Tower> team2Towers = this.createTowers(this.team2);
//
//            if (this.team1.size() == 2) {
//                this.setTowerPositionForTeam1(GameTypeEnum.FOUR_PLAYER_MODE, team1Towers, team2Towers);
//                this.broadcastCommand(new GameStartCommand(GameTypeEnum.FOUR_PLAYER_MODE, team1Users, team1Towers, team2Towers), team1);
//
//                this.setTowerPositionForTeam2(GameTypeEnum.FOUR_PLAYER_MODE, team1Towers, team2Towers);
//                this.broadcastCommand(new GameStartCommand(GameTypeEnum.FOUR_PLAYER_MODE, team1Users, team1Towers, team2Towers), team2);
//            } else {
//                this.setTowerPositionForTeam1(GameTypeEnum.TWO_PLAYER_MODE, team1Towers, team2Towers);
//                this.broadcastCommand(new GameStartCommand(GameTypeEnum.TWO_PLAYER_MODE, team1Users, team1Towers, team2Towers), team1);
//
//                this.setTowerPositionForTeam2(GameTypeEnum.TWO_PLAYER_MODE, team1Towers, team2Towers);
//                this.broadcastCommand(new GameStartCommand(GameTypeEnum.TWO_PLAYER_MODE, team1Users, team1Towers, team2Towers), team2);
//            }

//            this.startGameLoop();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
    }

    private void setTowerPositionForTeam1(GameTypeEnum gameMode, ArrayList<Tower> team1Towers, ArrayList<Tower> team2Towers) {
        if (gameMode.equals(GameTypeEnum.FOUR_PLAYER_MODE)) {
//            we have 2 queens and 2 king towers with respect to their index
            QueenTower team1Queen1 = (QueenTower) team1Towers.get(0);
            team1Queen1.setPosition(new Point2D(5, 10));
            QueenTower team1Queen2 = (QueenTower) team1Towers.get(1);
            team1Queen2.setPosition(new Point2D(16, 10));

            KingTower team1King1 = (KingTower) team1Towers.get(2);
            team1King1.setPosition(new Point2D(9, 7));
            KingTower team1King2 = (KingTower) team1Towers.get(3);
            team1King2.setPosition(new Point2D(14, 7));

            QueenTower team2Queen1 = (QueenTower) team2Towers.get(0);
            team2Queen1.setPosition(new Point2D(5, 30));
            QueenTower team2Queen2 = (QueenTower) team2Towers.get(1);
            team2Queen2.setPosition(new Point2D(16, 30));

            KingTower team2King1 = (KingTower) team2Towers.get(2);
            team2King1.setPosition(new Point2D(9, 32));
            KingTower team2King2 = (KingTower) team2Towers.get(3);
            team2King1.setPosition(new Point2D(14, 32));
        } else {
//            // TODO: 7/20/2021 AD finish this code.
        }
    }

    private ArrayList<Tower> createTowers(ArrayList<PlayerWorker> owners) {
        ArrayList<Tower> towers = new ArrayList<>();
        if (owners.size() == 1) {
            towers.add(QueenTower.create(owners.get(0).getUserData()));
            towers.add(QueenTower.create(owners.get(0).getUserData()));
            towers.add(KingTower.create(owners.get(0).getUserData()));
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
