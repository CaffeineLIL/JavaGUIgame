
package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import status_basis.RectangleHitbox;
import status_basis.TransparentHitbox;
import stage.Map_bg;

public class Player extends JPanel {

    private int x = 50;
    private int y = 50;
    private final int SIZE = 50;
    private final int MOVE_AMOUNT = 5;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private RectangleHitbox hitbox;
    private BufferedImage backgroundImage;
    private BufferedImage playerImage;

    public Player() {
        // 이미지 로드
        Map_bg mapBg = new Map_bg();
        backgroundImage = mapBg.getImage();

        PlayerImage playerImg = new PlayerImage();
        playerImage = playerImg.getImage();

        // 초기 히트박스 설정
        hitbox = new TransparentHitbox(x, y, SIZE, SIZE);

        // KeyBindings 설정
        setupKeyBindings();

        // Timer 설정 (이동을 부드럽게 하기 위해)
        Timer timer = new Timer(10, e -> {
            if (upPressed) y = Math.max(y - MOVE_AMOUNT, 0);
            if (downPressed) y = Math.min(y + MOVE_AMOUNT, getHeight() - SIZE);
            if (leftPressed) x = Math.max(x - MOVE_AMOUNT, 0);
            if (rightPressed) x = Math.min(x + MOVE_AMOUNT, getWidth() - SIZE);

            // 히트박스 업데이트
            hitbox.setPosition(x, y);
            repaint();
        });
        timer.start();

        setFocusable(true);
    }

    private void setupKeyBindings() {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "upPressed");
        inputMap.put(KeyStroke.getKeyStroke("released W"), "upReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "downPressed");
        inputMap.put(KeyStroke.getKeyStroke("released S"), "downReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "leftPressed");
        inputMap.put(KeyStroke.getKeyStroke("released A"), "leftReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "rightPressed");
        inputMap.put(KeyStroke.getKeyStroke("released D"), "rightReleased");

        actionMap.put("upPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = true;
            }
        });
        actionMap.put("upReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = false;
            }
        });
        actionMap.put("downPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = true;
            }
        });
        actionMap.put("downReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = false;
            }
        });
        actionMap.put("leftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = true;
            }
        });
        actionMap.put("leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = false;
            }
        });
        actionMap.put("rightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = true;
            }
        });
        actionMap.put("rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 이미지 그리기
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        // 캐릭터 이미지 그리기
        if (playerImage != null) {
            g.drawImage(playerImage, x, y, SIZE, SIZE, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, SIZE, SIZE);
        }

        // 히트박스 그리기 (투명한 히트박스는 그리지 않음)
        hitbox.draw(g);
    }
}
