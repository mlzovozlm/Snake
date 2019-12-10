/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Action;

/**
 *
 * @author Bao Anh Luu
 */
public class Snake {

    LinkedList<Grid> body;
    int direction;
    boolean die = false;
    final int UP = 0;
    final int RIGHT = 1;
    final int DOWN = 2;
    final int LEFT = 3;

    public Snake(int x, int y) {
        body = new LinkedList<>();
        body.add(new Grid(x, y));
        direction = RIGHT;
        die = false;
    }

    public Snake(LinkedList<Grid> body) {
        this.body = body;
    }

    boolean eat(Food f) {
        return body.getFirst().x == f.position.x && body.getFirst().y == f.position.y;
    }

    void turnLeft() {
        if (this.direction > UP) {
            this.direction--;
        } else {
            this.direction = 3;
        }
    }

    void turnRight() {
        if (this.direction < LEFT) {
            this.direction++;
        } else {
            this.direction = 0;
        }
    }

    void increaseSize() {
        Grid head = body.getFirst();
        switch (this.direction) {
            case RIGHT:
                body.addFirst(new Grid(head.x + Grid.size, head.y));
                break;
            case DOWN:
                body.addFirst(new Grid(head.x, head.y + Grid.size));
                break;
            case LEFT:
                body.addFirst(new Grid(head.x - Grid.size, head.y));
                break;
            case UP:
                body.addFirst(new Grid(head.x, head.y - Grid.size));
                break;
        }
    }

    void forward(int dx, int dy, int BOARD_SIZE) {
        //int xx = head.x, yy = head.y;
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).x = body.get(i - 1).x;
            body.get(i).y = body.get(i - 1).y;
        }
        Grid head = body.getFirst();
        //int xx = head.x, yy = head.y;
        switch (this.direction) {
            case RIGHT:
                head.x += dx;
                break;
            case DOWN:
                head.y += dy;
                break;
            case LEFT:
                head.x -= dx;
                break;
            case UP:
                head.y -= dy;
                break;
        }
        if (head.x >= BOARD_SIZE) {
            head.x = 0;
        }
        if (head.x < 0) {
            head.x = BOARD_SIZE - Grid.size;
        }
        if (head.y >= BOARD_SIZE) {
            head.y = 0;
        }
        if (head.y < 0) {
            head.y = BOARD_SIZE - Grid.size;
        }
    }

    boolean collision() {
        Grid head = body.getFirst();
        ListIterator<Grid> iterator = body.listIterator(1);
        while (iterator.hasNext()) {
            Grid xGrid = iterator.next();
            if (xGrid.x == head.x && xGrid.y == head.y) {
                return true;
            }
        }
        return false;
    }
}
