package status_basis;

import java.awt.Color;
import java.awt.Graphics;

public class RectangleHitbox {

    private int x, y, width, height;

    public RectangleHitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 255, 50)); // 반투명한 파란색
        g.fillRect(x, y, width, height);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
