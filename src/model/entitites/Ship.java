
package model.entitites;

import java.awt.*;

public class Ship extends Entity {
    private int velocityX;

    public Ship(int x, int y, int width, int height, Image image, int velocityX) {
        super(x, y, width, height, image);
        this.velocityX = velocityX;
    }

    public void moveLeft(int boundary) {
        if (x - velocityX >= 0)
            x -= velocityX;
    }

    public void moveRight(int boundary) {
        if (x + width + velocityX <= boundary)
            x += velocityX;
    }

    public int getVelocityX() {
        return velocityX;
    }
}
