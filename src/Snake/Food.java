/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.util.Random;

/**
 *
 * @author Bao Anh Luu
 */
public class Food {

    Grid position;

    public Food(Grid position) {
        this.position = position;
    }

    public Food(int x, int y) {
        position = new Grid(x, y);
    }

    public Food(int BOARD_SIZE) {
        Random rand = new Random();
        position = new Grid(rand.nextInt((BOARD_SIZE - Grid.size) / Grid.size) * 10,
                (rand.nextInt((BOARD_SIZE - Grid.size) / Grid.size)) * 10);
    }
}
