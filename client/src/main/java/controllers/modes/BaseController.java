package controllers.modes;

import cards.Card;
import cards.buildings.cannons.Cannon;
import controllers.Controller;
import events.cards.CardAddedEvent;
import globals.GlobalData;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;
import towers.KingTower;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * The type Base controller.
 */
public abstract class BaseController implements CustomEventHandler {
    /**
     * The Each frame duration.
     */
    protected final long eachFrameDuration;
    private final GameModel model;
    private final ArrayList<ImageView> previousMapElements;
    /**
     * The Frame remaining count.
     */
    protected long frameRemainingCount;
    /**
     * The Frame per second.
     */
    protected int FRAME_PER_SECOND;
    /**
     * The Opponent team crowns.
     */
    ImageView[] opponentTeamCrowns;
    private Image selectedImage;
    private ImageView selectedImgView;
    private ImageView[] playerTeamCrowns;
    private final int numberOfPlayers;
    private final Point2D playerQueenTower1Position;
    private final Point2D playerQueenTower2Position;
    private final Point2D opponentQueenTower1Position;
    private final Point2D opponentQueenTower2Position;

    @FXML
    private GridPane mapCells;
    @FXML
    private Text playerCrownsCount;
    @FXML
    private Text opponentCrownsCount;
    @FXML
    private Text opponentUsername;
    @FXML
    private Text opponentLevelField;
    @FXML
    private Text playerLevelField;
    @FXML
    private Text timeField;
    @FXML
    private ImageView playerQueenTower2;
    @FXML
    private ImageView playerQueenTower1;
    @FXML
    private ImageView opponentKingTower2;
    @FXML
    private ImageView opponentKingTower1;
    @FXML
    private ImageView opponentQueenTower1;
    @FXML
    private ImageView opponentQueenTower2;
    @FXML
    private ImageView playerKingTower1;
    @FXML
    private ImageView playerKingTower2;
    @FXML
    private Group cardsGroup;
    @FXML
    private GridPane comingCards;
    @FXML
    private GridPane battleCards;
    @FXML
    private GridPane elixirBox;
    @FXML
    private Text elixirCount;
    @FXML
    private ImageView nextCardImage;
    @FXML
    private Text nextCardText;
    @FXML
    private Group beforeGameState;
    @FXML
    private ImageView blackCover;
    @FXML
    private ImageView opponentBackground1;
    @FXML
    private ImageView playerBackground1;
    @FXML
    private ImageView opponentBackground2;
    @FXML
    private ImageView playerBackground2;
    @FXML
    private Text opponentName1;
    @FXML
    private Text playerName1;
    @FXML
    private Text opponentName2;
    @FXML
    private Text playerName2;
    @FXML
    private Group resultState;
    @FXML
    private Text opponentWinner;
    @FXML
    private Text playerWinner;
    @FXML
    private ImageView opponentCrown1;
    @FXML
    private ImageView opponentCrown2;
    @FXML
    private ImageView opponentCrown3;
    @FXML
    private ImageView playerCrown1;
    @FXML
    private ImageView playerCrown2;
    @FXML
    private ImageView playerCrown3;

    /**
     * Instantiates a new Base controller.
     */
    public BaseController() {
        this.model = GlobalData.gameModel;
        this.FRAME_PER_SECOND = GlobalData.FRAME_PER_SECOND;
        this.eachFrameDuration = Math.round((double) 1000 / FRAME_PER_SECOND);
        this.frameRemainingCount = 3L * 60 * FRAME_PER_SECOND;
        this.previousMapElements = new ArrayList<>();
        this.numberOfPlayers = GlobalData.playerTeam.size() + GlobalData.opponentTeam.size();
        this.playerQueenTower1Position = new Point2D(6, 29);
        this.playerQueenTower2Position = new Point2D(17, 29);
        this.opponentQueenTower1Position = new Point2D(17, 9);
        this.opponentQueenTower2Position = new Point2D(6, 9);
    }
    /**
     * Gets frame remaining count.
     *
     * @return the frame remaining count
     */
    public long getFrameRemainingCount() {
        return frameRemainingCount;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        // these variables will be updated by server
        String nameOfPlayer1 = GlobalData.user.getUsername();
        String nameOfOpponent1 = GlobalData.opponentTeam.get(0).getUsername();

        this.playerName1.setText(nameOfPlayer1);
        this.opponentName1.setText(nameOfOpponent1);
        this.opponentUsername.setText(nameOfOpponent1);

        // update levels
        this.playerLevelField.setText(GlobalData.user.getLevel().toString());
        this.opponentLevelField.setText(GlobalData.opponentTeam.get(0).getLevel().toString());

        if (numberOfPlayers == 4) {
            String nameOfPlayer2 = GlobalData.playerTeam.get(1).getUsername();
            String nameOfOpponent2 = GlobalData.opponentTeam.get(1).getUsername();
            this.playerName2.setText(nameOfPlayer2);
            this.opponentName2.setText(nameOfOpponent2);
            this.opponentUsername.setText(nameOfOpponent1 + ", " + nameOfOpponent2);

            this.playerBackground2.setVisible(true);
            this.opponentBackground2.setVisible(true);
            this.playerName2.setVisible(true);
            this.opponentName2.setVisible(true);

        }

        this.playerTeamCrowns = new ImageView[]{playerCrown1, playerCrown2, playerCrown3};
        this.opponentTeamCrowns = new ImageView[]{opponentCrown1, opponentCrown2, opponentCrown3};

        render();

    }

