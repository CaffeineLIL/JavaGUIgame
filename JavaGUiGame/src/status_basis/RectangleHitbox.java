package status_basis;

import abstracts.abstractHitbox;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class RectangleHitbox extends abstractHitbox {

    public RectangleHitbox(double x, double y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0 , 0, 0));  // 히트박스의 색상
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
}
