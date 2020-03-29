package com.company;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{

    private Cell[][] cells;
    private int player1Cells;
    private int player2Cells;
    private JLabel scores;

    public Board() {

        cells = new Cell[8][8];
        player1Cells = 2;
        player2Cells = 2;

        setLayout(new FlowLayout());
        for (int i=0; i<8; i++)
            for (int j=0; j<8; j++) {
                Cell c = new Cell(i, j);
                add(c.getButton());
                cells[i][j] = c;
            }
        cells[3][3].setStat(1);
        cells[4][4].setStat(1);
        cells[3][4].setStat(-1);
        cells[4][3].setStat(-1);
        scores = new JLabel("Player 1:   " + player1Cells + "            Player 2:   " + player2Cells + "       [Player 1]");
        add(scores);
    }

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
                action("E", color, x, y, false);
                action("W", color, x, y, false);
                action("S", color, x, y, false);
                action("N", color, x, y, false);
                action("NE", color, x, y, false);
                action("SE", color, x, y, false);
                action("NW", color, x, y, false);
                action("SW", color, x, y, false);

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

    private int disableCells() {
        int legalCells = 64;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                cells[i][j].getButton().setEnabled(true);
                int color = (Cell.getTurn()==0)? 1 : -1;
                boolean able = action("E", color, i, j, true)
                        || action("W", color, i, j, true)
                        || action("S", color, i, j, true)
                        || action("N", color, i, j, true)
                        || action("NE", color, i, j, true)
                        || action("SE", color, i, j, true)
                        || action("NW", color, i, j, true)
                        || action("SW", color, i, j, true)
                        || cells[i][j].getStat()!=0;
                if (!able) {
                    cells[i][j].getButton().setEnabled(false);
                        legalCells--;
                }
                if (cells[i][j].getStat()!=0)
                    legalCells--;
            }
        }
        return legalCells;
    }

    private boolean action(String dir, int color, int x, int y, boolean check) {
        int dx = 0, dy = 0, tmpx = x, tmpy = y;
        if (dir.contains("E")) dx = 1;
        if (dir.contains("W")) dx = -1;
        if (dir.contains("N")) dy = -1;
        if (dir.contains("S")) dy = 1;

        int cellsBetween = 0;
        x += dx;
        y += dy;
        while (x>=0 && y>=0 && x<8 && y<8 && cells[x][y].getStat()==-1*color) {
            x += dx;
            y += dy;
            cellsBetween++;
        }
        if ((x>=0 && y>=0 && x<8 && y<8) && cells[x][y].getStat()==color) {
            if (check)
                return (cellsBetween>0);
            if (color==1) {
                player1Cells += cellsBetween;
                player2Cells -= cellsBetween;
            }
            else {
                player2Cells += cellsBetween;
                player1Cells -= cellsBetween;
            }
            tmpx += dx;
            tmpy += dy;
            while (tmpx>=0 && tmpy>=0 && tmpx<8 && tmpy<8 && cells[tmpx][tmpy].getStat()!=color) {
                cells[tmpx][tmpy].updateCell((color==1)? "W" : "B");

                tmpx += dx;
                tmpy += dy;
            }
        }
        return false;
    }
}