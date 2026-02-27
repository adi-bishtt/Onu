package com.onu;

import com.onu.model.Color;
import com.onu.model.Deck;
import com.onu.model.Type;
import com.onu.model.Card;

public class Main {
    public static void main(String[] args) {

        // Create a deck
        Deck deck = new Deck();

        System.out.println("Deck size: " + deck.size()); // should print 108

        Card drawn = deck.draw();
        System.out.println("Drew: " + drawn.getColor() + " " + drawn.getType() + " " + drawn.getValue());

        System.out.println("Deck size after draw: " + deck.size()); // should print 107
    }
}
