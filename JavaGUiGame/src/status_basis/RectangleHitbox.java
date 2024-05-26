// File: status_basis/RectangleHitbox.java
package status_basis;

import abstracts.abstractHitbox;
import java.awt.Graphics;
import java.awt.Color;

public class RectangleHitbox extends abstractHitbox {
    
    public RectangleHitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);  // 히트박스의 색상
        g.drawRect(x, y, width, height);
    }
}
