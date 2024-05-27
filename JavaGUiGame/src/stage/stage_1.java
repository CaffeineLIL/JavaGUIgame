package stage;

import javax.swing.*;
import player.Player;
import Enemy.WeakEnemy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import stage.Map_bg;

public class stage_1 extends JPanel {
    private BufferedImage backgroundImage;
    private int playerX, playerY;
    private double enemy1_hp;
    private boolean EnemyAlive = true;

    public stage_1() {
        // 이미지 로드
        Map_bg mapBg = new Map_bg();
        backgroundImage = mapBg.getImage();
        
        Player player = new Player();
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        
        add(player);
        
        WeakEnemy enemy_1 = new WeakEnemy(playerX, playerY);
        enemy1_hp = enemy_1.getEnHp();
        add(enemy_1);
        
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enemy_1.getEnHp() > 0) {
                    enemy_1.move();
                    enemy_1.repaint();
                } else {
                    // 적이 죽었을 때 타이머 멈추기
                    ((Timer)e.getSource()).stop();
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
