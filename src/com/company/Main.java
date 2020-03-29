package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(500, 520);
        board.setVisible(true);
        board.repaint();
        board.runGame();
    }
}
