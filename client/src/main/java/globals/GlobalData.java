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
import user.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalData {
    public static User user = null;
    public static User bot = new User("crazyBot", "password");





    public static ArrayList<Card> createCards(User cardOwner)
    {
        ArrayList<Card> cards = new ArrayList<>();
        User user = new User("nejadipour", "password");
        HashMap<Integer, String> urlsData = Controller.CARD_QUERY_BUILDER.loadCards(user);

        for (int index : urlsData.keySet())
        {
            String className = getClassName(urlsData.get(index));
            Card card = getCardBasedOnName(className, cardOwner);

            cards.add(card);

        }

        return cards;

    }


    private static String getClassName(String data)
    {
        String[] elements = data.split("->");
        String imgAddress = elements[0];

        String[] name = imgAddress.split("/");
        int lastIndex = name.length - 1;

        return name[lastIndex].replace("Card.png", "");

    }


    private static Card getCardBasedOnName(String className, User cardOwner)
    {
        switch (className)
        {
            case "Archers" -> {return Archer.create(cardOwner, null);}
            case "Arrows" -> {return Arrows.create(cardOwner, null);}
            case "BabyDragon" -> {return BabyDragon.create(cardOwner, null);}
            case "Barbarians" -> {return Barbarian.create(cardOwner, null);}
            case "Cannon" -> {return Cannon.create(cardOwner, null);}
            case "Fireball" -> {return FireBall.create(cardOwner, null);}
            case "InfernoTower" -> {return InfernoTower.create(cardOwner, null);}
            case "Giant" -> {return Giant.create(cardOwner, null);}
            case "MiniPEKKA" -> {return MiniPekka.create(cardOwner, null);}
            case "Rage" -> {return Rage.create(cardOwner, null);}
            case "Valkyrie" -> {return Valkyrie.create(cardOwner, null);}
            case "Wizard" -> {return Wizard.create(cardOwner, null);}

        }

        return null;

    }

}