    /**
     * Check winner.
     */
    public void checkWinner() {
        ArrayList<ArrayList<User>> result;
        ArrayList<User> winners = new ArrayList<>();
        ArrayList<User> losers = new ArrayList<>();
    }

    /**
     * Make game result.
     *
     * @param winners          the winners
     * @param losers           the losers
     * @param winnerCrownCount the winner crown count
     * @param loserCrownCount  the loser crown count
     */
    public void makeGameResult(ArrayList<User> winners, ArrayList<User> losers, int winnerCrownCount, int loserCrownCount) {

    }

    /**
     * Gets frame per second.
     *
     * @return the frame per second
     */
    public int getFRAME_PER_SECOND() {
        return FRAME_PER_SECOND;
    }

    /**
     * Transfer position point 2 d.
     *
     * @param position the position
     * @return the point 2 d
     */
    public Point2D transferPosition(Point2D position) {
        int column = (int) position.getX();
        int row = (int) position.getY();

        int transferColumn = 23 - column;
        int transferRow = 38 - row;

        return new Point2D(transferColumn, transferRow);
    }

    private Card getSelectedCard() {
        String imgUrl = selectedImage.getUrl().toLowerCase();

        for (Card card : model.getPlayerBattleCards()) {
            String cardName = card.getClass().getSimpleName().toLowerCase();

            if (imgUrl.contains(cardName)) {
                return card;
            }
        }

        return null;
    }

    private void removeShadows() {
        ObservableList<Node> children = battleCards.getChildren();

        for (int index = 0; index < 4; index++) {
            ImageView imageView = (ImageView) children.get(index);
            imageView.setEffect(null);

        }
    }

    private String getGifKey(Card card, String owner) {
        String className = card.getClass().getSimpleName();
        String status = card.getStatus().toString().replace("_", "-").toLowerCase();

        return className + "_" + status + "_" + owner;
    }

    private int getIndexInMap(Point2D position) {
        int column = (int) position.getX();
        int row = (int) position.getY();
        return ((24 * 43) - 1 + 55 + column - 1 + (24 * row));
    }

    /**
     * Put card.
     *
     * @param event the event
     */
    @FXML
    void putCard(MouseEvent event) {
        if (selectedImage != null) {
            Rectangle cell = (Rectangle) event.getSource();
            if (cell.getCursor().equals(Cursor.HAND)) {
                int column = GridPane.getColumnIndex(cell);
                int row = GridPane.getRowIndex(cell);

                Point2D position = new Point2D(column, row);
                Card selectedCard = getSelectedCard();

                CardAddedEvent cardAddedEvent = new CardAddedEvent(Event.ANY, GlobalData.playerTeam, selectedCard, position);
                cardAddedEvent.invokeHandler(GlobalData.gameController);
                this.selectedImage = null;
                this.selectedImgView.setEffect(null);
                Controller.SCENE_CONTROLLER.convertToBlackAndWhite(selectedImgView);
            }
        }

    }

    /**
     * Start game.
     *
     * @param event the event
     */
    @FXML
    void startGame(MouseEvent event) {
        beforeGameState.setVisible(false);
        cardsGroup.setVisible(true);
        GlobalData.gameStarted = true;

    }

