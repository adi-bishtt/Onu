package com.onu.ui;

import com.onu.model.*;
import com.onu.model.GameState;
import com.onu.model.Player;


import javax.swing.*;
import java.awt.*;

import static com.onu.model.Type.NUMBER;

public class GameWindow extends JFrame {

    public GameWindow(GameState gameState) {
        setTitle("ONU");
        setSize(1280, 720);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        renderPlayerHand(gameState.getCurrentPlayer());
    }

    private void renderPlayerHand(Player player) {
       JPanel handPanel =  new JPanel(new FlowLayout());
       for (Card card : player.getHand()) {
           String cardName = card.getColor() + " " + card.getType();
           if (card.getType() == NUMBER){
               cardName = card.getColor() + " " + card.getType() + " " + card.getValue();
           }
           JButton cardButton = new JButton(cardName);
           handPanel.add(cardButton);
       }
        add(handPanel, BorderLayout.SOUTH);
    }


}
