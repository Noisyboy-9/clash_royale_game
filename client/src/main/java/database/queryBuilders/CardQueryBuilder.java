package database.queryBuilders;

import controllers.Controller;
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

    private CardQueryBuilder() {
        this.cardsDb = new File("client/src/main/java/Database/files/cards.database.binary");
    }

    /**
     * Update player cards.
     *
     * @param user       the user
     * @param battleDeck the battle deck
     */
    public void updatePlayerCards(User user, GridPane battleDeck) {
        try (
                FileOutputStream fos = new FileOutputStream(this.cardsDb);
                ObjectOutputStream outputStream = new ObjectOutputStream(fos)) {
            HashMap<User, HashMap<Integer, String>> userToCardsMap = null;

            if (cardsDb.length() != 0) {
                FileInputStream fis = new FileInputStream(this.cardsDb);
                ObjectInputStream inputStream = new ObjectInputStream(fis);

                try {
                    userToCardsMap = (HashMap<User, HashMap<Integer, String>>) inputStream.readObject();
                } catch (EOFException e) {
                    userToCardsMap = null;
                }

                fis.close();
                inputStream.close();

            }


            if (userToCardsMap == null)
            {
                userToCardsMap = new HashMap<>();
            }

            if (userToCardsMap.containsKey(user)) {
                userToCardsMap.replace(user, getUrls(battleDeck));

            } else {
                userToCardsMap.put(user, getUrls(battleDeck));
            }

            outputStream.writeObject(userToCardsMap);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Controller.SCENE_CONTROLLER.removeScene("Menu/ProfileMenu.fxml");

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


    /**
     * Load cards hash map.
     *
     * @param user the user
     * @return the hash map
     */
    public HashMap<Integer, String> loadCards(User user)
    {
        if (cardsDb.length() == 0)
        {
            return null;
        }

        ObjectInputStream inputStream = null;
        try
        {
            inputStream = new ObjectInputStream(new FileInputStream(this.cardsDb));
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }

        try
        {
            if (inputStream != null)
            {
                HashMap<User, HashMap<Integer, String>> userToCardsMap = (HashMap<User, HashMap<Integer, String>>) inputStream.readObject();

                if (userToCardsMap != null && userToCardsMap.containsKey(user))
                {
                    return userToCardsMap.get(user);
                }

            }

        }
        catch (ClassNotFoundException | IOException e)
        {
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

}
