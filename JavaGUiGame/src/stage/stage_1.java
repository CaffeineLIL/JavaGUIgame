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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 이미지 그리기
        if (backgroundImage != null) {
            // 이미지를 JPanel에 꽉 차도록 그립니다.
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // JPanel의 크기를 JFrame과 동일하게 반환합니다.
        return new Dimension(1100, 700);
    }
}
