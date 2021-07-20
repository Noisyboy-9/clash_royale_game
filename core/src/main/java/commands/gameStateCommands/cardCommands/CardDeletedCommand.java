package commands.gameStateCommands.cardCommands;

import cards.Card;

/**
 * The type Card deleted command.
 */
public class CardDeletedCommand extends CardCommand {
    private final Card card;

    /**
     * Instantiates a new Card deleted command.
     *
     * @param card the card
     */
    public CardDeletedCommand(Card card) {
        this.card = card;
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
        return card;
    }
}
