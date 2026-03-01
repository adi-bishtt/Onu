package com.onu.logic;

import com.onu.model.*;



public class GameEngine {
    private GameState gameState;

    public GameEngine(GameState gameState) {
        this.gameState = gameState;
    }

    public void startGame() {
        for(Player player : gameState.getPlayers()) {
            for(int i = 0; i < 7;i++){
                Card card = gameState.getDeck().draw();
                player.addCard(card);
            }
        }

        Card firstCard = gameState.getDeck().draw();
        gameState.addToDiscardPile(firstCard);
        gameState.setCurrentColor(firstCard.getColor());
    }

    private void applyEffect(Card card) {
        switch (card.getType()) {
            case SKIP:
                gameState.nextTurn();
                break;

            case REVERSE:
                gameState.reverseDirection();
                break;

            case DRAW_TWO:
                gameState.nextTurn();
                Player nextPlayer = gameState.getCurrentPlayer();
                nextPlayer.addCard(gameState.getDeck().draw());
                nextPlayer.addCard(gameState.getDeck().draw());
                gameState.nextTurn();
                break;

            case WILD_DRAW_FOUR:
                gameState.nextTurn();
                Player nextPlayer2 = gameState.getCurrentPlayer();
                nextPlayer2.addCard(gameState.getDeck().draw());
                nextPlayer2.addCard(gameState.getDeck().draw());
                nextPlayer2.addCard(gameState.getDeck().draw());
                nextPlayer2.addCard(gameState.getDeck().draw());
                gameState.nextTurn();
                break;

            default:
                break;
        }
    }

    public boolean canPlayCard(Card card) {

        Card topCard = gameState.getTopCard();

        if(card.getType() == Type.WILD || card.getType() == Type.WILD_DRAW_FOUR) {
            return true;
        }
        if(card.getColor() == gameState.getCurrentColor()) {
            return true;
        }
        if(card.getType() == topCard.getType()) {
            return true;
        }
        if(card.getType() == Type.NUMBER && topCard.getType() == Type.NUMBER && card.getValue() == topCard.getValue()) {
            return true;
        }
        return false;
    }

    public void playCard(Card card) {

        Player currentPlayer = gameState.getCurrentPlayer();

        currentPlayer.removeCard(card);
        gameState.addToDiscardPile(card);
        gameState.setCurrentColor(card.getColor());
        applyEffect(card);
        gameState.nextTurn();

    }

    public void drawCard() {
        Card card = gameState.getDeck().draw();
        gameState.getCurrentPlayer().addCard(card);
    }

    public boolean checkWin(){
        return gameState.getCurrentPlayer().hasWon();
    }
}
