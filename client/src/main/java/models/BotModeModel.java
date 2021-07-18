package models;

import cards.Card;
import towers.Tower;

import java.util.ArrayList;

public class BotModeModel
{
    private ArrayList<Tower> botTowers;
    private ArrayList<Card> botAllCards;
    private ArrayList<Card> botInMapCards;
    private ArrayList<Card> botBattleCards;
    private ArrayList<Card> botComingCards;
    private int botElixirCount;
    private ArrayList<Tower> playerTowers;
    private ArrayList<Card> playerAllCards;
    private ArrayList<Card> playerInMapCards;
    private ArrayList<Card> playerBattleCards;
    private ArrayList<Card> playerComingCards;
    private int playerElixirCount;

    public ArrayList<Tower> getBotTowers() {
        return botTowers;
    }

    public ArrayList<Card> getBotAllCards() {
        return botAllCards;
    }

    public ArrayList<Card> getBotInMapCards() {
        return botInMapCards;
    }

    public ArrayList<Card> getBotBattleCards() {
        return botBattleCards;
    }

    public ArrayList<Card> getBotComingCards() {
        return botComingCards;
    }

    public ArrayList<Tower> getPlayerTowers() {
        return playerTowers;
    }

    public ArrayList<Card> getPlayerAllCards() {
        return playerAllCards;
    }

    public ArrayList<Card> getPlayerInMapCards() {
        return playerInMapCards;
    }

    public ArrayList<Card> getPlayerBattleCards() {
        return playerBattleCards;
    }

    public ArrayList<Card> getPlayerComingCards() {
        return playerComingCards;
    }

}
