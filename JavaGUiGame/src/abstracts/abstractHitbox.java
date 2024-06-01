package abstracts;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class abstractHitbox {
    protected double x, y, width, height;
    public abstract Rectangle getBounds();

    public abstractHitbox(double x2, double y2, int width, int height) {
        this.x = x2;
        this.y = y2;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g);

    public boolean intersects(Rectangle other) {
        return this.x < other.x + other.width && this.x + this.width > other.x &&
               this.y < other.y + other.height && this.y + this.height > other.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Rectangle toRectangle() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }
    
    
}
