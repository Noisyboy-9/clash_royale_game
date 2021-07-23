package controllers.menus;

import controllers.Controller;
import globals.GlobalData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Battle deck controller.
 */
public class BattleDeckController extends MenuController {
    private ImageView firstImgView;
    private Image firstImage;
    private Text firstElixir;


    /**
     * Initialize.
     */
    @FXML
    void initialize()
    {
        if (GlobalData.user != null) {
            HashMap<Integer, String> cardsUrls = Controller.CARD_QUERY_BUILDER.loadCards(GlobalData.user);
            if (cardsUrls != null)
            {
                updateBattleCards(cardsUrls);

            }
            else {
                Controller.CARD_QUERY_BUILDER.updatePlayerCards(GlobalData.user, this.battleCards);
            }

        }

    }

    private void updateBattleCards(HashMap<Integer, String> cardsUrls)
    {
        ObservableList<Node> children = battleCards.getChildren();
        ArrayList<String> battleCardsUrls = new ArrayList<>();
        HashMap<String, String> changedCards = new HashMap<>();

        for (int cardIndex : cardsUrls.keySet())
        {
            String[] info = cardsUrls.get(cardIndex).split("->");
            String url = info[0];
            String elixirCount = info[1];
            battleCardsUrls.add(url);

            ImageView imageView = (ImageView) children.get(cardIndex);
            if (!imageView.getImage().getUrl().equals(url))
            {
                Image oldImg = imageView.getImage();
                String oldUrl = oldImg.getUrl();
                Image newImg = new Image(url, oldImg.getWidth(), oldImg.getHeight(), true, true);
                imageView.setImage(newImg);
                Text text = (Text) children.get(cardIndex + 16);
                String oldElixirCount = text.getText();
                text.setText(elixirCount);

                changedCards.put(oldUrl, oldElixirCount);

            }

        }

        updateCardCollection(battleCardsUrls, changedCards);

    }


    private void updateCardCollection(ArrayList<String> battleCardsUrls, HashMap<String, String> changedCards)
    {
        ObservableList<Node> children = cardCollection.getChildren();
        ArrayList<String> cardCollectionUrls = new ArrayList<>();
        HashMap<String, ImageView> availableCards = new HashMap<>();

        for (String url : Controller.SCENE_CONTROLLER.getCardsUrls())
        {
            if (!battleCardsUrls.contains(url))
            {
                cardCollectionUrls.add(url);

            }

        }

        for (int cardIndex = 0 ; cardIndex < 4 ; cardIndex++)
        {
            ImageView imageView = (ImageView) children.get(cardIndex);
            String url = imageView.getImage().getUrl();
            availableCards.put(url, imageView);

        }

        for (String url : availableCards.keySet())
        {
            if (cardCollectionUrls.contains(url))
            {
                cardCollectionUrls.remove(url);

            }
            else
            {
                ImageView imageView = availableCards.get(url);
                Image oldImg = availableCards.get(url).getImage();
                String newUrl = cardCollectionUrls.get(0);
                Image newImg = new Image(newUrl, oldImg.getWidth(), oldImg.getHeight(), true, true);
                imageView.setImage(newImg);

                String elixirCount = changedCards.get(newUrl);
                int cardIndex = children.indexOf(imageView);

                Text text = (Text) children.get(cardIndex + 8);
                text.setText(elixirCount);

                cardCollectionUrls.remove(newUrl);
            }

        }

    }


    @FXML
    private GridPane battleCards;

    @FXML
    private GridPane cardCollection;

    /**
     * Load elixir text.
     *
     * @param imgView     the img view
     * @param columnCount the column count
     * @param rowCount    the row count
     * @return the text
     */
    public Text loadElixir(ImageView imgView, Integer columnCount, Integer rowCount) {
        if (columnCount == null)
            columnCount = 0;

        if (rowCount == null)
            rowCount = 0;

        if (battleCards.getChildren().contains(imgView)) {
            return (Text) battleCards.getChildren().get(columnCount + 4 * rowCount + 16);

        } else {
            return (Text) cardCollection.getChildren().get(columnCount + rowCount + 8);

        }

    }

    /**
     * Change card.
     *
     * @param event the event
     */
    @FXML
    void changeCard(MouseEvent event) {
        DropShadow ds = new DropShadow(20, Color.CYAN);
        ImageView imageView = (ImageView) event.getSource();
        imageView.setEffect(ds);
        Integer columnCount = GridPane.getColumnIndex(imageView);
        Integer rowCount = GridPane.getRowIndex(imageView);


        if (firstImage == null) {
            firstImgView = imageView;
            firstImage = imageView.getImage();
            firstElixir = loadElixir(imageView, columnCount, rowCount);

        } else {
            Text secondElixir = loadElixir(imageView, columnCount, rowCount);

            // change the cards by image and elixir count
            String tempText = firstElixir.getText();
            Image tempImage = firstImage;

            firstImgView.setImage(imageView.getImage());
            firstElixir.setText(secondElixir.getText());

            imageView.setImage(tempImage);
            secondElixir.setText(tempText);

            // change cards to default view mode
            firstImgView.setEffect(null);
            imageView.setEffect(null);

            // make first image field null
            firstImage = null;

        }

    }

    @Override @FXML
    void changeMenuHandler(MouseEvent event)
    {
        Controller.CARD_QUERY_BUILDER.updatePlayerCards(GlobalData.user, battleCards);
        super.changeMenuHandler(event);

    }

}
