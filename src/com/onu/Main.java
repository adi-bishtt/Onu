package com.onu;

import com.onu.logic.GameEngine;
import com.onu.model.Color;
import com.onu.model.Deck;
import com.onu.model.Type;
import com.onu.model.Card;
import com.onu.model.Player;
import com.onu.model.GameState;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Aditya"));
        players.add(new Player("Rahul"));

        Deck deck = new Deck();
        GameState gameState = new GameState(players, deck);
        GameEngine gameEngine = new GameEngine(gameState);

    // Start game
        gameEngine.startGame();

    // Check cards dealt
        System.out.println("Aditya cards: " + players.get(0).handSize()); // should print 7
        System.out.println("Rahul cards: " + players.get(1).handSize()); // should print 7

    // Check top card
        System.out.println("Top card: " + gameState.getTopCard().getColor() + " " + gameState.getTopCard().getType());

    // Check current player
        System.out.println("Current player: " + gameState.getCurrentPlayer().getName()); // Aditya

    // Next turn
        gameState.nextTurn();
        System.out.println("After nextTurn: " + gameState.getCurrentPlayer().getName()); // Rahul

    // Check win
        System.out.println("Aditya won: " + gameEngine.checkWin()); // false
    }
}
