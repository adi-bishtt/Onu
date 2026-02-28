package com.onu.model;

import java.util.ArrayList;
public class GameState {

    private ArrayList<Player> players;

    private int currentPlayerIndex = 0;

    private Deck deck;

    private ArrayList<Card> discardPile;

    private Color currentColor;

    private boolean clockwise = true;

    public GameState(ArrayList<Player> players ,  Deck deck) {
        this.players = players;
        this.deck = deck;
        this.discardPile = new ArrayList<>();
        this.clockwise = true;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        if(clockwise)
            {currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        else {currentPlayerIndex = ((currentPlayerIndex - 1) + players.size()) % players.size();
        }
    }

    public void reverseDirection() {
        clockwise = !clockwise;
    }

    public Card getTopCard() {
        return discardPile.get(discardPile.size() - 1);
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(card);
    }

    public ArrayList<Player> getPlayers() { return players; }
    public Deck getDeck() { return deck; }
    public Color getCurrentColor() { return currentColor; }
    public boolean isClockwise() { return clockwise; }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }


}
