package com.company;

import javax.swing.*;

public class MultiPlayerBoard extends Board {

    public void runGame() {
        while (player1Cells + player2Cells<64) {
            int numberOfCells = disableCells();
            if (numberOfCells==0) {
                JOptionPane.showMessageDialog(null, "Pass!", "No Available Move",  JOptionPane.INFORMATION_MESSAGE);
                Cell.reverseTurn();
                numberOfCells = disableCells();
                if (numberOfCells==0)
                    JOptionPane.showMessageDialog(null, (player1Cells>player2Cells)? "<<PLAYER 1 WINS!>>" : "<<PLAYER 2 WINS!>>","End of the Game" , JOptionPane.PLAIN_MESSAGE);
            }
            else {
                while (!Cell.isSelected()) ;

                if (Cell.turn == 1) {
                    player1Cells++;
                }
                else
                    player2Cells++;

                int color = Cell.getSelectedStat();
                int x = Cell.getSelectedX();
                int y = Cell.getSelectedY();
                for (int d=0; d<8; d++)
                    action(directions[d], color, x, y, false);
                Cell.actionDone();

                remove(scores);
                scores = new JLabel("Player 1:   " + player1Cells + "            Player 2:   " + player2Cells  + "          " + ((Cell.getTurn()==0)? "[Player 1]" : "[Player 2]"));
                add(scores);
                repaint();
                revalidate();
            }
        }
        JOptionPane.showMessageDialog(null, (player1Cells>player2Cells)? "<<PLAYER 1 WINS!>>" : "<<PLAYER 2 WINS!>>","End of the Game" , JOptionPane.PLAIN_MESSAGE);
    }
}
