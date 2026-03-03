package com.onu.logic;

import com.onu.model.*;

import java.util.ArrayList;
import java.util.Random;


public class GameEngine {
    private GameState gameState;
    private RuleValidator validator;

    public GameEngine(GameState gameState) {
        this.gameState = gameState;
        this.validator = new RuleValidator(gameState);
    }

    public void startGame() {
        for (Player player : gameState.getPlayers()) {
            for (int i = 0; i < 7; i++) {
                Card card = gameState.getDeck().draw();
                player.addCard(card);
            }
        }

        // Keep drawing until first card is a NUMBER card
        Card firstCard;
        do {
            firstCard = gameState.getDeck().draw();
        } while (firstCard.getType() != Type.NUMBER);

        gameState.addToDiscardPile(firstCard);
        gameState.setCurrentColor(firstCard.getColor());
    }

    private Card safeDraw() {
        if (gameState.getDeck().isEmpty()) {
            reshuffleDeck();
        }
        return gameState.getDeck().draw();
    }

    private void applyEffect(Card card) {
        switch (card.getType()) {
            case SKIP:
                gameState.nextTurn();
                break;

            case DRAW_TWO:
                gameState.nextTurn();
                Player nextPlayer = gameState.getCurrentPlayer();
                nextPlayer.addCard(safeDraw());;
                nextPlayer.addCard(safeDraw());;
                gameState.nextTurn();
                break;

            case WILD_DRAW_FOUR:
                gameState.nextTurn();
                Player nextPlayer2 = gameState.getCurrentPlayer();
                nextPlayer2.addCard(safeDraw());;
                nextPlayer2.addCard(safeDraw());;
                nextPlayer2.addCard(safeDraw());;
                nextPlayer2.addCard(safeDraw());;
                gameState.nextTurn();
                break;

            case REVERSE:
                if (gameState.getPlayers().size() == 2) {
                    gameState.nextTurn();
                } else {
                    gameState.reverseDirection();
                }
                break;


            default:
                break;
        }
    }

    public boolean canPlayCard(Card card) {
        return validator.isValidPlay(card);
    }

    public void playCard(Card card) {
        Player currentPlayer = gameState.getCurrentPlayer();
        currentPlayer.removeCard(card);
        gameState.addToDiscardPile(card);

        if (currentPlayer.hasWon()) {
            return;
        }

        applyEffect(card);

        if (card.getType() == Type.WILD || card.getType() == Type.WILD_DRAW_FOUR) {
            Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
            gameState.setCurrentColor(colors[new Random().nextInt(4)]);
        } else {
            gameState.setCurrentColor(card.getColor());
        }

        gameState.nextTurn();
    }

    public void reshuffleDeck() {
        Card topCard = gameState.getTopCard();
        ArrayList<Card> discard = gameState.getDiscardPile();
        for (int i = 0; i < discard.size() - 1; i++) {
            gameState.getDeck().addCard(discard.get(i));
        }
        gameState.clearDiscardPile(topCard);
        gameState.getDeck().shuffleDeck();

    }
    public void drawCard() {
        Card card = safeDraw();
        gameState.getCurrentPlayer().addCard(card);
    }

    public boolean checkWin(){
        return gameState.getCurrentPlayer().hasWon();
    }
}
