package com.company;

import javax.swing.*;

/**
 * The main class determines if the player wants to play in single (With computer) or multi player mode.
 * Then it creates a board according to the game mode and runs it.
 * @author Negar Movaghatian
 * @since 2020-03-28
 */
public class Main {

    public static void main(String[] args) {

        // Determine if the game is single-player or multi-player
        String[] ops = {"Single Player", "Multi Player"};
        int ans = JOptionPane.showOptionDialog(null, "Please choose the game mode.",
                "Single or Multi Player", 0, 3, null, ops, ops[0]);

        if (ans==1) {
            // Run a multi-player game
            MultiPlayerBoard MPBoard = new MultiPlayerBoard();
            MPBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MPBoard.setSize(500, 520);
            MPBoard.setResizable(false);
            MPBoard.setVisible(true);
            MPBoard.repaint();
            MPBoard.runGame();
        }
        else {
            // Run a single-player game
            SinglePlayerBoard SPBoard = new SinglePlayerBoard();
            SPBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SPBoard.setSize(500, 520);
            SPBoard.setResizable(false);
            SPBoard.setVisible(true);
            SPBoard.repaint();
            SPBoard.runGame();
        }
    }
}
