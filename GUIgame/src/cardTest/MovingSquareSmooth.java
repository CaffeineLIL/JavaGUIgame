package cardTest;

import javax.swing.*;
import abstracts.Hitbox;
import java.awt.*;
import java.awt.event.ActionEvent;
import status_basis.RectangleHitbox.*;

public class MovingSquareSmooth extends JFrame {

    private int x = 50;
    private int y = 50;
    private final int SIZE = 50;
    private final int MOVE_AMOUNT = 5;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private Hitbox hitbox;

    public MovingSquareSmooth() {
        setTitle("Moving Square Smooth with Hitbox");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 초기 히트박스 설정
        hitbox = new status_basis.RectangleHitbox(x, y, SIZE, SIZE);

        // JPanel 설정
        DrawPanel drawPanel = new DrawPanel();
        add(drawPanel);

        // KeyBindings 설정
        setupKeyBindings(drawPanel);

        // Timer 설정 (이동을 부드럽게 하기 위해)
        Timer timer = new Timer(15, e -> {
            if (upPressed) y = Math.max(y - MOVE_AMOUNT, 0);
            if (downPressed) y = Math.min(y + MOVE_AMOUNT, getHeight() - SIZE);
            if (leftPressed) x = Math.max(x - MOVE_AMOUNT, 0);
            if (rightPressed) x = Math.min(x + MOVE_AMOUNT, getWidth() - SIZE);

            // 히트박스 업데이트
            hitbox.setPosition(x, y);
            drawPanel.repaint();
        });
        timer.start();

        setFocusable(true);
    }

    private void setupKeyBindings(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

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

    private class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillRect(x, y, SIZE, SIZE);

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovingSquareSmooth frame = new MovingSquareSmooth();
            frame.setVisible(true);
        });
    }
}
