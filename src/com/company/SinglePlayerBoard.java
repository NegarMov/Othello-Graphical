package com.company;

import javax.swing.*;

/**
 * The Board class simulates a board for Othello game. It holds a list of the board
 * cells which itself holds a disc for player 1, player 2 or is empty.
 * The first player plays white and the second player plays black.
 * In this mode, one of the players is computer and the other one is a human.
 * Also this class is a parent class for MultiPlayerBoard and SinglePlayerBoard.
 * @author Negar Movaghatian
 * @since 2020-03-29
 */
public class SinglePlayerBoard extends Board {

    /**
     * Run a single-player game while the board still has an empty cell
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
                    JOptionPane.showMessageDialog(null, (player1Cells > player2Cells) ? "<<PLAYER 1 WINS!>>" : "<<COMPUTER WINS!>>", "End of the Game", JOptionPane.PLAIN_MESSAGE);
                    break;
                }
            }
            else {
                if (Cell.getTurn()==0) {
                    // Human's turn
                    while (!Cell.isSelected()) ;

                    player1Cells++;
                    int x = Cell.getSelectedX();
                    int y = Cell.getSelectedY();
                    for (int d=0; d<8; d++)
                        action(directions[d], 1, x, y, false);

                    Cell.actionDone();
                }
                else {
                    // Computer's turn
                    try {
                        Thread.sleep(750);
                    }
                    catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    int maxCellsBetween = 0, maxi = 0, maxj = 0, cellsBetween = 0;
                    for (int i=0; i<8; i++)
                        for (int j=0; j<8; j++) {
                            if (cells[i][j].getButton().isEnabled() && cells[i][j].getStat()==0) {
                                cellsBetween = 0;
                                for (int d = 0; d < 8; d++)
                                    cellsBetween += action(directions[d], -1, i, j, true);
                                if (maxCellsBetween<cellsBetween) {
                                    maxCellsBetween = cellsBetween;
                                    maxi = i;
                                    maxj = j;
                                }
                            }
                        }
                    cells[maxi][maxj].updateCell("B");
                    for (int d=0; d<8; d++)
                        action(directions[d], -1, maxi, maxj, false);
                    player2Cells++;
                    Cell.reverseTurn();
                }
                remove(scores);
                scores = new JLabel("Player 1:   " + player1Cells + "            Computer:   " + player2Cells + "          " + ((Cell.getTurn() == 0) ? "[Player 1]" : "[Computer]"));
                add(scores);
                repaint();
                revalidate();
            }
        }
        JOptionPane.showMessageDialog(null, (player1Cells>player2Cells)? "<<PLAYER 1 WINS!>>" : "<<COMPUTER WINS!>>","End of the Game" , JOptionPane.PLAIN_MESSAGE);
    }

}
