package Controller;

import Globals.UserData;
import Models.GameResult;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import user.User;

import java.io.IOException;
import java.util.ArrayList;

public class HistoryController extends MenuController
{
    private ArrayList<GameResult> results;
    private int topBoxIndex;
    private int bottomBoxIndex;
    private int minValue;
    private int maxValue;

    @FXML
    void initialize()
    {
        if (UserData.user != null)
        {
            this.results = null;
            try
            {
                results = Controller.HISTORY_QUERY_BUILDER.readAll(UserData.user);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (this.results != null)
            {
                this.topBoxIndex = 0;
                this.bottomBoxIndex = 1;
                this.minValue = 0;
                this.maxValue = results.size() - 1;

            }
            else
            {
                convertToBlackAndWhite(upButton);
                convertToBlackAndWhite(downButton);
            }

        }

    }


    private void handleUpdates()
    {
        if (topBoxIndex >= minValue && topBoxIndex <= maxValue)
        {
            GameResult topBoxGameResult = results.get(topBoxIndex);
            clearFields(playersTeamTop);
            clearFields(opponentTeamTop);
            updateBoxFields(topBoxGameResult, playersTeamTop, opponentTeamTop, playerScoreTopBox, opponentScoreTopBox);

        }

        if (bottomBoxIndex >= minValue && bottomBoxIndex <= maxValue)
        {
            GameResult bottomGameResult = results.get(bottomBoxIndex);
            clearFields(playerTeamBottom);
            clearFields(opponentTeamBottom);
            updateBoxFields(bottomGameResult, playerTeamBottom, opponentTeamBottom, playerScoreBottomBox, opponentScoreBottomBox);

        }

        handleButtonsColors();

    }

    private void clearFields(Group group)
    {
        for (Node node : group.getChildren())
        {
            ((Text)node).setText("");
        }

    }

    private void handleButtonsColors()
    {
        if (topBoxIndex == minValue)
        {
            convertToBlackAndWhite(upButton);
        }
        else if (topBoxIndex > minValue)
        {
            convertToColorful(upButton);
        }
        else
        {
            convertToBlackAndWhite(upButton);
        }

        if (bottomBoxIndex == maxValue)
        {
            convertToBlackAndWhite(downButton);
        }
        else if (bottomBoxIndex < maxValue)
        {
            convertToColorful(downButton);
        }
        else
        {
            convertToBlackAndWhite(downButton);
        }

    }


    private void updateBoxFields(GameResult gameResult, Group playersGroup, Group opponentsGroup, Text playerScoreText, Text opponentScoreText)
    {
        int playerTeamScore;
        int opponentTeamScore;

        if (gameResult.isWinner(UserData.user))
        {
            playerTeamScore = gameResult.getWinnersCrownCount();
            opponentTeamScore = gameResult.getLosersCrownCount();
            updateTextGroup(playersGroup, gameResult.getWinners());
            updateTextGroup(opponentsGroup, gameResult.getLosers());

        }
        else
        {
            playerTeamScore = gameResult.getLosersCrownCount();
            opponentTeamScore = gameResult.getWinnersCrownCount();
            updateTextGroup(playersGroup, gameResult.getLosers());
            updateTextGroup(opponentsGroup, gameResult.getWinners());

        }

        playerScoreText.setText(Integer.toString(playerTeamScore));
        opponentScoreText.setText(Integer.toString(opponentTeamScore));

    }

    private void updateTextGroup(Group group, ArrayList<User> users)
    {
        for (User user : users)
        {
            int index = users.indexOf(user);

            ((Text)group.getChildren().get(index)).setText(user.getUsername());

        }

    }


    private void convertToBlackAndWhite(ImageView imageView)
    {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        colorAdjust.setBrightness(-0.5);

        imageView.setEffect(colorAdjust);

    }


    private void convertToColorful(ImageView imageView)
    {
        imageView.setEffect(null);

    }


    @FXML
    private ImageView upButton;

    @FXML
    private ImageView downButton;

    @FXML
    private Text playerScoreTopBox;

    @FXML
    private Text opponentScoreTopBox;

    @FXML
    private Text playerScoreBottomBox;

    @FXML
    private Text opponentScoreBottomBox;

    @FXML
    private Group playersTeamTop;

    @FXML
    private Group opponentTeamTop;

    @FXML
    private Group playerTeamBottom;

    @FXML
    private Group opponentTeamBottom;


    @FXML
    void moveDown(MouseEvent event)
    {
        bottomBoxIndex++;

        if (bottomBoxIndex <= maxValue)
        {
            topBoxIndex++;
            handleUpdates();
        }
        else
        {
            bottomBoxIndex--;
        }

    }

    @FXML
    void moveUp(MouseEvent event)
    {
        topBoxIndex--;

        if (topBoxIndex >= minValue)
        {
            bottomBoxIndex--;
            handleUpdates();
        }
        else
        {
            topBoxIndex++;
        }

    }

}
