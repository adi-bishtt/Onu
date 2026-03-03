package com.onu;

import com.onu.logic.GameEngine;
import com.onu.model.Deck;
import com.onu.model.Type;
import com.onu.model.Card;
import com.onu.model.Player;
import com.onu.model.GameState;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Setup
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Aditya"));
        players.add(new Player("Taicho"));

        Deck deck = new Deck();
        GameState gameState = new GameState(players, deck);
        GameEngine gameEngine = new GameEngine(gameState);

        // Start game
        gameEngine.startGame();

        System.out.println("=== ONU STARTED ===");
        System.out.println("Aditya cards: " + players.get(0).handSize());
        System.out.println("Taicho cards: " + players.get(1).handSize());
        System.out.println("Top card: " + gameState.getTopCard().getColor() + " " + gameState.getTopCard().getType() + " " + gameState.getTopCard().getValue());
        System.out.println("---");

        // Game loop
        while (true) {
            Player current = gameState.getCurrentPlayer();
            System.out.println(current.getName() + "'s turn — " + current.handSize() + " cards left");

            // Find first playable card
            Card playableCard = null;
            for (Card card : current.getHand()) {
                if (gameEngine.canPlayCard(card)) {
                    playableCard = card;
                    break;
                }
            }

            if (playableCard != null) {
                // Build card display
                String cardDisplay = playableCard.getColor() + " " + playableCard.getType();
                if (playableCard.getType() == Type.NUMBER) {
                    cardDisplay += " " + playableCard.getValue();
                }
                System.out.println(current.getName() + " plays: " + cardDisplay);

                gameEngine.playCard(playableCard);

                // Check win IMMEDIATELY after playing
                if (current.hasWon()) {
                    System.out.println("---");
                    System.out.println("🎉 " + current.getName() + " WINS!");
                    System.out.println("=== GAME OVER ===");
                    break;
                }

            } else {
                System.out.println(current.getName() + " has no playable card — drawing");
                gameEngine.drawCard();
                gameState.nextTurn();
            }

            System.out.println("---");
        }
    }
}