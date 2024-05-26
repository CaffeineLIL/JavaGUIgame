package Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import status_basis.RectangleHitbox;
import abstracts.abstractEnemy;
import abstracts.abstractHitbox;
import Enemy.WeakEnemyImage;

public class WeakEnemy extends abstractEnemy {
	
	private int hp = 10;
    private int x = 200;
    private int y = 200;
    private final int SIZE = 50;
    private final int MOVE_AMOUNT = 3;
   
    
    private abstractHitbox hitbox;
    private BufferedImage enemyImage;
    
    public WeakEnemy(int playerX, int playerY) {
    	setOpaque(false);
    	hitbox = new RectangleHitbox(x, y, SIZE, SIZE);
    	WeakEnemyImage enemyimg = new WeakEnemyImage();
    	enemyImage = enemyimg.getImage();
    	
    	move(playerX, playerY);
    	hitbox.setPosition(x, y);
    	repaint();
    	
    }
    
 // 적을 이동시키는 메서드
    public void move(int PlayerX, int PlayerY) {
        // 현재 위치와 목표 위치 간의 이동 벡터 계산
        double dx = PlayerX - x;
        double dy = PlayerY - y;

        // 이동 벡터의 크기 계산
        double distanceToTarget = Math.sqrt(dx * dx + dy * dy);

        // 이동 벡터의 크기가 이동 속도보다 작으면 목표 위치에 도달한 것으로 간주
        if (distanceToTarget <= MOVE_AMOUNT) {
            x = PlayerX;
            y = PlayerY;
        } else {
            // 이동 속도에 맞춰 이동 벡터를 스케일링하여 현재 위치에 추가
            double scale = MOVE_AMOUNT / distanceToTarget;
            x += dx * scale;
            y += dy * scale;
        }
    }
    
    // 적의 위치를 반환하는 메서드
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // 캐릭터 이미지 그리기
        g.setColor(Color.WHITE);
        
        g.fillRect(x, y, 200, 150); // 좌표 및 크기는 필요에 맞게 조정
        
        // 히트박스 그리기 (예외 처리 추가)
        try {
            if (hitbox != null) {
                hitbox.draw(g);
            } else {
                throw new RuntimeException("히트박스가 초기화되지 않았습니다.");
            }
        } catch (RuntimeException e) {
            System.out.println("히트박스 인식 오류: " + e.getMessage());
        }
    }
    
    
    
}
