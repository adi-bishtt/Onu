package com.onu.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        createDeck();
        shuffle();
    }

    private void createDeck() {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        for (Color color : colors) {
            cards.add(new Card(color, Type.NUMBER, 0));

            for (int i = 1; i <= 9; i++) {
                cards.add(new Card(color, Type.NUMBER, i));
                cards.add(new Card(color, Type.NUMBER, i));
            }

            cards.add(new Card(color, Type.SKIP, -1));
            cards.add(new Card(color, Type.SKIP, -1));

            cards.add(new Card(color, Type.REVERSE, -1));
            cards.add(new Card(color, Type.REVERSE, -1));

            cards.add(new Card(color, Type.DRAW_TWO, -1));
            cards.add(new Card(color, Type.DRAW_TWO, -1));

        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Color.WILD, Type.WILD, -1));
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Color.WILD, Type.WILD_DRAW_FOUR, -1));
        }
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(cards.size()-1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}
