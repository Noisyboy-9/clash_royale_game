package controllers.modes;

import cards.Card;
import controllers.Controller;
import events.cards.CardAddedEvent;
import globals.GlobalData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
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
import user.User;

import java.util.ArrayList;

public abstract class BaseController implements CustomEventHandler {
    protected final long eachFrameDuration;
    protected long frameRemainingCount;

    private final GameModel model;
    private Image selectedImage;
    private ImageView selectedImgView;
    private final int[] comingCardSize = {34, 41};
    private final int[] battleCardSize = {59, 73};
    private final int[] nextCardSize = {50, 62};

    ImageView[] playerTeamCrowns;

    public BaseController(BotModeModel model) {
        this.model = model;

        int FRAME_PER_SECOND = 30;
        this.eachFrameDuration = Math.round((double) 1000 / FRAME_PER_SECOND);
        this.frameRemainingCount = 3 * 60 * FRAME_PER_SECOND;

    }

    ImageView[] opponentTeamCrowns;

    @FXML
    public void initialize() {
        // these variables will be updated by server
        int numberOfPlayers = GlobalData.playerTeam.size() + GlobalData.opponentTeam.size();
        String nameOfPlayer1 = GlobalData.playerTeam.get(0).getUsername();
        String nameOfOpponent1 = GlobalData.opponentTeam.get(0).getUsername();

        playerName1.setText(nameOfPlayer1);
        opponentName1.setText(nameOfOpponent1);

        if (numberOfPlayers == 4)
        {
            String nameOfPlayer2 = GlobalData.playerTeam.get(1).getUsername();
            String nameOfOpponent2 = GlobalData.opponentTeam.get(1).getUsername();
            playerName2.setText(nameOfPlayer2);
            opponentName2.setText(nameOfOpponent2);

            playerBackground2.setVisible(true);
            opponentBackground2.setVisible(true);
            playerName2.setVisible(true);
            opponentName2.setVisible(true);

        }

        this.playerTeamCrowns = new ImageView[] {playerCrown1, playerCrown2, playerCrown3};
        this.opponentTeamCrowns = new ImageView[] {opponentCrown1, opponentCrown2, opponentCrown3};

    }

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

