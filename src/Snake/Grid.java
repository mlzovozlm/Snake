/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Bao Anh Luu
 */
public class Grid {

    int x, y; 
    static int size = 10;

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }
    
    void render(Graphics g) {
        g.fillRect(x, y, size, size);
    }
}
