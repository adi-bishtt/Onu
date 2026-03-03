package com.onu.logic;

import com.onu.model.*;

public class RuleValidator {
    private GameState gameState;

    public RuleValidator(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isValidPlay(Card card) {
        Card topCard = gameState.getTopCard();

        if(card.getType() == Type.WILD || card.getType() == Type.WILD_DRAW_FOUR) {
            return true;
        }
        if(card.getColor() == gameState.getCurrentColor()) {
            return true;
        }
        if(card.getType() != Type.NUMBER && card.getType() == topCard.getType()) {
            return true;
        }
        if(card.getType() == Type.NUMBER && topCard.getType() == Type.NUMBER && card.getValue() == topCard.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isONUViolation(Player player) {
        return player.handSize() == 1 && !player.hasDeclaredONU();
    }

    public boolean isValidDrawFourPlay(Card card) {
        Player currentPlayer = gameState.getCurrentPlayer();

        for (Card handCard : currentPlayer.getHand()) {
            if (handCard.getType() != Type.WILD_DRAW_FOUR
                    && isValidPlay(handCard)) {
                return false;
            }
        }
        return true;
    }
}