    /**
     * Select card.
     *
     * @param event the event
     */
    @FXML
    void selectCard(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        if (imageView.getCursor().equals(Cursor.HAND)) {
            removeShadows();
            handleInvalidCards();
            DropShadow ds = new DropShadow(20, Color.AQUA);
            imageView.setEffect(ds);
            this.selectedImage = imageView.getImage();
            this.selectedImgView = imageView;

        }

    }

    /**
     * Finish game.
     */
    @FXML
    public void finishGame() {
        beforeGameState.setCursor(Cursor.DEFAULT);
        beforeGameState.setVisible(true);
        resultState.setVisible(true);

        if (this.model instanceof BotModeModel) {
            showCrowns(this.model.getPlayerCrownCount(), playerTeamCrowns);
            showCrowns(((BotModeModel) this.model).getBotCrownCount(), opponentTeamCrowns);
        }

        if (this.model instanceof OnlineModeModel) {
            showCrowns(this.model.getPlayerCrownCount(), playerTeamCrowns);
            showCrowns(((OnlineModeModel) this.model).getOpponentCrownCount(), opponentTeamCrowns);
        }
    }

    /**
     * Show crowns.
     *
     * @param numberOfCrowns the number of crowns
     * @param crownsList     the crowns list
     */
    @FXML
    void showCrowns(int numberOfCrowns, ImageView[] crownsList) {
        for (int number = 0; number < numberOfCrowns; number++) {
            crownsList[number].setVisible(true);

        }

    }

    /**
     * Check level up.
     */
    @FXML
    void checkLevelUp() {
        // after checking that player's level can upgrade or not
        Controller.SCENE_CONTROLLER.showScene("Menu/LevelUpPage.fxml");

    }

    /**
     * Handle invalid cards.
     */
    @FXML
    void handleInvalidCards() {
        ObservableList<Node> children = battleCards.getChildren();

        for (int index = 0; index < 4; index++) {
            ImageView cardImgView = (ImageView) children.get(index);
            Effect previousEffect = cardImgView.getEffect();
            ImageView elixirBackground = (ImageView) children.get(index + 4);

            int cardElixir = Integer.parseInt(((Text) children.get(index + 8)).getText());

            if (cardElixir > model.getPlayerElixirCount()) {
                Controller.SCENE_CONTROLLER.convertToBlackAndWhite(cardImgView);
                Controller.SCENE_CONTROLLER.convertToBlackAndWhite(elixirBackground);
                cardImgView.setCursor(Cursor.DEFAULT);

            } else {
                Controller.SCENE_CONTROLLER.convertToColorful(cardImgView);
                Controller.SCENE_CONTROLLER.convertToColorful(elixirBackground);
                cardImgView.setCursor(Cursor.HAND);
            }

            if (previousEffect instanceof Shadow)
                cardImgView.setEffect(previousEffect);

        }

    }

    /**
     * Update elixir box.
     */
    @FXML
    protected void updateElixirBox() {
        int currentElixir = this.model.getPlayerElixirCount();
        elixirCount.setText(Integer.toString(currentElixir));

        ObservableList<Node> elixirElements = elixirBox.getChildren();

        for (int index = 0; index < currentElixir; index++) {
            ImageView element = (ImageView) elixirElements.get(index);
            element.setVisible(true);

        }

        for (int index = currentElixir; index < 10; index++) {
            ImageView element = (ImageView) elixirElements.get(index);
            element.setVisible(false);
        }

    }


    /**
     * Handle towers.
     */
    @FXML
    public void handleTowers() {
        ArrayList<Tower> playerTowers = this.model.getPlayerTowers();
        ArrayList<Tower> opponentTowers = new ArrayList<>();

        if (this.model instanceof BotModeModel) {
            opponentTowers = ((BotModeModel) this.model).getBotTowers();
        }
        else if (this.model instanceof OnlineModeModel) {
            opponentTowers = ((OnlineModeModel) this.model).getOpponentTowers();
        }

        handleTowersExistence(playerTowers, this.playerQueenTower1, this.playerQueenTower2, this.playerKingTower1, this.playerQueenTower1Position, this.playerQueenTower2Position);
        handleTowersExistence(opponentTowers, this.opponentQueenTower1, this.opponentQueenTower2, this.opponentKingTower1, transferPosition(this.opponentQueenTower1Position), transferPosition(this.opponentQueenTower2Position));
        handleTowersAttacks(playerTowers, false);
        handleTowersAttacks(opponentTowers, true);

    }


