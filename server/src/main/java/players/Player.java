package players;

import cards.Card;
import towers.Tower;
import user.User;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type player that can play a game.
 */
public class Player {
    private final ArrayList<Tower> towers;
    private final ArrayList<Card> cards;
    private final User userDate;

    /**
     * Instantiates a new Player.
     *
     * @param towers   the towers
     * @param cards    the cards
     * @param userDate the user date
     */
    public Player(ArrayList<Tower> towers, ArrayList<Card> cards, User userDate) {
        this.towers = towers;
        this.cards = cards;
        this.userDate = userDate;
    }

    /**
     * Gets towers.
     *
     * @return the towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Gets user date.
     *
     * @return the user date
     */
    public User getUserDate() {
        return userDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return userDate.equals(player.userDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDate);
    }
}
