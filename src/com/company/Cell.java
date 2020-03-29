package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cell{
    private JButton button;
    static int turn;
    private int x;
    private int y;
    private int stat; //0: empty    1: white    -1: black
    static boolean isSelected = false;
    static int selectedStat = 0;
    static int selectedX = 0;
    static int selectedY = 0;

    public Cell(int x, int y) {
        turn = 0;
        this.x = x;
        this.y = y;
        stat = 0;

        button = new JButton();
        button.setBackground(Color.WHITE);
        JPanel pnl = new JPanel();
        button.setPreferredSize(new Dimension(50, 50));
        pnl.setPreferredSize(new Dimension(50, 50));
        pnl.setLayout(new BorderLayout());

        ButtonHandler handler = new ButtonHandler();
        button.addActionListener(handler);
    }

    public void updateCell(String color) {
        Icon black = new ImageIcon(getClass().getResource("B.png"));
        Icon white = new ImageIcon(getClass().getResource("W.png"));
        if (color.equals("B")) {
            button.setIcon(black);
            stat = -1;
        }
        else {
            button.setIcon(white);
            stat = 1;
        }
    }

    public JButton getButton() {
        return button;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
        updateCell((stat==1)? "W" : "B");
    }

    public static int getSelectedStat() {
        return selectedStat;
    }

    public static boolean isSelected() {
        return isSelected;
    }

    public static int getSelectedX() {
        return selectedX;
    }

    public static int getSelectedY() {
        return selectedY;
    }

    public static int getTurn() {
        return turn;
    }

    static void reverseTurn() {
        turn = 1-turn;
    }

    static void actionDone() {
        isSelected = false;
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (stat==0) {
                updateCell((turn==0)? "W" : "B");
                stat = (turn==0)? 1 : -1;
                turn = 1 - turn;
                selectedStat = stat;
                selectedX = x;
                selectedY = y;
                isSelected = true;
            }
            else
                JOptionPane.showMessageDialog(null, "Please choose another cell.","Occupied Cell Selected" ,JOptionPane.ERROR_MESSAGE);
        }
    }
}