    @FXML
    void putCard(MouseEvent event) {
        if (selectedImage != null) {
            Rectangle cell = (Rectangle) event.getSource();
            if (cell.getCursor().equals(Cursor.HAND)) {
                int column = GridPane.getColumnIndex(cell);
                int row = GridPane.getRowIndex(cell);

                Point2D position = new Point2D(column, row);
                Card selectedCard = getSelectedCard();

                CardAddedEvent cardAddedEvent = new CardAddedEvent(event.getEventType(), GlobalData.playerTeam, selectedCard, position);
                cell.fireEvent(cardAddedEvent);

                this.selectedImage = null;
                this.selectedImgView.setEffect(null);
                Controller.SCENE_CONTROLLER.convertToBlackAndWhite(selectedImgView);

            }

        }

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


    @FXML
    void startGame(MouseEvent event)
    {
        beforeGameState.setVisible(false);
        cardsGroup.setVisible(true);

    }

    @FXML
    void selectCard(MouseEvent event)
    {
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

    private void removeShadows() {
        ObservableList<Node> children = battleCards.getChildren();

        for (int index = 0 ; index < 4 ; index++)
        {
            ImageView imageView = (ImageView)children.get(index);
            imageView.setEffect(null);

        }
    }

    @FXML
    void finishGame()
    {
        beforeGameState.setCursor(Cursor.DEFAULT);
        beforeGameState.setVisible(true);
        resultState.setVisible(true);

        // these values will be updated by server or game logic or any other thing


        showCrowns(GlobalData.playerTeamCrownCount, playerTeamCrowns);
        showCrowns(GlobalData.opponentTeamCrownCount, opponentTeamCrowns);

        // this part will completely change in future
        if (GlobalData.opponentTeamCrownCount > GlobalData.playerTeamCrownCount)
        {
            playerWinner.setVisible(false);

        }

    }


    public void checkWinner()
    {
        ArrayList<ArrayList<User>> result;
        ArrayList<User> winners = new ArrayList<>();
        ArrayList<User> losers = new ArrayList<>();



        if (GlobalData.playerTeamCrownCount > GlobalData.opponentTeamCrownCount) {
            winners = GlobalData.playerTeam;
        }

    }


    public void makeGameResult(ArrayList<User> winners, ArrayList<User> losers, int winnerCrownCount, int loserCrownCount) {

    }


    @FXML
    void showCrowns(int numberOfCrowns, ImageView[] crownsList)
    {
        for (int number = 0 ; number < numberOfCrowns ; number++)
        {
            crownsList[number].setVisible(true);

        }

    }

    @FXML
    void checkLevelUp()
    {
        // after checking that player's level can upgrade or not
        Controller.SCENE_CONTROLLER.showScene("Menu/LevelUpPage.fxml");

    }


    @FXML
    void handleInvalidCards()
    {
        ObservableList<Node> children = battleCards.getChildren();

        for (int index = 0 ; index < 4 ; index++)
        {
            ImageView cardImgView = (ImageView) children.get(index);
            ImageView elixirBackground = (ImageView) children.get(index + 4);

            int cardElixir = Integer.parseInt(((Text) children.get(index)).getText());

            if (cardElixir > model.getPlayerElixirCount()) {
                Controller.SCENE_CONTROLLER.convertToBlackAndWhite(cardImgView);
                Controller.SCENE_CONTROLLER.convertToBlackAndWhite(elixirBackground);
                cardImgView.setCursor(Cursor.DEFAULT);

            }
            else {
                Controller.SCENE_CONTROLLER.convertToColorful(cardImgView);
                Controller.SCENE_CONTROLLER.convertToColorful(elixirBackground);
                cardImgView.setCursor(Cursor.HAND);
            }

        }

    }


    @FXML
    void updateElixirBox() {
        int currentElixir = Integer.parseInt(elixirCount.getText());

        ObservableList<Node> elixirElements = elixirBox.getChildren();

        for (int index = 0 ; index < currentElixir ; index++) {
            ImageView element = (ImageView) elixirElements.get(index);
            element.setVisible(true);

        }

        for (int index = currentElixir ; index < 10 ; index++) {
            ImageView element = (ImageView) elixirElements.get(index);
            element.setVisible(false);
        }

    }


    @FXML
    void handleInMapCards() {
        ObservableList<Node> mapChildren = mapCells.getChildren();

        for (Card card : model.getPlayerInMapCards()) {
            String key = getGifKey(card, "player");
            Image gif = Controller.SCENE_CONTROLLER.getGif(key);
            int index = getIndexInMap(card.getPosition());
            mapChildren.set(index, new ImageView(gif));

        }

        ArrayList<Card> opponentCardsInMap;

        if (model instanceof OnlineModeModel) {
            opponentCardsInMap = ((OnlineModeModel) model).getOpponentInMapCards();
        }
        else {
            opponentCardsInMap = ((BotModeModel) model).getBotInMapCards();
        }

        for (Card card : opponentCardsInMap) {
            String key = getGifKey(card, "opponent");
            Image gif = Controller.SCENE_CONTROLLER.getGif(key);
            int index = getIndexInMap(transferPosition(card.getPosition()));
            mapChildren.set(index, new ImageView(gif));

        }

    }

    private String getGifKey(Card card, String owner) {
            String className = card.getClass().getSimpleName();
            String status = card.getStatus().toString().replace("_", "-").toLowerCase();

            return className + "_" + status + "_" + owner;
    }

    private Point2D transferPosition(Point2D position) {
        int column = (int) position.getX();
        int row = (int) position.getY();

        int transferColumn = 23 - column;
        int transferRow = 38 - row;

        return new Point2D(transferColumn, transferRow);
    }

    private int getIndexInMap(Point2D position) {
        int column = (int) position.getX();
        int row = (int) position.getY();

        return row * 8 + column;
    }


    @FXML
    void render() {
        timeField.setText("");
        elixirCount.setText("");
        updateElixirBox();
        handleInvalidCards();
        // erase previous cards in map
        handleInMapCards();
        // update battle box
        // update coming cards
        // update next card

    }

}
