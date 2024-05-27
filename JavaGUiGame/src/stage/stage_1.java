package stage;
import javax.swing.*;

import player.Player;
import Enemy.WeakEnemy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class stage_1 extends JPanel {
    private BufferedImage backgroundImage;
    private double playerX, playerY; // 변경: int -> double
    private double enemy1_hp;
    private boolean EnemyAlive = true;
    Timer timer;

    ArrayList<WeakEnemy> Enemy = new ArrayList<>();

    public stage_1() {
        // 이미지 로드
        Map_bg mapBg = new Map_bg();
        backgroundImage = mapBg.getImage();

        Player player = new Player();
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();

        add(player);

        WeakEnemy enemy_1 = new WeakEnemy(playerX, playerY);
        enemy1_hp = enemy_1.getenHp();
        add(enemy_1);
        
        // 타이머 설정 (적의 이동을 주기적으로 업데이트하기 위해)
         timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.player_isalive() && EnemyAlive == true) {
                    enemy_1.setPlayerPosition(player.getPlayerX(), player.getPlayerY());
                    enemy_1.move();
                    enemy_1.repaint();
                }
             
                else {
                    // 플레이어가 죽으면 타이머 멈추기
                	timer.stop();
                }
            }
        });
        timer.start();
    }
    
    // 투명한 벽을 그리는 메서드 추가
    private void drawWalls(Graphics2D g2d) {
    	//위 , 아래
        g2d.setColor(new Color(23, 79, 0, 200)); // 투명한 색상 : 0, 0, 0, 0
        g2d.fillRect(80, 20, 1000, 50); // 예시: (100, 100) 위치에 가로 1000, 세로 50 크기의 투명한 사각형 그리기
        g2d.fillRect(80, 520, 1000, 50);
        
        //왼쪽, 오른쪽
        g2d.fillRect(40, 20, 50, 600); 
        g2d.fillRect(1005, 20, 50, 600); 
        // 필요한 만큼 더 많은 사각형을 추가하여 벽을 만듭니다.
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
