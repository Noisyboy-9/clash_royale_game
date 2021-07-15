package Controller;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BattleDeckController extends MenuController {
    private ImageView firstImgView;
    private Image firstImage;
    private Text firstElixir;

    // TODO: ۱۴/۰۷/۲۰۲۱ a method needed to load battle deck and update levels

    @FXML
    private GridPane battleCards;

    @FXML
    private GridPane cardCollection;

    public Text loadElixir(ImageView imgView, Integer columnCount, Integer rowCount) {
        if (columnCount == null)
            columnCount = 0;

        if (rowCount == null)
            rowCount = 0;

        if (battleCards.getChildren().contains(imgView)) {
            return (Text) battleCards.getChildren().get(3 * columnCount + 12 * rowCount + 2);

        } else {
            return (Text) cardCollection.getChildren().get(3 * columnCount + 12 * rowCount + 2);

        }

    }

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

}
