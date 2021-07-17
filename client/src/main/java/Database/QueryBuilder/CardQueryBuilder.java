package Database.QueryBuilder;

import Controller.Controller;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import user.User;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * The type Card query builder.
 */
public class CardQueryBuilder {
    private static CardQueryBuilder singletonInstance = null;
    private final File cardsDb;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    private CardQueryBuilder() {
        this.cardsDb = new File("client/src/main/java/Database/files/cards.database.binary");
        try {
            this.reader = new ObjectInputStream(new FileInputStream(this.cardsDb));
            this.writer = new ObjectOutputStream(new FileOutputStream(this.cardsDb));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update player cards.
     *
     * @param user       the user
     * @param battleDeck the battle deck
     */
    public void updatePlayerCards(User user, GridPane battleDeck) {
        HashMap<User, HashMap<Integer, String>> userToCardsMap = null;

        if (cardsDb.length() != 0) {
            try {
                userToCardsMap = (HashMap<User, HashMap<Integer, String>>) this.reader.readObject();
            } catch (EOFException e) {
                userToCardsMap = null;
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }

        if (userToCardsMap == null) {
            userToCardsMap = new HashMap<>();
        }

        if (userToCardsMap.containsKey(user)) {
            userToCardsMap.replace(user, getUrls(battleDeck));

        } else {
            userToCardsMap.put(user, getUrls(battleDeck));
        }

        try {
            this.writer.writeObject(userToCardsMap);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Controller.SCENE_CONTROLLER.removeScene("Menu/BattleDeckMenu.fxml");
        Controller.SCENE_CONTROLLER.removeScene("Menu/ProfileMenu.fxml");
    }

    /**
     * Load cards hash map.
     *
     * @param user the user
     * @return the hash map
     */
    public HashMap<Integer, String> loadCards(User user) {
        if (cardsDb.length() == 0) {
            return null;
        }

        try {
            HashMap<User, HashMap<Integer, String>> userToCardsMap = (HashMap<User, HashMap<Integer, String>>) this.reader.readObject();

            if (userToCardsMap != null && userToCardsMap.containsKey(user)) {
                return userToCardsMap.get(user);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static CardQueryBuilder getSingletonInstance() {
        if (Objects.isNull(singletonInstance)) {
            singletonInstance = new CardQueryBuilder();
        }

        return singletonInstance;
    }

    private HashMap<Integer, String> getUrls(GridPane battleDeck) {
        HashMap<Integer, String> urls = new HashMap<>();

        for (Node node : battleDeck.getChildren()) {
            if (node instanceof ImageView) {
                String url = ((ImageView) node).getImage().getUrl();
                if (!url.contains("card_exir")) {
                    int index = battleDeck.getChildren().indexOf(node);
                    String elixirCount = ((Text) battleDeck.getChildren().get(index + 16)).getText();

                    urls.put(index, url + "->" + elixirCount);
                }
            }
        }

        return urls;
    }

}
