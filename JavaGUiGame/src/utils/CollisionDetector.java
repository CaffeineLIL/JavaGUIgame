package utils;

import java.awt.Rectangle;
import java.util.ArrayList;


//벽 충돌 감지
public class CollisionDetector {

    private ArrayList<Rectangle> walls;

    public CollisionDetector() {
        walls = new ArrayList<>();
    }

    public void addWall(Rectangle wall) {
        walls.add(wall);
    }

    public boolean checkCollision(Rectangle playerHitbox) {
        for (Rectangle wall : walls) {
            if (playerHitbox.intersects(wall)) {
                return true;
            }
        }
        return false;
    }
}
