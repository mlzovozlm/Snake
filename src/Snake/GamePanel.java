/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Bao Anh Luu
 */
public class GamePanel extends JPanel {

    Snake snake;
    Food food;
    InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = getActionMap();
    int WIDTH = 500, HEIGHT = 500;
    Thread screenThread;
    Thread gameThread;
    int speed = 150;
    Graphics g;
    int FPS = 60;

    boolean gameEnd;

    Action turnLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            snake.turnLeft();
        }

    };
    Action turnRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            snake.turnRight();
        }
    };

    void setKey() {
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "TurnLeft");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "TurnLeft");
        am.put("TurnLeft", turnLeft);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "TurnRight");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "TurnRight");
        am.put("TurnRight", turnRight);
    }

    void init() {
        snake = new Snake(WIDTH / 2 - (WIDTH / 2) % 10, HEIGHT / 2 - (HEIGHT / 2) % 10);
        food = new Food(WIDTH);
        gameEnd = false;
        setKey();
    }

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        init();
        
        gameThread = new Thread() {
            @Override
            public void run(){
                while(true){
                    if (!gameEnd) {
                        update();
                    }
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        screenThread = new Thread() {
            @Override
            public void run() {
                long T = 1000 / FPS;
                long TimeBuffer = T / 2;

                long BeginTime = System.currentTimeMillis();
                long EndTime;
                long sleepTime;

                while (true) {
                    if (!gameEnd) {
                        repaint();
                    }
                    
                    EndTime = System.currentTimeMillis();
                    sleepTime = T - (EndTime - BeginTime);
                    if (sleepTime < 0) {
                        sleepTime = TimeBuffer;
                    }

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        gameThread.start();
        screenThread.start();
    }

    void update() {
        snake.forward(Grid.size, Grid.size, WIDTH);
        if (snake.collision()) {
            gameEnd = true;
        }
        if (snake.eat(food)) {
            snake.increaseSize();
            food = new Food(WIDTH);
            if (speed > 60) {
                speed -= 10;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GREEN);
        ListIterator<Grid> iterator = snake.body.listIterator(0);
        while (iterator.hasNext()) {
            iterator.next().render(g);
        }
        g.setColor(Color.RED);
        food.position.render(g);
    }
}
