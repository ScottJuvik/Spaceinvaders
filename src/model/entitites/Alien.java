package model.entitites;

import java.awt.*;

public class Alien extends Entity {
    private boolean alive = true;

    public Alien(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}