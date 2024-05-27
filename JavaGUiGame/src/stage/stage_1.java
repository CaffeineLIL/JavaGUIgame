package stage;

import javax.swing.*;
import player.Player;
import Enemy.WeakEnemy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class stage_1 extends JPanel {
    private BufferedImage backgroundImage;
    private Player player;
    private WeakEnemy enemy_1;
    private Timer timer;

    public stage_1() {
        // 이미지 로드
        Map_bg mapBg = new Map_bg();
        backgroundImage = mapBg.getImage();

        player = new Player();
        add(player);

        enemy_1 = new WeakEnemy(player.getPlayerX(), player.getPlayerY());
        add(enemy_1);

        // Timer 설정 (플레이어와 적의 움직임을 갱신하기 위해)
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.player_isalive()) {
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
