package model.entitites;

public class Bullet extends Entity {
    private boolean used = false;
    private int velocityY;

    public Bullet(int x, int y, int width, int height, int velocityY) {
        super(x, y, width, height, null);
        this.velocityY = velocityY;
    }

    public void update() {
        y += velocityY;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}