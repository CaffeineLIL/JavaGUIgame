package status_basis;

import abstracts.Hitbox;
import java.awt.Color;
import java.awt.Graphics;

public class RectangleHitbox extends Hitbox {

    public RectangleHitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 255, 50)); // 반투명한 파란색
        g.fillRect(x, y, width, height);
    }
}
