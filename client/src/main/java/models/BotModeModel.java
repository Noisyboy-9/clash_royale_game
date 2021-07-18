package models;

import cards.Card;
import globals.UserData;
import towers.KingTower;
import towers.QueenTower;
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

    public BotModeModel(ArrayList<Card> botAllCards, ArrayList<Card> botBattleCards, ArrayList<Card> botComingCards,
                        ArrayList<Card> playerAllCards, ArrayList<Card> playerBattleCards, ArrayList<Card> playerComingCards) {
        this.botTowers = new ArrayList<>();
        this.botAllCards = botAllCards;
        this.botInMapCards = new ArrayList<>();
        this.botBattleCards = botBattleCards;
        this.botComingCards = botComingCards;
        this.botElixirCount = 4;
        this.playerTowers = new ArrayList<>();
        this.playerAllCards = playerAllCards;
        this.playerInMapCards = new ArrayList<>();
        this.playerBattleCards = playerBattleCards;
        this.playerComingCards = playerComingCards;
        this.playerElixirCount = 4;

        this.botTowers.add(QueenTower.create(UserData.bot));
        this.botTowers.add(KingTower.create(UserData.bot));
        this.botTowers.add(QueenTower.create(UserData.bot));

        this.playerTowers.add(QueenTower.create(UserData.user));
        this.playerTowers.add(KingTower.create(UserData.user));
        this.playerTowers.add(QueenTower.create(UserData.user));

    }

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
