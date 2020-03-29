package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The class Cell represents a cell of the board. Each cell is made of a button, the cell's
 * coordinate ((x, y)), the status of the cell (is empty, a black disc is on it or a white disc is on it)
 * and manages which player's turn it is at the moment.
 * @author Negar Movaghatian
 * @since 2020-03-28
 */
public class Cell{
    // The button which shows the cell and has features, like its
    // icon changes when clicked on
    private JButton button;
    // The turn of the players [0: first player(white)  1: second player(black)]
    static int turn;
    // The x of the cell
    private int x;
    // The y of the cell
    private int y;
    // The status of the cell [0: empty    1: white    -1: black]
    private int stat;
    // Shows if a cell is selected at this moment [At the time a cell is selected changes to true and after that to false]
    static boolean isSelected = false;
    // The status of the selected cell
    static int selectedStat = 0;
    // The x of the selected cell
    static int selectedX = 0;
    // The y of the selected cell
    static int selectedY = 0;

    /**
     * Create a new cell with the given coordinate and add a button with no icon
     * to it.
     * @param x the x of the new cell.
     * @param y the y of the new cell.
     */
    public Cell(int x, int y) {
        turn = 1;
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

    /**
     * Update the cell and change its status to the given color.
     * @param color The color of the disk on the cell.
     */
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

    /**
     * Get the button of the cell.
     * @return button field.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Get the status of the cell.
     * @return stat field.
     */
    public int getStat() {
        return stat;
    }

    /**
     * Change the status (color) of the cell.
     * @param stat the new status of the cell.
     */
    public void setStat(int stat) {
        this.stat = stat;
        updateCell((stat==1)? "W" : "B");
    }

    /**
     * Get the status of the selected cell.
     * @return selectedStat field.
     */
    public static int getSelectedStat() {
        return selectedStat;
    }

    /**
     * Show if any cell is selected or not.
     * @return isSelected field.
     */
    public static boolean isSelected() {
        return isSelected;
    }

    /**
     * Get the x of the selected cell.
     * @return selectedX field.
     */
    public static int getSelectedX() {
        return selectedX;
    }

    /**
     * Get the y of the selected cell.
     * @return selectedY field.
     */
    public static int getSelectedY() {
        return selectedY;
    }

    /**
     * Get the turn of the players at the moment.
     * @return turn field.
     */
    public static int getTurn() {
        return turn;
    }

    /**
     * Give the current turn to the other player.
     */
    static void reverseTurn() {
        turn = 1-turn;
    }

    /**
     * Relief the selected button and make it ready for the next
     * action.
     */
    static void actionDone() {
        isSelected = false;
    }

    /**
     * The class ButtonHandler manages the ActionEvent created by
     * the cell's button.
     */
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
