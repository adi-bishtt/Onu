package com.onu;

import com.onu.model.Color;
import com.onu.model.Deck;
import com.onu.model.Type;
import com.onu.model.Card;
import com.onu.model.Player;
import com.onu.model.GameState;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Create players
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Aditya"));
        players.add(new Player("Rahul"));

// Create deck
        Deck deck = new Deck();

// Create game state
        GameState gameState = new GameState(players, deck);

// Test current player
        System.out.println("Current player: " + gameState.getCurrentPlayer().getName());

// Test next turn
        gameState.nextTurn();
        System.out.println("After nextTurn: " + gameState.getCurrentPlayer().getName());

// Test reverse
        gameState.reverseDirection();
        gameState.nextTurn();
        System.out.println("After reverse: " + gameState.getCurrentPlayer().getName());

    }
}
