package utils;

import java.awt.Rectangle;
import java.util.ArrayList;

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
