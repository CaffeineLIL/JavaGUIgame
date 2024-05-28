package player;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Projectile {
    private double x, y;
    private double directionX, directionY;
    private double speed;
    private int size;
    private boolean active;

    public Projectile(double x, double y, double directionX, double directionY, double speed, int size) {
        this.x = x;
        this.y = y;
        this.directionX = directionX;
        this.directionY = directionY;
        this.speed = speed;
        this.size = size;
        this.active = true;
    }

    public void update() {
        x += directionX * speed;
        y += directionY * speed;
    }

    public void draw(Graphics2D g) {
        if (active) {
            g.setColor(Color.YELLOW);
            g.fillRect((int)x, (int)y, size, size);
        }
    }

    public boolean checkCollision(Rectangle target) {
        Rectangle projectileRect = new Rectangle((int)x, (int)y, size, size);
        return projectileRect.intersects(target);
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }
}
