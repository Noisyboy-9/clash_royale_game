package globals;

import cards.Card;
import cards.buildings.cannons.Cannon;
import cards.buildings.towers.InfernoTower;
import cards.spells.arrows.Arrows;
import cards.spells.balls.FireBall;
import cards.spells.rages.Rage;
import cards.troops.archers.Archer;
import cards.troops.barbarians.Barbarian;
import cards.troops.dragons.BabyDragon;
import cards.troops.giants.Giant;
import cards.troops.pekkas.MiniPekka;
import cards.troops.valkyries.Valkyrie;
import cards.troops.wizards.Wizard;
import controllers.Controller;
import controllers.modes.BaseController;
import models.GameModel;
import user.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Global data.
 */
public class GlobalData {
    /**
     * The constant user.
     */
    public static User user = null;
    /**
     * The constant bot.
     */
    public static User bot = new User("Bot", "password");
    /**
     * The constant gameModel.
     */
    public static GameModel gameModel;
    /**
     * The constant gameController.
     */
    public static BaseController gameController;
    /**
     * The constant gameStarted.
     */
    public static boolean gameStarted = false;
    /**
     * The constant playerTeam.
     */
    public static ArrayList<User> playerTeam = new ArrayList<>();
    /**
     * The constant opponentTeam.
     */
    public static ArrayList<User> opponentTeam = new ArrayList<>();
    /**
     * The constant FRAME_PER_SECOND.
     */
    public static int FRAME_PER_SECOND = 30;

    /**
     * Create cards array list.
     *
     * @param cardOwner the card owner
     * @return the array list
     */
    public static ArrayList<Card> createCards(User cardOwner) {
        ArrayList<Card> cards = new ArrayList<>();
        HashMap<Integer, String> urlsData = Controller.CARD_QUERY_BUILDER.loadCards(cardOwner);

        for (int index : urlsData.keySet()) {
            String className = getClassName(urlsData.get(index));
            Card card = getCardBasedOnName(className, cardOwner);

            cards.add(card);
        }

        return cards;

    }


    private static String getClassName(String data) {
        String[] elements = data.split("->");
        String imgAddress = elements[0];

        String[] name = imgAddress.split("/");
        int lastIndex = name.length - 1;

        return name[lastIndex].replace("Card.png", "");
    }


    /**
     * Gets card based on name.
     *
     * @param className the class name
     * @param cardOwner the card owner
     * @return the card based on name
     */
    public static Card getCardBasedOnName(String className, User cardOwner) {
        return switch (className) {
            case "Archers", "Archer" -> Archer.create(cardOwner);
            case "Arrows" -> Arrows.create(cardOwner);
            case "BabyDragon" -> BabyDragon.create(cardOwner);
            case "Barbarians", "Barbarian" -> Barbarian.create(cardOwner);
            case "Cannon" -> Cannon.create(cardOwner);
            case "Fireball", "FireBall" -> FireBall.create(cardOwner);
            case "InfernoTower" -> InfernoTower.create(cardOwner);
            case "Giant" -> Giant.create(cardOwner);
            case "MiniPEKKA", "MiniPekka" -> MiniPekka.create(cardOwner);
            case "Rage" -> Rage.create(cardOwner);
            case "Valkyrie" -> Valkyrie.create(cardOwner);
            case "Wizard" -> Wizard.create(cardOwner);
            default -> throw new IllegalStateException("Unexpected value: " + className);
        };
    }
}
