package com.company;

import javax.swing.*;
import java.util.Scanner;

/**
 * The main class determines if the player wants to play in single (With computer) or multi player mode.
 * Then it creates a board according to the game mode and runs it.
 * @author Negar Movaghatian
 * @since 2020-03-28
 */
public class Main {

    public static void main(String[] args) {

        // Determine if the game is single-player or multi-player
        System.out.println("1)Single Player\n2)Multi Player");
        Scanner scn = new Scanner(System.in);
        int ans = scn.nextInt();

        if (ans==2) {
            // Run a multi-player game
            MultiPlayerBoard MPBoard = new MultiPlayerBoard();
            MPBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MPBoard.setSize(500, 520);
            MPBoard.setVisible(true);
            MPBoard.repaint();
            MPBoard.runGame();
        }
        else {
            // Run a single-player game
            SinglePlayerBoard SPBoard = new SinglePlayerBoard();
            SPBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SPBoard.setSize(500, 520);
            SPBoard.setVisible(true);
            SPBoard.repaint();
            SPBoard.runGame();
        }
    }
}
