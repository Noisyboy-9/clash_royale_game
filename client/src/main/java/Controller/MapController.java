package Controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MapController
{
    @FXML
    public void initialize()
    {
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

    }

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
    void startGame(MouseEvent event)
    {
        beforeGameState.setVisible(false);

    }

}
