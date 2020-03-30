package com.company;

import javax.swing.*;

/**
 * The MultiPlayerBoard class simulates an Othello board for two players. It holds a list of the board
 * cells which itself holds a disc for player 1, player 2 or is empty.
 * The first player plays white and the second player plays black.
 * In this mode, both of the players are human.
 * Also this class is a child class of Board.
 * @author Negar Movaghatian
 * @since 2020-03-28
 */
public class MultiPlayerBoard extends Board {

    /**
     * Run a multi-player game while the board still has an empty cell
     * capable of placing a new disc on. Also manage the number of discs of
     * each player on board (which represents the player's score).
     * At the end show the name of the winner, the player whose score is greater.
     */
    public void runGame() {
        while (player1Cells + player2Cells<64) {
            int numberOfCells = disableCells();
            if (numberOfCells==0) {
                JOptionPane.showMessageDialog(null, "Pass!", "No Available Move",  JOptionPane.INFORMATION_MESSAGE);
                Cell.reverseTurn();
                numberOfCells = disableCells();
                if (numberOfCells==0) {
                    JOptionPane.showMessageDialog(null, (player1Cells > player2Cells) ? "<<PLAYER 1 WINS!>>" : "<<PLAYER 2 WINS!>>", "End of the Game", JOptionPane.PLAIN_MESSAGE);
                    break;
                }
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
