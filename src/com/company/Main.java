package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("1)Single Player\n2)Multi Player");
        Scanner scn = new Scanner(System.in);
        int ans = scn.nextInt();

        if (ans==2) {
            MultiPlayerBoard MPBoard = new MultiPlayerBoard();
            MPBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MPBoard.setSize(500, 520);
            MPBoard.setVisible(true);
            MPBoard.repaint();
            MPBoard.runGame();
        }
        else {
            SinglePlayerBoard SPBoard = new SinglePlayerBoard();
            SPBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SPBoard.setSize(500, 520);
            SPBoard.setVisible(true);
            SPBoard.repaint();
            SPBoard.runGame();
        }
    }
}
