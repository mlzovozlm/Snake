/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Bao Anh Luu
 */
public class GUI {

    JFrame f;
    GamePanel board;

    public GUI() {
        initGUI();
    }

    void initGUI() {
        f = new JFrame("DemoSnake");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new GamePanel();
        //f.setContentPane(board);
        f.setLayout(new GridBagLayout());
        f.add(board, new GridBagConstraints());
        f.setMinimumSize(new Dimension(board.WIDTH, board.HEIGHT));
        //f.add(board);
        //f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.pack();
    }

    public static void main(String[] args) {
        new GUI();

    }
}
