package com.onu;

import com.onu.model.Color;
import com.onu.model.Deck;
import com.onu.model.Type;
import com.onu.model.Card;
import com.onu.model.Player;

public class Main {
    public static void main(String[] args) {

        Deck deck = new Deck();
        Player aditya = new Player("Aditya");

        aditya.addCard(deck.draw());
        aditya.addCard(deck.draw());
        aditya.addCard(deck.draw());

        System.out.println(aditya.getName() + " has " + aditya.handSize() + " cards");

        System.out.println("Won: " + aditya.hasWon()); // false — still has cards


    }
}
