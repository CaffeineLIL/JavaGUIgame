package utils;

import java.awt.Rectangle;
import java.util.ArrayList;

import abstracts.abstractHitbox;
import player.Projectile;


//벽 충돌 감지
public class CollisionDetector {

    private ArrayList<Rectangle> walls;

    public CollisionDetector() {
        walls = new ArrayList<>();
    }

    public void addWall(Rectangle wall) {
        walls.add(wall);
    }

    public boolean checkCollision(Rectangle Hitbox) {
        for (Rectangle wall : walls) {
            if (Hitbox.intersects(wall)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkProjectileCollision(Projectile projectile, abstractHitbox targetHitbox) {
        return projectile.getHitbox().intersects(targetHitbox.toRectangle());
    }
}
