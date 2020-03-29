package com.company;

import javax.swing.*;

public class SinglePlayerBoard extends Board {

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
                    while (!Cell.isSelected()) ;

                    player1Cells++;
                    int x = Cell.getSelectedX();
                    int y = Cell.getSelectedY();
                    for (int d=0; d<8; d++)
                        action(directions[d], 1, x, y, false);

                    Cell.actionDone();
                }
                else {
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