    @FXML
    private void handleTowersExistence(ArrayList<Tower> towers, ImageView queenTower1, ImageView queenTower2, ImageView kingTower1, Point2D queenTower1Position, Point2D queenTower2Position) {
        // when king towers are destroyed
        if (towers.size() == 0) {
            queenTower1.setImage(null);
            queenTower2.setImage(null);
            kingTower1.setImage(null);

            if (numberOfPlayers == 4) {
                if (this.playerKingTower1.getImage() == null) {
                    this.playerKingTower2.setImage(null);
                }

                if (this.opponentKingTower1.getImage() == null) {
                    this.opponentKingTower2.setImage(null);
                }

            }

        }
        else {
            if (queenTower1.getImage() != null || queenTower2.getImage() != null) {
                boolean tower1Exists = false;
                boolean tower2Exists = false;

                for (Tower tower : towers) {
                    if (tower.getPosition().equals(queenTower1Position))
                        tower1Exists = true;

                    if (tower.getPosition().equals(queenTower2Position))
                        tower2Exists = true;
                }

                if (!tower1Exists) {
                    queenTower1.setImage(null);
                    clearTowerArea(queenTower1Position);
                }

                if (!tower2Exists) {
                    queenTower2.setImage(null);
                    clearTowerArea(queenTower2Position);
                }
            }
        }

    }

    @FXML
    private void clearTowerArea(Point2D position) {
        int column = (int) position.getX();
        int row = (int) position.getY();
        ObservableList<Node> mapChildren = this.mapCells.getChildren();

        for (int i = -1 ; i <= 1 ; i++) {
            for (int j = -1 ; j <= 1 ; j++) {
                int indexToChange = getIndexInMap(new Point2D(column + i, row + j));
                mapChildren.get(indexToChange).setCursor(Cursor.HAND);
            }
        }

    }


    @FXML
    private void handleTowersAttacks(ArrayList<Tower> towers, boolean isOpponent) {
        ObservableList<Node> mapChildren = this.mapCells.getChildren();

        String kingKey;
        String queenKey;

        if (isOpponent) {
            kingKey = "Shot_fight_opponent";
            queenKey = "Tower_fight_opponent";
        } else {
            kingKey = "Shot_fight_player";
            queenKey = "Tower_fight_player";
        }

        for (Tower tower : towers) {
            if (tower.getTarget() != null) {
                if (tower instanceof KingTower) {
                    if (((KingTower) tower).isShooting()) {
                        attackTowerTarget(isOpponent, mapChildren, kingKey, tower);

                    }
                }
                else {
                    attackTowerTarget(isOpponent, mapChildren, queenKey, tower);
                }
            }
        }
    }

    @FXML
    private void attackTowerTarget(boolean isOpponent, ObservableList<Node> mapChildren, String gifKey, Tower tower) {
        Image gif = Controller.SCENE_CONTROLLER.getGif(gifKey);
        Point2D position;

        if (isOpponent)
            position = transferPosition(tower.getPosition());
        else
            position = tower.getPosition();

        int index = getIndexInMap(position);

        ImageView imageView = (ImageView) mapChildren.get(index);
        imageView.setImage(gif);
        mapChildren.set(index, imageView);
        this.previousMapElements.add(imageView);

    }


    /**
     * Refresh map.
     */
    @FXML
    private void refreshMap() {
        for (ImageView imgView : this.previousMapElements) {
            imgView.setImage(null);
        }
    }

    /**
     * Handle in map cards.
     */
    @FXML
    private void handleInMapCards() {
        ObservableList<Node> mapChildren = mapCells.getChildren();

        for (Card card : model.getPlayerInMapCards()) {
            String key = getGifKey(card, "player");
            Image gif = Controller.SCENE_CONTROLLER.getGif(key);
            int index = getIndexInMap(card.getPosition());
            ImageView imgView = (ImageView) mapChildren.get(index);
            imgView.setImage(gif);
            this.previousMapElements.add(imgView);

            if (card instanceof Cannon)
                handleCannon((Cannon) card, "player");

        }

        ArrayList<Card> opponentCardsInMap;

        if (model instanceof OnlineModeModel) {
            opponentCardsInMap = ((OnlineModeModel) model).getOpponentInMapCards();
        } else {
            opponentCardsInMap = ((BotModeModel) model).getBotInMapCards();
        }

        for (Card card : opponentCardsInMap) {
            String key = getGifKey(card, "opponent");
            Image gif = Controller.SCENE_CONTROLLER.getGif(key);
            int index = getIndexInMap(transferPosition(card.getPosition()));
            ImageView imgView = (ImageView) mapChildren.get(index);
            imgView.setImage(gif);
            this.previousMapElements.add(imgView);

            if (card instanceof Cannon)
                handleCannon((Cannon) card, "opponent");

        }

    }


