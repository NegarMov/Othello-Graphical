package com.company;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{

    protected Cell[][] cells;
    protected int player1Cells;
    protected int player2Cells;
    protected JLabel scores;
    protected String[] directions = {"E", "W", "S", "N", "SE", "SW", "NE", "NW"};

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

    protected int disableCells() {
        int legalCells = 64;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                cells[i][j].getButton().setEnabled(true);
                int color = (Cell.getTurn()==0)? 1 : -1;
                boolean able = cells[i][j].getStat()!=0;
                for (int d=0; d<8; d++)
                    able = able || action(directions[d], color, i, j, true)>0;
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

    protected int action(String dir, int color, int x, int y, boolean check) {
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
                return cellsBetween;
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
        return 0;
    }
}