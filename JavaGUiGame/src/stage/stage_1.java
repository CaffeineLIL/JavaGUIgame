package stage;

import javax.swing.*;
import player.Player;
import Enemy.WeakEnemy;
import utils.CollisionDetector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class stage_1 extends JPanel {
    private BufferedImage backgroundImage;
    private CollisionDetector collisionDetector;  // 충돌 감지기 추가
    private Player player;
    private int playerX, playerY;
    private double enemy1_hp;
    private boolean EnemyAlive = true;
    Timer timer;

    ArrayList<WeakEnemy> Enemy = new ArrayList<>();

    public stage_1() {
        // 이미지 로드
        Map_bg mapBg = new Map_bg();
        backgroundImage = mapBg.getImage();
        
        // 충돌 감지기 초기화 및 벽 추가
        collisionDetector = new CollisionDetector();
        initializeWalls();

        player = new Player();
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        
        // 플레이어에 충돌 감지기 설정
        player.setCollisionDetector(collisionDetector);
        
        add(player);

        
        WeakEnemy enemy_1 = new WeakEnemy(playerX, playerY);
        enemy_1.setCollisionDetector(collisionDetector); // 적에게도 충돌 감지기 설정
        enemy1_hp = enemy_1.getenHp();
        add(enemy_1);

      
        // 타이머 설정 (적의 이동을 주기적으로 업데이트하기 위해)
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.player_isalive() && EnemyAlive == true) {
                    enemy_1.setPlayerPosition(player.getPlayerX(), player.getPlayerY());
                    enemy_1.move();
                    enemy_1.repaint();
                } else {
                    // 플레이어가 죽으면 타이머 멈추기
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void initializeWalls() {
        // 벽 좌표 및 크기 추가
        collisionDetector.addWall(new Rectangle(10, 0, 1000, 50)); //위쪽 벽
        collisionDetector.addWall(new Rectangle(80, 520, 1000, 50)); //아래쪽 벽
        collisionDetector.addWall(new Rectangle(-20, 20, 50, 600)); // 왼쪽 벽 
        collisionDetector.addWall(new Rectangle(1005, 20, 50, 600)); //오른쪽 벽
    }

    // 투명한 벽을 그리는 메서드 추가
    private void drawWalls(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 0)); // 투명한 색상으로 나중에 바꾸기
        g2d.fillRect(10, 0, 1000, 50);
        g2d.fillRect(80, 520, 1000, 50);
        g2d.fillRect(-20, 20, 50, 600);
        g2d.fillRect(1005, 20, 50, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // 배경 이미지 그리기
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // 투명한 벽 그리기
        drawWalls(g2d);

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        // JPanel의 크기를 JFrame과 동일하게 반환합니다.
        return new Dimension(1100, 700);
    }
}
