package player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import abstracts.PlayerPositionProvider;
import abstracts.abstractHitbox;
import status_basis.RectangleHitbox;
import abstracts.abstractPlayer;

public class Player extends abstractPlayer implements PlayerPositionProvider {

    private double hp = getHP();
    private int x = 700;
    private int y = 100;
    private final int SIZE = 105; // 여기서 원하는 크기로 변경
    private final double MOVE_AMOUNT = 6;
    private boolean alive = true;
    
    int projectileSize = 10; // Projectile 크기 설정
    double speed = 10;

    // w, s, a, d 동작 인식 변수 초기화
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    // 화살표 키 상태 변수 추가
    private boolean upkeyPressed = false;
    private boolean downkeyPressed = false;
    private boolean leftkeyPressed = false;
    private boolean rightkeyPressed = false;

    private abstractHitbox hitbox;
    private BufferedImage playerImage;
    private PlayerImage playerImg;

    // Projectile 리스트 추가
    private ArrayList<Projectile> projectiles;

    // 인터페이스 구현을 위한 오버라이드. 현재 x값과 y값 반환 메서드
    @Override
    public int getPlayerX() {
        return x;
    }

    @Override
    public int getPlayerY() {
        return y;
    }

    public boolean player_isalive() {
        if (hp < 0)
            alive = false;
        return alive;
    }

    public Player() {
        setOpaque(false);
        playerImg = new PlayerImage();
        playerImage = playerImg.getImage();
        projectiles = new ArrayList<>();

        // 초기 히트박스 설정
        hitbox = new RectangleHitbox(x, y, SIZE, SIZE);

        // KeyBindings 설정
        setupKeyBindings();

        // Timer 설정 (이동을 부드럽게 하기 위해)
        Timer timer = new Timer(10, e -> {
            boolean moved = false;
            if (upPressed) {
                y = (int) Math.max(y - MOVE_AMOUNT, 0);
                playerImg.moveDown();  // 위로 이동 시 이미지 변경
                moved = true;
            }
            if (downPressed) {
                y = (int) Math.min(y + MOVE_AMOUNT, getHeight() - SIZE);
                playerImg.moveDown();  // 아래로 이동 시 이미지 변경
                moved = true;
            }
            if (leftPressed) {
                x = (int) Math.max(x - MOVE_AMOUNT, 0);
                playerImg.moveDown();  // 왼쪽으로 이동 시 이미지 변경
                moved = true;
            }
            if (rightPressed) {
                x = (int) Math.min(x + MOVE_AMOUNT, getWidth() - SIZE);
                playerImg.moveDown();  // 오른쪽으로 이동 시 이미지 변경
                moved = true;
            }

            // Projectile 업데이트
            for (Projectile projectile : projectiles) {
                projectile.update();
            }

            // 히트박스 업데이트
            hitbox.setPosition(x, y);
            
           
            // 이동이 없을 때 이미지 초기화
            if (!moved) {
                playerImg.moveInit();
            }
            playerImage = playerImg.getImage(); // Update playerImage after moveDown or moveInit

            repaint();
        });
        timer.start();


        setFocusable(true);
    }

    private void setupKeyBindings() {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        // 기존 WASD 키 바인딩
        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "upPressed");
        inputMap.put(KeyStroke.getKeyStroke("released W"), "upReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed S"), "downPressed");
        inputMap.put(KeyStroke.getKeyStroke("released S"), "downReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "leftPressed");
        inputMap.put(KeyStroke.getKeyStroke("released A"), "leftReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "rightPressed");
        inputMap.put(KeyStroke.getKeyStroke("released D"), "rightReleased");

        // 화살표 키 바인딩
        inputMap.put(KeyStroke.getKeyStroke("pressed UP"), "upkeyPressed");
        inputMap.put(KeyStroke.getKeyStroke("released UP"), "upkeyReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed DOWN"), "downkeyPressed");
        inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "downkeyReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed LEFT"), "leftkeyPressed");
        inputMap.put(KeyStroke.getKeyStroke("released LEFT"), "leftkeyReleased");
        inputMap.put(KeyStroke.getKeyStroke("pressed RIGHT"), "rightkeyPressed");
        inputMap.put(KeyStroke.getKeyStroke("released RIGHT"), "rightkeyReleased");

        // 이동 액션
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

        // 화살표 키 액션 (Projectile 발사)
        actionMap.put("upkeyPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upkeyPressed = true;
                shootProjectile(0, -1); // 위쪽으로 발사
            }
        });
        actionMap.put("upkeyReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upkeyPressed = false;
            }
        });
        actionMap.put("downkeyPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downkeyPressed = true;
                shootProjectile(0, 1); // 아래쪽으로 발사
            }
        });
        actionMap.put("downkeyReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downkeyPressed = false;
            }
        });
        actionMap.put("leftkeyPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftkeyPressed = true;
                shootProjectile(-1, 0); // 왼쪽으로 발사
            }
        });
        actionMap.put("leftkeyReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftkeyPressed = false;
            }
        });
        actionMap.put("rightkeyPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightkeyPressed = true;
                shootProjectile(1, 0); // 오른쪽으로 발사
            }
        });
        actionMap.put("rightkeyReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightkeyPressed = false;
            }
        });
    }

    private void shootProjectile(double directionX, double directionY) {
        projectiles.add(new Projectile(x + SIZE / 2, y + SIZE / 2, directionX, directionY, speed, projectileSize));
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

     // 캐릭터 이미지 그리기
        if (playerImage != null) {
            g.drawImage(playerImage, (int)x, (int)y, (int)SIZE, (int)SIZE, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect((int)x, (int)y, (int)SIZE, (int)SIZE);
        }

        // Projectile 그리기
        Graphics2D g2d = (Graphics2D) g;
        for (Projectile projectile : projectiles) {
            if (projectile.isActive()) {
                projectile.draw(g2d);
            }
        }

        // 히트박스 그리기
        //hitbox.draw(g);
    }
}
