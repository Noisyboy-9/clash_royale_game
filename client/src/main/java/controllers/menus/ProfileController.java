package controllers.menus;

import controllers.Controller;
import globals.GlobalData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Objects;

/**
 * The type Profile controller.
 */
public class ProfileController extends MenuController {
    // TODO: ۱۴/۰۷/۲۰۲۱ some methods needed to update username, level and battle deck of player (be careful that level is placed under cards too)

    @FXML
    private GridPane battleCards;

    @FXML
    private Text levelFiled;

    @FXML
    private Text usernameField;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        if (!Objects.isNull(GlobalData.user))
        {
            this.usernameField.setText(GlobalData.user.getUsername());
            this.levelFiled.setText(GlobalData.user.getLevel().toString());
        }

        HashMap<Integer, String> cardsUrls = Controller.CARD_QUERY_BUILDER.loadCards(GlobalData.user);
        if (cardsUrls != null)
        {
            updateBattleCards(cardsUrls);

        }

    }


    private void updateBattleCards(HashMap<Integer, String> cardsUrls)
    {
        ObservableList<Node> children = battleCards.getChildren();

        for (int cardIndex : cardsUrls.keySet())
        {
            String[] info = cardsUrls.get(cardIndex).split("->");
            String url = info[0];

            ImageView imageView = (ImageView) children.get(cardIndex);
            if (!imageView.getImage().getUrl().equals(url))
            {
                Image oldImg = imageView.getImage();
                Image newImg = new Image(url, oldImg.getWidth(), oldImg.getHeight(), true, true);
                imageView.setImage(newImg);

            }

        }

    }

}
