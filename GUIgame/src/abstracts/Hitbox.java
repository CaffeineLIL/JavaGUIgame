package abstracts;

import java.awt.Graphics;

public abstract class Hitbox {
    protected int x, y, width, height;

    public Hitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g);

    public boolean intersects(Hitbox other) {
        return this.x < other.x + other.width && this.x + this.width > other.x &&
               this.y < other.y + other.height && this.y + this.height > other.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