    @FXML
    private void handleCannon(Cannon cannon, String owner) {
        if (cannon.getTarget() != null) {
            if (cannon.isShooting()) {
                Image gif = Controller.SCENE_CONTROLLER.getGif("Shot_fight_" + owner);
                ImageView imgView = new ImageView(gif);
                Point2D position;

                if (owner.equals("opponent"))
                    position = transferPosition(cannon.getTarget().getPosition());
                else
                    position = cannon.getTarget().getPosition();

                int index = getIndexInMap(position);

                this.mapCells.getChildren().set(index, imgView);
                this.previousMapElements.add(imgView);

            }
        }

    }


    /**
     * Handle battle cards.
     */
    @FXML
    private void handleBattleCards() {
        ObservableList<Node> battleCardsChildren = this.battleCards.getChildren();
        for (int index = 0; index < 4; index++) {
            Card card = this.model.getPlayerBattleCards().get(index);
            Image cardImage = Controller.SCENE_CONTROLLER.getBattleBoxImg(card.getClass().getSimpleName());
            int elixir = card.getCost();

            ImageView cardImgView = (ImageView) battleCardsChildren.get(index);
            Effect previousEffect = cardImgView.getEffect();
            Text elixirField = (Text) battleCardsChildren.get(index + 8);

            cardImgView.setImage(cardImage);
            cardImgView.setEffect(previousEffect);
            elixirField.setText(Integer.toString(elixir));

        }

    }


    /**
     * Handle coming cards.
     */
    @FXML
    private void handleComingCards() {
        ObservableList<Node> battleCardsChildren = this.comingCards.getChildren();
        for (int index = 0; index < 4; index++) {
            Card card = this.model.getPlayerComingCards().get(index);
            Image cardImage = Controller.SCENE_CONTROLLER.getComingBoxImg(card.getClass().getSimpleName());
            int elixir = card.getCost();

            ImageView cardImgView = (ImageView) battleCardsChildren.get(index);
            Text elixirField = (Text) battleCardsChildren.get(index + 8);

            cardImgView.setImage(cardImage);
            elixirField.setText(Integer.toString(elixir));

        }

    }


    /**
     * Handle next card.
     */
    @FXML
    private void handleNextCard() {
        Card nextCard = this.model.getPlayerComingCards().get(0);
        Image image = Controller.SCENE_CONTROLLER.getNextCardImg(nextCard.getClass().getSimpleName());
        String elixir = Integer.toString(nextCard.getCost());

        this.nextCardImage.setImage(image);
        this.nextCardText.setText(elixir);

    }


    @FXML
    private void handleCrownsCount() {
        int playerCrowns = this.model.getPlayerCrownCount();
        int opponentCrowns = 0;

        if (this.model instanceof BotModeModel) {
            opponentCrowns = ((BotModeModel) this.model).getBotCrownCount();
        } else if (this.model instanceof OnlineModeModel) {
            opponentCrowns = ((OnlineModeModel) this.model).getOpponentCrownCount();
        }

        this.playerCrownsCount.setText(Integer.toString(playerCrowns));
        this.opponentCrownsCount.setText(Integer.toString(opponentCrowns));

    }


    /**
     * Handle time.
     */
    @FXML
    private void handleTime() {
        if (this.frameRemainingCount % this.FRAME_PER_SECOND == 0) {
            long seconds = this.frameRemainingCount / this.FRAME_PER_SECOND;
            long minutes = TimeUnit.SECONDS.toMinutes(seconds);
            seconds = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
            String splitter = ":";

            if (seconds >= 0 && seconds <= 9)
                splitter += "0";

            timeField.setText(minutes + splitter + seconds);

        }

    }


    /**
     * Render.
     */
    @FXML
    public void render() {
        updateElixirBox();
        refreshMap();
        handleInMapCards();
        handleTowers();
        handleBattleCards();
        handleInvalidCards();
        handleComingCards();
        handleNextCard();
        handleCrownsCount();
        handleTime();

    }
}
