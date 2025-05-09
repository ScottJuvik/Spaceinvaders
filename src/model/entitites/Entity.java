package model.entitites;

import java.awt.*;

public abstract class Entity {
    protected int x, y, width, height;
    protected Image image;

    public Entity(int x, int y, int width, int intHeight, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = intHeight;
        this.image = image;
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
