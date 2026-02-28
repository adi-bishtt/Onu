package com.onu.model;

import java.util.ArrayList;
public class Player {

    private String name;

    private ArrayList<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public boolean hasWon() {
        return hand.isEmpty();
    }

    public int handSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

}
