package status_basis;

import java.awt.Graphics;

public class TransparentHitbox extends RectangleHitbox {

    public TransparentHitbox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        // 투명한 히트박스: 아무 것도 그리지 않음
    }
}
