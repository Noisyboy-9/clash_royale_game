package controllers.modes;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.BotModeModel;

public class MapController {
    protected BotModeModel model;
    ImageView[] playerTeamCrowns;

    public MapController(BotModeModel model) {
        this.model = model;
    }

    ImageView[] opponentTeamCrowns;

    @FXML
    public void initialize() {
        // these variables will be updated by server
        int numberOfPlayers = 4;
        String nameOfPlayer1 = "";
        String nameOfOpponent1 = "";
        String nameOfPlayer2 = "";
        String nameOfOpponent2 = "";

        playerName1.setText(nameOfPlayer1);
        opponentName1.setText(nameOfOpponent1);

        if (numberOfPlayers == 4)
        {
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
    private Group cardsGroup;

    @FXML
    private GridPane comingCards;

    @FXML
    private GridPane battleCards;

    @FXML
    private GridPane elixirBox;

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
    void startGame(MouseEvent event)
    {
        beforeGameState.setVisible(false);
        cardsGroup.setVisible(true);

    }

    @FXML
    void selectCard(MouseEvent event)
    {
        removeShadows();
        DropShadow ds = new DropShadow(20, Color.AQUA);
        ImageView imageView = (ImageView) event.getSource();
        imageView.setEffect(ds);
//        finishGame();

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
        int playerTeamCrownCount = 2;
        int opponentTeamCrownCount = 3;

        showCrowns(playerTeamCrownCount, playerTeamCrowns);
        showCrowns(opponentTeamCrownCount, opponentTeamCrowns);

        // this part will completely change in future
        if (opponentTeamCrownCount > playerTeamCrownCount)
        {
            playerWinner.setVisible(false);

        }

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
        controllers.Controller.SCENE_CONTROLLER.showScene("Menu/LevelUpPage.fxml");

    }

}
